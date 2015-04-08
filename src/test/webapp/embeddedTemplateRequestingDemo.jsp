<%@page
	import="com.hellosign.sdk.*,com.hellosign.sdk.resource.*,com.hellosign.sdk.resource.support.*,java.io.*,java.util.*,org.apache.commons.fileupload.*,org.apache.commons.fileupload.servlet.*,org.apache.commons.fileupload.disk.*,org.apache.commons.io.*,com.hellosign.sdk.resource.support.types.UnclaimedDraftType"%>
<%

// Load authentication properties
Properties properties = new Properties();
properties.load(getServletContext().getResourceAsStream("/WEB-INF/web.properties"));
String apiKey = properties.getProperty("hellosign.api.key");
HelloSignClient client = new HelloSignClient(apiKey);
String clientId = properties.getProperty("client.id");
String myName = properties.getProperty("my.name");
String myEmail = properties.getProperty("my.email");
String claimUrl = "";
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
            UnclaimedDraft draft = (UnclaimedDraft) client.createEmbeddedRequest(embedded);
            claimUrl = draft.getClaimUrl();

        } catch (HelloSignException ex) {
            errorMessage = ex.getMessage();
            ex.printStackTrace();
        }
    }
}
%>
<jsp:include page="/common-jsp/header.jsp">
    <jsp:param name="title" value="Embedded Template Request Demo"/>
    <jsp:param name="description" value="Request signatures for documents based on a
                    HelloSign Template directly from your website. Follow the steps
                    below to add this feature to your Java-based web application." />
    <jsp:param name="errorMessage" value="${errorMessage}"/>
</jsp:include>

	<input type="hidden" name="template" id="templateId" value="" />
	<div id="demoForm">
		<h3>1. Enter the requester's email address</h3>
		<input name="requester_email_address" placeholder="email@example.com"
			type="text" />
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

<jsp:include page="/common-jsp/footer.jsp" />

    <script type="text/javascript" src="/js/embeddedTemplateDemo.js"></script>
	<script type='text/javascript'>
		var templates = [
<%
if (templateList != null) {
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

		$(document).ready(function(){

		    // This function initializes the form behavior and is
            // defined in /js/embeddedTemplateDemo.js
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
                    $("#demoContainer").html("<h3>" + msg + "</h3><a class=\"btn btn-lg btn-success\" href=\"/embeddedTemplateRequestingDemo.jsp\">Try it again</a>");
                }
            });
<%}%>
        });
    </script>
</body>
</html>