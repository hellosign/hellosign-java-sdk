<%@ page import="com.hellosign.sdk.*,com.hellosign.sdk.resource.*,com.hellosign.sdk.resource.support.*,java.io.*,java.util.*,org.apache.commons.fileupload.*,org.apache.commons.fileupload.servlet.*,org.apache.commons.fileupload.disk.*,org.apache.commons.io.*" %>
<%
// Load authentication properties
Properties properties = new Properties();
properties.load(getServletContext().getResourceAsStream("/WEB-INF/web.properties"));
String apiKey = properties.getProperty("hellosign.api.key");
String clientId = properties.getProperty("client.id");
String myName = properties.getProperty("my.name");
String myEmail = properties.getProperty("my.email");
String signUrl = "";
String env = System.getProperty("hellosign.env");
boolean isLocalDev = "dev".equalsIgnoreCase(env);
boolean isStaging = "staging".equalsIgnoreCase(env);
String errorMessage = null;

//Get the user's templates to populate the form
TemplateList templateList = null;
if (apiKey != null) {
	try {
	    HelloSignClient client = new HelloSignClient(apiKey);
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

        	// First, create a templated request
            TemplateSignatureRequest sigReq = new TemplateSignatureRequest();
            sigReq.setTestMode(true);
            System.out.println("Using templateId: " + templateId);
            sigReq.setTemplateId(templateId);
            for (String role : signersList.keySet()) {
            	Signer s = signersList.get(role);
            	sigReq.setSigner(role, s.getEmail(), s.getNameOrRole());
            }
            for (String role : ccList.keySet()) {
            	sigReq.setCC(role, ccList.get(role));
            }
            for (String fieldName : customFieldList.keySet()) {
            	sigReq.setCustomFieldValue(fieldName, customFieldList.get(fieldName));
            }

            // Second, create an embedded request with it
            EmbeddedRequest embedded = new EmbeddedRequest(clientId, sigReq);

            // Send it to HelloSign
            HelloSignClient client = new HelloSignClient(apiKey);
            SignatureRequest newSigReq = (SignatureRequest) client.createEmbeddedRequest(embedded);

            // Retrieve the first Signature ID
            // (for demo purposes we'll just demonstrate embedding the first signature request)
            Signature sig = newSigReq.getSignatures().get(0);

            // Retrieve the embedded signing URL for the signature
            EmbeddedResponse embeddedResponse = client.getEmbeddedSignUrl(sig.getId());

            // Use the sign URL to open the embedded signing page
            signUrl = embeddedResponse.getSignUrl();

        } catch (HelloSignException ex) {
        	errorMessage = ex.getMessage();
            ex.printStackTrace();
        }
    }
}
%>
<html>
    <head>
        <title>Embedded Template Signing Demo | HelloSign</title>
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
        <link rel="stylesheet" type="text/css" media="screen" href="/css/demos.css" />
        <script type="text/javascript" src="/js/init.js"></script>

        <script type="text/javascript" src="/js/prettify.js"></script>
        <link rel="stylesheet" type="text/css" media="screen" href="/css/hs/main.css" />
        <script type="text/javascript" src="/js/main.js" ></script>

        <link rel="shortcut icon" href="/favicon.ico" />
        <link rel="apple-touch-icon" href="/apple-touch-icon-precomposed.png" />
        <style>
            html { background-color: white !important; }
            pre { font-family: Monaco; font-size: 13px; margin-left: 15px; margin-top: 5px; }
            input#startButton { padding: 10px 20px; height: 40px; font-size: 16px; }
            input[type='file'] { padding: 5px; border: 1px solid #EEE; clear:both; }
            label { display: block; margin: 4px; margin-top: 10px;}
            button#addFile { margin-left: 10px;}
            button#addSigner { margin-left: 10px;}
            div#files { margin-bottom: 5px;}
            div#signers { margin-bottom:5px;}
            div#fileContainer { width: 400px;}
            div#demoForm {border: 1px solid gray; padding: 20px;}
            fieldset {
            	border: 1px solid gray;
            	padding: 10px;
            	margin: 4px;
            	-webkit-box-shadow: none;
            	-moz-box-shadow: none;
            	box-shadow: none;
            	-webkit-border-radius: 3px;
            	-khtml-border-radius: 3px;
            	-moz-border-radius: 3px;
            	border-radius: 3px;
            }
            div#message { margin: 10px; color: red; }
        </style>
        <script type='text/javascript'>
        	function initTemplates() {
        		var templates = [
<%
if(templateList != null) {
	Iterator<Template> it = templateList.iterator();
	while(it != null && it.hasNext()) {
		Template t = it.next();
		out.write(t.toString());
		if (it.hasNext()) {
			out.write(",");
		}
	}
}
%>
        		];
        		var templateFields = $("#templateFields");
        		templateFields.hide();
        		$("#startButton").hide();
        		var selectList = $("#templates");
        		for (var i = 0; i < templates.length; i++) {
        			selectList.append('<option value="' + i + '">' + templates[i].title + '</option>');
        		}
        		selectList.change(function(e) {
        			templateFields.show();
        			$('#signersContainer').empty();
        			$('#ccsContainer').empty();
        			$('#customFieldsContainer').empty();
        			var template = templates[$("option:selected", this).attr("value")];
        			if (template) {
	        			for (var i = 0; i < template.signer_roles.length; i++) {
	        				var signerRole = template.signer_roles[i];
	        				var newOptionStr = '<label for="signerRole_' + signerRole.name + '">' + (signerRole.order != null ? signerRole.order : '') +
	    						signerRole.name + ':</label>&nbsp;<input type="text" name="signerRole_email_' + signerRole.name + '" placeholder="Email address"/> ' +
	    						'<input type="text" name="signerRole_name_' + signerRole.name + '" placeholder="Name"/><br />';
	    					var newSignerFields = $(newOptionStr);
	        				$('#signersContainer').append(newSignerFields);
	        			}
	        			for (var i = 0; i < template.cc_roles.length; i++) {
	        				var ccRole = template.cc_roles[i].name;
	        				var newCCFieldStr = '<label for="ccRole_' + ccRole + '">' + ccRole + ':</label>&nbsp;<input type="text" name="ccRole_' +
	        					ccRole + '" placeholder="Email address"/><br />'
	        				var newCCFields = $(newCCFieldStr);
	       					$('#ccsContainer').append(newCCFields);
	        			}
	        			for (var i = 0; i < template.custom_fields.length; i++) {
	        				var cf = template.custom_fields[i];
	        				var newCFFieldStr = '<label for="cf_' + cf.name + '">' + cf.name + ':</label>&nbsp;<input type="text" name="cf_' + cf.name + '" placeholder="' +
	        					cf.type + '"/><br />';
	        				var newCFField = $(newCFFieldStr);
	        				$('#customFieldsContainer').append(newCFField);
	        			}
	        			$('#templateId').val(template.template_id);
	        			$('#startButton').show();
        			} else {
        				$('#templateFields').hide();
        				$('#startButton').hide();
        			}
        		});
        	};
            $(document).ready(function(){
                initEmbeddedDemo();
                initTemplates();
<% if (!signUrl.isEmpty()) { %>
                // Initialize HelloSign with the client ID
                HelloSign.init("<%= clientId %>");

                // Open the iFrame dialog for embedded signing
                HelloSign.open({
                    url: "<%= signUrl %>",
                    debug: true,
                    allowCancel: true,
                    skipDomainVerification: true,
                    messageListener: function(eventData) {
						console.log("Event received: " + eventData);
						var msg;
                        if (eventData.event == HelloSign.EVENT_SENT) {
                        	msg = "Request Sent!";
                        } else if (eventData.event == HelloSign.EVENT_SIGNED) {
                        	msg = "Request Signed!";
                        } else {
                        	msg = eventData.event;
                        }
                        $("#demoContainer").html(msg + "<br /><a href=\"/embeddedTemplateSigningDemo.jsp\">Try it again</a>");
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
                	<div class="sub-nav"><a href="/">Index</a> <span class="rsaquo">&rsaquo;</span> Embedded Template Request Demo</div>
                    <h2 class="page-title default headline">Embedded Template Request Demo</h2>
                    <div class="embeddedSigning bs_container">
                        <p class="intro">
                            Request signatures for documents based on a HelloSign Template directly from your website. Follow the steps below to add this feature to your Java-based web application.
                        </p>
                        <br />
                        <div class="row">
                            <div class="span12">
                                <h2>Before You Begin</h2>
                                <ul>
                                    <li><b>1.</b>&nbsp;Obtain an API key.<br />Sign up for an API plan <a href="https://www.hellosign.com/api/pricing">here</a>. Adding embedded signing to your website requires a Silver or Gold API plan. However, you can test the functionality for free by creating signature requests in test mode.</li>
                                    <li><b>2.</b>&nbsp;Obtain a Client ID.<br />Sign up for a Client ID for your application <a href="https://www.hellosign.com/oauth/createAppForm">here</a>.</li>
                                    <li><b>3.</b>&nbsp;Set the API key and Client ID in the properties file:<br /><pre class="code-render prettyprint">hellosign-java-sdk/src/main/webapp/WEB-INF/web.properties</pre></li>
                                    <li><b>4.</b>&nbsp;Create a template.<br />Create a template on the HelloSign website <a href="https://www.hellosign.com/home/createReusableDocs">here</a>. Your templates will be retrieved for use in the demo when you load this page.</li>
                                </ul>
                                <br />
                                <h2>Try it Out</h2>
<% if (errorMessage != null) { %>
                                <div id="message"><%= errorMessage %></div>
<% } %>
                                <div id="demoContainer">
                                    <form action="/embeddedTemplateSigningDemo.jsp" method="post" enctype="multipart/form-data">
    									<input type="hidden" name="template" id="templateId" value="" />
                                        <div id="demoForm">
                                            <h3>Your Templates</h3>
                                            <select id="templates">
                                            	<option selected>Choose a template...</option>
                                            </select>
                                            <div id="templateFields">
    	                                        <h3>Template Details</h3>
    	                                        <fieldset id="signers"><legend>Signers</legend><div id="signersContainer"></div></fieldset>
    	                                        <fieldset id="ccs"><legend>Carbon Copy</legend><div id="ccsContainer"></div></fieldset>
    	                                        <fieldset id="customFields"><legend>Custom Fields</legend><div id="customFieldsContainer"></div></fieldset>
                                           	 </div>
                                        </div>
                                        <br />
                                        <input class="btn blue-sub" id="startButton" type="submit" value="Launch Demo" />
                                    </form>
                                </div>
                                <br />
                                <br />
                                <h2>Creating an Embedded Template Signature Request</h2>
                                <p>The steps below demonstrate how to embedded a signature request capability based on a HelloSign template into a JSP web page. See the server-side source of this file for the code used in this example.</p><br />
                                <ul>
                                    <li><b>1.</b>&nbsp;Obtain the template to be used. In this example, the JSP retrieves the demo user's templates and makes them available as a select list to choose from.</li>
                                    <li><b>2.</b>&nbsp;<b>Server-side</b>: Import the packages for hellosign-java-sdk and its dependencies.<br /><pre class="code-render prettyprint">&lt;%@ page import="com.hellosign.sdk.*,com.hellosign.sdk.resource.*,com.hellosign.sdk.resource.support.*,java.io.*,java.util.*,org.apache.commons.fileupload.*,org.apache.commons.fileupload.servlet.*,org.apache.commons.fileupload.disk.*,org.apache.commons.io.*" %&gt;</pre></li>
                                    <li><b>3.</b>&nbsp;<b>Server-side</b>: Obtain the information provided by the user in Step 1 and create an Unclaimed Draft.<br />
<pre class="code-render prettyprint">
    UnclaimedDraft draft = new UnclaimedDraft();
    draft.setTestMode(true);
    draft.setIsForEmbeddedSigning(true);
    draft.setClientId(clientId);
    draft.setSubject(subject);
    draft.setMessage(message);
    draft.addSigner(signerEmail, signerName);
    draft.setRequesterEmail(myEmail);
    draft.addFile(file);
</pre>
                                    </li>
                                    <li><b>4.</b>&nbsp;<b>Server-side</b>: Retrieve the URL for the unclaimed draft.<br />
<pre class="code-render prettyprint">
    HelloSignClient client = new HelloSignClient(apiKey);
    UnclaimedDraft responseDraft = client.createUnclaimedDraft(draft);
    signUrl = responseDraft.getClaimUrl();
</pre>
                                    </li>
                                    <li><b>5.</b>&nbsp;<b>Client-side</b>: Include "embedded.js".<br /><pre class="code-render prettyprint">&lt;script type="text/javascript" src="//s3.amazonaws.com/cdn.hellofax.com/js/embedded.js"&gt;&lt;/script&gt;</pre></li>
                                    <li><b>6.</b>&nbsp;<b>Client-side</b>: Use the server-generated client ID and URL from Step 4 to initialize the HelloSign JavaScript components. In JSP, this can be done like so:<br />
<pre class="code-render prettyprint">&lt;script type="text/javascript"&gt;
    function openSigningDialog() {
        HelloSign.init("&lt;%= clientId %&gt;");
        HelloSign.open({
            skipDomainVerification: true,
            url: "&lt;%= embeddedResponse.getSignUrl() %&gt;"
        });
    }
&lt;/script&gt;</pre>
                                </ul>
                                <br />
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