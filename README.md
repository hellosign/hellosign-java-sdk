# HelloSign Java SDK 
[![Build Status](https://travis-ci.org/HelloFax/hellosign-java-sdk.svg?branch=master)](https://travis-ci.org/HelloFax/hellosign-java-sdk) [![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.hellosign/hellosign-java-sdk/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.hellosign/hellosign-java-sdk/) [![Javadoc](https://javadoc-emblem.rhcloud.com/doc/com.hellosign/hellosign-java-sdk/badge.svg)](http://www.javadoc.io/doc/com.hellosign/hellosign-java-sdk/)

Get your Java app connected to HelloSign's API in jiffy.

## Installing

SDK releases are published to Maven's [Central repository](https://repo1.maven.org/maven2/com/hellosign/hellosign-java-sdk/):

```xml
<dependency>
  <groupId>com.hellosign</groupId>
  <artifactId>hellosign-java-sdk</artifactId>
  <version>RELEASE</version>
  <classifier>jar-with-dependencies</classifier>
</dependency>
```

Alternatively, you can build the JAR yourself:

    mvn clean package

Place `target/hellosign-java-sdk-<VERSION>.jar` on your project classpath.

## Usage

First initialize an instance of the `HelloSignClient` with your [API key](https://www.hellosign.com/home/myAccount/current_tab/integrations#api):

```java
HelloSignClient client = new HelloSignClient(apiKey);
```

### Create a Signature Request

```java
SignatureRequest request = new SignatureRequest();
request.setSubject("NDA");
request.setMessage("Hi Jack, Please sign this NDA and let's discuss.");
request.addSigner("jack@example.com", "Jack");
request.addFile(new File("nda.pdf"));

SignatureRequest response = client.sendSignatureRequest(request);
System.out.println(response.toString());
```

### Retrieve Templates

```java
TemplateList templateList = client.getTemplates();
for (Template template : templateList) {
    System.out.println(template.getTitle());
}
```

Or filter the paged list:

```java
TemplateList templateList = client.getTemplates();
List<Template> filteredList = templateList.filterCurrentPageBy(Template.TEMPLATE_TITLE, "W-2 Template");
for (Template template : filteredList) {
    System.out.println(template.getTitle());
}
```

### Create a Signature Request from a Template

```java
TemplateSignatureRequest request = new TemplateSignatureRequest();
request.setTemplateId(templateId);
request.setSigner("Client", "george@example.com", "George");
request.setCC("Accounting", "accounting@hellosign.com");
request.addCustomFieldValue("Cost", "$20,000");

SignatureRequest response = client.sendTemplateSignatureRequest(request);
System.out.println(response.toString());
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

* [API Reference](http://www.javadoc.io/doc/com.hellosign/hellosign-java-sdk)
* [Sample JSP web application](https://www.github.com/cmpaul/jellosign)

## License

```
The MIT License (MIT)

Copyright (C) 2015 hellosign.com

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
