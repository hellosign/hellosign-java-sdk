package com.hellosign.sdk.http;

/**
 * The MIT License (MIT)
 * 
 * Copyright (C) 2014 hellosign.com
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

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Scanner;

import org.json.JSONException;
import org.json.JSONObject;

import com.hellosign.sdk.HelloSignException;

public abstract class AbstractHttpRequest {

    public final static String DEFAULT_ENCODING = "UTF-8";
    public final static String USER_AGENT = createUserAgent();

    private static String createUserAgent() {
        String filename = "config.properties";
        Properties props = new Properties();
        InputStream is = AbstractHttpRequest.class.getClassLoader().getResourceAsStream(filename);
        if (is != null) {
            try {
                props.load(is);
            } catch (IOException e) {
                throw new Error(e);
            }
        } else {
            throw new Error(new FileNotFoundException("Could not find " + filename));
        }
        String version = props.getProperty("sdk.version");
        if (version == null) {
            version = "x.x.x";
        }
        return "hellosign-java-sdk/" + version;
    }

    protected String url;
    protected Authentication auth;

    public static String convertStreamToString(InputStream in) {
        Scanner s = new Scanner(in);
        s.useDelimiter("\\A");
        String result = (s.hasNext()) ? s.next() : "";
        s.close();
        return result;
    }

    protected static void validate(JSONObject json, int code) throws HelloSignException {
        if (json.has("error")) {
            try {
                JSONObject error = json.getJSONObject("error");
                String message = error.getString("error_msg");
                String type = error.getString("error_name");
                throw new HelloSignException(message, code, type);
            } catch (JSONException ex) {
                throw new HelloSignException(ex);
            }
        }
    }
}
