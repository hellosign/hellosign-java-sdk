<%@page import="com.hellosign.sdk.http.Authentication"%>
<%@page import="java.util.*,java.io.File" %>
<%@page import="com.hellosign.sdk.*,com.hellosign.sdk.resource.*,com.hellosign.sdk.resource.support.*"%>
<%@page import="org.apache.commons.fileupload.servlet.ServletFileUpload"%>
<%@page import="org.apache.commons.fileupload.disk.DiskFileItemFactory"%>
<%@page import="org.apache.commons.fileupload.FileItem"%>
<%
    // Load authentication properties
    Properties properties = new Properties();
    properties.load(getServletContext().getResourceAsStream("/WEB-INF/web.properties"));
    String clientId = properties.getProperty("client.id");
    String env = System.getProperty("hellosign.env");
    boolean isLocalDev = "dev".equalsIgnoreCase(env);
    boolean isStaging = "staging".equalsIgnoreCase(env);

    // Properties for sending a sample signature request
    String senderEmail = null;

    String signerEmail = null;
    String signerName = null;

    // If this is a form submission, attempt to retrieve the form
    // values from the request.
    if (ServletFileUpload.isMultipartContent(request)) {
        try {
            // Process the fields entered by the user
            List<FileItem> items = new ServletFileUpload(
                    new DiskFileItemFactory()).parseRequest(request);
            for (FileItem item : items) {
                if (item.isFormField()) {
                    String fieldName = item.getFieldName();
                    String value = item.getString();
                    if (value == null || value.equals("") ||
                            fieldName == null ||
                            fieldName.equals("")) {
                        continue;
                    }
                    if ("signerName".equals(fieldName)) {
                        signerName = value;
                    } else if ("signerEmail".equals(fieldName)) {
                        signerEmail = value;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Check to see if the user has given us authorization to send requests
    // to HelloSign on their behalf. Also check to see if they've given us
    // an email address to send the request with.
    OauthData oauth = (OauthData) session.getAttribute("hellosign_oauth");
    HelloSignClient client = null;
    if (oauth != null) {
        // Instantiate the client
        Authentication auth = new Authentication();
        auth.setAccessToken(oauth.getAccessToken(), oauth.getTokenType());
        client = new HelloSignClient(auth);

        Account account = client.getAccount();
        senderEmail = account.getEmail();
    }
    
    boolean signatureRequestSent = false;
    if (client != null && signerName != null && signerEmail != null) {
        // Create the signature request
        SignatureRequest sigReq = new SignatureRequest();
        sigReq.addFile(new File(application.getRealPath("/docs/nda.pdf")));
        sigReq.setSubject("OAuth Demo - NDA");
        sigReq.setMessage("Sent via the hellosign-java-sdk demo app.");
        sigReq.addSigner(signerEmail, signerName);
        sigReq.setTestMode(true);

        // Send it!
        SignatureRequest sigReqResponse = client.sendSignatureRequest(sigReq);

        // Verify it's been created
        signatureRequestSent = sigReqResponse.hasId();

        // For demo purposes only:
        // Clear the session so the demo provides an example of retrieving
        // authorization from HelloSign. Normally, you would want to
        // maintain the access token in the user's session so they do not need
        // to continually authorize the application.
        session.invalidate();
    }

%>
<jsp:include page="/common-jsp/header.jsp">
    <jsp:param name="title" value="OAuth 2.0 Demo"/>
    <jsp:param name="description" value="This page demonstrates how you can add HelloSign OAuth to your Java-based web application to perform HelloSign requests on behalf of your users." />
    <jsp:param name="errorMessage" value="${errorMessage}"/>
</jsp:include>

<% if (oauth == null) { %>
    <h3>Status: <span id="status">Not currently authorized</span></h3>
    Click "Launch Demo" to log into HelloSign and obtain authorization for this demo.
<% } else { %>
    <h3>Status: <span id="status">Authorization received</span></h3>
    Sending a signature request as HelloSign user <strong><%= senderEmail %></strong>.
    <br /><br />
    Who should sign?
    <br /><br />    
    <input type="text" name="signerName" placeholder="Signer name" />
    <input type="text" name="signerEmail" placeholder="Signer email" />
<% } %>

<jsp:include page="/common-jsp/footer.jsp" />

        <script type='text/javascript'>
<% if (oauth == null) { %>
            $(document).ready(function(){

                // If OAuth authorization has not been established yet
                // (saved to the user's session), request it by opening
                // a window to HelloSign's authorization page.

                // We'll do this by overriding the "Launch Demo" button
                // submit behavior.

                $("#startButton").val("Obtain authorization").click(function() {

                       // Request an OAuth token from HelloSign

                       // Note: This is currently set to query a local HelloSign development
                       // environment, so use this JSP more as an example for setting up
                       // your own OAuth solution. You would simply replace the
                       // "www.dev-hellosign.com" with "www.hellosign.com" for this to work.
                       // (Be sure to correctly configure your app's OAuth callback using
                       // the oauthDemoCallback.jsp as an example).

                       var win = window.open(
    <% if (isLocalDev) { %>
                               "https://www.dev-hellosign.com/webapp_dev.php/oauth/authorize?" +
    <% } else if (isStaging) { %>
                               "https://staging.hellosign.com/oauth/authorize?" +
    <% } else { %>
                               "https://www.hellosign.com/oauth/authorize?" +
    <% } %>
                               "response_type=code&client_id=<%= clientId %>&" +
                               "state=demo",
                               "hellosign_oauth",
                               "width=800,height=600");

                       // Poll window to see if it's closed
                       var pollTimer = setInterval(function() {
                           if (win.closed !== false) {
                            window.clearInterval(pollTimer);
                            $('form').trigger("submit");
                        }
                       }, 200);

                    return false;
                });
           });
<% } else { %>
        $(document).ready(function() {
            $('#startButton').val('Send signature request');
        });
<% }
if (signatureRequestSent) { %>
		$(document).ready(function() {
		    $("#demoContainer").html("<h3>Signature request sent!</h3><a class=\"btn btn-lg btn-success\" href=\"/oauthDemo.jsp\">Try it again</a>");
		});
<% } %>
        </script>
    </body>
</html>