package com.hellosign.sdk.resource.support;

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

    public String getAccessCode() {
        return accessCode;
    }

    public void setAccessCode(String accessCode) {
        this.accessCode = accessCode;
    }
}
