# HelloSign Java SDK

Use the `hellosign-java-sdk` to get your Java app connected to HelloSign's API in minutes.

## Installing

The SDK is built and deployed to the [Central Maven repository](http://search.maven.org/#browse%7C-564959052). Add it to your Maven project by including the following `<dependency>` in your `pom.xml`:

```xml
<dependency>
  <groupId>com.hellosign</groupId>
  <artifactId>hellosign-java-sdk</artifactId>
  <version>RELEASE</version>
  <classifier>jar-with-dependencies</classifier>
</dependency>
```

> NOTE: It is compiled with and targeted for Java 7 and depends on the [SL4J 1.7.5](http://www.slf4j.org/) and JSON v20090211 libraries. If your project already includes these, use the JAR without dependencies by removing the `<classifier>` element in the example above.

Alternatively, you can build the JAR yourself:

    mvn clean package -DskipTests

Locate the JAR file in the `target` directory and place it on your project classpath.

## Usage

All HelloSign API requests are made using the `HelloSignClient`. This class must be initialized with your [API key](https://www.hellosign.com/home/myAccount/current_tab/integrations#api).
```java
HelloSignClient client = new HelloSignClient(apiKey);
```
The following examples assume the client has been initialized this way.

### Create a Signature Request
Construct a `SignatureRequest` object and populate it with request details. When you provide this object to the `HelloSignClient.sendSignatureRequest()` method, an HTTP request will be made and the method will return a `SignatureRequest` object. Use this object to read details about the new signature request.
```java
SignatureRequest request = new SignatureRequest();
request.setSubject("NDA");
request.setMessage("Hi Jack, Please sign this NDA and let's discuss.");
request.addSigner("jack@example.com", "Jack");
request.addFile(new File("nda.pdf"));

SignatureRequest response = client.sendSignatureRequest(request);
System.out.println(response.toString());
// Prints the JSON response to the console
```

### Retrieve Templates
The HelloSign API provides paged lists of templates and signature requests (`client.getSignatureRequests()`). These lists are represented as objects that can be iterated upon:

```java
TemplateList templateList = client.getTemplates();
for (Template template : templateList) {
    System.out.println(template.getTitle());
}
```

The paged list can also be filtered by a particular parameter and value:

```java
TemplateList templateList = client.getTemplates();
List<Template> filteredList = templateList.filterCurrentPageBy(Template.TEMPLATE_TITLE, "W-2 Template");
for (Template template : filteredList) {
    System.out.println(template.getTitle());
}
```

### Create a Signature Request from a Template
Using a `template` object retrieved from the API, create a signature request with it:
```java
TemplateSignatureRequest request = new TemplateSignatureRequest();
request.setTemplateId(template.getId());
request.setSigner("Client", "george@example.com", "George");
request.setCC("Accounting", "accounting@hellosign.com");
request.addCustomFieldValue("Cost", "$20,000");

SignatureRequest response = client.sendTemplateSignatureRequest(request);
System.out.println(response.toString());
// Prints the JSON response to the console
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

<!-- We've also built a sample J2EE application that demonstrates how to use the SDK for creating requests, working with embedded flows, and handling callback events:
https://www.github.com/cmpaul/jellosign -->

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
