package com.hellosign.sdk.resource;

import java.util.Date;

import org.json.JSONObject;

import com.hellosign.sdk.HelloSignException;

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
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

/**
 * POJO that represents a HelloSign File URL resource.
 */
public class FileUrlResponse extends AbstractResource {
  public static final String FILE_KEY = "file";
  public static final String FILE_URL_KEY = "file_url";
  public static final String EXPIRES_AT_KEY = "expires_at";

  public FileUrlResponse() {
    super();
  }

  public FileUrlResponse(JSONObject jsonObject) throws HelloSignException {
    super(jsonObject, FILE_KEY);
  }

  public String getFileUrl() {
    return getString(FILE_URL_KEY);
  }

  public Date getExpiresAt() {
    return getDate(EXPIRES_AT_KEY);
  }
}
