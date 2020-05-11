package com.hellosign.sdk;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;

import com.hellosign.sdk.http.Authentication;
import com.hellosign.sdk.http.HttpClient;
import com.hellosign.sdk.resource.AbstractRequest;
import com.hellosign.sdk.resource.Account;
import com.hellosign.sdk.resource.ApiApp;
import com.hellosign.sdk.resource.EmbeddedRequest;
import com.hellosign.sdk.resource.EmbeddedResponse;
import com.hellosign.sdk.resource.SignatureRequest;
import com.hellosign.sdk.resource.Team;
import com.hellosign.sdk.resource.Template;
import com.hellosign.sdk.resource.TemplateDraft;
import com.hellosign.sdk.resource.TemplateSignatureRequest;
import com.hellosign.sdk.resource.UnclaimedDraft;
import com.hellosign.sdk.resource.support.ApiAppList;
import com.hellosign.sdk.resource.support.CustomField;
import com.hellosign.sdk.resource.support.Document;
import com.hellosign.sdk.resource.support.FormField;
import com.hellosign.sdk.resource.support.OauthData;
import com.hellosign.sdk.resource.support.Signature;
import com.hellosign.sdk.resource.support.SignatureRequestList;
import com.hellosign.sdk.resource.support.Signer;
import com.hellosign.sdk.resource.support.TemplateList;
import com.hellosign.sdk.resource.support.TemplateRole;
import com.hellosign.sdk.resource.support.WhiteLabelingOptions;
import com.hellosign.sdk.resource.support.Attachment;
import com.hellosign.sdk.resource.support.types.FieldType;
import com.hellosign.sdk.resource.support.types.UnclaimedDraftType;
import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.Arrays;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;

public class HelloSignClientTest {

    // TODO: Make convenience method to mock response code

    @Rule
    public TestName name = new TestName();

    private HttpClient spy;
    private HelloSignClient client;

    @Before
    public void setup() throws Exception {
        spy = spy(new HttpClient());
        client = new HelloSignClient(spy, new Authentication("testapikey"));

        // Don't actually make HTTP requests
        doReturn(spy).when(spy).post(any(String.class));
        doReturn(spy).when(spy).get(any(String.class));
        doReturn(spy).when(spy).put(any(String.class));
        doReturn(spy).when(spy).delete(any(String.class));
        doReturn(spy).when(spy).options(any(String.class));

        // Return the test case's JSON response instead
        // TODO: Add instructions for autogeneration
        doReturn(getTestFileAsString(name.getMethodName() + ".json")).when(spy).getLastResponse();
    }

    protected String getTestFileAsString(String name) throws FileNotFoundException {
        String result = null;
        String url = System.getProperty("file.separator") + this.getClass().getSimpleName()
            + System.getProperty("file.separator") + name;
        URL resource = this.getClass().getResource(url);
        if (resource != null) {
            Scanner s = new Scanner(new File(resource.getFile()));
            s.useDelimiter("\\Z");
            result = (s.hasNext() ? s.next() : "");
            s.close();
        }
        return result;
    }

    protected File getTestFixture(String name) {
        String path = System.getProperty("file.separator") + this.getClass().getSimpleName()
            + System.getProperty("file.separator") + "Fixtures" + System
            .getProperty("file.separator") + name;
        URL resource = this.getClass().getResource(path);
        return new File(resource.getFile());
    }

    protected void mockResponseCode(int code) {
        doReturn(code).when(spy).getLastResponseCode();
    }

    @After
    public void tearDown() {
        spy = null;
        client = null;
    }

    @Test
    public void testCreateAccount() throws Exception {
        String email = "newuser@hellosign.com";
        Account account = client.createAccount(email);
        assertNotNull(account);
        assertEquals(email, account.getEmail());
    }

    @Test(expected = HelloSignException.class)
    public void testCreateAccountExisting() throws Exception {
        mockResponseCode(400);
        client.createAccount("chris@hellosign.com");
    }

    @Test
    public void testCreateAccountWithOAuthData() throws Exception {
        String email = "newuser1@hellosign.com";
        Account account = client.createAccount(email);
        assertNotNull(account);
        assertEquals(email, account.getEmail());
        OauthData data = account.getOauthData();
        assertNotNull(data);
        assertNotNull(data.getAccessToken());
        assertFalse(data.getAccessToken().isEmpty());
        assertNotNull(data.getTokenType());
        assertFalse(data.getTokenType().isEmpty());
        assertNotNull(data.getRefreshToken());
        assertFalse(data.getRefreshToken().isEmpty());
        assertTrue(data.getExpiresIn() > 0);
    }

    @Test
    public void testGetOauthData() throws Exception {
        String code = "testcode";
        String clientId = "testclientid";
        String secret = "testsecret";
        String state = "teststate";
        OauthData data = client.getOauthData(code, clientId, secret, state, false);
        assertNotNull(data);
        assertNotNull(data.getAccessToken());
        assertFalse(data.getAccessToken().isEmpty());
        assertNotNull(data.getExpiresIn());
        assertTrue(data.getExpiresIn() > 0);
        assertNotNull(data.getRefreshToken());
        assertFalse(data.getRefreshToken().isEmpty());
        assertNotNull(data.getTokenType());
        assertFalse(data.getTokenType().isEmpty());

        // Confirm that NOT auto-saving the access token works
        assertTrue(client.getAuth().getAccessToken().isEmpty());
        assertTrue(client.getAuth().getAccessTokenType().isEmpty());

        // Confirm that auto-saving the access token works
        client.getOauthData(code, clientId, secret, state, true);
        assertFalse(client.getAuth().getAccessToken().isEmpty());
        assertFalse(client.getAuth().getAccessTokenType().isEmpty());
    }

    @Test
    public void testGetAccount() throws Exception {
        Account account = client.getAccount();
        assertNotNull(account);
        assertNotNull(account.getId());
        assertTrue(Pattern.matches("\\A.*@hellosign.com\\z", account.getEmail()));
        assertNull(account.getCallbackUrl()); // not set
        assertNull(account.getOauthData()); // not set
        assertNotNull(account.getQuotas());
        assertNotNull(account.getApiSignatureRequestsLeft());
        assertNull(account.getDocumentsLeft()); // null = unlimited
        assertNotNull(account.getTemplatesLeft());
        assertNotNull(account.getRoleCode());
    }

    @Test
    public void testIsAccountValid() throws Exception {
        assertTrue(client.isAccountValid("chris@hellosign.com"));
    }

    @Test
    public void testIsAccountInvalid() throws Exception {
        assertFalse(client.isAccountValid("zztop@hellosign.com"));
    }

    @Test
    public void testSetAccountCallback() throws Exception {
        String callbackUrl = "https://www.example.com";
        Account account = client.setCallback(callbackUrl);
        assertNotNull(account);
        assertEquals(callbackUrl, account.getCallbackUrl());
    }

    @Test(expected = HelloSignException.class)
    public void testSetAccountCallbackInvalid() throws Exception {
        mockResponseCode(400);
        String callbackUrl = "foo";
        client.setCallback(callbackUrl);
    }

    @Test
    public void testGetTeam() throws Exception {
        Team team = client.getTeam();
        assertNotNull(team);
    }

    @Test(expected = HelloSignException.class)
    public void testGetTeamInvalid() throws Exception {
        mockResponseCode(404);
        client.getTeam();
    }

    @Test
    public void testCreateTeam() throws Exception {
        String teamName = "Team Echevaria";
        Team newTeam = client.createTeam(teamName);
        assertNotNull(newTeam);
        assertEquals(teamName, newTeam.getName());
    }

    @Test(expected = HelloSignException.class)
    public void testCreateTeamInvalid() throws Exception {
        mockResponseCode(400);
        client.createTeam("foo");
    }

    @Test
    public void testInviteTeamMember() throws Exception {
        String email = "chris+2@hellosign.com";
        Team team = client.inviteTeamMember(email);
        boolean emailFound = false;
        for (Account a : team.getInvitedAccounts()) {
            if (email.equals(a.getEmail())) {
                emailFound = true;
                break;
            }
        }
        assertTrue(emailFound);
    }

    @Test(expected = HelloSignException.class)
    public void testInviteTeamMemberInvalid() throws Exception {
        mockResponseCode(400);
        client.inviteTeamMember("foo");
    }

    @Test
    public void testRemoveTeamMember() throws Exception {
        String email = "chris+1@hellosign.com";
        Team team = client.removeTeamMember(email);
        for (Account a : team.getAccounts()) {
            assertNotEquals(email, a.getEmail());
        }
    }

    @Test(expected = HelloSignException.class)
    public void testRemoveTeamMemberInvalid() throws Exception {
        mockResponseCode(404);
        client.removeTeamMember("foo");
    }

    @Test
    public void testUpdateTeamName() throws Exception {
        String name = "Team America";
        Team team = client.updateTeamName(name);
        assertNotNull(team);
        assertEquals(name, team.getName());
    }

    @Test(expected = HelloSignException.class)
    public void testUpdateTeamNameInvalid() throws Exception {
        mockResponseCode(404);
        client.updateTeamName("foo");
    }

    @Test
    public void testDestroyTeam() throws Exception {
        mockResponseCode(200);
        client.destroyTeam();
    }

    @Test(expected = HelloSignException.class)
    public void testDestroyTeamInvalid() throws Exception {
        mockResponseCode(404);
        client.destroyTeam();
    }

    @Test
    public void testGetSignatureRequest() throws Exception {
        String id = "5fd97d3b6a2ac509b7837891d8c804e29cc35636";
        SignatureRequest request = client.getSignatureRequest(id);
        assertNotNull(request);
        assertEquals(id, request.getId());
    }

    @Test(expected = HelloSignException.class)
    public void testGetSignatureRequestInvalid() throws Exception {
        mockResponseCode(404);
        client.getSignatureRequest("foo");
    }

    @Test
    public void testGetSignatureRequests() throws Exception {
        SignatureRequestList list = client.getSignatureRequests();
        for (SignatureRequest s : list) {
            assertNotNull(s);
            assertNotNull(s.getId());
        }
    }

    @Test(expected = HelloSignException.class)
    public void testGetSignatureRequestsInvalid() throws Exception {
        mockResponseCode(401);
        client.getSignatureRequests();
    }

    @Test
    public void testGetSignatureRequestsPage() throws Exception {
        SignatureRequestList list = client.getSignatureRequests(1);
        for (SignatureRequest s : list) {
            assertNotNull(s);
            assertNotNull(s.getId());
        }
    }

    @Test
    public void testGetSignatureRequestsPageInvalid() throws Exception {
        SignatureRequestList list = client.getSignatureRequests(19);
        assertFalse(list.iterator().hasNext());
    }

    @Test
    public void testGetSignatureRequestsPageSize() throws Exception {
        SignatureRequestList list = client.getSignatureRequests(1, 20);
        for (SignatureRequest s : list) {
            assertNotNull(s);
            assertNotNull(s.getId());
        }
    }

    @Test
    public void testGetSignatureRequestsPageSizeInvalid() throws Exception {
        SignatureRequestList list = client.getSignatureRequests(1, 200);
        assertFalse(list.iterator().hasNext());
    }

    @Test
    public void testSendSignatureRequest() throws Exception {
        String subject = "From the Mare";
        String message = "Pls sign";
        SignatureRequest req = new SignatureRequest();
        req.setTestMode(true);
        req.addSigner("charlie@hotmail.com", "Charlie Day");
        req.addFileUrl("http://www.orimi.com/pdf-test.pdf");
        req.setSubject(subject);
        req.setMessage(message);
        SignatureRequest sentReq = client.sendSignatureRequest(req);
        assertNotNull(sentReq);
        assertTrue(sentReq.hasId());
        assertNotNull(sentReq.getSignature("charlie@hotmail.com", "Charlie Day"));
        assertEquals(subject, sentReq.getSubject());
        assertEquals(message, sentReq.getMessage());
        assertTrue(req.isTestMode());
    }

    @Test
    public void testSendSignatureRequestWithAttachment() throws Exception {
        String subject = "From Vaibhavi";
        String message = "Pls sign";
        SignatureRequest req = new SignatureRequest();
        req.setTestMode(true);
        req.addSigner("vaibhavij+stg@hellosign.com", "Vaibhavi Joshi");
        req.addSigner("vaibhavij+stg1@hellosign.com","Vaibhavi");
        req.addFileUrl("http://www.orimi.com/pdf-test.pdf");
        req.setSubject(subject);
        req.setMessage(message);
        Attachment attachment1 = new Attachment("License","Pl provide copy of license.",0,true);
        Attachment attachment2 = new Attachment("California Id","Pl provide copy of California Id.",1,true);
        req.setAttachments(Arrays.asList(attachment1,attachment2));
        System.out.println(req);
        SignatureRequest sentReq = client.sendSignatureRequest(req);

        assertNotNull(sentReq);
        assertTrue(sentReq.hasId());
        assertNotNull(sentReq.getSignature("vaibhavij+stg@hellosign.com", "Vaibhavi Joshi"));
        assertEquals(subject, sentReq.getSubject());
        assertEquals(message, sentReq.getMessage());
        assertTrue(req.isTestMode());

        //sentReq.getResponseData().
        JSONObject responseObject = sentReq.getJSONObject();
        JSONArray attachmentsObject = responseObject.getJSONArray("attachments");
        JSONObject attachmentObj1 = (JSONObject) attachmentsObject.get(0);
        JSONObject attachmentObj2 = (JSONObject) attachmentsObject.get(1);

        // Assert on Signer 1 attachment.
        Assert.assertTrue(attachmentObj1.get("name").equals("License"));
        Assert.assertTrue(attachmentObj1.get("instructions").equals("Pl provide copy of license."));
        Assert.assertTrue(attachmentObj1.get("signer").equals(1));
        Assert.assertTrue(attachmentObj1.get("required").equals(true));

        // Assert on Signer 2 attachment.
        Assert.assertTrue(attachmentObj2.get("name").equals("California Id"));
        Assert.assertTrue(attachmentObj2.get("instructions").equals("Pl provide copy of California Id."));
        Assert.assertTrue(attachmentObj2.get("signer").equals(2));
        Assert.assertTrue(attachmentObj2.get("required").equals(true));
    }


    @Test
    public void testCreateEmbeddedRequestWithAttachment() throws Exception {
        SignatureRequest req = new SignatureRequest();
        req.addFileUrl("http://www.orimi.com/pdf-test.pdf");
        req.addSigner("vaibhavij+stg@hellosign.com", "Chris");
        req.setTestMode(true);
        req.addMetadata("test_key", "test_value");
        Attachment attachment1 = new Attachment("License","Pl provide copy of license.",0,true);
        req.setAttachments(Arrays.asList(attachment1));
        EmbeddedRequest embeddedReq = new EmbeddedRequest("82d8101db9a2147f8e07244f9ca030a6", req);
        Map<String, String> fields = new HashMap<>();
        fields.put("Field A", "Hello");
        fields.put("Field B", "World!");
        fields.put("Checkbox A", "true");
        fields.put("Checkbox B", "false");
        embeddedReq.setCustomFields(fields);
        AbstractRequest newReq = client.createEmbeddedRequest(embeddedReq);
        assertNotNull(newReq);
        assertNotNull(newReq.getId());
        assertEquals(req.getMetadata("test_key"), newReq.getMetadata("test_key"));
        for (CustomField cf : newReq.getCustomFields()) {
            assertEquals(fields.get(cf.getName()), cf.getValue());
        }
        //sentReq.getResponseData().
        JSONObject responseObject = newReq.getJSONObject();
        JSONArray attachmentsObject = responseObject.getJSONArray("attachments");
        JSONObject attachmentObj1 = (JSONObject) attachmentsObject.get(0);

        // Assert on Signer 1 attachment.
        Assert.assertTrue(attachmentObj1.get("name").equals("License"));
        Assert.assertTrue(attachmentObj1.get("instructions").equals("Pl provide copy of license."));
        Assert.assertTrue(attachmentObj1.get("signer").equals(1));
        Assert.assertTrue(attachmentObj1.get("required").equals(true));

    }


    @Test(expected = HelloSignException.class)
    public void testSendSignatureRequestInvalid() throws Exception {
        mockResponseCode(400);
        SignatureRequest req = new SignatureRequest();
        req.setTestMode(true);
        client.sendSignatureRequest(req);
    }

    @Test
    public void testUpdateSignatureRequest() throws Exception {
        String id = "5fd97d3b6a2ac509b7837891d8c804e29cc35636";
        String signatureId = "c390fac7bb23c2b48e6314272ec9db17";
        String newEmailAddress = "barack@obama.com";
        SignatureRequest updatedReq = client
            .updateSignatureRequest(id, signatureId, newEmailAddress);
        assertNotNull(updatedReq);
        assertEquals(id, updatedReq.getId());
        Signature sig = updatedReq.getSignature(newEmailAddress, "Barack");
        assertNotNull(sig);
        assertEquals(signatureId, sig.getId());
    }

    @Test(expected = HelloSignException.class)
    public void testUpdateSignatureRequestInvalid() throws Exception {
        mockResponseCode(400);
        String id = "84f8767b525611511ed24e5eaacee537589a30be";
        String signatureId = "e477e554af09555eea762d1c204d9f3d";
        String newEmailAddress = "nightman@hotmail.com";
        client.updateSignatureRequest(id, signatureId, newEmailAddress);
    }

    @Test
    public void testGetTemplates() throws Exception {
        TemplateList templates = client.getTemplates();
        assertNotNull(templates);
        for (Template t : templates) {
            assertNotNull(t);
            assertTrue(t.hasId());
        }
    }

    @Test(expected = HelloSignException.class)
    public void testGetTemplatesInvalid() throws Exception {
        mockResponseCode(401);
        client.getTemplates();
    }

    @Test
    public void testGetTemplatesPage() throws Exception {
        TemplateList templates = client.getTemplates(1);
        assertNotNull(templates);
        for (Template t : templates) {
            assertNotNull(t);
            assertTrue(t.hasId());
        }
    }

    @Test
    public void testGetTemplatesPageInvalid() throws Exception {
        TemplateList templates = client.getTemplates(10);
        assertNotNull(templates);
        assertFalse(templates.iterator().hasNext());
    }

    @Test
    public void testGetTemplatePageSize() throws Exception {
        TemplateList templates = client.getTemplates(1, 20);
        assertNotNull(templates);
        for (Template t : templates) {
            assertNotNull(t);
            assertTrue(t.hasId());
        }
    }

    @Test
    public void testGetTemplatePageSizeInvalid() throws Exception {
        TemplateList templates = client.getTemplates(1, 200);
        assertNotNull(templates);
        for (Template t : templates) {
            assertNotNull(t);
            assertTrue(t.hasId());
        }
    }

    @Test
    public void testGetTemplateFile() throws Exception {
        // Mock a random byte size written
        doReturn((long) 100000).when(spy).getLastResponseAsFile(any(File.class));
        File f = client.getTemplateFile("templateid");
        assertNotNull(f);
        assertTrue(f.exists());
        assertTrue(f.delete());
    }

    @Test(expected = HelloSignException.class)
    public void testGetTemplateFileInvalid() throws Exception {
        mockResponseCode(404);
        client.getTemplateFile("foo");
    }

    @Test
    public void testGetTemplate() throws Exception {
        String templateId = "475c0a43282985bb0fc02c995bfce6df2840a6f5";
        Template template = client.getTemplate(templateId);
        assertNotNull(template);
        assertEquals(templateId, template.getId());
        for (TemplateRole s : template.getSignerRoles()) {
            assertNotNull(s.getRole());
        }
        for (Document d : template.getDocuments()) {
            assertNotNull(d.getName());
            for (CustomField cf : d.getCustomFields()) {
                assertNotNull(cf.getApiId());
                assertNotNull(cf.getName());
                assertNotNull(cf.getType());
                assertNotNull(cf.getX());
                assertNotNull(cf.getY());
                assertNotNull(cf.getWidth());
                assertNotNull(cf.getHeight());
                assertNotNull(cf.isRequired());
                if (cf.getType().equals(FieldType.TEXT)) {
                    assertNotNull(cf.getEstimatedTextLines());
                    assertNotNull(cf.getEstimatedCharsPerLine());
                }
            }
            for (FormField ff : d.getFormFields()) {
                assertNotNull(ff.getApiId());
                assertNotNull(ff.getName());
                assertNotNull(ff.getType());
                assertNotNull(ff.getX());
                assertNotNull(ff.getY());
                assertNotNull(ff.getWidth());
                assertNotNull(ff.getHeight());
                assertNotNull(ff.isRequired());
                assertNotNull(ff.getSigner());
                if (ff.getType().equals(FieldType.TEXT)) {
                    assertNotNull(ff.getEstimatedTextLines());
                    assertNotNull(ff.getEstimatedCharsPerLine());
                }
            }
            for (Account a : template.getAccounts()) {
                assertNotNull(a);
            }
        }

    }

    @Test(expected = HelloSignException.class)
    public void testGetTemplateInvalid() throws Exception {
        mockResponseCode(404);
        client.getTemplateFile("foo");
    }

    @Test
    public void testAddTemplateUser() throws Exception {
        String templateId = "475c0a43282985bb0fc02c995bfce6df2840a6f5";
        String email = "chris+1@hellosign.com";
        Template template = client.addTemplateUser(templateId, email);
        boolean found = false;
        for (Account a : template.getAccounts()) {
            if (email.equals(a.getEmail())) {
                found = true;
                break;
            }
        }
        assertTrue(found);
    }

    @Test(expected = HelloSignException.class)
    public void testAddTemplateUserInvalid() throws Exception {
        mockResponseCode(403);
        client.addTemplateUser("foo", "bar");
    }

    @Test
    public void testDeleteTemplate() throws Exception {
        mockResponseCode(200);
        client.deleteTemplate("foo");
    }

    @Test(expected = HelloSignException.class)
    public void testDeleteTemplateInvalid() throws Exception {
        mockResponseCode(401);
        client.deleteTemplate("foo");
    }

    @Test
    public void testRemoveTemplateUser() throws Exception {
        String templateId = "475c0a43282985bb0fc02c995bfce6df2840a6f5";
        String email = "chris+1@hellosign.com";
        Template template = client.removeTemplateUser(templateId, email);
        assertNotNull(template);
        boolean found = false;
        for (Account a : template.getAccounts()) {
            if (email.equals(a.getEmail())) {
                found = true;
            }
        }
        assertFalse(found);
    }

    @Test(expected = HelloSignException.class)
    public void testRemoveTemplateUserInvalid() throws Exception {
        mockResponseCode(403);
        String templateId = "475c0a43282985bb0fc02c995bfce6df2840a6f5";
        String email = "chris@hellosign.com";
        client.removeTemplateUser(templateId, email);
    }

    @Test
    public void testSendTemplateSignatureRequest() throws Exception {
        String templateId = "9cc3d5819959419abee4dbff2073d497e7c0a962";
        String title = "Java SDK request";
        String message = "Pls to be signing kthx";
        TemplateSignatureRequest req = new TemplateSignatureRequest();
        req.setTemplateId(templateId);
        req.setTitle(title);
        req.setMessage(message);
        req.setSigner("Tester A", "chris+a@hellosign.com", "Chris A");
        req.setSigner("Tester B", "chris+b@hellosign.com", "Chris B");
        Map<String, String> fields = new HashMap<String, String>();
        fields.put("Field A", "Hello");
        fields.put("Field B", "World!");
        fields.put("Checkbox A", "true");
        fields.put("Checkbox B", "false");
        req.setCustomFields(fields);
        SignatureRequest sentReq = client.sendTemplateSignatureRequest(req);
        assertNotNull(sentReq);
        assertEquals(title, sentReq.getTitle());
        assertEquals(message, sentReq.getMessage());
        for (CustomField cf : sentReq.getCustomFields()) {
            assertEquals(fields.get(cf.getName()), cf.getValue());
        }
    }

    @Test(expected = HelloSignException.class)
    public void testSendTemplateSignatureRequestInvalid() throws Exception {
        mockResponseCode(400);
        SignatureRequest req = new SignatureRequest();
        req.addFileUrl("http://www.example.com");
        client.sendSignatureRequest(req);
    }

    @Test
    public void testCancelSignatureRequest() throws Exception {
        mockResponseCode(200);
        assertTrue(client.cancelSignatureRequest("9cc3d5819959419abee4dbff2073d497e7c0a962"));
    }

    @Test
    public void testCancelSignatureRequestInvalid() throws Exception {
        mockResponseCode(410);
        try {
            client.cancelSignatureRequest("foo");
            fail("Expected HelloSignException");
        } catch (HelloSignException e) {
            assertEquals("HTTP Code 410", e.getMessage());
            assertEquals(410, (int) e.getHttpCode());
        }
    }

    @Test
    public void testRequestEmailReminder() throws Exception {
        String id = "89d1f4f831ff1bbaf707c398881d98bff447c64e";
        SignatureRequest req = client.requestEmailReminder(id, "chris+a@hellosign.com");
        assertNotNull(req);
        assertEquals(id, req.getId());
    }

    @Test(expected = HelloSignException.class)
    public void testRequestEmailReminderInvalid() throws Exception {
        mockResponseCode(400);
        String id = "89d1f4f831ff1bbaf707c398881d98bff447c64e";
        client.requestEmailReminder(id, "chris+a@hellosign.comd");
    }

    @Test
    public void testGetFiles() throws Exception {
        // Mock a random byte size written
        doReturn((long) 100000).when(spy).getLastResponseAsFile(any(File.class));
        File f = client.getFiles("id");
        // This really just makes sure a temp file is created
        // the content isn't actually written, since we'd have to
        // mock the response stream
        assertNotNull(f);
        assertTrue(f.exists());
        assertTrue(f.delete());
    }

    @Test(expected = HelloSignException.class)
    public void testGetFilesInvalid() throws Exception {
        mockResponseCode(404);
        client.getFiles("foo");
    }

    @Test(expected = HelloSignException.class)
    public void testGetFilesUrlInvalid() throws Exception {
        mockResponseCode(404);
        client.getFilesUrl("fa5c8a0b0f492d768749333ad6fcc214c111e967");
    }

    @Test
    public void testGetFilesUrl() throws Exception {
        mockResponseCode(200);
        String filesUrl = client.getFilesUrl("fa5c8a0b0f492d768749333ad6fcc214c111e967")
            .getFileUrl();
        assertEquals("https://www.example.com/request/files/url/here", filesUrl);
    }

    @Test
    public void testCreateApiApp() throws Exception {
        ApiApp app = new ApiApp();
        app.setName("Java API App");
        app.setDomain("example.com");
        app.setCallbackUrl("http://www.example.com");
        ApiApp createdApp = client.createApiApp(app);
        assertNotNull(createdApp);
        assertNotNull(createdApp.getClientId());
        assertEquals(app.getName(), createdApp.getName());
        assertEquals(app.getDomain(), createdApp.getDomain());
        assertEquals(app.getCallbackUrl(), createdApp.getCallbackUrl());
    }

    @Test(expected = HelloSignException.class)
    public void testCreateApiAppInvalid() throws Exception {
        mockResponseCode(400);
        ApiApp app = new ApiApp();
        app.setName("Java API App");
        client.createApiApp(app);
    }

    @Test
    public void testGetApiApp() throws Exception {
        ApiApp app = client.getApiApp("034fb51064187cf28e4aad1c2533ad8f");
        assertNotNull(app);
    }

    @Test(expected = HelloSignException.class)
    public void testGetApiAppInvalid() throws Exception {
        mockResponseCode(401);
        client.getApiApp("034fb51064187cf28e4aad1c2533ad8f");
    }

    @Test
    public void testGetApiApps() throws Exception {
        ApiAppList appList = client.getApiApps();
        assertNotNull(appList);
        assertNotNull(appList.getNumPages());
        assertTrue(appList.getNumPages() > 0);
        assertNotNull(appList.getNumResults());
        assertTrue(appList.getNumResults() > 0);
        assertNotNull(appList.getPageSize());
        assertTrue(appList.getPageSize() > 0);
        for (ApiApp app : appList) {
            assertNotNull(app);
            assertNotNull(app.getClientId());
        }
    }

    @Test(expected = HelloSignException.class)
    public void testGetApiAppsInvalid() throws Exception {
        mockResponseCode(401);
        client.getApiApps();
    }

    @Test
    public void testCreateEmbeddedRequest() throws Exception {
        SignatureRequest req = new SignatureRequest();
        req.addFileUrl("http://www.orimi.com/pdf-test.pdf");
        req.addSigner("chris@hellosign.com", "Chris");
        req.setTestMode(true);
        req.addMetadata("test_key", "test_value");
        EmbeddedRequest embeddedReq = new EmbeddedRequest("034fb51064187cf28e4aad1c2533ad8f", req);
        Map<String, String> fields = new HashMap<>();
        fields.put("Field A", "Hello");
        fields.put("Field B", "World!");
        fields.put("Checkbox A", "true");
        fields.put("Checkbox B", "false");
        embeddedReq.setCustomFields(fields);
        AbstractRequest newReq = client.createEmbeddedRequest(embeddedReq);
        assertNotNull(newReq);
        assertNotNull(newReq.getId());
        assertEquals(req.getMetadata("test_key"), newReq.getMetadata("test_key"));
        for (CustomField cf : newReq.getCustomFields()) {
            assertEquals(fields.get(cf.getName()), cf.getValue());
        }
    }

    @Test(expected = HelloSignException.class)
    public void testCreateEmbeddedRequestInvalid() throws Exception {
        mockResponseCode(400);
        EmbeddedRequest embeddedReq = new EmbeddedRequest("clientId", new SignatureRequest());
        client.createEmbeddedRequest(embeddedReq);
    }

    @Test
    public void testGetEmbeddedSignUrl() throws Exception {
        EmbeddedResponse s = client.getEmbeddedSignUrl("37b35bb8b53e3ed26658c1b64c2ffa08");
        assertNotNull(s);
        assertNotNull(s.getSignUrl());
        assertNotNull(s.getExpiresAt());
    }

    @Test(expected = HelloSignException.class)
    public void testGetEmbeddedSignUrlInvalid() throws Exception {
        mockResponseCode(404);
        client.getEmbeddedSignUrl("foo");
    }

    @Test
    public void testCreateUnclaimedDraft() throws Exception {
        UnclaimedDraft draft = new UnclaimedDraft();
        draft.addFileUrl("http://www.orimi.com/pdf-test.pdf");
        draft.setType(UnclaimedDraftType.request_signature);
        draft.setSubject("Java is hot hot hot");
        draft.setMessage("Best served at 180 degrees");
        UnclaimedDraft response = client.createUnclaimedDraft(draft);
        assertNotNull(response);
        assertNotNull(response.getClaimUrl());
        assertFalse(response.isTestMode());
        assertNotNull(response.getExpiresAt());
        assertNotNull(response.getSignatureRequestId());
    }

    @Test(expected = HelloSignException.class)
    public void testCreateUnclaimedDraftInvalid() throws Exception {
        mockResponseCode(400);
        client.createUnclaimedDraft(new UnclaimedDraft());
    }

    @Test
    public void testCreateEmbeddedTemplateDraft() throws Exception {
        TemplateDraft draft = new TemplateDraft();
        draft.setClientId("034fb51064187cf28e4aad1c2533ad8f");
        draft.addFileUrl("http://www.orimi.com/pdf-test.pdf");
        draft.addSignerRole("Role A", 1);
        draft.addSignerRole("Role B", 2);
        draft.addCCRole("Lawyer");
        draft.addCCRole("Manager");
        draft.addMergeField("Full Name", FieldType.TEXT);
        draft.addMergeField("Is registered?", FieldType.CHECKBOX);
        draft.addMetadata("test_key", "test_value");
        EmbeddedRequest embeddedReq = new EmbeddedRequest("034fb51064187cf28e4aad1c2533ad8f",
            draft);
        TemplateDraft response = client.createEmbeddedTemplateDraft(embeddedReq);
        assertNotNull(response);
        assertNotNull(response.getId());
        assertNotNull(response.getEditUrl());
        assertNotNull(response.getExpiresAt());
    }

    @Test(expected = HelloSignException.class)
    public void testCreateEmbeddedTemplateDraftInvalid() throws Exception {
        doReturn(400).when(spy).getLastResponseCode();
        client.createEmbeddedTemplateDraft(new EmbeddedRequest("foo", new TemplateDraft()));
    }

    @Test
    public void testGetEmbeddedTemplateEditUrl() throws Exception {
        EmbeddedResponse response = client.getEmbeddedTemplateEditUrl("foo");
        assertNotNull(response);
        assertNotNull(response.getEditUrl());
        assertNotNull(response.getExpiresAt());
    }

    @Test(expected = HelloSignException.class)
    public void testGetEmbeddedTemplateEditUrlInvalid() throws Exception {
        mockResponseCode(400);
        client.getEmbeddedTemplateEditUrl("foo");
    }

    @Test
    public void testUpdateApiApp() throws Exception {
        // Usually, you'd retrieve this from the client first, update, and then
        // send
        JSONObject obj = new JSONObject(getTestFileAsString("testGetApiApp.json"));
        ApiApp app = new ApiApp(obj);
        WhiteLabelingOptions options = new WhiteLabelingOptions();
        options.setPrimaryButtonColor("#FF0000");
        app.setWhiteLabelingOptions(options);
        ApiApp updatedApp = client.updateApiApp(app);
        assertNotNull(updatedApp);
        assertTrue(app.getWhiteLabelingOptions().getPrimaryButtonColor()
            .equalsIgnoreCase(updatedApp.getWhiteLabelingOptions().getPrimaryButtonColor()));
    }

    @Test(expected = HelloSignException.class)
    public void testUpdateApiAppInvalid() throws Exception {
        mockResponseCode(400);
        client.updateApiApp(new ApiApp());
    }

    @Test
    public void testDeleteApiApp() throws Exception {
        mockResponseCode(204);
        assertTrue(client.deleteApiApp("034fb51064187cf28e4aad1c2533ad8f"));
    }

    @Test(expected = HelloSignException.class)
    public void testDeleteApiAppInvalid() throws Exception {
        mockResponseCode(404);
        assertFalse(client.deleteApiApp("foo"));
    }

    @Test
    public void testTemplateFilesUpdate() throws Exception {
        TemplateDraft newTemplate = new TemplateDraft();
        newTemplate.addFileUrl("http://www.orimi.com/pdf-test.pdf");
        newTemplate.setSubject("Part-time Model Contract");
        newTemplate.setMessage("You too could be a part-time model!");
        client.updateTemplateFiles("02ff71f35bf31d92f367021d208b49b54e4ef6ee", newTemplate, null);
    }

    @Test(expected = HelloSignException.class)
    public void testTemplateFilesUpdateInvalid() throws Exception {
        mockResponseCode(404);
        client.updateTemplateFiles("0000000000000000000000000000000000000000", new TemplateDraft(),
            null);
    }

    @Test
    public void testSignatureRequestWithFormFields()
        throws HelloSignException, FileNotFoundException {
        SignatureRequest request = new SignatureRequest();
        request.setTitle("NDA with Acme Co.");
        request.setSubject("The NDA we talked about");
        request.setMessage(
            "Please sign this NDA and then we can discuss more. Let me know if you have any questions.");
        request.addSigner("jack@example.com", "Jack");
        request.setTestMode(true);

        Document nda = new Document();

        File file = this.getTestFixture("W9.pdf");
        nda.setFile(file);

        FormField text = new FormField();
        text.setApiId("text_e5e36acbe");
        text.setName("Name");
        text.setType(FieldType.TEXT);
        text.setX(71);
        text.setY(94);
        text.setWidth(200);
        text.setHeight(12);
        text.setIsRequired(true);
        text.setSigner(0);
        text.setPage(1);
        nda.addFormField(text);

        FormField mergeText = new FormField();
        mergeText.setApiId("text_merge_e5e36acbe");
        mergeText.setName("Business");
        mergeText.setType(FieldType.TEXT_MERGE);
        mergeText.setX(70);
        mergeText.setY(118);
        mergeText.setWidth(200);
        mergeText.setHeight(12);
        mergeText.setIsRequired(true);
        mergeText.setSigner(0);
        mergeText.setPage(1);
        nda.addFormField(mergeText);

        FormField mergeCheckbox = new FormField();
        mergeCheckbox.setApiId("checkbox_merge_e5e36acbe");
        mergeCheckbox.setName("Tax Class");
        mergeCheckbox.setType(FieldType.CHECKBOX_MERGE);
        mergeCheckbox.setX(65);
        mergeCheckbox.setY(145);
        mergeCheckbox.setWidth(9);
        mergeCheckbox.setHeight(9);
        mergeCheckbox.setIsRequired(true);
        mergeCheckbox.setSigner(0);
        mergeCheckbox.setPage(1);
        nda.addFormField(mergeCheckbox);

        FormField checkbox = new FormField();
        checkbox.setApiId("checkbox_e5e36acbe");
        checkbox.setName("Tax Class");
        checkbox.setType(FieldType.CHECKBOX);
        checkbox.setX(185);
        checkbox.setY(145);
        checkbox.setWidth(9);
        checkbox.setHeight(9);
        checkbox.setIsRequired(true);
        checkbox.setSigner(0);
        checkbox.setPage(1);
        nda.addFormField(checkbox);

        FormField signature = new FormField();
        signature.setApiId("signature_e5e36acbe");
        signature.setName("Sig");
        signature.setType(FieldType.SIGNATURE);
        signature.setX(130);
        signature.setY(520);
        signature.setWidth(100);
        signature.setHeight(16);
        signature.setIsRequired(true);
        signature.setSigner(0);
        signature.setPage(1);
        nda.addFormField(signature);

        FormField dateSigned = new FormField();
        dateSigned.setApiId("date_e5e36acbe");
        dateSigned.setName("Date");
        dateSigned.setType(FieldType.DATE_SIGNED);
        dateSigned.setX(410);
        dateSigned.setY(520);
        dateSigned.setWidth(100);
        dateSigned.setHeight(16);
        dateSigned.setIsRequired(true);
        dateSigned.setSigner(0);
        dateSigned.setPage(1);
        nda.addFormField(dateSigned);

        request.addDocument(nda);

        Map<String, String> fields = new HashMap<>();
        fields.put("checkbox_merge_e5e36acbe", "true");
        fields.put("text_merge_e5e36acbe", "This text is merged!?");
        request.setCustomFields(fields);

        SignatureRequest newRequest = client.sendSignatureRequest(request);
        List<CustomField> customFields = newRequest.getCustomFields();
        Assert.assertEquals(4, customFields.size());

        CustomField customField0 = customFields.get(0);
        CustomField customField1 = customFields.get(1);
        CustomField customField2 = customFields.get(2);
        CustomField customField3 = customFields.get(3);

        // Assert CustomField Object for  Name , Type
        Assert.assertEquals("Name", customField0.getName());
        Assert.assertEquals(FieldType.TEXT, customField0.getType());

        // Assert CustomField Object for  Name , Type
        Assert.assertEquals("Business", customField1.getName());
        Assert.assertEquals(FieldType.TEXT, customField1.getType());

        // Assert CustomField Object for  Name , Type
        Assert.assertEquals("Tax Class", customField2.getName());
        Assert.assertEquals(FieldType.CHECKBOX, customField2.getType());

        // Assert CustomField Object for  Name , Type
        Assert.assertEquals("Tax Class", customField3.getName());
        Assert.assertEquals(FieldType.CHECKBOX, customField3.getType());
    }

    @Test
    public void testRemovingSignersByEmail() throws HelloSignException {
        TemplateSignatureRequest request = new TemplateSignatureRequest();
        request.setSigner("role", "john@example.com", "John");
        request.removeSignerByEmail("john@example.com");
        Map<String, Signer> signers = request.getSigners();
        var set = signers.entrySet();
        Assert.assertEquals(0, set.size());
    }
}
