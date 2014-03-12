<%@page import="org.apache.commons.fileupload.servlet.ServletFileUpload"%>
<%@page import="org.apache.commons.fileupload.disk.DiskFileItemFactory"%>
<%@page import="org.apache.commons.fileupload.FileItem"%>
<%@ page import="com.hellosign.sdk.*,com.hellosign.sdk.resource.*,com.hellosign.sdk.resource.support.*,java.io.File,java.util.*" %>
<%@ page import="com.twilio.sdk.TwilioRestClient,com.twilio.sdk.TwilioRestException,com.twilio.sdk.resource.factory.SmsFactory,com.twilio.sdk.resource.instance.Sms,com.twilio.sdk.resource.list.SmsList,java.util.HashMap,java.util.Map" %>
<%
    // Load authentication properties
    Properties properties = new Properties();
    properties.load(getServletContext().getResourceAsStream("/WEB-INF/web.properties"));
    String apiKey = properties.getProperty("hellosign.api.key");
    String clientId = properties.getProperty("client.id");
    String twilioSid = properties.getProperty("twilio.account.sid");
    String twilioToken = properties.getProperty("twilio.auth.token");
    String twilioNumber = properties.getProperty("twilio.sender.number");
    String twilioRedirectUrl = properties.getProperty("twilio.redirect.url");
    String signUrl = null;
    String errorMessage = null;
    String env = System.getProperty("hellosign.env");
    boolean success = false;
    boolean isLocalDev = "dev".equalsIgnoreCase(env);
    boolean isStaging = "staging".equalsIgnoreCase(env);
    String allDone = request.getParameter("alldone");

    if (ServletFileUpload.isMultipartContent(request)) {
        String myName = null;
        String myEmail = null;
        String myPhone = null;
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
	                } else if ("yourPhone".equals(fieldName)) {
	                    myPhone = value;
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
		        sigReq.addFile(new File(application.getRealPath("/docs/top_secret_doc.pdf")));
		        sigReq.setTitle("Top Secret Document");
		        Signer signer = new Signer(myEmail, myName);
		        
		        // Assign a secret pin
		        
		        Random rand = new Random();
		        String secretPin = "";
		        for(int x = 0 ; x < Signer.PIN_LENGTH ; x++) {
		        	secretPin += (new Integer(rand.nextInt(10))).toString();
		        }
		        System.out.println("Signer pin = " + secretPin);
		        signer.setPin(secretPin);
		        sigReq.addSigner(signer);
		        sigReq.setSubject("Hey " + myName + ", sign this secret document");
		        sigReq.setMessage("You should have received a SMS from Twilio with the code to sign this.");
		        sigReq.setTestMode(true);
		        sigReq.setRedirectUrl(twilioRedirectUrl);

		        // Send it to HelloSign
		        HelloSignClient client = new HelloSignClient(apiKey);
		        SignatureRequest clientResponse = client.sendSignatureRequest(sigReq);
		        
		        // Now have Twilio send out the secret code
		        TwilioRestClient twilioClient = new TwilioRestClient(twilioSid, twilioToken);
		        
		        // Build a filter for the SmsList
		        Map<String, String> params = new HashMap<String, String>();
		        params.put("Body", "Psst " + myName + ", here's you secret PIN for the document: " + secretPin + ". Now check your email for the link");
		        params.put("To", "+1" + myPhone);
		        params.put("From", "+1" + twilioNumber);
		     
		        SmsFactory messageFactory = twilioClient.getAccount().getSmsFactory();
		        Sms message = messageFactory.create(params);
		        System.out.println(message.getSid());
		        success = true;

	    	} catch (HelloSignException ex) {
	    		errorMessage = ex.getMessage();
	    	} catch (Exception e) {
	    		errorMessage = e.getMessage();
	    	}
	    }
    }

%>
<html>
    <head>
        <title>Embedded Signing Demo | HelloSign</title>
        <script type="text/javascript" src="/js/jquery.js"></script>
        <link rel="stylesheet" type="text/css" media="screen" href="/css/prettify.css" />
        <link rel="stylesheet" type="text/css" media="screen" href="/css/main.css" />
        <link rel="stylesheet" type="text/css" media="screen" href="/css/main-loggedOut.css" />
        <link rel="stylesheet" type="text/css" media="screen" href="/css/demos.css" />
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
    </head>
    <body class="api documentation logged-out twilio" id="hs">
        <div id="wrap">
            <div id="container">
                 <div id="header">
                     <a href="https://www.hellosign.com"><span id="logo"></span></a>
                     <a href="https://www.hellosign.com/api/gettingStarted" class="nav">Getting Started</a>
                     <a href="https://www.hellosign.com/api/embedded" class="nav">Embedded</a>
                     <a href="https://www.hellosign.com/api/reference" class="nav">API Reference</a>
                     <a href="https://www.hellosign.com/api/libraries" class="nav">Libraries</a>
                     <a href="https://www.hellosign.com/api/pricing" class="nav">Pricing</a>
                     <a id="signinButton" class="signin blue-sub" href="https://www.hellosign.com/account/logIn">Sign in</a>
                 </div>
                <div id="main-content">
                	<div class="sub-nav"><a href="/">Index</a> <span class="rsaquo">&rsaquo;</span> Twilio Mashup Demo</div>
                	<div id="hero">
                		<div id="outer_box" style="pointer-events: auto; width: 756px; height: 73px; z-index: 1; border: 0px none rgb(160, 160, 160); opacity: 1; border-bottom-left-radius: 6px; border-bottom-right-radius: 6px; border-top-left-radius: 6px; border-top-right-radius: 6px; text-shadow: none; overflow: visible;">
                			<div id="inner_box" style="position:relative;display:block;width:100%;	background-color:rgba(0,0,0,0.1);-webkit-box-shadow: inset 0px 2px 3px 1px rgba(0, 0, 0, 0.15);box-shadow: inset 0px 2px 3px 1px rgba(0, 0, 0, 0.15); color:#fff; -webkit-border-radius: 6px;-moz-border-radius: 6px;border-radius: 6px;">
								Enhanced security with <img id="twilio_logo" src="css/images/twilio_white.png"/> and HelloSign        
       					    </div>
    					</div>
    					<div id="description">
    						Using twilio's messaging platform you can add 2-factor authentication to any HelloSign document.
    					</div>
                	</div>
            	    <h2 class="page-title default headline">Try It Out</h2>
            	    <div class="embeddedSigning bs_container">
                    	<div id="message">
                    		<% 
                    		if (errorMessage != null) { 
                    			out.println(errorMessage);
                   			} else if(success) {
                    			out.println("<span class=\"green\">Success! Check your phone and email for the document details.");
                   			} else if(allDone != null) {
                   				out.println("<span class=\"green\">Success! You have completed the demo and signed the document.");
                   			}%>
                 		</div>
                                <div id="demoContainer">
	                                <p class="intro">Enter your details and we'll send a test document and secure pin via SMS. Standard data and messaging rates apply.</p>
	                                <form action="/twilioSigningDemo.jsp" method="post" enctype="multipart/form-data">
	                                	<input type="text" name="yourName" placeholder="Your Name" required/>
	                                	<input type="email" name="yourEmail" placeholder="Your Email" required/>
	                                	<input id="phone" type="text" name="yourPhone" placeholder="Your Phone Number" required/>
	                                	<br />
	                                	<br />
	                                	<input class="btn blue-sub" id="startButton" type="submit" value="Try it" />
	                                </form>
	                            </div>
                                <br />
                                <br />
                                <br />
                                <hr/>
                                <br />
                                
                    <h2 class="headline page-title major">Run this example yourself</h2>              
                        <br />
                        <div class="row">
                            <div class="span12">
                                <h2>Before You Begin</h2>
                                <ul>
                                	<li><b>1.</b>&nbsp;Get the code.<br/>Download our java SDK for HelloSign <a href="https://github.com/HelloFax/hellosign-java-sdk">here</a>.</li>
                                    <li><b>2.</b>&nbsp;Obtain an API key.<br />You don't need an API license to run the demo. Instructions for finding your key are <a href="https://www.hellosign.com/api/gettingStarted">here</a>.</li>
                                    <li><b>3.</b>&nbsp;Obtain a Twilio SID, auth token, and phone number.<br />Sign up for a free twilio trial account <a href="https://www.twilio.com/try-twilio">here</a>. With a trial account you can send text messages to yourself, but no other unverified numbers.</li>
                                    <li><b>4.</b>&nbsp;Set the API key and Twilio fields in the properties file:<br />The redirect url is optional for sending signers back to your site after signing.<pre class="code-render prettyprint">hellosign-java-sdk/src/main/webapp/WEB-INF/web.properties</pre></li>
                                </ul>
                                <br />
                                <h2>Creating Signature Request</h2>
                                <p>The steps below demonstrate how to automated signature requests to a JSP-based web page, but the steps are similar for other Java EE-based applications. See the server-side source of this file for the code used in this example.</p><br />
                                <ul>
                                    <li><b>1.</b>&nbsp;<b>Server-side</b>: Import the packages for hellosign-java-sdk twilio's java sdk and their dependencies.<br /><pre class="code-render prettyprint">&lt;%@ page import="com.hellosign.sdk.*,com.hellosign.sdk.resource.*,com.hellosign.sdk.resource.support.*,java.io.File,java.util.*,com.twilio.sdk.TwilioRestClient,com.twilio.sdk.TwilioRestException,com.twilio.sdk.resource.factory.SmsFactory,com.twilio.sdk.resource.instance.Sms,com.twilio.sdk.resource.list.SmsList,java.util.HashMap,java.util.Map" %&gt;</pre></li>
                                    <li><b>2.</b>&nbsp;<b>Server-side</b>: Create the signature request.<br />
<pre class="code-render prettyprint">
    // Create the signature request
    SignatureRequest sigReq = new SignatureRequest();
    sigReq.addFile(new File(application.getRealPath("/docs/top_secret_doc.pdf")));
    sigReq.setTitle("Top Secret Document");
    Signer signer = new Signer(myEmail, myName);
    
    // Assign a secret pin
    Random rand = new Random();
    String secretPin = "";
    for(int x = 0 ; x < Signer.PIN_LENGTH ; x++) {
    	secretPin += (new Integer(rand.nextInt(10))).toString();
    }
    signer.setPin(secretPin);
    sigReq.addSigner(signer);
    sigReq.setSubject("Hey " + myName + ", sign this secret document");
    sigReq.setMessage("You should have received a SMS from Twilio with the code to sign this.");
    sigReq.setTestMode(true);
    sigReq.setRedirectUrl(twilioRedirectUrl);

    // Send it to HelloSign
    HelloSignClient client = new HelloSignClient(apiKey);
    SignatureRequest clientResponse = client.sendSignatureRequest(sigReq);
</pre>
                                    </li>
                                    <li><b>3.</b>&nbsp;<b>Server-side</b>: Send out the signer PIN via twilio<br />
<pre class="code-render prettyprint">
    // Now have Twilio send out the secret code
    TwilioRestClient twilioClient = new TwilioRestClient(twilioSid, twilioToken);
    
    // Build a filter for the SmsList
    Map<String, String> params = new HashMap<String, String>();
    params.put("Body", "Psst " + myName + ", here's you secret PIN for the document: " + secretPin + ". Now check your email for the link");
    params.put("To", "+1" + myPhone);
    params.put("From", "+1" + twilioNumber);
 
    SmsFactory messageFactory = twilioClient.getAccount().getSmsFactory();
    Sms message = messageFactory.create(params);
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
                            &copy; <strong>HelloSign, Inc.</strong>, 2014. All rights reserved.
                        </p>
                        <div class="clearfix"></div>
                    </div><br>
                </div>
            </div>
        </div>
        <script type="text/javascript">
        $(document).ready(function(){
        	$('#demoContainer form').submit(function(e) {
        		if(window.form_submitted) {
        			e.preventDefault();
        			return false;
        		}
        		
        		var regEx = new RegExp("[0-9]{10}");
        		var phone = $(this).find('#phone').val();
        		if(!phone || !regEx.test(phone)) {
                    $("#message").html('Invalid phone number. Just use 10 digits');
                    e.preventDefault();
                    return false;
        		}
        		
        		window.form_submitted = true;
        		var btn = $(this).find('input[type=submit]');
        		btn.attr('disabled',true);
        		btn.val('Processing...');
        		btn.css('opacity',0.75);
        		return true;
        	});
        });
        </script>
    </body>
</html>