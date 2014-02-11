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
    String env = System.getProperty("hellosign.env");
    boolean isLocalDev = "dev".equalsIgnoreCase(env);
    boolean isStaging = "staging".equalsIgnoreCase(env);

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
		        SignatureRequest newRequest = client.createEmbeddedRequest(embedded);

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
<html>
    <head>
        <title>Embedded Signing Demo | HelloSign</title>
        <script type="text/javascript" src="/js/jquery.js"></script>
<% if (isLocalDev) { %>
        <script type="text/javascript" src="//www.my.hellosign.com/js/embedded.js"></script>
<% } else if (isStaging) { %>
        <script type="text/javascript" src="//staging.hellosign.com/js/embedded.js"></script>
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
            pre { font-family: Monaco; font-size: 13px; margin-left: 15px; margin-top: 5px; }
            input#startButton { padding: 10px 20px; height: 40px; font-size: 16px; clear: both; }
            div#message { margin: 10px; color: red; }
        </style>
        <script type='text/javascript'>
            $(document).ready(function(){
                initEmbeddedDemo();
<% if (clientId != null && signUrl != null) { %>
                // Initialize HelloSign with the client ID
                HelloSign.init("<%= clientId %>");

                // Open the iFrame dialog for embedded signing
                HelloSign.open({
                    url: "<%= signUrl %>",
                    debug: true,
                    allowCancel: true,
                    messageListener: function(eventData) {
                    	console.log("Event received:");
                        console.log(eventData);
                        if (eventData.event == HelloSign.EVENT_SIGNED) {
                        	$("#demoContainer").html('Thanks for signing!<br /><a href="/embeddedSigningDemo.jsp">Try it again</a>');
                        }
                    }
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
                	<p><a href="/">Index</a> &gt; Embedded Signing Demo</p>
                    <h2 class="page-title default headline">Embedded Signing Demo</h2>
                    <div class="embeddedSigning bs_container">
                        <p class="intro">
                            This page demonstrates how you can add an embedded signature request to your Java-based web application.
                        </p>
                        <br />
                        <div class="row">
                            <div class="span12">
                                <h2>Before You Begin</h2>
                                <ul>
                                    <li><b>1.</b>&nbsp;Obtain an API key.<br />Sign up for an API plan <a href="https://www.hellosign.com/api/pricing">here</a>. Adding embedded signing to your website requires a Silver or Gold API plan. However, you can test the functionality for free by creating signature requests in test mode.</li>
                                    <li><b>2.</b>&nbsp;Obtain a Client ID.<br />Sign up for a Client ID for your application <a href="https://www.hellosign.com/oauth/createAppForm">here</a>.</li>
                                    <li><b>3.</b>&nbsp;Set the API key and Client ID in the properties file:<br /><pre class="code-render prettyprint">jello-sign/src/main/webapp/WEB-INF/web.properties</pre></li>
                                </ul>
                                <br />
                                <h2>Try It Out</h2>
<% if (errorMessage != null) { %>
                                <div id="message"><%= errorMessage %></div>
<% } %>
                                <div id="demoContainer">
	                                <p>Please sign our NDA.</p>
	                                <form action="/embeddedSigningDemo.jsp" method="post" enctype="multipart/form-data">
	                                	<input type="text" name="yourName" placeholder="Your Name" /> <input type="text" name="yourEmail" placeholder="Your Email" />
	                                	<br />
	                                	<br />
	                                	<input class="btn blue-sub" id="startButton" type="submit" value="Launch Demo" />
	                                </form>
	                            </div>
                                <br />
                                <br />
                                <h2>Creating an Embedded Signature Request</h2>
                                <p>The steps below demonstrate how to add embedded signing to a JSP-based web page, but the steps are similar for other JEE-based applications. See the server-side source of this file for the code used in this example.</p><br />
                                <ul>
                                    <li><b>1.</b>&nbsp;<b>Server-side</b>: Import the packages for jello-sign and its dependencies.<br /><pre class="code-render prettyprint">&lt;%@ page import="com.hellosign.sdk.*,com.hellosign.sdk.resource.*,com.hellosign.sdk.resource.support.*,java.io.File,java.util.*" %&gt;</pre></li>
                                    <li><b>2.</b>&nbsp;<b>Server-side</b>: Create the embedded signature request.<br />
<pre class="code-render prettyprint">
    // Create the signature request
    SignatureRequest sigReq = new SignatureRequest();
    sigReq.addFile(new File(application.getRealPath("/docs/nda.pdf")));
    sigReq.setTitle("Embedded NDA");
    sigReq.addSigner(myEmail, myName);
    sigReq.setTestMode(true);

    // Turn it into an embedded request
    EmbeddedRequest embedded = new EmbeddedRequest(clientId, sigReq);

    // Send it to HelloSign
    HelloSignClient client = new HelloSignClient(apiKey);
    SignatureRequest newRequest = client.createEmbeddedRequest(embedded);
</pre>
                                    </li>
                                    <li><b>3.</b>&nbsp;<b>Server-side</b>: Retrieve a signing URL for the new request.<br />
<pre class="code-render prettyprint">
    // Grab the signature ID for the signature page that will
    // be embedded in the page (for the demo, we'll just use the first one)
    Signature signature = newRequest.getSignatures().get(0);

    // Retrieve the URL to sign the document
    EmbeddedResponse embeddedResponse = client.getEmbeddedSignUrl(signature.getId());

    // Store it to use with the embedded.js HelloSign.open() call
    signUrl = embeddedResponse.getSignUrl();
</pre>
                                    </li>
                                    <li><b>4.</b>&nbsp;<b>Client-side</b>: Include "embedded.js".<br /><pre class="code-render prettyprint">&lt;script type="text/javascript" src="//s3.amazonaws.com/cdn.hellofax.com/js/embedded.js"&gt;&lt;/script&gt;</pre></li>
                                    <li><b>5.</b>&nbsp;<b>Client-side</b>: Use the server-generated client ID and signing URL to initialize the HelloSign JavaScript components. In JSP, this can be done like so:<br />
<pre class="code-render prettyprint">&lt;script type="text/javascript"&gt;
    function openSigningDialog() {
        HelloSign.init("&lt;%= clientId %&gt;");
        HelloSign.open({
            url: "&lt;%= signUrl %&gt;"
        });
    }
&lt;/script&gt;</pre>
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