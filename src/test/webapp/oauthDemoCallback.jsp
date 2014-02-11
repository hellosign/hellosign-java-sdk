<%@page import="com.hellosign.sdk.HelloSignException"%>
<%@page import="com.hellosign.sdk.resource.support.OauthData"%>
<%@page import="com.hellosign.sdk.HelloSignClient"%>
<%@page import="java.util.Properties"%>
<%
	String code = request.getParameter("code");
	if (code != null) {
		// Load authentication properties
		Properties properties = new Properties();
		properties.load(getServletContext().getResourceAsStream("/WEB-INF/web.properties"));
		String apiKey = properties.getProperty("hellosign.api.key");
		String clientId = properties.getProperty("client.id");
		String clientSecret = properties.getProperty("client.secret");

		try {

			// Instantiate a client
			HelloSignClient client = new HelloSignClient(apiKey);

			// Using the code sent by HelloSign, ask for an access token
			OauthData data = client.getOauthData(code, clientId, clientSecret);

			// Store the token to the user's session
			session.setAttribute("hellosign_oauth", data);

		} catch (HelloSignException ex) {
			out.write(ex.getMessage());
		}
	}
%>
<html>
    <head>
        <title>OAuth Callback Demo | HelloSign</title>
    </head>
    <body>
        <script type='text/javascript'>
        	window.close();
        </script>
<h1>Page Source:</h1>
<p style="font-family:Monaco;font-size:11px;">
&lt;%@page import="com.hellosign.sdk.HelloSignException"%&gt;<br />
&lt;%@page import="com.hellosign.sdk.resource.support.OauthData"%&gt;<br />
&lt;%@page import="com.hellosign.sdk.HelloSignClient"%&gt;<br />
&lt;%@page import="java.util.Properties"%&gt;<br />
&lt;%<br /><br />
&nbsp;&nbsp;&nbsp;&nbsp;String code = request.getParameter("code");<br />
<br />
&nbsp;&nbsp;&nbsp;&nbsp;if (code != null) {<br />
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;// Load authentication properties<br />
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Properties properties = new Properties();<br />
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;properties.load(getServletContext().getResourceAsStream("/WEB-INF/web.properties"));<br />
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;String apiKey = properties.getProperty("hellosign.api.key");<br />
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;String clientId = properties.getProperty("client.id");<br />
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;String clientSecret = properties.getProperty("client.secret");<br />
<br />
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;try {<br />
<br />
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;// Instantiate a client<br />
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;HelloSignClient client = new HelloSignClient(apiKey);<br />
<br />
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;// Using the code sent by HelloSign, ask for an access token<br />
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;OauthData data = client.getOauthData(<br />
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;request.getParameter("code"), clientId, clientSecret);<br />
<br />
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;// Store the token to the user's session<br />
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;session.setAttribute("hellosign_oauth", data);<br />
<br />
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;} catch (HelloSignException ex) {<br />
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;out.write(ex.getMessage());<br />
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;}<br />
&nbsp;&nbsp;&nbsp;&nbsp;}<br />
%&gt;<br />
&lt;html&gt;<br />
&nbsp;&nbsp;&nbsp;&nbsp;&lt;head&gt;<br />
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&lt;title&gt;OAuth Callback Demo | HelloSign&lt;/title&gt;<br />
&nbsp;&nbsp;&nbsp;&nbsp;&lt;/head&gt;<br />
&nbsp;&nbsp;&nbsp;&nbsp;&lt;body&gt;<br />
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&lt;script type='text/javascript'&gt;<br />
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;window.close();<br />
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&lt;/script&gt;<br />
&nbsp;&nbsp;&nbsp;&nbsp;&lt;/body&gt;<br />
&lt;/html&gt;<br />
</p>
    </body>
</html>