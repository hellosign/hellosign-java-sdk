package com.hellosign.sdk.callback;

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

import java.io.IOException;
import java.text.DateFormat;
import java.util.List;
import java.util.TimeZone;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hellosign.sdk.HelloSignException;
import com.hellosign.sdk.resource.Event;

/**
 * This example servlet demonstrates how to use the HelloSign Java SDK resource
 * classes to handle events POSTed from the HelloSign API. 
 * 
 * @author "Chris Paul (chris@hellosign.com)"
 */
public class ExampleCallbackServlet extends HttpServlet {
	
	private static final Logger logger = LoggerFactory.getLogger(ExampleCallbackServlet.class);

	private static final long serialVersionUID = -5673774222721758983L;
	private static final String API_KEY_INIT_PARAM = "hellosign.api.key";
	
	private String apiKey;
	
	public ExampleCallbackServlet() {
		apiKey = System.getProperty(API_KEY_INIT_PARAM);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// First, process the response body and retrieve the JSON object
		// (it lives in the POST field called "json")
		String jsonStr = null;
		try {
			List<FileItem> items = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
			for (FileItem item : items) {
				if ("json".equals(item.getFieldName())) {
					jsonStr = item.getString();
				}
			}
		} catch (FileUploadException ex) {
			ex.printStackTrace();
		}
		if (jsonStr == null) {
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			return;
		}
		
		// Convert the JSON string to a JSONObject
		JSONObject json = null;
		try {
			json = new JSONObject(jsonStr);
		} catch (JSONException ex) {
			ex.printStackTrace();
		}
		if (json == null) {
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			return;
		}
		
		// Create a new Event so we can work with the response
		try {			
			Event event = new Event(json);
			logger.debug(event.toString());
			if (event.isValid(apiKey)) {
				logger.debug("Event received:");
				if (event.hasAccountId()) {
					logger.debug("\tAccount ID: " + event.getAccountId());
				}
				DateFormat dateformat = DateFormat.getDateTimeInstance();
				dateformat.setTimeZone(TimeZone.getTimeZone("US/Pacific"));
				logger.debug("\tDate: " + dateformat.format(event.getEventDate()));
				logger.debug("\tType: " + event.getType());
				if (event.hasSignatureRequest()) {
					logger.debug("\tSignature Request: " + event.getSignatureRequest().getId());
				}
				if (event.hasRelatedSignatureId()) {
					logger.debug("\tSignature ID: " + event.getRelatedSignatureId());
				}
			}
		} catch (HelloSignException ex) {
			ex.printStackTrace();
		}
		
		// Respond to HelloSign with an HTTP 200 and valid message
		response.setContentType("text/plain");
		response.setStatus(HttpServletResponse.SC_OK);
		response.getWriter().println("Hello API Event Received");
	}
}
