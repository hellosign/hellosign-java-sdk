package com.hellosign.sdk.http;

import java.io.InputStream;
import java.util.Scanner;

import org.json.JSONException;
import org.json.JSONObject;

import com.hellosign.sdk.HelloSignException;

public abstract class AbstractHttpRequest {
	
	public final static String DEFAULT_ENCODING = "UTF-8";
	public final static String USER_AGENT = "HelloSign Java SDK";
	
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
