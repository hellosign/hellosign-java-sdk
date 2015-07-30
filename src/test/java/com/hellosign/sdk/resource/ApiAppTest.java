package com.hellosign.sdk.resource;

/**
 * The MIT License (MIT)
 * 
 * Copyright (C) 2015 hellosign.com
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

import static org.junit.Assert.*;

import java.text.DateFormat;
import java.util.Set;

import org.json.JSONObject;
import org.junit.Test;

import com.hellosign.sdk.AbstractHelloSignTest;
import com.hellosign.sdk.resource.support.ApiAppOauth;
import com.hellosign.sdk.resource.support.types.ApiAppOauthScopeType;

/**
 * Simple test for the ApiApp resource class.
 * 
 * @author "Chris Paul (chris@hellosign.com)"
 */
public class ApiAppTest extends AbstractHelloSignTest {

    @Test
    public void testDefaultApiApp() throws Exception {
        ApiApp app = new ApiApp();
        assertNull(app.getCallbackUrl());
    }

    @Test
    public void testApiAppWithValidJSON() throws Exception {
        JSONObject apiapp = new JSONObject(getTestFileAsString("validApiApp.txt"));
        ApiApp app = new ApiApp(apiapp);
        assertNotNull(app);
        assertEquals(app.getCallbackUrl(), "http://example.com/hello");
        assertEquals(app.getClientId(), "0dd3b823a682527788c4e40cb7b6f7e9");
        DateFormat format = DateFormat.getDateInstance();
        assertEquals(format.format(app.getCreatedAt()), "Jul 6, 2015");
        assertEquals(app.getDomain(), "example.com");
        assertEquals(app.isApproved(), true);
        assertEquals(app.getName(), "My Production App");
        ApiAppOauth oauth = app.getOauthInfo();
        assertEquals(oauth.getCallbackUrl(), "http://example.com/oauth");
        Set<ApiAppOauthScopeType> scopes = oauth.getScopes();
        assertTrue(scopes.contains(ApiAppOauthScopeType.basic_account_info));
        assertTrue(scopes.contains(ApiAppOauthScopeType.request_signature));
        assertEquals(oauth.getSecret(), "98891a1b59f312d04cd88e4e0c498d75");
        Account owner = app.getOwnerAccount();
        assertEquals(owner.getId(), "dc5deeb9e10b044c591ef2475aafad1d1d3bd888");
        assertEquals(owner.getEmail(), "john@example.com");
    }

    @Test
    public void testApiAppPostFields() throws Exception {
        ApiApp app = new ApiApp();
        app.setName("My Production App");
        app.setDomain("example.com");
        app.setCallbackUrl("https://example.com/hello");
        app.setOAuthCallbackUrl("https://example.com/oauth");
        app.addScope(ApiAppOauthScopeType.basic_account_info);
        app.addScope(ApiAppOauthScopeType.request_signature);
        assertTrue(areFieldsEqual(getExpectedFields(), app.getPostFields()));
    }
}
