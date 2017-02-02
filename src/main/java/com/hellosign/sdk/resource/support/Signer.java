package com.hellosign.sdk.resource.support;

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

import com.hellosign.sdk.HelloSignException;

/**
 * Class that stores signer information for a signature request.
 * 
 * @author "Chris Paul (chris@hellosign.com)"
 */
public class Signer {

    String nameOrRole;
    String email;
    String accessCode;

    public Signer() {
    }

    public Signer(String email, String nameOrRole) throws HelloSignException {
        setEmail(email);
        setNameOrRole(nameOrRole);
    }

    public String getNameOrRole() {
        return nameOrRole;
    }

    public void setNameOrRole(String name) {
        this.nameOrRole = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) throws HelloSignException {
        if (!email.contains("@")) {
            throw new HelloSignException("Invalid email address: " + email);
        }
        this.email = email;
    }

    public void setAccessCode(String accessCode) {
        this.accessCode = accessCode;
    }

    public String getAccessCode() {
        return accessCode;
    }
}
