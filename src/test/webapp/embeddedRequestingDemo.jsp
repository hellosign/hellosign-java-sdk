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
	String requestUrl = "";
	String errorMessage = null;

	if (ServletFileUpload.isMultipartContent(request)) {

	    List<File> files = new ArrayList<File>();
	    Map<Integer, Signer> signers = new HashMap<Integer, Signer>();
	    String myEmail = null;
	    String myName = null;
	    String subject = null;
	    String message = null;

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
	                if (fieldName.startsWith("signer")) {
	                    int i = Integer.valueOf(fieldName.substring(6, 7)) - 1;
	                    Signer s;
	                    if (!signers.containsKey(i)) {
	                        s = new Signer();
	                        signers.put(i, s);
	                    } else {
	                        s = signers.get(i);
	                    }
	                    String type = fieldName.substring(8, fieldName.length());
	                    if ("name".equals(type)) {
	                        s.setNameOrRole(value);
	                    } else if ("email".equals(type)) {
	                        s.setEmail(value);
	                    }
	                } else if ("yourName".equals(fieldName)) {
	                    myName = value;
	                } else if ("yourEmail".equals(fieldName)) {
	                    myEmail = value;
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
                    while (prefix.length() < 3) {
                        prefix += '_';
                    }
	                String suffix = filename.substring(filename.indexOf("."), filename.length());
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
	    if (files.size() > 0 && !myEmail.isEmpty()) {
	        try {
	        	// Create a signature request
	            SignatureRequest sigReq = new SignatureRequest();
	            sigReq.setTestMode(true);
	            if (subject != null) {
	            	sigReq.setSubject(subject);
	            }
	            if (message != null) {
	            	sigReq.setMessage(message);
	            }
	            if (signers.size() > 0) {
		            for (Integer key : signers.keySet()) {
		                Signer s = signers.get(key);
		                sigReq.addSigner(s.getEmail(), s.getNameOrRole());
		            }
		        }
	            for (File file : files) {
	                sigReq.addFile(file);
	            }

	            // Create an unclaimed draft from the request
				UnclaimedDraft draft = new UnclaimedDraft(sigReq, UnclaimedDraftType.request_signature);
				draft.setIsForEmbeddedSigning(false);
				draft.setRequesterEmail(myEmail);

                // Make this an embedded unclaimed draft
                EmbeddedRequest embed = new EmbeddedRequest(clientId, draft);

	            // Send it to HelloSign
	            HelloSignClient client = new HelloSignClient(apiKey);
	            UnclaimedDraft responseDraft = (UnclaimedDraft) client.createEmbeddedRequest(embed);

	         	// Retrieve the embedded signing URL from the response
	            requestUrl = responseDraft.getClaimUrl();
                System.out.println("CHRIS: " + responseDraft.getSignatureRequestId());

	        } catch (HelloSignException ex) {
	        	errorMessage = ex.getMessage();
	            ex.printStackTrace();
	        }
	    }
	}
%>
<jsp:include page="/common-jsp/header.jsp">
    <jsp:param name="title" value="Embedded Signature Request Demo"/>
    <jsp:param name="description" value="Request signatures for documents directly from your website with HelloSign's embedded request capability. Follow the steps below to add this feature to your Java-based web application." />
    <jsp:param name="errorMessage" value="${errorMessage}"/>
</jsp:include>
	<div id="demoForm">
	    <h3>What needs to be signed?</h3>
	    <div id="fileContainer">
	        <div id="files" >
	           <div class="field-container">
	               <input id="file_1" type="file" name="file_1" />
               </div>
	        </div>
	        <button id="addFile" class="btn btn-sm btn-default">Add Another File</button>
	    </div>
	    <br />
	    <h3>Who are you?</h3>
	    <input type="text" name="yourName" placeholder="Your name" /> <input type="text" name="yourEmail" placeholder="Your email" />
        <button id="toggleOptionalFields" class="btn btn-sm btn-default">Toggle optional parameters</button>
        <br />
        <div id="optionalFields" style="display: none;">
		    <em>The information below is optional. If you leave these fields blank, HelloSign will walk you through the information it needs.</em>
		    <br />
		    <h3>Who needs to sign it?</h3>
		    <div id="signerContainer">
		        <div id="signers">
		            <div class="field-container">
		                <input type="text" name="signer1_name" placeholder="Signer name" />
		                <input type="text" name="signer1_email" placeholder="Signer email" />
  	                </div>
		        </div>
		        <button id="addSigner" class="btn btn-sm btn-default">Add Signer</button>
		    </div>
		    <br />
		    <h3>Anything else?</h3>
		    <input type="text" name="subject" placeholder="Subject (optional)" /><br /><br />
		    <input type="text" name="message" placeholder="Message (optional)" style="height: 100px;"/>
        </div>
	</div>
<jsp:include page="/common-jsp/footer.jsp" />
    <script type="text/javascript" src="/js/embeddedRequestingDemo.js" ></script>
    <script type='text/javascript'>
        
        $(document).ready(function(){

            // Defined in /js/embeddedRequestingDemo.js
            initEmbeddedRequesting();

<% if (requestUrl != "") { %>
            HelloSign.init("<%= clientId %>");
            HelloSign.open({
                url: "<%= requestUrl %>",
                debug: true,
                allowCancel: true,
                skipDomainVerification: true,
                messageListener: function(eventData) {
                    console.log("Event received:");
                    console.log(eventData);
                    var msg;
                    if (eventData.event == HelloSign.EVENT_SENT) {
                        msg = "Request Sent! Your recipient will receive an email with a link to the document signature page on HelloSign.com.";
                    } else if (eventData.event == HelloSign.EVENT_CANCELED) {
                        msg = "Request Cancelled";
                    } else {
                        msg = eventData.event;
                    }
                    $("#demoContainer").html("<h3>" + msg + "</h3><a class=\"btn btn-lg btn-success\" href=\"/embeddedRequestingDemo.jsp\">Try it again</a>");
                }
            });
<% } %>
        });
    </script>
    </body>
</html>