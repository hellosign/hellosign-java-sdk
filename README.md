# HelloSign Java SDK

Use the HelloSign Java SDK to get your Java app connected to HelloSign's services in minutes.

## Installing

The HelloSign Java SDK is built and deployed to the [Sonatype Nexus Maven repository](https://oss.sonatype.org/#nexus-search;quick~hellosign). To include it, add the following dependency to your `pom.xml`:

```xml
<dependency>
  <groupId>com.hellosign</groupId>
  <artifactId>hellosign-java-sdk</artifactId>
  <version>3.1.3</version>
</dependency>
```

It is compiled with and targeted for Java 7 and depends on the [SL4J 1.7.5](http://www.slf4j.org/) and JSON v20090211 libraries. If your project does not already include these, you can use the JAR that includes these dependencies:

```xml
<dependency>
  <groupId>com.hellosign</groupId>
  <artifactId>hellosign-java-sdk</artifactId>
  <version>3.1.3</version>
  <classifier>jar-with-dependencies</classifier>
</dependency>
```

Alternatively, you can build the JAR yourself:

    mvn clean package -DskipTests

Locate the JAR file in the `target` directory and place it on your project classpath.

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

## Reference

The complete JavaDoc is kindly hosted at [javadoc.io](http://www.javadoc.io/):
http://www.javadoc.io/doc/com.hellosign/hellosign-java-sdk

We've also built a sample J2EE application that demonstrates how to use the SDK for creating requests, working with embedded flows, and handling callback events:
https://www.github.com/cmpaul/jellosign

## License

```
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
```
