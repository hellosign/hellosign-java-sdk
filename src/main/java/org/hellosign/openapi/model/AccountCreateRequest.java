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


package org.hellosign.openapi.model;

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
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.hellosign.openapi.JSON;


import org.hellosign.openapi.ApiException;
/**
 * AccountCreateRequest
 */
@JsonPropertyOrder({
    AccountCreateRequest.JSON_PROPERTY_EMAIL_ADDRESS,
    AccountCreateRequest.JSON_PROPERTY_CLIENT_ID,
    AccountCreateRequest.JSON_PROPERTY_CLIENT_SECRET
})
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen")
public class AccountCreateRequest {
  public static final String JSON_PROPERTY_EMAIL_ADDRESS = "email_address";
  private String emailAddress;

  public static final String JSON_PROPERTY_CLIENT_ID = "client_id";
  private String clientId;

  public static final String JSON_PROPERTY_CLIENT_SECRET = "client_secret";
  private String clientSecret;

  public AccountCreateRequest() { 
  }

  public AccountCreateRequest emailAddress(String emailAddress) {
    this.emailAddress = emailAddress;
    return this;
  }

   /**
   * The email address to create a new Account for.
   * @return emailAddress
  **/
  @javax.annotation.Nonnull
  @ApiModelProperty(required = true, value = "The email address to create a new Account for.")
  @JsonProperty(JSON_PROPERTY_EMAIL_ADDRESS)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)

  public String getEmailAddress() {
    return emailAddress;
  }


  @JsonProperty(JSON_PROPERTY_EMAIL_ADDRESS)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)
  public void setEmailAddress(String emailAddress) {
    this.emailAddress = emailAddress;
  }


  public AccountCreateRequest clientId(String clientId) {
    this.clientId = clientId;
    return this;
  }

   /**
   * Used when creating a new account and requesting OAuth authorization.  See [OAuth 2.0 Authorization](https://app.hellosign.com/api/oauthWalkthrough#OAuthAuthorization)
   * @return clientId
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Used when creating a new account and requesting OAuth authorization.  See [OAuth 2.0 Authorization](https://app.hellosign.com/api/oauthWalkthrough#OAuthAuthorization)")
  @JsonProperty(JSON_PROPERTY_CLIENT_ID)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getClientId() {
    return clientId;
  }


  @JsonProperty(JSON_PROPERTY_CLIENT_ID)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setClientId(String clientId) {
    this.clientId = clientId;
  }


  public AccountCreateRequest clientSecret(String clientSecret) {
    this.clientSecret = clientSecret;
    return this;
  }

   /**
   * Used when creating a new account and requesting OAuth authorization.  See [OAuth 2.0 Authorization](https://app.hellosign.com/api/oauthWalkthrough#OAuthAuthorization)
   * @return clientSecret
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Used when creating a new account and requesting OAuth authorization.  See [OAuth 2.0 Authorization](https://app.hellosign.com/api/oauthWalkthrough#OAuthAuthorization)")
  @JsonProperty(JSON_PROPERTY_CLIENT_SECRET)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getClientSecret() {
    return clientSecret;
  }


  @JsonProperty(JSON_PROPERTY_CLIENT_SECRET)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setClientSecret(String clientSecret) {
    this.clientSecret = clientSecret;
  }


  /**
   * Return true if this AccountCreateRequest object is equal to o.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AccountCreateRequest accountCreateRequest = (AccountCreateRequest) o;
    return Objects.equals(this.emailAddress, accountCreateRequest.emailAddress) &&
        Objects.equals(this.clientId, accountCreateRequest.clientId) &&
        Objects.equals(this.clientSecret, accountCreateRequest.clientSecret);
  }

  @Override
  public int hashCode() {
    return Objects.hash(emailAddress, clientId, clientSecret);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AccountCreateRequest {\n");
    sb.append("    emailAddress: ").append(toIndentedString(emailAddress)).append("\n");
    sb.append("    clientId: ").append(toIndentedString(clientId)).append("\n");
    sb.append("    clientSecret: ").append(toIndentedString(clientSecret)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  public Map<String, Object> createFormData() throws ApiException {
    Map<String, Object> map = new HashMap<>();
    boolean fileTypeFound = false;
    try {
    if (emailAddress != null) {
        if (isFileTypeOrListOfFiles(emailAddress)) {
            fileTypeFound = true;
        }

        if (emailAddress.getClass().equals(java.io.File.class) ||
            emailAddress.getClass().equals(Integer.class) ||
            emailAddress.getClass().equals(String.class) ) {
            map.put("email_address", emailAddress);
        } else if (isListOfFile(emailAddress)) {
            for(int i = 0; i< getListSize(emailAddress); i++) {
                map.put("email_address[" + i + "]", getFromList(emailAddress, i));
            }
        }
        else {
            map.put("email_address", JSON.getDefault().getMapper().writeValueAsString(emailAddress));
        }
    }
    if (clientId != null) {
        if (isFileTypeOrListOfFiles(clientId)) {
            fileTypeFound = true;
        }

        if (clientId.getClass().equals(java.io.File.class) ||
            clientId.getClass().equals(Integer.class) ||
            clientId.getClass().equals(String.class) ) {
            map.put("client_id", clientId);
        } else if (isListOfFile(clientId)) {
            for(int i = 0; i< getListSize(clientId); i++) {
                map.put("client_id[" + i + "]", getFromList(clientId, i));
            }
        }
        else {
            map.put("client_id", JSON.getDefault().getMapper().writeValueAsString(clientId));
        }
    }
    if (clientSecret != null) {
        if (isFileTypeOrListOfFiles(clientSecret)) {
            fileTypeFound = true;
        }

        if (clientSecret.getClass().equals(java.io.File.class) ||
            clientSecret.getClass().equals(Integer.class) ||
            clientSecret.getClass().equals(String.class) ) {
            map.put("client_secret", clientSecret);
        } else if (isListOfFile(clientSecret)) {
            for(int i = 0; i< getListSize(clientSecret); i++) {
                map.put("client_secret[" + i + "]", getFromList(clientSecret, i));
            }
        }
        else {
            map.put("client_secret", JSON.getDefault().getMapper().writeValueAsString(clientSecret));
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

