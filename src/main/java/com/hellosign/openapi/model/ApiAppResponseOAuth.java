/*
 * HelloSign API
 * HelloSign v3 API
 *
 * The version of the OpenAPI document: 3.0.0
 * Contact: apisupport@hellosign.com
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */


package com.hellosign.openapi.model;

import java.util.Objects;
import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.hellosign.openapi.JSON;


import com.hellosign.openapi.ApiException;
/**
 * An object describing the app&#39;s OAuth properties, or null if OAuth is not configured for the app.
 */
@ApiModel(description = "An object describing the app's OAuth properties, or null if OAuth is not configured for the app.")
@JsonPropertyOrder({
    ApiAppResponseOAuth.JSON_PROPERTY_CALLBACK_URL,
    ApiAppResponseOAuth.JSON_PROPERTY_SECRET,
    ApiAppResponseOAuth.JSON_PROPERTY_SCOPES,
    ApiAppResponseOAuth.JSON_PROPERTY_CHARGES_USERS
})
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen")
public class ApiAppResponseOAuth {
  public static final String JSON_PROPERTY_CALLBACK_URL = "callback_url";
  private String callbackUrl;

  public static final String JSON_PROPERTY_SECRET = "secret";
  private String secret;

  public static final String JSON_PROPERTY_SCOPES = "scopes";
  private List<String> scopes = null;

  public static final String JSON_PROPERTY_CHARGES_USERS = "charges_users";
  private Boolean chargesUsers;

  public ApiAppResponseOAuth() { 
  }

  public ApiAppResponseOAuth callbackUrl(String callbackUrl) {
    this.callbackUrl = callbackUrl;
    return this;
  }

   /**
   * The app&#39;s OAuth callback URL.
   * @return callbackUrl
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "The app's OAuth callback URL.")
  @JsonProperty(JSON_PROPERTY_CALLBACK_URL)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getCallbackUrl() {
    return callbackUrl;
  }


  @JsonProperty(JSON_PROPERTY_CALLBACK_URL)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setCallbackUrl(String callbackUrl) {
    this.callbackUrl = callbackUrl;
  }


  public ApiAppResponseOAuth secret(String secret) {
    this.secret = secret;
    return this;
  }

   /**
   * The app&#39;s OAuth secret, or null if the app does not belong to user.
   * @return secret
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "The app's OAuth secret, or null if the app does not belong to user.")
  @JsonProperty(JSON_PROPERTY_SECRET)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getSecret() {
    return secret;
  }


  @JsonProperty(JSON_PROPERTY_SECRET)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setSecret(String secret) {
    this.secret = secret;
  }


  public ApiAppResponseOAuth scopes(List<String> scopes) {
    this.scopes = scopes;
    return this;
  }

  public ApiAppResponseOAuth addScopesItem(String scopesItem) {
    if (this.scopes == null) {
      this.scopes = new ArrayList<>();
    }
    this.scopes.add(scopesItem);
    return this;
  }

   /**
   * Array of OAuth scopes used by the app.
   * @return scopes
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Array of OAuth scopes used by the app.")
  @JsonProperty(JSON_PROPERTY_SCOPES)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public List<String> getScopes() {
    return scopes;
  }


  @JsonProperty(JSON_PROPERTY_SCOPES)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setScopes(List<String> scopes) {
    this.scopes = scopes;
  }


  public ApiAppResponseOAuth chargesUsers(Boolean chargesUsers) {
    this.chargesUsers = chargesUsers;
    return this;
  }

   /**
   * Boolean indicating whether the app owner or the account granting permission is billed for OAuth requests.
   * @return chargesUsers
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Boolean indicating whether the app owner or the account granting permission is billed for OAuth requests.")
  @JsonProperty(JSON_PROPERTY_CHARGES_USERS)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public Boolean getChargesUsers() {
    return chargesUsers;
  }


  @JsonProperty(JSON_PROPERTY_CHARGES_USERS)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setChargesUsers(Boolean chargesUsers) {
    this.chargesUsers = chargesUsers;
  }


  /**
   * Return true if this ApiAppResponseOAuth object is equal to o.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ApiAppResponseOAuth apiAppResponseOAuth = (ApiAppResponseOAuth) o;
    return Objects.equals(this.callbackUrl, apiAppResponseOAuth.callbackUrl) &&
        Objects.equals(this.secret, apiAppResponseOAuth.secret) &&
        Objects.equals(this.scopes, apiAppResponseOAuth.scopes) &&
        Objects.equals(this.chargesUsers, apiAppResponseOAuth.chargesUsers);
  }

  @Override
  public int hashCode() {
    return Objects.hash(callbackUrl, secret, scopes, chargesUsers);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ApiAppResponseOAuth {\n");
    sb.append("    callbackUrl: ").append(toIndentedString(callbackUrl)).append("\n");
    sb.append("    secret: ").append(toIndentedString(secret)).append("\n");
    sb.append("    scopes: ").append(toIndentedString(scopes)).append("\n");
    sb.append("    chargesUsers: ").append(toIndentedString(chargesUsers)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  public Map<String, Object> createFormData() throws ApiException {
    Map<String, Object> map = new HashMap<>();
    boolean fileTypeFound = false;
    try {
    if (callbackUrl != null) {
        if (isFileTypeOrListOfFiles(callbackUrl)) {
            fileTypeFound = true;
        }

        if (callbackUrl.getClass().equals(java.io.File.class) ||
            callbackUrl.getClass().equals(Integer.class) ||
            callbackUrl.getClass().equals(String.class) ||
            callbackUrl.getClass().isEnum() ) {
            map.put("callback_url", callbackUrl);
        } else if (isListOfFile(callbackUrl)) {
            for(int i = 0; i< getListSize(callbackUrl); i++) {
                map.put("callback_url[" + i + "]", getFromList(callbackUrl, i));
            }
        }
        else {
            map.put("callback_url", JSON.getDefault().getMapper().writeValueAsString(callbackUrl));
        }
    }
    if (secret != null) {
        if (isFileTypeOrListOfFiles(secret)) {
            fileTypeFound = true;
        }

        if (secret.getClass().equals(java.io.File.class) ||
            secret.getClass().equals(Integer.class) ||
            secret.getClass().equals(String.class) ||
            secret.getClass().isEnum() ) {
            map.put("secret", secret);
        } else if (isListOfFile(secret)) {
            for(int i = 0; i< getListSize(secret); i++) {
                map.put("secret[" + i + "]", getFromList(secret, i));
            }
        }
        else {
            map.put("secret", JSON.getDefault().getMapper().writeValueAsString(secret));
        }
    }
    if (scopes != null) {
        if (isFileTypeOrListOfFiles(scopes)) {
            fileTypeFound = true;
        }

        if (scopes.getClass().equals(java.io.File.class) ||
            scopes.getClass().equals(Integer.class) ||
            scopes.getClass().equals(String.class) ||
            scopes.getClass().isEnum() ) {
            map.put("scopes", scopes);
        } else if (isListOfFile(scopes)) {
            for(int i = 0; i< getListSize(scopes); i++) {
                map.put("scopes[" + i + "]", getFromList(scopes, i));
            }
        }
        else {
            map.put("scopes", JSON.getDefault().getMapper().writeValueAsString(scopes));
        }
    }
    if (chargesUsers != null) {
        if (isFileTypeOrListOfFiles(chargesUsers)) {
            fileTypeFound = true;
        }

        if (chargesUsers.getClass().equals(java.io.File.class) ||
            chargesUsers.getClass().equals(Integer.class) ||
            chargesUsers.getClass().equals(String.class) ||
            chargesUsers.getClass().isEnum() ) {
            map.put("charges_users", chargesUsers);
        } else if (isListOfFile(chargesUsers)) {
            for(int i = 0; i< getListSize(chargesUsers); i++) {
                map.put("charges_users[" + i + "]", getFromList(chargesUsers, i));
            }
        }
        else {
            map.put("charges_users", JSON.getDefault().getMapper().writeValueAsString(chargesUsers));
        }
    }
    } catch (Exception e) {
        throw new ApiException(e);
    }

    return fileTypeFound ? map : new HashMap<>();
  }

  private boolean isFileTypeOrListOfFiles(Object obj) throws Exception {
    return obj.getClass().equals(java.io.File.class) || isListOfFile(obj);
  }

  private boolean isListOfFile(Object obj) throws Exception {
      return obj instanceof java.util.List && !isListEmpty(obj) && getFromList(obj, 0) instanceof java.io.File;
  }

  private boolean isListEmpty(Object obj) throws Exception {
    return (boolean) Class.forName(java.util.List.class.getName()).getMethod("isEmpty").invoke(obj);
  }

  private Object getFromList(Object obj, int index) throws Exception {
    return Class.forName(java.util.List.class.getName()).getMethod("get", int.class).invoke(obj, index);
  }

  private int getListSize(Object obj) throws Exception {
    return (int) Class.forName(java.util.List.class.getName()).getMethod("size").invoke(obj);
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }

}

