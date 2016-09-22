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
import com.hellosign.sdk.resource.support.WhiteLabelingOptions;
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
        assertEquals(format.format(app.getCreatedAt()), "06-Jul-2015");
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

    private String getShiftedColor(String color) {
        int c = Integer.parseInt(color.substring(1), 16);
        if (c - 20 >= 0) {
            c -= 20;
        } else {
            c += 20;
        }
        String str = String.format("%06x", c);
        return "#" + str;
    }

    @Test
    public void testApiAppWithWhiteLabelingOptions() throws Exception {
        JSONObject apiapp = new JSONObject(getTestFileAsString("apiAppWithWhiteLabeling.txt"));
        ApiApp app = new ApiApp(apiapp);
        assertNotNull(app);
        WhiteLabelingOptions oldOptions = app.getWhiteLabelingOptions();

        String shiftedColor = getShiftedColor(oldOptions.getHeaderBackgroundColor());
        app.setHeaderBackgroundColor(shiftedColor);
        assertEquals(app.getHeaderBackgroundColor(), shiftedColor);

        shiftedColor = getShiftedColor(oldOptions.getPageBackgroundColor());
        app.setPageBackgroundColor(shiftedColor);
        assertEquals(app.getPageBackgroundColor(), shiftedColor);

        shiftedColor = getShiftedColor(oldOptions.getLinkColor());
        app.setLinkColor(shiftedColor);
        assertEquals(app.getLinkColor(), shiftedColor);

        shiftedColor = getShiftedColor(oldOptions.getPrimaryButtonColor());
        app.setPrimaryButtonColor(shiftedColor);
        assertEquals(app.getPrimaryButtonColor(), shiftedColor);

        shiftedColor = getShiftedColor(oldOptions.getPrimaryButtonHoverColor());
        app.setPrimaryButtonHoverColor(shiftedColor);
        assertEquals(app.getPrimaryButtonHoverColor(), shiftedColor);

        shiftedColor = getShiftedColor(oldOptions.getPrimaryButtonTextColor());
        app.setPrimaryButtonTextColor(shiftedColor);
        assertEquals(app.getPrimaryButtonTextColor(), shiftedColor);

        shiftedColor = getShiftedColor(oldOptions.getPrimaryButtonTextHoverColor());
        app.setPrimaryButtonTextHoverColor(shiftedColor);
        assertEquals(app.getPrimaryButtonTextHoverColor(), shiftedColor);

        shiftedColor = getShiftedColor(oldOptions.getSecondaryButtonColor());
        app.setSecondaryButtonColor(shiftedColor);
        assertEquals(app.getSecondaryButtonColor(), shiftedColor);

        shiftedColor = getShiftedColor(oldOptions.getSecondaryButtonHoverColor());
        app.setSecondaryButtonHoverColor(shiftedColor);
        assertEquals(app.getSecondaryButtonHoverColor(), shiftedColor);

        shiftedColor = getShiftedColor(oldOptions.getSecondaryButtonTextColor());
        app.setSecondaryButtonTextColor(shiftedColor);
        assertEquals(app.getSecondaryButtonTextColor(), shiftedColor);

        shiftedColor = getShiftedColor(oldOptions.getSecondaryButtonTextHoverColor());
        app.setSecondaryButtonTextHoverColor(shiftedColor);
        assertEquals(app.getSecondaryButtonTextHoverColor(), shiftedColor);

        shiftedColor = getShiftedColor(oldOptions.getTextColor1());
        app.setTextColor1(shiftedColor);
        assertEquals(app.getTextColor1(), shiftedColor);

        shiftedColor = getShiftedColor(oldOptions.getTextColor2());
        app.setTextColor2(shiftedColor);
        assertEquals(app.getTextColor2(), shiftedColor);
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
        app.setHeaderBackgroundColor("#FFFFFF");
        app.setPageBackgroundColor("#FFFFFF");
        app.setLinkColor("#FFFFFF");
        app.setPrimaryButtonColor("#FFFFFF");
        app.setPrimaryButtonHoverColor("#FFFFFF");
        app.setPrimaryButtonTextColor("#FFFFFF");
        app.setPrimaryButtonTextHoverColor("#FFFFFF");
        app.setSecondaryButtonColor("#FFFFFF");
        app.setSecondaryButtonHoverColor("#FFFFFF");
        app.setSecondaryButtonTextColor("#FFFFFF");
        app.setSecondaryButtonTextHoverColor("#FFFFFF");
        app.setTextColor1("#FFFFFF");
        app.setTextColor2("#FFFFFF");
        assertTrue(areFieldsEqual(getExpectedFields(), app.getPostFields()));
    }
}
