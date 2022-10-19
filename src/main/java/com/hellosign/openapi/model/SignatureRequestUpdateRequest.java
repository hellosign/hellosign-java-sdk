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
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.hellosign.openapi.JSON;


import com.hellosign.openapi.ApiException;
/**
 * SignatureRequestUpdateRequest
 */
@JsonPropertyOrder({
    SignatureRequestUpdateRequest.JSON_PROPERTY_SIGNATURE_ID,
    SignatureRequestUpdateRequest.JSON_PROPERTY_EMAIL_ADDRESS,
    SignatureRequestUpdateRequest.JSON_PROPERTY_NAME,
    SignatureRequestUpdateRequest.JSON_PROPERTY_EXPIRES_AT
})
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen")
public class SignatureRequestUpdateRequest {
  public static final String JSON_PROPERTY_SIGNATURE_ID = "signature_id";
  private String signatureId;

  public static final String JSON_PROPERTY_EMAIL_ADDRESS = "email_address";
  private String emailAddress;

  public static final String JSON_PROPERTY_NAME = "name";
  private String name;

  public static final String JSON_PROPERTY_EXPIRES_AT = "expires_at";
  private Integer expiresAt;

  public SignatureRequestUpdateRequest() { 
  }

  public SignatureRequestUpdateRequest signatureId(String signatureId) {
    this.signatureId = signatureId;
    return this;
  }

   /**
   * The signature ID for the recipient.
   * @return signatureId
  **/
  @javax.annotation.Nonnull
  @ApiModelProperty(required = true, value = "The signature ID for the recipient.")
  @JsonProperty(JSON_PROPERTY_SIGNATURE_ID)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)

  public String getSignatureId() {
    return signatureId;
  }


  @JsonProperty(JSON_PROPERTY_SIGNATURE_ID)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)
  public void setSignatureId(String signatureId) {
    this.signatureId = signatureId;
  }


  public SignatureRequestUpdateRequest emailAddress(String emailAddress) {
    this.emailAddress = emailAddress;
    return this;
  }

   /**
   * The new email address for the recipient.  **NOTE**: Optional if &#x60;name&#x60; is provided.
   * @return emailAddress
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "The new email address for the recipient.  **NOTE**: Optional if `name` is provided.")
  @JsonProperty(JSON_PROPERTY_EMAIL_ADDRESS)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getEmailAddress() {
    return emailAddress;
  }


  @JsonProperty(JSON_PROPERTY_EMAIL_ADDRESS)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setEmailAddress(String emailAddress) {
    this.emailAddress = emailAddress;
  }


  public SignatureRequestUpdateRequest name(String name) {
    this.name = name;
    return this;
  }

   /**
   * The new name for the recipient.  **NOTE**: Optional if &#x60;email_address&#x60; is provided.
   * @return name
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "The new name for the recipient.  **NOTE**: Optional if `email_address` is provided.")
  @JsonProperty(JSON_PROPERTY_NAME)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getName() {
    return name;
  }


  @JsonProperty(JSON_PROPERTY_NAME)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setName(String name) {
    this.name = name;
  }


  public SignatureRequestUpdateRequest expiresAt(Integer expiresAt) {
    this.expiresAt = expiresAt;
    return this;
  }

   /**
   * _t__SignatureRequestUpdate::EXPIRES_AT
   * @return expiresAt
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "_t__SignatureRequestUpdate::EXPIRES_AT")
  @JsonProperty(JSON_PROPERTY_EXPIRES_AT)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public Integer getExpiresAt() {
    return expiresAt;
  }


  @JsonProperty(JSON_PROPERTY_EXPIRES_AT)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setExpiresAt(Integer expiresAt) {
    this.expiresAt = expiresAt;
  }


  /**
   * Return true if this SignatureRequestUpdateRequest object is equal to o.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SignatureRequestUpdateRequest signatureRequestUpdateRequest = (SignatureRequestUpdateRequest) o;
    return Objects.equals(this.signatureId, signatureRequestUpdateRequest.signatureId) &&
        Objects.equals(this.emailAddress, signatureRequestUpdateRequest.emailAddress) &&
        Objects.equals(this.name, signatureRequestUpdateRequest.name) &&
        Objects.equals(this.expiresAt, signatureRequestUpdateRequest.expiresAt);
  }

  @Override
  public int hashCode() {
    return Objects.hash(signatureId, emailAddress, name, expiresAt);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SignatureRequestUpdateRequest {\n");
    sb.append("    signatureId: ").append(toIndentedString(signatureId)).append("\n");
    sb.append("    emailAddress: ").append(toIndentedString(emailAddress)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    expiresAt: ").append(toIndentedString(expiresAt)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  public Map<String, Object> createFormData() throws ApiException {
    Map<String, Object> map = new HashMap<>();
    boolean fileTypeFound = false;
    try {
    if (signatureId != null) {
        if (isFileTypeOrListOfFiles(signatureId)) {
            fileTypeFound = true;
        }

        if (signatureId.getClass().equals(java.io.File.class) ||
            signatureId.getClass().equals(Integer.class) ||
            signatureId.getClass().equals(String.class) ||
            signatureId.getClass().isEnum()) {
            map.put("signature_id", signatureId);
        } else if (isListOfFile(signatureId)) {
            for(int i = 0; i< getListSize(signatureId); i++) {
                map.put("signature_id[" + i + "]", getFromList(signatureId, i));
            }
        }
        else {
            map.put("signature_id", JSON.getDefault().getMapper().writeValueAsString(signatureId));
        }
    }
    if (emailAddress != null) {
        if (isFileTypeOrListOfFiles(emailAddress)) {
            fileTypeFound = true;
        }

        if (emailAddress.getClass().equals(java.io.File.class) ||
            emailAddress.getClass().equals(Integer.class) ||
            emailAddress.getClass().equals(String.class) ||
            emailAddress.getClass().isEnum()) {
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
    if (name != null) {
        if (isFileTypeOrListOfFiles(name)) {
            fileTypeFound = true;
        }

        if (name.getClass().equals(java.io.File.class) ||
            name.getClass().equals(Integer.class) ||
            name.getClass().equals(String.class) ||
            name.getClass().isEnum()) {
            map.put("name", name);
        } else if (isListOfFile(name)) {
            for(int i = 0; i< getListSize(name); i++) {
                map.put("name[" + i + "]", getFromList(name, i));
            }
        }
        else {
            map.put("name", JSON.getDefault().getMapper().writeValueAsString(name));
        }
    }
    if (expiresAt != null) {
        if (isFileTypeOrListOfFiles(expiresAt)) {
            fileTypeFound = true;
        }

        if (expiresAt.getClass().equals(java.io.File.class) ||
            expiresAt.getClass().equals(Integer.class) ||
            expiresAt.getClass().equals(String.class) ||
            expiresAt.getClass().isEnum()) {
            map.put("expires_at", expiresAt);
        } else if (isListOfFile(expiresAt)) {
            for(int i = 0; i< getListSize(expiresAt); i++) {
                map.put("expires_at[" + i + "]", getFromList(expiresAt, i));
            }
        }
        else {
            map.put("expires_at", JSON.getDefault().getMapper().writeValueAsString(expiresAt));
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

