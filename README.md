# HelloSign Java SDK

Use the HelloSign Java SDK to get your Java app connected to HelloSign's services in minutes.

## Installing

Download the HelloSign Java SDK JAR and include it in your project classpath:

* [hellosign-java-sdk-3.0.jar](https://github.com/HelloFax/hellosign-java-sdk/raw/master/dist/hellosign-java-sdk-3.0.jar)

Alternatively, you can build the SDK yourself by forking this repository and use Maven to build it:

    mvn clean package -DskipTests

Locate the JAR file in the `target` directory and place it on your project classpath. 

The SDK is built with Java 7 and depends on the SL4J 1.7.5 and JSON v20090211 libraries. If your project does not already include these, you can use the [JAR that includes these dependencies](https://github.com/HelloFax/hellosign-java-sdk/raw/master/dist/hellosign-java-sdk-3.0-jar-with-dependencies.jar).

## Usage

All HelloSign API requests can be made using the `HelloSignClient`. This class must be initialized with your authentication details, such as an [API key](https://www.hellosign.com/home/myAccount/current_tab/integrations#api) (preferred) or website credentials.

```java
HelloSignClient client = new HelloSignClient(apiKey);
```

Or:

```java
HelloSignClient client = new HelloSignClient(emailAddress, password);
```

You can additionally authenticate using an OAuth access token to perform requests on behalf of your users. See the [OAuth demonstration](#enabling-oauth) section below for more information.

### Creating a Signature Request
```java
SignatureRequest request = new SignatureRequest();
request.setTitle("NDA with Acme Co.");
request.setSubject("The NDA we talked about");
request.setMessage("Please sign this NDA and let's discuss.");
request.addSigner("jack@example.com", "Jack");
request.addSigner("jill@example.com", "Jill");
request.addCC("lawyer@hellosign.com");
request.addFile(new File("nda.pdf"));

SignatureRequest response = client.sendSignatureRequest(request);
```

### Retrieving a User's Templates
The HelloSign API provides paged lists in response to requests for user templates and signature requests. These lists are represented as objects that can be iterated upon:

```java
TemplateList templateList = client.getTemplates();
for (Template template : templateList) {
    System.out.println(template.getTitle());
}
```

The paged list can also be filtered by a particular parameter and value:

```java
TemplateList templateList = client.getTemplates();
List<Template> filteredList = templateList.filterCurrentPageBy(Template.TEMPLATE_TITLE, "W-2 for 2014");
for (Template template : filteredList) {
    System.out.println(template.getTitle());
}
```

### Creating a Signature Request from a Template
```java
TemplateSignatureRequest request = new TemplateSignatureRequest();
request.setTemplateId(template.getId());
request.setSubject("Purchase Order");
request.setMessage("Glad we could come to an agreement.");
request.setSigner("Client", "george@example.com", "George");
request.setCC("Accounting", "accounting@hellosign.com");
request.addCustomFieldValue("Cost", "$20,000");

SignatureRequest response = client.sendTemplateSignatureRequest(request);
```

### Checking the Status of a Signature Request

``` java
SignatureRequest response = client.getSignatureRequest(signatureRequestId);
if (response.isComplete()) {
    System.out.println("All signers have signed this request.");
} else {
    for (Signature signature : response.getSignatures()) {
        System.out.println(signature.getStatusString());
    }
}
```

### Handling HelloSign Events
The [Event](src/main/java/com/hellosign/sdk/resource/Event.java) class simplifies handling HelloSign callback messages. A sample servlet is provided in the test code to listen for HelloSign events and you can see it in action by following a few configuration steps:

1. Copy the file `src/test/webapp/WEB-INF/web.properties.sample` to `src/test/webapp/WEB-INF/web.properties`.
1. Edit `web.properties` and enter your [API key](https://www.hellosign.com/home/myAccount/current_tab/integrations#api) in the "hellosign.api.key" property, and your [Client ID and Client Secret](https://www.hellosign.com/oauth/createAppForm) in the "hellosign.client.id" and "hellosign.client.secret" properties.
1. Run the servlet by executing the "mvn jetty:run" command from the project directory:

    ```
    hellosign-java-sdk $ mvn jetty:run
    [INFO] Scanning for projects...
    [INFO]
    [INFO] ------------------------------------------------------------------------
    [INFO] Building HelloSign Java SDK 1.0
    [INFO] ------------------------------------------------------------------------
       ...
    [INFO] Started Jetty Server
    ```

1. Verify the server is accessible from your local machine by opening a web browser to [http://localhost:8080]().
1. Configure the server so that it is accessible from HelloSign (i.e., the internet). 
1. Set your HelloSign account's callback URL via the API, either with the `HelloSignClient.setCallbackUrl(String)` method or by executing a [curl](http://www.hellosign.com/api/gettingStarted#RetrievingSignedDocuments) request from the command line. The callback URL should be set to `http://[your_server]:8080/hello`, where "your_server" is the Internet-accessible IP or hostname of your server. 

The HelloSign servers can POST events to the URL you provided and the server will log them to the console like so:

    Event received:
        Account ID: e61177c601ae53e940532b6e8c2da0ebf36023a4
        Date: Jan 16, 2014 10:33:16 AM
        Type: signature_request_signed
        Signature Request: 31031c8b25646ff0dab12833f2a420d514633957
        Signature ID: 6e980b82f444a11e051643388b9e003bf5824ac1

### Creating an Embedded Signature Request
This project contains sample JSPs that demonstrate adding embedded signing capabilities to your Java-based web application. To see these demos in action, deploy them to Jetty using Maven by following the instructions above for [Handling HelloSign Events](#handling-hellosign-events). Once your Jetty server is running, navigate to [http://localhost:8080/](http://localhost:8080/). The landing page contains links to API documentation and available demos that describe the steps required to add embedded similar behavior in your site.

### Enabling OAuth
This project contains two sample JSPs that demonstrate how to add OAuth to your Java-based web application. This allows your application to perform HelloSign requests on behalf of your users, using their HelloSign accounts. Like the samples above, configure and deploy them to Jetty by following the instructions for [Handling HelloSign Events](#handling-hellosign-events). Then navigate to the demo index page at [http://localhost:8080/](http://localhost:8080/) and click on the "OAuth Demo" link.

In order for the demonstration to work completely, HelloSign must be able to redirect the user to the callback URL provided in the [Application setup](https://www.hellosign.com/oauth/createAppForm). This must be a non-localhost hostname, e.g., `http://[your_server]:8080/oauthDemoCallback.jsp`.

## Testing
This project contains JUnit tests that exercise the SDK code and provide examples of how to use library classes. Most are functional and integrated tests that walk through real user scenarios. In some cases, this means you must have an active network connection with access to HelloSign to execute all tests.

*NOTE: In general, we advise running these tests against your personal account as they may perform destructive actions.*

To run the tests, first configure your environment:

1. Copy the file: `/src/test/resources/test.properties.sample` to `/src/test/resources/test.properties`.
1. Edit the new file and enter your [API key](https://www.hellosign.com/home/myAccount/current_tab/integrations#api) and your [Client ID](https://www.hellosign.com/oauth/createAppForm), as well as a callback URL. (For testing, any value will do, but it helps to set this to a valid callback URL that HelloSign can use to send events.).
1. Run: `mvn test`


## License
The MIT License (MIT)

Copyright (C) 2014 hellosign.com

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.