<%@page import="org.apache.commons.fileupload.servlet.ServletFileUpload"%>
<%@page import="org.apache.commons.fileupload.disk.DiskFileItemFactory"%>
<%@page import="org.apache.commons.fileupload.FileItem"%>
<%@ page import="com.hellosign.sdk.*,com.hellosign.sdk.resource.*,com.hellosign.sdk.resource.support.*,java.io.File,java.util.*" %>
<%
    // Load authentication properties
    Properties properties = new Properties();
    properties.load(getServletContext().getResourceAsStream("/WEB-INF/web.properties"));
    String apiKey = properties.getProperty("hellosign.api.key");
    String clientId = properties.getProperty("client.id");
    String signUrl = null;
    String errorMessage = null;

    if (ServletFileUpload.isMultipartContent(request)) {
        String myName = null;
        String myEmail = null;
	    try {
	        // Process the fields entered by the user
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
	                if ("yourName".equals(fieldName)) {
	                    myName = value;
	                } else if ("yourEmail".equals(fieldName)) {
	                    myEmail = value;
	                }
	            }
	        }
	    } catch (Exception e) {
	    	errorMessage = e.getMessage();
	        e.printStackTrace();
	    }
	    if (myEmail != null && apiKey != null && clientId != null) {

	    	try {
		        // Create the signature request
		        SignatureRequest sigReq = new SignatureRequest();
		        sigReq.addFile(new File(application.getRealPath("/docs/nda.pdf")));
		        sigReq.setTitle("Embedded NDA");
		        sigReq.addSigner(myEmail, myName);
		        sigReq.setTestMode(true);

		        /// Turn it into an embedded request
		        EmbeddedRequest embedded = new EmbeddedRequest(clientId, sigReq);

		        // Send it to HelloSign
		        HelloSignClient client = new HelloSignClient(apiKey);
		        SignatureRequest newRequest = (SignatureRequest) client.createEmbeddedRequest(embedded);

		        // Grab the signature ID for the signature page that will
		        // be embedded in the page (for the demo, we'll just use the first one)
		        Signature signature = newRequest.getSignatures().get(0);

		        // Retrieve the URL to sign the document
		        EmbeddedResponse embeddedResponse = client.getEmbeddedSignUrl(signature.getId());

		        // Store it to use with the embedded.js HelloSign.open() call
		        signUrl = embeddedResponse.getSignUrl();

	    	} catch (HelloSignException ex) {
	    		errorMessage = ex.getMessage();
	    	}
	    }
    }

%>
<jsp:include page="/common-jsp/header.jsp">
    <jsp:param name="title" value="Embedded Signing Demo"/>
    <jsp:param name="description" value="Add document signing directly to your Java-based web application." />
    <jsp:param name="errorMessage" value="${errorMessage}"/>
</jsp:include>

    <input type="text" name="yourName" placeholder="Your Name" /> <input type="text" name="yourEmail" placeholder="Your Email" />

<jsp:include page="/common-jsp/footer.jsp"/>

<% if (clientId != null && signUrl != null) { %>
<script type='text/javascript'>
    $(document).ready(function(){
        // Initialize HelloSign with the client ID
        HelloSign.init("<%= clientId %>");
        // Open the iFrame dialog for embedded signing
        HelloSign.open({
            url: "<%= signUrl %>",
            debug: true,
            allowCancel: true,
            skipDomainVerification: true,
            messageListener: function(eventData) {
                console.log("Event received:");
                console.log(eventData);
                var msg = "Received event: " + eventData.event;
                if (eventData.event == HelloSign.EVENT_SIGNED) {
                    msg = "Thanks for signing!";
                }
                $("#demoContainer").html("<h3>" + msg + "</h3><a class=\"btn btn-lg btn-success\" href=\"/embeddedSigningDemo.jsp\">Try it again</a>");
            }
        });
    });
</script>
<% } %>
    </body>
</html>