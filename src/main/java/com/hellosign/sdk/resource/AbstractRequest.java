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

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;

import com.hellosign.sdk.HelloSignException;
import com.hellosign.sdk.resource.support.Document;
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
	private List<Document> documents = new ArrayList<Document>();
	private List<String> fileUrls = new ArrayList<String>();
	private boolean orderMatters = false;

	public AbstractRequest() {
		super();
		metadata = new Metadata();
	}
	
	public AbstractRequest(JSONObject json, String optionalKey) 
			throws HelloSignException {
		super(json, optionalKey);
		metadata = new Metadata(dataObj);
	}

	protected Map<String, Serializable> getPostFields() throws HelloSignException {
		Map<String, Serializable> fields = new HashMap<String, Serializable>();
		try {
			Metadata metadata = getMetadata();
			if (metadata != null) {
				JSONObject mj = metadata.getJSONObject();
				@SuppressWarnings("unchecked")
				Iterator<String> keys = (Iterator<String>) mj.keys();
				while (keys.hasNext()) {
					String key = keys.next();
					fields.put(REQUEST_METADATA + "[" + key + "]", mj.getString(key));
				}
			}
		} catch (Exception ex) {
			throw new HelloSignException("Could not extract metadata fields.", ex);
		}
		return fields;
	}
	
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
	
	/**
	 * Adds the file to the request. 
	 * @param file File
	 * @throws HelloSignException
	 */
	public void addFile(File file) throws HelloSignException {
		addFile(file, null);
	}
	
	/**
	 * Adds the file to the request in the given order. 
	 * 
	 * The order should be a 0-based index into the file list. 
	 * Therefore, the first item of the file list is 0, and so forth.
	 * 
	 * If order is null, the document is appended to the end of the file list.
	 * 
	 * @param file File
	 * @param order Integer or null
	 * @throws HelloSignException
	 */
	public void addFile(File file, Integer order) throws HelloSignException {
		Document doc = new Document();
		doc.setFile(file);
    	if (order == null) {
    		addDocument(doc);
    	} else {
    		addDocument(doc, order);
    	}
	}
	
	/**
	 * Adds a Document to the signature request.
	 * @param doc
	 * @throws HelloSignException
	 */
	public void addDocument(Document doc) throws HelloSignException {
		if (doc == null) {
			throw new HelloSignException("Document cannot be null");
		}
		documents.add(doc);
	}
	
	/**
	 * Adds a Document to the signature request at the specific order. 
	 * @param doc
	 * @param order
	 * @throws HelloSignException
	 */
	public void addDocument(Document doc, int order) throws HelloSignException {
		if (doc == null) {
			throw new HelloSignException("Document cannot be null");
		}
		try {
			documents.add(order, doc);
		} catch (Exception ex) {
			throw new HelloSignException(ex);
		}
	}
	
	/**
	 * Returns a reference to the list of documents for this request. 
	 * Modifying this list will modify the list that will be sent with the
	 * request. Useful for more fine-grained modification.
	 * @return List<Document>
	 */
	public List<Document> getDocuments() {
		return documents;
	}
	
	/**
	 * Overwrites this requests document list with the provided document list.
	 * @param docs List<Document>
	 */
	public void setDocuments(List<Document> docs) {
		documents = docs;
	}
	
	/**
	 * Remove all documents from this request.
	 */
	public void clearDocuments() {
		documents = new ArrayList<Document>();
	}

	/**
	 * Determines whether the order of the signers list is to be enforced.
	 * @param b true if the order matters, false otherwise
	 */
	public void setOrderMatters(boolean b) {
		orderMatters = b;
	}
	
	/**
	 * A flag that determines whether order of the signers list is enforced.
	 * @return true if the order matters, false otherwise
	 */
	public boolean getOrderMatters() {
		return orderMatters;
	}

	/**
	 * Add a file_url to this request.
	 * @param url String
	 */
	public void addFileUrl(String url) {
		fileUrls.add(url);
	}

	/**
	 * Return the current file_url list.
	 * @return List<String>
	 */
	public List<String> getFileUrls() {
		return fileUrls;
	}

	/**
	 * Overwrite the current file_url list.
	 * @param fileUrls List<String>
	 */
	public void setFileUrls(List<String> fileUrls) {
		this.fileUrls = fileUrls;
	}
}
