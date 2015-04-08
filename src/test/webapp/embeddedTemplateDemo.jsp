<%@page import="java.io.Serializable"%>
<%@page import="com.hellosign.sdk.resource.TemplateSignatureRequest"%>
<%@page import="com.hellosign.sdk.resource.TemplateDraft"%>
<%@page import="com.hellosign.sdk.HelloSignException"%>
<%@page import="com.hellosign.sdk.HelloSignClient"%>
<%@page import="com.hellosign.sdk.resource.EmbeddedRequest"%>
<%@page import="com.hellosign.sdk.resource.UnclaimedDraft"%>
<%@page import="com.hellosign.sdk.resource.SignatureRequest"%>
<%@page import="com.hellosign.sdk.resource.support.types.UnclaimedDraftType"%>
<%@page import="org.apache.commons.io.FilenameUtils"%>
<%@page import="org.apache.commons.fileupload.disk.DiskFileItemFactory"%>
<%@page import="org.apache.commons.fileupload.FileItem"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.hellosign.sdk.resource.support.Signer"%>
<%@page import="java.util.Map"%>
<%@page import="java.io.File"%>
<%@page import="java.util.List"%>
<%@page import="org.apache.commons.fileupload.servlet.ServletFileUpload"%>
<%@page import="java.util.Properties"%>

<%

    // Load authentication properties
    Properties properties = new Properties();
    properties.load(getServletContext().getResourceAsStream("/WEB-INF/web.properties"));
    String apiKey = properties.getProperty("hellosign.api.key");
    String clientId = properties.getProperty("client.id");
    String editUrl = "";
    String templateId = "";
    String errorMessage = null;

    if (ServletFileUpload.isMultipartContent(request)) {

        List<File> files = new ArrayList<File>();
        List<String> signerRoles = new ArrayList<String>();
        List<String> ccRoles = new ArrayList<String>();
        String subject = null;
        String message = null;
        boolean isOrdered = false;

        try {
            // Process the files uploaded by the user
            List<FileItem> items = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
            for (FileItem item : items) {
                if (item.isFormField()) {
                    String fieldName = item.getFieldName();
                    String value = item.getString();
                    System.out.println("item.getFieldName() = " + fieldName);
                    System.out.println("item.getString() = " + value);
                    if (value == null || value.equals("") || fieldName == null || fieldName.equals("")) {
                        continue;
                    }
                    if (fieldName.startsWith("signerRoleOrdered")) {
                        if ("on".equals(value)) {
                            isOrdered = true;
                        }
                    } else if (fieldName.startsWith("signerRole")) {
                        int i = Integer.valueOf(fieldName.substring(10, 11)) - 1;
                        signerRoles.add(i, value);
                    } else if (fieldName.startsWith("ccRole")) {
                        int i = Integer.valueOf(fieldName.substring(6, 7)) - 1;
                        ccRoles.add(i, value);
                    } else if ("subject".equals(fieldName)) {
                        subject = value;
                    } else if ("message".equals(fieldName)) {
                        message = value;
                    }
                } else {
                    String filename = FilenameUtils.getName(item.getName());
                    if (filename == null || filename.equals("")) {
                        continue;
                    }
                    String prefix = filename.substring(0, filename.indexOf("."));
                    String suffix = filename.substring(filename.indexOf("."), filename.length());
                    while (prefix.length() < 3) {
                        prefix += '_';
                    }
                    File outfile = File.createTempFile(prefix, suffix);
                    item.write(outfile);
                    System.out.println("Saved upload to: " + outfile.getAbsolutePath());
                    files.add(outfile);
                }
            }
        } catch (Exception e) {
            errorMessage = e.getMessage();
            e.printStackTrace();
        }
        if (files.size() > 0) {
            try {
                // Create a template draft
                TemplateDraft draft = new TemplateDraft();
                draft.setTestMode(true);
                for (File file : files) {
                    draft.addFile(file);
                }
                draft.setOrderMatters(isOrdered);
                for (int i = 0; i < signerRoles.size(); i++) {
                    String signerRole = signerRoles.get(i);
                    draft.addSignerRole(signerRole, i + 1);
                }
                for (String cc : ccRoles) {
                    draft.addCCRole(cc);
                }
                if (!"".equals(subject)) {
                    draft.setTitle(subject);
                }
                if (!"".equals(message)) {
                    draft.setMessage(message);
                }
                EmbeddedRequest req = new EmbeddedRequest(clientId, draft);
                Map<String, Serializable> postFields = req.getPostFields();
                for (String key : postFields.keySet()) {
                    Serializable value = postFields.get(key);
                    System.out.println(key + " = " + value.toString());
                }
                HelloSignClient client = new HelloSignClient(apiKey);
                TemplateDraft embeddedDraft = client.createEmbeddedTemplateDraft(req);
                editUrl = embeddedDraft.getEditUrl();
            } catch (HelloSignException ex) {
                errorMessage = ex.getMessage();
                ex.printStackTrace();
            }
        }
    }
%>
<jsp:include page="/common-jsp/header.jsp">
    <jsp:param name="title" value="Embedded Template Demo"/>
    <jsp:param name="description" value="Embedded templates allow your users to create templates in your HelloSign account for use when sending signature requests on their behalf." />
    <jsp:param name="errorMessage" value="${errorMessage}"/>
</jsp:include>

      <h3>Upload template documents</h3>
      <div id="fileContainer">
          <div id="files" >
              <div class="field-container">
                  <input id="file_1" type="file" name="file_1" />
              </div>
          </div>
          <button id="addFile" class="btn btn-sm btn-default">Add File</button>
      </div>
      <button id="toggleOptionalFields" class="btn btn-sm btn-default">Toggle optional parameters</button>
      <br />
      <div id="optionalFields" style="display:none;">
          <br />
          <h3>What roles need to sign?</h3>
          <div id="signerRoleContainer">
              <div id="signerRoles">
                  <input type="text" name="signerRole1" placeholder="Signer role (optional)" />
              </div>
              <br />
              <button id="addSignerRole" class="btn btn-sm btn-default">Add Signer Role</button>
          </div>
          <br />
          <div id="signerRoleOrderContainer">
              <input type="checkbox" name="signerRoleOrdered" />&nbsp;<label for="signerRoleOrdered">Signer Roles Ordered?</label>
          </div>
          <br />
          <h3>Any CC roles?</h3>
          <div id="ccRoleContainer">
              <div id="ccRoles">
                  <input type="text" name="ccRole1" placeholder="CC role (optional)" />
              </div>
              <br />
              <button id="addCCRole" class="btn btn-sm btn-default">Add CC Role</button>
          </div>
          <br />
          <h3>Anything else?</h3>
          <input type="text" name="subject" placeholder="Subject (optional)" /><br /><br />
          <input type="text" name="message" placeholder="Message (optional)" style="height: 100px;" />
      </div>

<jsp:include page="/common-jsp/footer.jsp" />

    <script type="text/javascript" src="/js/embeddedTemplateDemo.js"></script>
    <script type='text/javascript'>
       
       $(document).ready(function(){

           // Defined in /js/embeddedTemplateDemo.js
           initTemplateDemo();

<% if (editUrl != "") { %>
           // Initialize HelloSign with the client ID
           HelloSign.init("<%= clientId %>");
           // Open the iFrame dialog for embedded signing
           HelloSign.open({
               url: "<%= editUrl %>",
               debug: true,
               allowCancel: true,
               skipDomainVerification: true,
               messageListener: function(eventData) {
                   console.log("Event received:");
                    console.log(eventData);
                    var msg;
                    if (eventData.event == HelloSign.EVENT_TEMPLATE_CREATED) {
                        msg = "Your template is being created!";
                    } else {
                        msg = eventData.event;
                    }
                    $("#demoContainer").html("<h3>" + msg + "</h3><a class=\"btn btn-lg btn-success\" href=\"/embeddedTemplateDemo.jsp\">Try it again</a>");
               }
           });
<% } %>
       });
   </script>
   </body>
</html>