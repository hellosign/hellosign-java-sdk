package com.hellosign.sdk;

import com.hellosign.sdk.resource.TemplateDraft;

public class Test {

    public static void main(String[] args) throws Exception {
        HelloSignClient client = new HelloSignClient(System.getProperty("hellosign.api.key"));
        TemplateDraft newTemplate = new TemplateDraft();
        newTemplate.addFileUrl("http://www.orimi.com/pdf-test.pdf");
        newTemplate.setSubject("Part-time Model Contract");
        newTemplate.setMessage("You too could be a part-time model!");
        client.updateTemplateFiles("02ff71f35ba31d92f367021d208b49b54e4ef6ee", newTemplate, null);
    }

}
