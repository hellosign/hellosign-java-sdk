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
import java.util.Map;

import org.json.JSONObject;

import com.hellosign.sdk.HelloSignException;
import com.hellosign.sdk.resource.support.types.UnclaimedDraftType;

/**
 * Represents an unclaimed draft response and request. 
 * 
 * The UnclaimedDraft object essentially "wraps" a SignatureRequest. There are
 * two types of unclaimed drafts that can be created:
 * <ol>
 * <li>"send_document" - simply creates a claimable file. Use the <code>addFile(File)</code>
 * and <code>addFile(File, int)</code> methods to add files to be claimed.</li>
 * <li>"request_signature" - creates a claimable signature request. If this type is 
 * chosen, the signers name(s) and email address(es) are not optional.</li>
 * </ol>
 * 
 * @author "Chris Paul (chris@hellosign.com)"
 *
 */
public class UnclaimedDraft extends AbstractRequest {

	public static final String UNCLAIMED_DRAFT_KEY = "unclaimed_draft";
	public static final String UNCLAIMED_DRAFT_CLAIM_URL = "claim_url";
	public static final String UNCLAIMED_DRAFT_TYPE = "type";
	public static final String UNCLAIMED_DRAFT_CLIENT_ID = "client_id";
	public static final String UNCLAIMED_IS_FOR_EMBEDDED_SIGNING = "is_for_embedded_signing";
	
	private UnclaimedDraftType type;
	
	private String clientId;
	private boolean isForEmbeddedSigning = false;

	private AbstractRequest request;
	
	/**
	 * Default constructor. This will instantiate the unclaimed draft with a 
	 * SignatureRequest and the UnclaimedDraftType.send_document.
	 */
	public UnclaimedDraft() {
		this(new SignatureRequest());
	}
	
	/**
	 * Creates an unclaimed draft with the provided AbstractRequest, and defaults
	 * the type to <code>UnclaimedDraftType.send_document</code>.
	 * @param request AbstractRequest
	 */
	public UnclaimedDraft(AbstractRequest request) {
		this(request, null);
	}
	
	/**
	 * Creates an unclaimed draft with the provided AbstractRequest and 
	 * UnclaimedDraftType.
	 * @param request AbstractRequest
	 * @param type UnclaimedDraftType
	 */
	public UnclaimedDraft(AbstractRequest request, UnclaimedDraftType type) {
		setRequest(request);
		if (type == null) {
			type = UnclaimedDraftType.send_document;
		}
		setType(type);
	}
	
	/**
	 * Constructor to provide a way to store the API response JSON information.
	 * @param json JSONObject API response object
	 * @throws HelloSignException
	 */
	public UnclaimedDraft(JSONObject json) throws HelloSignException {
		super(json, UNCLAIMED_DRAFT_KEY);
	}
	
	/**
	 * Sets the unclaimed draft type. Use the public enum:
	 * UnclaimedDraft.UNCLAIMED_DRAFT_TYPE.
	 * @param type UnclaimedDraft.UNCLAIMED_DRAFT_TYPE
	 */
	public void setType(UnclaimedDraftType type) {
		this.type = type;
	}
	
	/**
	 * Gets the string value of the unclaimed draft type.
	 * @return String
	 */
	public String getTypeString() {
		return type.toString();
	}
	
	/**
	 * Gets the unclaimed draft type.
	 * @return UnclaimedDraft.UNCLAIMED_DRAFT_TYPE
	 */
	public UnclaimedDraftType getType() {
		return type;
	}
	
	/**
	 * Sets the associated request object from which this unclaimed draft will
	 * be created.
	 * @param request AbstractRequest
	 */
	public void setRequest(AbstractRequest request) {
		this.request = request;
	}
	
	/**
	 * Gets the associated request object. Currently this will always be a
	 * SignatureRequest.
	 * @return AbstractRequest
	 */
	public AbstractRequest getRequest() {
		return request;
	}
	
	/**
	 * Gets the claim URL if the draft has been created.
	 * @return String claim URL
	 */
	public String getClaimUrl() {
		return getString(UNCLAIMED_DRAFT_CLAIM_URL);
	}
	
	/**
	 * Returns true if the draft has been created and a claim URL exists.
	 * @return true or false, if not set
	 */
	public boolean hasClaimUrl() {
		return has(UNCLAIMED_DRAFT_CLAIM_URL);
	}
	
	/**
	 * Adds a file to the unclaimed draft.
	 * @param file File
	 * @throws HelloSignException
	 */
	public void addFile(File file) throws HelloSignException {
		if (!(request instanceof SignatureRequest)) {
			throw new HelloSignException("Cannot add files to this unclaimed draft");
		}
		((SignatureRequest) request).addFile(file);
	}
	
	/**
	 * Adds a file to the unclaimed draft at the given document order.
	 * @param file File
	 * @param order int
	 * @throws HelloSignException
	 */
	public void addFile(File file, int order) throws HelloSignException {
		if (!(request instanceof SignatureRequest)) {
			throw new HelloSignException("Cannot add files to this unclaimed draft");
		}
		((SignatureRequest) request).addFile(file, order);
	}
	
	/**
	 * Removes all files from this request.
	 * @throws HelloSignException
	 */
	public void clearFiles() throws HelloSignException {
		if (!(request instanceof SignatureRequest)) {
			throw new HelloSignException("Cannot add files to this unclaimed draft");
		}
		((SignatureRequest) request).clearDocuments();
	}
	
	/**
	 * Returns true if this Unclaimed Draft is to be embedded.
	 * @return true if this Unclaimed Draft is to be embedded, false otherwise 
	 */
	public boolean isForEmbeddedSigning() {
		return isForEmbeddedSigning;
	}
	
	/**
	 * Sets whether this Unclaimed Draft is to be embedded.
	 * @param b boolean
	 */
	public void setIsForEmbeddedSigning(boolean b) {
		isForEmbeddedSigning = b;
		if (isForEmbeddedSigning) {
			// TODO: Verify we can automatically set this for embedded signing...
			setType(UnclaimedDraftType.request_signature);
		}
	}
	
	/**
	 * Sets the client ID for embedded drafts.
	 * @param id
	 */
	public void setClientId(String id) {
		this.clientId = id;
	}
	
	/**
	 * Returns the client ID.
	 * @return String
	 */
	public String getClientId() {
		return clientId;
	}
	
	@Override
	public Map<String, Serializable> getPostFields() throws HelloSignException {
		Map<String, Serializable> map = request.getPostFields();
		map.put(UNCLAIMED_DRAFT_TYPE, getTypeString());
		if (isForEmbeddedSigning() && getClientId() != null) {
			map.put(UNCLAIMED_IS_FOR_EMBEDDED_SIGNING, "1");
			map.put(UNCLAIMED_DRAFT_CLIENT_ID, getClientId());
		}
		return map;
	}

	@Override
	public String getTitle() {
		return request.getTitle();
	}

	@Override
	public void setTitle(String title) {
		request.setTitle(title);
	}

	@Override
	public boolean hasTitle() {
		return request.hasTitle();
	}

	@Override
	public String getSubject() {
		return request.getSubject();
	}

	@Override
	public void setSubject(String subject) {
		request.setSubject(subject);
	}

	@Override
	public boolean hasSubject() {
		return request.hasSubject();
	}

	@Override
	public String getMessage() {
		return request.getMessage();
	}

	@Override
	public void setMessage(String message) {
		request.setMessage(message);
	}

	@Override
	public boolean hasMessage() {
		return request.hasMessage();
	}

	@Override
	public void setTestMode(boolean testMode) {
		request.setTestMode(testMode);
	}

	@Override
	public boolean isTestMode() {
		return request.isTestMode();
	}

	@Override
	public String getRedirectUrl() {
		return request.getRedirectUrl();
	}

	@Override
	public void setRedirectUrl(String url) {
		request.setRedirectUrl(url);
	}

	@Override
	public boolean hasRedirectUrl() {
		return request.hasRedirectUrl();
	}

	@Override
	public String getId() {
		return request.getId();
	}
}
