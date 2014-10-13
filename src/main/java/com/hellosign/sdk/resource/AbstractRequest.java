package com.hellosign.sdk.resource;

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

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import com.hellosign.sdk.HelloSignException;
import com.hellosign.sdk.resource.support.Metadata;

/**
 * Requests to HelloSign will have common fields such as a 
 * request title, subject, and message. This class centralizes
 * those fields. 
 * 
 * @author "Chris Paul (chris@hellosign.com)"
 */
public abstract class AbstractRequest extends AbstractResource {
	
	public static final String REQUEST_TITLE = "title";
	public static final String REQUEST_SUBJECT = "subject";
	public static final String REQUEST_MESSAGE = "message";
	public static final String REQUEST_REDIRECT_URL = "signing_redirect_url";
	public static final String REQUEST_TEST_MODE = "test_mode";
	public static final String REQUEST_USE_TEXT_TAGS = "use_text_tags";
	public static final String REQUEST_HIDE_TEXT_TAGS = "hide_text_tags";
	public static final String REQUEST_METADATA = "metadata";

	private Metadata metadata;

	public AbstractRequest() {
		super();
	}
	
	public AbstractRequest(JSONObject json, String optionalKey) 
			throws HelloSignException {
		super(json, optionalKey);
		metadata = new Metadata(dataObj);
	}

	protected abstract Map<String, Serializable> getPostFields() throws HelloSignException;
	
	public abstract String getId();

	public String getTitle() {
		return getString(REQUEST_TITLE);
	}
	
	public void setTitle(String title) {
		set(REQUEST_TITLE, title);
	}
	
	public boolean hasTitle() {
		return has(REQUEST_TITLE);
	}
	
	public String getSubject() {
		return getString(REQUEST_SUBJECT);
	}
	public void setSubject(String subject) {
		set(REQUEST_SUBJECT, subject);
	}
	
	public boolean hasSubject() {
		return has(REQUEST_SUBJECT);
	}
	
	public String getMessage() {
		return getString(REQUEST_MESSAGE);
	}
	
	public void setMessage(String message) {
		set(REQUEST_MESSAGE, message);
	}	
	
	public boolean hasMessage() {
		return has(REQUEST_MESSAGE);
	}
	
	public void setTestMode(boolean testMode) {
		set(REQUEST_TEST_MODE, testMode);
	}
	
	public boolean isTestMode() {
		if (has(REQUEST_TEST_MODE)) {
			return getBoolean(REQUEST_TEST_MODE);
		}
		return false;
	}
	
	public String getRedirectUrl() {
		return getString(REQUEST_REDIRECT_URL);
	}
	
	public void setRedirectUrl(String url) {
		set(REQUEST_REDIRECT_URL, url);
	}
	
	public boolean hasRedirectUrl() {
		return has(REQUEST_REDIRECT_URL);
	}

	public boolean hasUseTextTags() {
		return has(REQUEST_USE_TEXT_TAGS);
	}
	public Boolean isUsingTextTags() {
		return getBoolean(REQUEST_USE_TEXT_TAGS);
	}
	public void setUseTextTags(boolean useTextTags) {
		set(REQUEST_USE_TEXT_TAGS, useTextTags);
	}
	public boolean hasHideTextTags() {
		return has(REQUEST_HIDE_TEXT_TAGS);
	}
	public Boolean isHidingTextTags() {
		return getBoolean(REQUEST_HIDE_TEXT_TAGS);
	}
	public void setHideTextTags(boolean hideTextTags) {
		set(REQUEST_HIDE_TEXT_TAGS, hideTextTags);
	}
	public Metadata getMetadata() {
		return metadata;
	}
	public void addMetadata(String key, String value) {
		metadata.set(key, value);
	}
	public String getMetadata(String key) {
		return metadata.get(key);
	}
}
