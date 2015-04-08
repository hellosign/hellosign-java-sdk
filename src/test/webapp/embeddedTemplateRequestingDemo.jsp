<%@page
	import="com.hellosign.sdk.resource.support.types.UnclaimedDraftType"%>
<%@page
	import="com.hellosign.sdk.*,com.hellosign.sdk.resource.*,com.hellosign.sdk.resource.support.*,java.io.*,java.util.*,org.apache.commons.fileupload.*,org.apache.commons.fileupload.servlet.*,org.apache.commons.fileupload.disk.*,org.apache.commons.io.*"%>
<%
    // Load authentication properties
Properties properties = new Properties();
properties.load(getServletContext().getResourceAsStream("/WEB-INF/web.properties"));
String apiKey = properties.getProperty("hellosign.api.key");
String clientId = properties.getProperty("client.id");
String myName = properties.getProperty("my.name");
String myEmail = properties.getProperty("my.email");
String claimUrl = "";
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
    String requesterEmail = null;

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
                else if ("requester_email_address".equals(item.getFieldName())) {
                    requesterEmail = item.getString();
                }
            }
        }
    } catch (Exception e) {
        errorMessage = e.getMessage();
        e.printStackTrace();
    }

    // If we have a template ID, let's try to create the embedded request
    if (templateId != null && requesterEmail != null) {

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

            // Next create an Unclaimed Draft to hold it
            UnclaimedDraft ucd = new UnclaimedDraft(sigReq);
            ucd.setType(UnclaimedDraftType.request_signature);
            ucd.setRequesterEmail(requesterEmail);

            // Second, create an embedded request with it
            EmbeddedRequest embedded = new EmbeddedRequest(clientId, ucd);

            // Send it to HelloSign
            HelloSignClient client = new HelloSignClient(apiKey);
            UnclaimedDraft draft = (UnclaimedDraft) client.createEmbeddedRequest(embedded);
            claimUrl = draft.getClaimUrl();

        } catch (HelloSignException ex) {
            errorMessage = ex.getMessage();
            ex.printStackTrace();
        }
    }
}
%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<title>Embedded Template Request Demo | HelloSign</title>

<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">

<!-- Optional theme -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap-theme.min.css">

<link rel="stylesheet" href="/css/style.css" />

<link rel="shortcut icon" href="/favicon.ico?v=2" />
<link rel="apple-touch-icon"
	href="/apple-touch-icon-precomposed.png?v=2" />

<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>

	<div class="container">

		<!-- Static navbar -->
		<nav class="navbar navbar-default">
			<div class="container-fluid">
				<div class="navbar-header">
					<button type="button" class="navbar-toggle collapsed"
						data-toggle="collapse" data-target="#navbar" aria-expanded="false"
						aria-controls="navbar">
						<span class="sr-only">Toggle navigation</span> <span
							class="icon-bar"></span> <span class="icon-bar"></span> <span
							class="icon-bar"></span>
					</button>
					<a class="navbar-brand" href="/">hellosign-java-sdk Demos</a>
				</div>
				<div id="navbar"
					class="navbar-collapse collapse nav nav-pills pull-right">
					<ul class="nav navbar-nav">
						<li class="dropdown"><a href="#" class="dropdown-toggle"
							data-toggle="dropdown" role="button" aria-expanded="false">Demos
								<span class="caret"></span>
						</a>
							<ul class="dropdown-menu" role="menu">
								<li class="active"><a
									href="/embeddedTemplateRequestingDemo.jsp">Embedded
										Template Request Demo</a></li>
							</ul></li>
						<li><a href="https://www.hellosign.com/api/documentation"
							target="_blank">Docs</a></li>
						<li><a href="https://www.hellosign.com/api/pricing"
							target="_blank">Pricing</a></li>
						<li><a
							href="https://www.github.com/hellofax/hellosign-java-sdk"
							target="_blank">GitHub</a></li>
					</ul>
				</div>
				<!--/.nav-collapse -->
			</div>
			<!--/.container-fluid -->
		</nav>
	</div>

	<div class="container">
		<div class="clearfix">
			<div class="jumbotron">
				<h2>Embedded Template Request Demo</h2>
				<p class="lead">Request signatures for documents based on a
					HelloSign Template directly from your website. Follow the steps
					below to add this feature to your Java-based web application.</p>
				<div id="demoContainer">
					<div class="error-message"><%= (errorMessage != null ? errorMessage : "") %></div>
                    <form method="post" enctype="multipart/form-data">
                        <input type="hidden" name="template" id="templateId" value="" />
                        <div id="demoForm">
                            <h3>1. Enter the requester's email address</h3>
                            <input name="requester_email_address"
								placeholder="email@example.com" type="text" />
							<h3>2. Select a Template</h3>
							<select id="templates">
								<option selected>Choose a template...</option>
							</select> <em>(Create templates <a href="/embeddedTemplateDemo.jsp">here</a>)
							</em>
							<div id="templateFields">
								<h3>Template Details</h3>
								<fieldset id="signers">
									<legend>Signers</legend>
									<div id="signersContainer">(None)</div>
								</fieldset>
								<fieldset id="ccs">
									<legend>Carbon Copy</legend>
									<div id="ccsContainer">(None)</div>
								</fieldset>
								<fieldset id="customFields">
									<legend>Custom Fields</legend>
									<div id="customFieldsContainer">(None)</div>
								</fieldset>
							</div>
						</div>
						<input class="btn btn-lg btn-primary" id="startButton"
							type="submit" value="Launch Demo" />
					</form>
				</div>
			</div>

			<footer class="footer">
				<p>&copy; HelloSign 2015</p>
			</footer>
		</div>
	</div>

	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
	<!-- Include all compiled plugins (below), or include individual files as needed -->
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
	<!-- Include the HelloSign embedded.js library -->
	<%
        if (isLocalDev) {
    %>
	<script type="text/javascript"
		src="//www.my.hellosign.com/js/embedded.js"></script>
	<%
        } else if (isStaging) {
    %>
	<script type="text/javascript"
		src="//staging.hellosign.com/js/embedded.js"></script>
	<%
        } else {
    %>
	<script type="text/javascript"
		src="//s3.amazonaws.com/cdn.hellofax.com/js/embedded.js"></script>
	<%
        }
    %>
	<script type='text/javascript'>
        function initTemplates() {
            var templates = [
<%if(templateList != null) {
    Iterator<Template> it = templateList.iterator();
    while(it != null && it.hasNext()) {
        Template t = it.next();
        out.write(t.toString());
        if (it.hasNext()) {
            out.write(",");
        }
    }
}%>
            ];
            var templateFields = $("#templateFields");
            templateFields.hide();
            $('#startButton').hide();
            var selectList = $("#templates");
            for (var i = 0; i < templates.length; i++) {
                selectList.append('<option value="' + i + '">' + templates[i].title + '</option>');
            }
            selectList.change(function(e) {
                templateFields.show();
                $('#signersContainer').text('(None)');
                $('#ccsContainer').text('(None)');
                $('#customFieldsContainer').text('(None)');
                var template = templates[$("option:selected", this).attr("value")];
                if (template) {
                    if (template.signer_roles.length > 0) {
                        $('#signersContainer').empty();
                    }
                    for (var i = 0; i < template.signer_roles.length; i++) {
                        var signerRole = template.signer_roles[i];
                        var newOptionStr = '<label for="signerRole_' + signerRole.name + '">' + (signerRole.order != null ? signerRole.order : '') +
                            signerRole.name + ':</label>&nbsp;<input type="text" name="signerRole_email_' + signerRole.name + '" placeholder="Email address"/> ' +
                            '<input type="text" name="signerRole_name_' + signerRole.name + '" placeholder="Name"/><br />';
                        var newSignerFields = $(newOptionStr);
                        $('#signersContainer').append(newSignerFields);
                    }
                    if (template.cc_roles.length > 0) {
                        $('#ccsContainer').text('(None)');
                    }
                    for (var i = 0; i < template.cc_roles.length; i++) {
                        var ccRole = template.cc_roles[i].name;
                        var newCCFieldStr = '<label for="ccRole_' + ccRole + '">' + ccRole + ':</label>&nbsp;<input type="text" name="ccRole_' + ccRole + '" placeholder="Email address"/><br />'
                        var newCCFields = $(newCCFieldStr);
                        $('#ccsContainer').append(newCCFields);
                    }
                    if (template.custom_fields.length > 0) {
                        $('#customFieldsContainer').text('(None)');
                    }
                    for (var i = 0; i < template.custom_fields.length; i++) {
                        var cf = template.custom_fields[i];
                        var newCFFieldStr = '<label for="cf_' + cf.name + '">' + cf.name + ':</label>&nbsp;<input type="text" name="cf_' + cf.name + '" placeholder="' + cf.type + '"/><br />';
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
            initTemplates();
<%if (!claimUrl.isEmpty()) {%>
            // Initialize HelloSign with the client ID
            HelloSign.init("<%=clientId%>");

            // Open the iFrame dialog for embedded signing
            HelloSign.open({
                url: "<%=claimUrl%>",
                debug : true,
                allowCancel : true,
                skipDomainVerification : true,
                messageListener : function(eventData) {
                    console.log("Event received: " + eventData);
                    var msg;
                    if (eventData.event == HelloSign.EVENT_SENT) {
                        msg = "Request Sent!";
                    } else if (eventData.event == HelloSign.EVENT_SIGNED) {
                        msg = "Request Signed!";
                    } else {
                        msg = eventData.event;
                    }
                    $("#demoContainer")
                            .html("<h3>" + msg + "</h3><a class=\"btn btn-lg btn-success\" href=\"/embeddedTemplateRequestingDemo.jsp\">Try it again</a>");
                }
            });
    <%}%>
        });
    </script>
</body>
</html>