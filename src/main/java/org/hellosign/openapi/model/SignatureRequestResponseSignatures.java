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
 * An array of signature obects, 1 for each signer.
 */
@ApiModel(description = "An array of signature obects, 1 for each signer.")
@JsonPropertyOrder({
    SignatureRequestResponseSignatures.JSON_PROPERTY_SIGNATURE_ID,
    SignatureRequestResponseSignatures.JSON_PROPERTY_SIGNER_EMAIL_ADDRESS,
    SignatureRequestResponseSignatures.JSON_PROPERTY_SIGNER_NAME,
    SignatureRequestResponseSignatures.JSON_PROPERTY_SIGNER_ROLE,
    SignatureRequestResponseSignatures.JSON_PROPERTY_ORDER,
    SignatureRequestResponseSignatures.JSON_PROPERTY_STATUS_CODE,
    SignatureRequestResponseSignatures.JSON_PROPERTY_DECLINE_REASON,
    SignatureRequestResponseSignatures.JSON_PROPERTY_SIGNED_AT,
    SignatureRequestResponseSignatures.JSON_PROPERTY_LAST_VIEWED_AT,
    SignatureRequestResponseSignatures.JSON_PROPERTY_LAST_REMINDED_AT,
    SignatureRequestResponseSignatures.JSON_PROPERTY_HAS_PIN,
    SignatureRequestResponseSignatures.JSON_PROPERTY_HAS_SMS_AUTH,
    SignatureRequestResponseSignatures.JSON_PROPERTY_REASSIGNED_BY,
    SignatureRequestResponseSignatures.JSON_PROPERTY_REASSIGNMENT_REASON,
    SignatureRequestResponseSignatures.JSON_PROPERTY_ERROR
})
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen")
public class SignatureRequestResponseSignatures {
  public static final String JSON_PROPERTY_SIGNATURE_ID = "signature_id";
  private String signatureId;

  public static final String JSON_PROPERTY_SIGNER_EMAIL_ADDRESS = "signer_email_address";
  private String signerEmailAddress;

  public static final String JSON_PROPERTY_SIGNER_NAME = "signer_name";
  private String signerName;

  public static final String JSON_PROPERTY_SIGNER_ROLE = "signer_role";
  private String signerRole;

  public static final String JSON_PROPERTY_ORDER = "order";
  private Integer order;

  public static final String JSON_PROPERTY_STATUS_CODE = "status_code";
  private String statusCode;

  public static final String JSON_PROPERTY_DECLINE_REASON = "decline_reason";
  private String declineReason;

  public static final String JSON_PROPERTY_SIGNED_AT = "signed_at";
  private Integer signedAt;

  public static final String JSON_PROPERTY_LAST_VIEWED_AT = "last_viewed_at";
  private Integer lastViewedAt;

  public static final String JSON_PROPERTY_LAST_REMINDED_AT = "last_reminded_at";
  private Integer lastRemindedAt;

  public static final String JSON_PROPERTY_HAS_PIN = "has_pin";
  private Boolean hasPin;

  public static final String JSON_PROPERTY_HAS_SMS_AUTH = "has_sms_auth";
  private Boolean hasSmsAuth;

  public static final String JSON_PROPERTY_REASSIGNED_BY = "reassigned_by";
  private String reassignedBy;

  public static final String JSON_PROPERTY_REASSIGNMENT_REASON = "reassignment_reason";
  private String reassignmentReason;

  public static final String JSON_PROPERTY_ERROR = "error";
  private String error;

  public SignatureRequestResponseSignatures() { 
  }

  public SignatureRequestResponseSignatures signatureId(String signatureId) {
    this.signatureId = signatureId;
    return this;
  }

   /**
   * Signature identifier.
   * @return signatureId
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Signature identifier.")
  @JsonProperty(JSON_PROPERTY_SIGNATURE_ID)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getSignatureId() {
    return signatureId;
  }


  @JsonProperty(JSON_PROPERTY_SIGNATURE_ID)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setSignatureId(String signatureId) {
    this.signatureId = signatureId;
  }


  public SignatureRequestResponseSignatures signerEmailAddress(String signerEmailAddress) {
    this.signerEmailAddress = signerEmailAddress;
    return this;
  }

   /**
   * The email address of the signer.
   * @return signerEmailAddress
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "The email address of the signer.")
  @JsonProperty(JSON_PROPERTY_SIGNER_EMAIL_ADDRESS)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getSignerEmailAddress() {
    return signerEmailAddress;
  }


  @JsonProperty(JSON_PROPERTY_SIGNER_EMAIL_ADDRESS)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setSignerEmailAddress(String signerEmailAddress) {
    this.signerEmailAddress = signerEmailAddress;
  }


  public SignatureRequestResponseSignatures signerName(String signerName) {
    this.signerName = signerName;
    return this;
  }

   /**
   * The name of the signer.
   * @return signerName
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "The name of the signer.")
  @JsonProperty(JSON_PROPERTY_SIGNER_NAME)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getSignerName() {
    return signerName;
  }


  @JsonProperty(JSON_PROPERTY_SIGNER_NAME)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setSignerName(String signerName) {
    this.signerName = signerName;
  }


  public SignatureRequestResponseSignatures signerRole(String signerRole) {
    this.signerRole = signerRole;
    return this;
  }

   /**
   * The role of the signer.
   * @return signerRole
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "The role of the signer.")
  @JsonProperty(JSON_PROPERTY_SIGNER_ROLE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getSignerRole() {
    return signerRole;
  }


  @JsonProperty(JSON_PROPERTY_SIGNER_ROLE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setSignerRole(String signerRole) {
    this.signerRole = signerRole;
  }


  public SignatureRequestResponseSignatures order(Integer order) {
    this.order = order;
    return this;
  }

   /**
   * If signer order is assigned this is the 0-based index for this signer.
   * @return order
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "If signer order is assigned this is the 0-based index for this signer.")
  @JsonProperty(JSON_PROPERTY_ORDER)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public Integer getOrder() {
    return order;
  }


  @JsonProperty(JSON_PROPERTY_ORDER)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setOrder(Integer order) {
    this.order = order;
  }


  public SignatureRequestResponseSignatures statusCode(String statusCode) {
    this.statusCode = statusCode;
    return this;
  }

   /**
   * The current status of the signature. eg: awaiting_signature, signed, declined.
   * @return statusCode
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "The current status of the signature. eg: awaiting_signature, signed, declined.")
  @JsonProperty(JSON_PROPERTY_STATUS_CODE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getStatusCode() {
    return statusCode;
  }


  @JsonProperty(JSON_PROPERTY_STATUS_CODE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setStatusCode(String statusCode) {
    this.statusCode = statusCode;
  }


  public SignatureRequestResponseSignatures declineReason(String declineReason) {
    this.declineReason = declineReason;
    return this;
  }

   /**
   * The reason provided by the signer for declining the request.
   * @return declineReason
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "The reason provided by the signer for declining the request.")
  @JsonProperty(JSON_PROPERTY_DECLINE_REASON)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getDeclineReason() {
    return declineReason;
  }


  @JsonProperty(JSON_PROPERTY_DECLINE_REASON)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setDeclineReason(String declineReason) {
    this.declineReason = declineReason;
  }


  public SignatureRequestResponseSignatures signedAt(Integer signedAt) {
    this.signedAt = signedAt;
    return this;
  }

   /**
   * Time that the document was signed or null.
   * @return signedAt
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Time that the document was signed or null.")
  @JsonProperty(JSON_PROPERTY_SIGNED_AT)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public Integer getSignedAt() {
    return signedAt;
  }


  @JsonProperty(JSON_PROPERTY_SIGNED_AT)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setSignedAt(Integer signedAt) {
    this.signedAt = signedAt;
  }


  public SignatureRequestResponseSignatures lastViewedAt(Integer lastViewedAt) {
    this.lastViewedAt = lastViewedAt;
    return this;
  }

   /**
   * The time that the document was last viewed by this signer or null.
   * @return lastViewedAt
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "The time that the document was last viewed by this signer or null.")
  @JsonProperty(JSON_PROPERTY_LAST_VIEWED_AT)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public Integer getLastViewedAt() {
    return lastViewedAt;
  }


  @JsonProperty(JSON_PROPERTY_LAST_VIEWED_AT)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setLastViewedAt(Integer lastViewedAt) {
    this.lastViewedAt = lastViewedAt;
  }


  public SignatureRequestResponseSignatures lastRemindedAt(Integer lastRemindedAt) {
    this.lastRemindedAt = lastRemindedAt;
    return this;
  }

   /**
   * The time the last reminder email was sent to the signer or null.
   * @return lastRemindedAt
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "The time the last reminder email was sent to the signer or null.")
  @JsonProperty(JSON_PROPERTY_LAST_REMINDED_AT)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public Integer getLastRemindedAt() {
    return lastRemindedAt;
  }


  @JsonProperty(JSON_PROPERTY_LAST_REMINDED_AT)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setLastRemindedAt(Integer lastRemindedAt) {
    this.lastRemindedAt = lastRemindedAt;
  }


  public SignatureRequestResponseSignatures hasPin(Boolean hasPin) {
    this.hasPin = hasPin;
    return this;
  }

   /**
   * Boolean to indicate whether this signature requires a PIN to access.
   * @return hasPin
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Boolean to indicate whether this signature requires a PIN to access.")
  @JsonProperty(JSON_PROPERTY_HAS_PIN)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public Boolean getHasPin() {
    return hasPin;
  }


  @JsonProperty(JSON_PROPERTY_HAS_PIN)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setHasPin(Boolean hasPin) {
    this.hasPin = hasPin;
  }


  public SignatureRequestResponseSignatures hasSmsAuth(Boolean hasSmsAuth) {
    this.hasSmsAuth = hasSmsAuth;
    return this;
  }

   /**
   * Boolean to indicate whether this signature has SMS authentication enabled.
   * @return hasSmsAuth
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Boolean to indicate whether this signature has SMS authentication enabled.")
  @JsonProperty(JSON_PROPERTY_HAS_SMS_AUTH)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public Boolean getHasSmsAuth() {
    return hasSmsAuth;
  }


  @JsonProperty(JSON_PROPERTY_HAS_SMS_AUTH)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setHasSmsAuth(Boolean hasSmsAuth) {
    this.hasSmsAuth = hasSmsAuth;
  }


  public SignatureRequestResponseSignatures reassignedBy(String reassignedBy) {
    this.reassignedBy = reassignedBy;
    return this;
  }

   /**
   * Email address of original signer who reassigned to this signer.
   * @return reassignedBy
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Email address of original signer who reassigned to this signer.")
  @JsonProperty(JSON_PROPERTY_REASSIGNED_BY)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getReassignedBy() {
    return reassignedBy;
  }


  @JsonProperty(JSON_PROPERTY_REASSIGNED_BY)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setReassignedBy(String reassignedBy) {
    this.reassignedBy = reassignedBy;
  }


  public SignatureRequestResponseSignatures reassignmentReason(String reassignmentReason) {
    this.reassignmentReason = reassignmentReason;
    return this;
  }

   /**
   * Reason provided by original signer who reassigned to this signer.
   * @return reassignmentReason
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Reason provided by original signer who reassigned to this signer.")
  @JsonProperty(JSON_PROPERTY_REASSIGNMENT_REASON)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getReassignmentReason() {
    return reassignmentReason;
  }


  @JsonProperty(JSON_PROPERTY_REASSIGNMENT_REASON)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setReassignmentReason(String reassignmentReason) {
    this.reassignmentReason = reassignmentReason;
  }


  public SignatureRequestResponseSignatures error(String error) {
    this.error = error;
    return this;
  }

   /**
   * Error message pertaining to this signer, or null.
   * @return error
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Error message pertaining to this signer, or null.")
  @JsonProperty(JSON_PROPERTY_ERROR)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getError() {
    return error;
  }


  @JsonProperty(JSON_PROPERTY_ERROR)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setError(String error) {
    this.error = error;
  }


  /**
   * Return true if this SignatureRequestResponseSignatures object is equal to o.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SignatureRequestResponseSignatures signatureRequestResponseSignatures = (SignatureRequestResponseSignatures) o;
    return Objects.equals(this.signatureId, signatureRequestResponseSignatures.signatureId) &&
        Objects.equals(this.signerEmailAddress, signatureRequestResponseSignatures.signerEmailAddress) &&
        Objects.equals(this.signerName, signatureRequestResponseSignatures.signerName) &&
        Objects.equals(this.signerRole, signatureRequestResponseSignatures.signerRole) &&
        Objects.equals(this.order, signatureRequestResponseSignatures.order) &&
        Objects.equals(this.statusCode, signatureRequestResponseSignatures.statusCode) &&
        Objects.equals(this.declineReason, signatureRequestResponseSignatures.declineReason) &&
        Objects.equals(this.signedAt, signatureRequestResponseSignatures.signedAt) &&
        Objects.equals(this.lastViewedAt, signatureRequestResponseSignatures.lastViewedAt) &&
        Objects.equals(this.lastRemindedAt, signatureRequestResponseSignatures.lastRemindedAt) &&
        Objects.equals(this.hasPin, signatureRequestResponseSignatures.hasPin) &&
        Objects.equals(this.hasSmsAuth, signatureRequestResponseSignatures.hasSmsAuth) &&
        Objects.equals(this.reassignedBy, signatureRequestResponseSignatures.reassignedBy) &&
        Objects.equals(this.reassignmentReason, signatureRequestResponseSignatures.reassignmentReason) &&
        Objects.equals(this.error, signatureRequestResponseSignatures.error);
  }

  @Override
  public int hashCode() {
    return Objects.hash(signatureId, signerEmailAddress, signerName, signerRole, order, statusCode, declineReason, signedAt, lastViewedAt, lastRemindedAt, hasPin, hasSmsAuth, reassignedBy, reassignmentReason, error);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SignatureRequestResponseSignatures {\n");
    sb.append("    signatureId: ").append(toIndentedString(signatureId)).append("\n");
    sb.append("    signerEmailAddress: ").append(toIndentedString(signerEmailAddress)).append("\n");
    sb.append("    signerName: ").append(toIndentedString(signerName)).append("\n");
    sb.append("    signerRole: ").append(toIndentedString(signerRole)).append("\n");
    sb.append("    order: ").append(toIndentedString(order)).append("\n");
    sb.append("    statusCode: ").append(toIndentedString(statusCode)).append("\n");
    sb.append("    declineReason: ").append(toIndentedString(declineReason)).append("\n");
    sb.append("    signedAt: ").append(toIndentedString(signedAt)).append("\n");
    sb.append("    lastViewedAt: ").append(toIndentedString(lastViewedAt)).append("\n");
    sb.append("    lastRemindedAt: ").append(toIndentedString(lastRemindedAt)).append("\n");
    sb.append("    hasPin: ").append(toIndentedString(hasPin)).append("\n");
    sb.append("    hasSmsAuth: ").append(toIndentedString(hasSmsAuth)).append("\n");
    sb.append("    reassignedBy: ").append(toIndentedString(reassignedBy)).append("\n");
    sb.append("    reassignmentReason: ").append(toIndentedString(reassignmentReason)).append("\n");
    sb.append("    error: ").append(toIndentedString(error)).append("\n");
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
            signatureId.getClass().equals(String.class) ) {
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
    if (signerEmailAddress != null) {
        if (isFileTypeOrListOfFiles(signerEmailAddress)) {
            fileTypeFound = true;
        }

        if (signerEmailAddress.getClass().equals(java.io.File.class) ||
            signerEmailAddress.getClass().equals(Integer.class) ||
            signerEmailAddress.getClass().equals(String.class) ) {
            map.put("signer_email_address", signerEmailAddress);
        } else if (isListOfFile(signerEmailAddress)) {
            for(int i = 0; i< getListSize(signerEmailAddress); i++) {
                map.put("signer_email_address[" + i + "]", getFromList(signerEmailAddress, i));
            }
        }
        else {
            map.put("signer_email_address", JSON.getDefault().getMapper().writeValueAsString(signerEmailAddress));
        }
    }
    if (signerName != null) {
        if (isFileTypeOrListOfFiles(signerName)) {
            fileTypeFound = true;
        }

        if (signerName.getClass().equals(java.io.File.class) ||
            signerName.getClass().equals(Integer.class) ||
            signerName.getClass().equals(String.class) ) {
            map.put("signer_name", signerName);
        } else if (isListOfFile(signerName)) {
            for(int i = 0; i< getListSize(signerName); i++) {
                map.put("signer_name[" + i + "]", getFromList(signerName, i));
            }
        }
        else {
            map.put("signer_name", JSON.getDefault().getMapper().writeValueAsString(signerName));
        }
    }
    if (signerRole != null) {
        if (isFileTypeOrListOfFiles(signerRole)) {
            fileTypeFound = true;
        }

        if (signerRole.getClass().equals(java.io.File.class) ||
            signerRole.getClass().equals(Integer.class) ||
            signerRole.getClass().equals(String.class) ) {
            map.put("signer_role", signerRole);
        } else if (isListOfFile(signerRole)) {
            for(int i = 0; i< getListSize(signerRole); i++) {
                map.put("signer_role[" + i + "]", getFromList(signerRole, i));
            }
        }
        else {
            map.put("signer_role", JSON.getDefault().getMapper().writeValueAsString(signerRole));
        }
    }
    if (order != null) {
        if (isFileTypeOrListOfFiles(order)) {
            fileTypeFound = true;
        }

        if (order.getClass().equals(java.io.File.class) ||
            order.getClass().equals(Integer.class) ||
            order.getClass().equals(String.class) ) {
            map.put("order", order);
        } else if (isListOfFile(order)) {
            for(int i = 0; i< getListSize(order); i++) {
                map.put("order[" + i + "]", getFromList(order, i));
            }
        }
        else {
            map.put("order", JSON.getDefault().getMapper().writeValueAsString(order));
        }
    }
    if (statusCode != null) {
        if (isFileTypeOrListOfFiles(statusCode)) {
            fileTypeFound = true;
        }

        if (statusCode.getClass().equals(java.io.File.class) ||
            statusCode.getClass().equals(Integer.class) ||
            statusCode.getClass().equals(String.class) ) {
            map.put("status_code", statusCode);
        } else if (isListOfFile(statusCode)) {
            for(int i = 0; i< getListSize(statusCode); i++) {
                map.put("status_code[" + i + "]", getFromList(statusCode, i));
            }
        }
        else {
            map.put("status_code", JSON.getDefault().getMapper().writeValueAsString(statusCode));
        }
    }
    if (declineReason != null) {
        if (isFileTypeOrListOfFiles(declineReason)) {
            fileTypeFound = true;
        }

        if (declineReason.getClass().equals(java.io.File.class) ||
            declineReason.getClass().equals(Integer.class) ||
            declineReason.getClass().equals(String.class) ) {
            map.put("decline_reason", declineReason);
        } else if (isListOfFile(declineReason)) {
            for(int i = 0; i< getListSize(declineReason); i++) {
                map.put("decline_reason[" + i + "]", getFromList(declineReason, i));
            }
        }
        else {
            map.put("decline_reason", JSON.getDefault().getMapper().writeValueAsString(declineReason));
        }
    }
    if (signedAt != null) {
        if (isFileTypeOrListOfFiles(signedAt)) {
            fileTypeFound = true;
        }

        if (signedAt.getClass().equals(java.io.File.class) ||
            signedAt.getClass().equals(Integer.class) ||
            signedAt.getClass().equals(String.class) ) {
            map.put("signed_at", signedAt);
        } else if (isListOfFile(signedAt)) {
            for(int i = 0; i< getListSize(signedAt); i++) {
                map.put("signed_at[" + i + "]", getFromList(signedAt, i));
            }
        }
        else {
            map.put("signed_at", JSON.getDefault().getMapper().writeValueAsString(signedAt));
        }
    }
    if (lastViewedAt != null) {
        if (isFileTypeOrListOfFiles(lastViewedAt)) {
            fileTypeFound = true;
        }

        if (lastViewedAt.getClass().equals(java.io.File.class) ||
            lastViewedAt.getClass().equals(Integer.class) ||
            lastViewedAt.getClass().equals(String.class) ) {
            map.put("last_viewed_at", lastViewedAt);
        } else if (isListOfFile(lastViewedAt)) {
            for(int i = 0; i< getListSize(lastViewedAt); i++) {
                map.put("last_viewed_at[" + i + "]", getFromList(lastViewedAt, i));
            }
        }
        else {
            map.put("last_viewed_at", JSON.getDefault().getMapper().writeValueAsString(lastViewedAt));
        }
    }
    if (lastRemindedAt != null) {
        if (isFileTypeOrListOfFiles(lastRemindedAt)) {
            fileTypeFound = true;
        }

        if (lastRemindedAt.getClass().equals(java.io.File.class) ||
            lastRemindedAt.getClass().equals(Integer.class) ||
            lastRemindedAt.getClass().equals(String.class) ) {
            map.put("last_reminded_at", lastRemindedAt);
        } else if (isListOfFile(lastRemindedAt)) {
            for(int i = 0; i< getListSize(lastRemindedAt); i++) {
                map.put("last_reminded_at[" + i + "]", getFromList(lastRemindedAt, i));
            }
        }
        else {
            map.put("last_reminded_at", JSON.getDefault().getMapper().writeValueAsString(lastRemindedAt));
        }
    }
    if (hasPin != null) {
        if (isFileTypeOrListOfFiles(hasPin)) {
            fileTypeFound = true;
        }

        if (hasPin.getClass().equals(java.io.File.class) ||
            hasPin.getClass().equals(Integer.class) ||
            hasPin.getClass().equals(String.class) ) {
            map.put("has_pin", hasPin);
        } else if (isListOfFile(hasPin)) {
            for(int i = 0; i< getListSize(hasPin); i++) {
                map.put("has_pin[" + i + "]", getFromList(hasPin, i));
            }
        }
        else {
            map.put("has_pin", JSON.getDefault().getMapper().writeValueAsString(hasPin));
        }
    }
    if (hasSmsAuth != null) {
        if (isFileTypeOrListOfFiles(hasSmsAuth)) {
            fileTypeFound = true;
        }

        if (hasSmsAuth.getClass().equals(java.io.File.class) ||
            hasSmsAuth.getClass().equals(Integer.class) ||
            hasSmsAuth.getClass().equals(String.class) ) {
            map.put("has_sms_auth", hasSmsAuth);
        } else if (isListOfFile(hasSmsAuth)) {
            for(int i = 0; i< getListSize(hasSmsAuth); i++) {
                map.put("has_sms_auth[" + i + "]", getFromList(hasSmsAuth, i));
            }
        }
        else {
            map.put("has_sms_auth", JSON.getDefault().getMapper().writeValueAsString(hasSmsAuth));
        }
    }
    if (reassignedBy != null) {
        if (isFileTypeOrListOfFiles(reassignedBy)) {
            fileTypeFound = true;
        }

        if (reassignedBy.getClass().equals(java.io.File.class) ||
            reassignedBy.getClass().equals(Integer.class) ||
            reassignedBy.getClass().equals(String.class) ) {
            map.put("reassigned_by", reassignedBy);
        } else if (isListOfFile(reassignedBy)) {
            for(int i = 0; i< getListSize(reassignedBy); i++) {
                map.put("reassigned_by[" + i + "]", getFromList(reassignedBy, i));
            }
        }
        else {
            map.put("reassigned_by", JSON.getDefault().getMapper().writeValueAsString(reassignedBy));
        }
    }
    if (reassignmentReason != null) {
        if (isFileTypeOrListOfFiles(reassignmentReason)) {
            fileTypeFound = true;
        }

        if (reassignmentReason.getClass().equals(java.io.File.class) ||
            reassignmentReason.getClass().equals(Integer.class) ||
            reassignmentReason.getClass().equals(String.class) ) {
            map.put("reassignment_reason", reassignmentReason);
        } else if (isListOfFile(reassignmentReason)) {
            for(int i = 0; i< getListSize(reassignmentReason); i++) {
                map.put("reassignment_reason[" + i + "]", getFromList(reassignmentReason, i));
            }
        }
        else {
            map.put("reassignment_reason", JSON.getDefault().getMapper().writeValueAsString(reassignmentReason));
        }
    }
    if (error != null) {
        if (isFileTypeOrListOfFiles(error)) {
            fileTypeFound = true;
        }

        if (error.getClass().equals(java.io.File.class) ||
            error.getClass().equals(Integer.class) ||
            error.getClass().equals(String.class) ) {
            map.put("error", error);
        } else if (isListOfFile(error)) {
            for(int i = 0; i< getListSize(error); i++) {
                map.put("error[" + i + "]", getFromList(error, i));
            }
        }
        else {
            map.put("error", JSON.getDefault().getMapper().writeValueAsString(error));
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

