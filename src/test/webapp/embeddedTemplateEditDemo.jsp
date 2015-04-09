<%@ page import="com.hellosign.sdk.*,com.hellosign.sdk.resource.*,com.hellosign.sdk.resource.support.*,java.io.*,java.util.*,org.apache.commons.fileupload.*,org.apache.commons.fileupload.servlet.*,org.apache.commons.fileupload.disk.*,org.apache.commons.io.*" %>
<%
// Load authentication properties
Properties properties = new Properties();
properties.load(getServletContext().getResourceAsStream("/WEB-INF/web.properties"));
String apiKey = properties.getProperty("hellosign.api.key");
HelloSignClient client = new HelloSignClient(apiKey);
String clientId = properties.getProperty("client.id");
String myName = properties.getProperty("my.name");
String myEmail = properties.getProperty("my.email");
String editUrl = "";
String errorMessage = null;

//Get the user's templates to populate the form
TemplateList templateList = null;
if (apiKey != null) {
	try {
	    templateList = client.getTemplates();
	} catch (HelloSignException ex) {
		errorMessage = ex.getMessage();
		ex.printStackTrace();
	}
}

// If this is a form submission, pull the form fields from the request
if (ServletFileUpload.isMultipartContent(request)) {

	// Store the form field information for our request
    Map<String, Signer> signersList = new HashMap<String, Signer>();
    Map<String, String> ccList = new HashMap<String, String>();
    Map<String, String> customFieldList = new HashMap<String, String>();
    String templateId = null;

    try {
        List<FileItem> items = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
        for (FileItem item : items) {
            String fieldName = item.getFieldName();
            String value = item.getString();
            System.out.println("item.getFieldName() = " + fieldName);
            System.out.println("item.getString() = " + value);
            if (item.isFormField()) {
                if ("template".equals(item.getFieldName())) {
                    templateId = item.getString();
                }
                else if (fieldName.startsWith("signerRole_")) {
                    String type = fieldName.substring(fieldName.indexOf("_") + 1, fieldName.lastIndexOf("_"));
                    String role = fieldName.substring(fieldName.lastIndexOf("_") + 1, fieldName.length());
                    Signer s;
                    if (!signersList.containsKey(role)) {
                        s = new Signer();
                        signersList.put(role, s);
                    } else {
                        s = signersList.get(role);
                    }
                    if ("name".equals(type)) {
                        s.setNameOrRole(value);
                    } else if ("email".equals(type)) {
                        s.setEmail(value);
                    }
                }
                else if (fieldName.startsWith("ccRole_")) {
                    String role = fieldName.substring(fieldName.lastIndexOf("_") + 1, fieldName.length());
                    ccList.put(role, value);
                }
                else if (fieldName.startsWith("cf_")) {
                    String role = fieldName.substring(fieldName.lastIndexOf("_") + 1, fieldName.length());
                    customFieldList.put(role, value);
                }
            }
        }
    } catch (Exception e) {
    	errorMessage = e.getMessage();
        e.printStackTrace();
    }

    // If we have a template ID, let's try to create the embedded request
    if (templateId != null) {

        try {

            EmbeddedResponse resp = client.getEmbeddedTemplateEditUrl(templateId);
            if (resp != null) {
                editUrl = resp.getEditUrl();
            }

        } catch (HelloSignException ex) {
        	errorMessage = ex.getMessage();
            ex.printStackTrace();
        }
    }
}
%>
<jsp:include page="/common-jsp/header.jsp">
    <jsp:param name="title" value="Embedded Template Edit Demo"/>
    <jsp:param name="description" value="Allow your users to edit templates directly on your website. Follow the steps below to add this feature to your Java-based web application." />
    <jsp:param name="errorMessage" value="${errorMessage}"/>
</jsp:include>
	<input type="hidden" name="template" id="templateId" value="" />
	<div id="demoForm">
		<h3>Your Templates</h3>
		<select id="templates">
			<option selected>Choose a template to edit...</option>
		</select>
	</div>
<jsp:include page="/common-jsp/footer.jsp" />

<script type="text/javascript" src="/js/embeddedTemplateXDemo.js"></script>
<script type='text/javascript'>
    var templates = [
<%if(templateList != null) {
    Iterator<Template> it = templateList.iterator();
    while(it != null && it.hasNext()) {
        Template t = it.next();
        if (t.isEmbedded()) {
	        out.write(t.toString());
	        if (it.hasNext()) {
	            out.write(",");
	        }
        }
    }
}%>
    ];
    $(document).ready(function() {

        // Defined in /js/embeddedTemplateXDemo.js
        initTemplates();

<% if (!editUrl.isEmpty()) { %>
        // Initialize HelloSign with the client ID
        HelloSign.init("<%= clientId %>");

        // Open the iFrame dialog for embedded signing
           HelloSign.open({
            url: "<%= editUrl %>",
            debug: true,
            allowCancel: true,
            skipDomainVerification: true,
            messageListener: function(eventData) {
                console.log("Event received: " + eventData);
                var msg;
                if (eventData.event == HelloSign.EVENT_TEMPLATE_CREATED) {
                    msg = "Success!";
                } else {
                    msg = eventData.event;
                }
                $("#demoContainer").html("<h3>" + msg + "</h3><a class=\"btn btn-lg btn-success\" href=\"/embeddedTemplateEditDemo.jsp\">Try it again</a>");
            }
        });
<% } %>
    });
</script>
    </body>
</html>