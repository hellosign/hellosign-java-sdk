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

    boolean signatureRequestSent = false;
    if (oauth != null && signerEmail != null) {

        // Instantiate the client
        Authentication auth = new Authentication();
        auth.setAccessToken(oauth.getAccessToken(), oauth.getTokenType());
        HelloSignClient userClient = new HelloSignClient(auth);

        // Create the signature request
        SignatureRequest sigReq = new SignatureRequest();
        sigReq.addFile(new File(application.getRealPath("/docs/nda.pdf")));
        sigReq.setTitle("OAuth Demo - NDA");
        sigReq.addSigner(signerEmail, signerName);
        sigReq.setTestMode(true);

        // Send it!
        SignatureRequest sigReqResponse = userClient.sendSignatureRequest(sigReq);

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
<html>
    <head>
        <title>OAuth Demo | HelloSign</title>
        <script type="text/javascript" src="/js/jquery.js"></script>
<% if (isLocalDev) { %>
        <script type="text/javascript" src="//www.my.hellosign.com/js/embedded.js"></script>
<% } else { %>
        <script type="text/javascript" src="//s3.amazonaws.com/cdn.hellofax.com/js/embedded.js"></script>
<% } %>
        <link rel="stylesheet" type="text/css" media="screen" href="/css/prettify.css" />
        <link rel="stylesheet" type="text/css" media="screen" href="/css/main.css" />
        <link rel="stylesheet" type="text/css" media="screen" href="/css/main-loggedOut.css" />
        <script type="text/javascript" src="/js/init.js"></script>

        <script type="text/javascript" src="/js/prettify.js"></script>
        <link rel="stylesheet" type="text/css" media="screen" href="/css/hs/main.css" />
        <script type="text/javascript" src="/js/main.js" ></script>

        <link rel="shortcut icon" href="/favicon.ico" />
        <link rel="apple-touch-icon" href="/apple-touch-icon-precomposed.png" />
        <style>
            html { background-color: white !important; }
            input[type="submit"] { padding: 10px 20px; height: 40px; font-size: 16px; text-align: center; }
        </style>
        <script type='text/javascript'>
            $(document).ready(function(){
                initEmbeddedDemo();
<% if (oauth == null) { %>

                // If OAuth authorization has not been established yet
                // (saved to the user's session), request it by opening
                // a window to HelloSign's authorization page.

                // We'll do this by overriding the "Launch Demo" button
                // submit behavior.

                $("#startButton").click(function() {

                       // Request an OAuth token from HelloSign

                       // Note: This is currently set to query a local HelloSign development
                       // environment, so use this JSP more as an example for setting up
                       // your own OAuth solution. You would simply replace the
                       // "www.my.hellosign.com" with "www.hellosign.com" for this to work.
                       // (Be sure to correctly configure your app's OAuth callback using
                       // the oauthDemoCallback.jsp as an example).

                       var win = window.open(
<% if (isLocalDev) { %>
                               "https://www.my.hellosign.com/oauth/authorize?" +
<% } else if (isStaging) { %>
                               "https://staging.hellosign.com/oauth/authorize?" +
<% } else { %>
                               "https://www.hellosign.com/oauth/authorize?" +
<% } %>
                               "response_type=code&client_id=<%= clientId %>&" +
                               "state=demo",
                               "hellosign_oauth",
                               "width=600,height=400");

                       // Poll window to see if it's closed
                       var pollTimer = setInterval(function() {
                           if (win.closed !== false) {
                            window.clearInterval(pollTimer);
                            $('form').trigger("submit");
                        }
                       }, 200);

                    return false;
                });
<% } %>
            });
        </script>
    </head>
    <body class="api documentation logged-out " id="hs">
        <div id="wrap">
            <div id="container">
                 <div id="header">
                     <a href="https://www.hellosign.com"><span id="logo"></span></a>
                     <a href="https://www.hellosign.com/api/gettingStarted" class="nav">Getting Started</a>
                     <a href="https://www.hellosign.com/api/embedded" class="nav selected">Embedded</a>
                     <a href="https://www.hellosign.com/api/reference" class="nav">API Reference</a>
                     <a href="https://www.hellosign.com/api/libraries" class="nav">Libraries</a>
                     <a href="https://www.hellosign.com/api/pricing" class="nav">Pricing</a>
                     <a id="signinButton" class="signin blue-sub" href="https://www.hellosign.com/account/logIn">Sign in</a>
                 </div>
                <div id="main-content">
                    <p><a href="/">Index</a> &gt; OAuth Demo</p>
                    <h2 class="page-title default headline">OAuth Demo</h2>
                    <div class="embeddedSigning bs_container">
                        <p class="intro">
                            This page demonstrates how you can add HelloSign OAuth to your Java-based web application to perform HelloSign requests on behalf of your users.
                        </p>
                        <br />
                        <div class="row">
                            <div class="span12">
                                <h2>Before You Begin</h2>
                                <ul>
                                    <li><b>1.</b>&nbsp;Obtain an API key.<br /><em>Sign up for an API plan <a href="https://www.hellosign.com/api/pricing">here</a>. Adding embedded signing to your website requires a Silver or Gold API plan. However, you can test the functionality for free by creating signature requests in test mode.</em></li>
                                    <li><b>2.</b>&nbsp;Obtain a Client ID.<br /><em>Sign up for a Client ID for your application <a href="https://www.hellosign.com/oauth/createAppForm">here</a>. To fully enable this demo, configure your application server to be accessible from HelloSign and set the OAuth callback URL to the demo callback (e.g., "http://localhost:8080/oauthDemoCallback.jsp"). This callback page will store the HelloSign access token in the current session for use in the user's requests.</em></li>
                                    <li><b>3.</b>&nbsp;Set the API key and Client ID in the properties file:<br /><pre class="code-render prettyprint">jello-sign/src/main/webapp/WEB-INF/web.properties</pre></li>
                                </ul>
                                <br />
                                <h2>Try It Out</h2>
<% if (oauth != null) { %>
                                <h3>Authorization received!</h3><br />
<% } else { %>
                                <h3>Not currently authorized.</h3><br />
<% } %>
                                <div id="demoContainer">
<% if (signatureRequestSent) { %>
                                    <h3>Signature Request Sent!</h3>
                                    <a href="/oauthDemo.jsp">Try it again</a>
<% } else { %>
                                    <p>This demo will prompt you for authorization to send a signature request to someone on your behalf. Enter their email address and an optional name below and click "Launch Demo."</p><br />
                                    <form action="/oauthDemo.jsp" method="post" enctype="multipart/form-data">
                                        <input type="text" name="signerEmail" placeholder="signer@email.com" />&nbsp;<input type="text" name="signerName" placeholder="Signer name" /><br /><br />
                                        <input type="submit" class="btn-blue" id="startButton" value="Launch Demo" />
                                    </form>
<% } %>
                                </div>
                                <br />
                                <br />
                                <h2>Enabling HelloSign OAuth</h2>
                                <p>The steps below demonstrate how to add embedded signing to a JSP-based web page, but the steps are similar for other JEE-based applications. See the server-side source of this file for the code used in this example.</p><br />
                                <ul>
                                    <li><b>1.</b>&nbsp;<b>Server-side</b>: Create a callback page that can receive the OAuth request token, with which you can retrieve the user's OAuth access token. See the page <a href="/oauthDemoCallback.jsp">/oauthDemoCallback.jsp</a> for an example of how this should be done.</li>
                                    <li><b>2.</b>&nbsp;<b>Server-side</b>: Import the packages for jello-sign, along with the rest of your dependencies.<br /><pre class="code-render prettyprint">&lt;%@page import="com.hellosign.sdk.*,com.hellosign.sdk.resource.*,com.hellosign.sdk.resource.support.*"%&gt;</pre></li>
                                    <li><b>3.</b>&nbsp;<b>Client-side</b>: Create a form that will collect any information from the user needed for the request they want to make. In the case of this demo, we are building a signature request with one signer. Therefore, the form has inputs for the signer's email address and name.</li>
                                    <li><b>4.</b>&nbsp;<b>Client-side</b>: Provide a way for the user to request OAuth authorization from HelloSign. Typically, this comes in the form of a button the user clicks, which checks for the presence of the OAuth access token and directs the user to the authorization page if it doesn't exist. In this demo, the "Launch Demo" button provides this functionality. If the OAuth access token does not exist on the user's session, the necessary JavaScript is rendered to override the default button behavior and redirect the user to the authorization page prior to launching the demo.<br />
<pre class="code-render prettyprint">
$("#startButton").click(function() {
    // Request an OAuth token from HelloSign
    var win = window.open(
            "https://www.hellosign.com/oauth/authorize?" +
            "response_type=code&amp;client_id=&lt;%= clientId %&gt;&amp;" +
            "state=demo",
            "hellosign_oauth",
            "width=600,height=400");

    // Poll window to see if it's closed
    var pollTimer = setInterval(function() {
        if (win.closed !== false) {
            // Cancel the polling
            window.clearInterval(pollTimer);

            // Assume we have authorization and submit
            // the form to create the signature request
            $('form').trigger("submit");
        }
    }, 200);
    return false;
});
</pre>
                                    </li>
                                    <li><b>5.</b>&nbsp;<b>Server-side</b>: Create the HelloSign request using the jello-sign classes and provide the user's OAuth access token to the client.<br />
<pre class="code-render prettyprint">
    // Instantiate the client
    // Notice we are not providing any other authorization other than
    // the user's OAuth access token
    HelloSignAuthentication auth = new HelloSignAuthentication();
    auth.setAccessToken(oauth.getAccessToken(), oauth.getTokenType());
    HelloSignClient userClient = new HelloSignClient(auth);

    // Create the signature request
    SignatureRequest sigReq = new SignatureRequest();
    sigReq.addFile(new File(application.getRealPath("/docs/nda.pdf")));
    sigReq.setTitle("OAuth Demo - NDA");
    sigReq.addSigner(signerEmail, signerName);
    sigReq.setTestMode(true);

    // Send it!
    SignatureRequest sigReqResponse = userClient.sendSignatureRequest(sigReq);
</pre>
                                    </li>
                                </ul>
                                <br />
                                <br />
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div id="footer" class="border-box">
            <div class="footer_container">
                <div class="inner border-box">
                    <div id="footer_links" class="clearfix">
                        <div class="col left">
                            <span><a id="footer_contact" href="https://www.hellosign.com/info/contact">Contact</a></span> <span><a id="footer_about_us" href="https://www.hellosign.com/info/aboutUs">About Us</a></span> <span><a id="footer_pricing" href="https://www.hellosign.com/info/pricing">Pricing</a></span> <span><a id="footer_benefits" href="https://www.hellosign.com/info/benefits">Benefits</a></span>
                        </div>
                        <div class="col left">
                            <span><a target="_blank" id="footer_faq" href="http://faq.hellofax.com">FAQ</a></span> <span><a target="_blank" id="footer_hs" href="https://www.my.hellosign.com/webapp_dev.php/">HelloSign</a></span> <span><a id="footer_security" href="https://www.hellosign.com/info/security">Security</a></span> <span><a target="_blank" id="footer_blog" href="http://blog.hellofax.com">Blog</a></span>
                        </div>
                        <div class="col left">
                            <span><a id="footer_jobs" href="https://www.hellosign.com/info/jobs">Jobs</a></span> <span><a id="footer_legal" href="https://www.hellosign.com/info/legal">Legal</a></span> <span><a id="footer_privacy" href="https://www.hellosign.com/info/privacyPolicy">Privacy</a></span> <span><a id="footer_tos" href="https://www.hellosign.com/info/tos">Terms</a></span>
                        </div>
                        <p class="legal">
                            &copy; <strong>HelloFax, Inc.</strong>, 2014. All rights reserved.
                        </p>
                        <div class="clearfix"></div>
                    </div><br>
                </div>
            </div>
        </div>
    </body>
</html>