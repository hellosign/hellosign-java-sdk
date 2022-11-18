/*
 * Dropbox Sign API
 * Dropbox Sign v3 API
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
 * Specific metadata about the event.
 */
@ApiModel(description = "Specific metadata about the event.")
@JsonPropertyOrder({
    EventCallbackRequestEventMetadata.JSON_PROPERTY_RELATED_SIGNATURE_ID,
    EventCallbackRequestEventMetadata.JSON_PROPERTY_REPORTED_FOR_ACCOUNT_ID,
    EventCallbackRequestEventMetadata.JSON_PROPERTY_REPORTED_FOR_APP_ID,
    EventCallbackRequestEventMetadata.JSON_PROPERTY_EVENT_MESSAGE
})
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen")
public class EventCallbackRequestEventMetadata {
  public static final String JSON_PROPERTY_RELATED_SIGNATURE_ID = "related_signature_id";
  private String relatedSignatureId;

  public static final String JSON_PROPERTY_REPORTED_FOR_ACCOUNT_ID = "reported_for_account_id";
  private String reportedForAccountId;

  public static final String JSON_PROPERTY_REPORTED_FOR_APP_ID = "reported_for_app_id";
  private String reportedForAppId;

  public static final String JSON_PROPERTY_EVENT_MESSAGE = "event_message";
  private String eventMessage;

  public EventCallbackRequestEventMetadata() { 
  }

  public EventCallbackRequestEventMetadata relatedSignatureId(String relatedSignatureId) {
    this.relatedSignatureId = relatedSignatureId;
    return this;
  }

   /**
   * Signature ID for a specific signer. Applicable to &#x60;signature_request_signed&#x60; and &#x60;signature_request_viewed&#x60; events.
   * @return relatedSignatureId
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Signature ID for a specific signer. Applicable to `signature_request_signed` and `signature_request_viewed` events.")
  @JsonProperty(JSON_PROPERTY_RELATED_SIGNATURE_ID)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getRelatedSignatureId() {
    return relatedSignatureId;
  }


  @JsonProperty(JSON_PROPERTY_RELATED_SIGNATURE_ID)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setRelatedSignatureId(String relatedSignatureId) {
    this.relatedSignatureId = relatedSignatureId;
  }


  public EventCallbackRequestEventMetadata reportedForAccountId(String reportedForAccountId) {
    this.reportedForAccountId = reportedForAccountId;
    return this;
  }

   /**
   * Account ID the event was reported for.
   * @return reportedForAccountId
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Account ID the event was reported for.")
  @JsonProperty(JSON_PROPERTY_REPORTED_FOR_ACCOUNT_ID)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getReportedForAccountId() {
    return reportedForAccountId;
  }


  @JsonProperty(JSON_PROPERTY_REPORTED_FOR_ACCOUNT_ID)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setReportedForAccountId(String reportedForAccountId) {
    this.reportedForAccountId = reportedForAccountId;
  }


  public EventCallbackRequestEventMetadata reportedForAppId(String reportedForAppId) {
    this.reportedForAppId = reportedForAppId;
    return this;
  }

   /**
   * App ID the event was reported for.
   * @return reportedForAppId
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "App ID the event was reported for.")
  @JsonProperty(JSON_PROPERTY_REPORTED_FOR_APP_ID)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getReportedForAppId() {
    return reportedForAppId;
  }


  @JsonProperty(JSON_PROPERTY_REPORTED_FOR_APP_ID)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setReportedForAppId(String reportedForAppId) {
    this.reportedForAppId = reportedForAppId;
  }


  public EventCallbackRequestEventMetadata eventMessage(String eventMessage) {
    this.eventMessage = eventMessage;
    return this;
  }

   /**
   * Message about a declined or failed (due to error) signature flow.
   * @return eventMessage
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Message about a declined or failed (due to error) signature flow.")
  @JsonProperty(JSON_PROPERTY_EVENT_MESSAGE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getEventMessage() {
    return eventMessage;
  }


  @JsonProperty(JSON_PROPERTY_EVENT_MESSAGE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setEventMessage(String eventMessage) {
    this.eventMessage = eventMessage;
  }


  /**
   * Return true if this EventCallbackRequestEventMetadata object is equal to o.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    EventCallbackRequestEventMetadata eventCallbackRequestEventMetadata = (EventCallbackRequestEventMetadata) o;
    return Objects.equals(this.relatedSignatureId, eventCallbackRequestEventMetadata.relatedSignatureId) &&
        Objects.equals(this.reportedForAccountId, eventCallbackRequestEventMetadata.reportedForAccountId) &&
        Objects.equals(this.reportedForAppId, eventCallbackRequestEventMetadata.reportedForAppId) &&
        Objects.equals(this.eventMessage, eventCallbackRequestEventMetadata.eventMessage);
  }

  @Override
  public int hashCode() {
    return Objects.hash(relatedSignatureId, reportedForAccountId, reportedForAppId, eventMessage);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class EventCallbackRequestEventMetadata {\n");
    sb.append("    relatedSignatureId: ").append(toIndentedString(relatedSignatureId)).append("\n");
    sb.append("    reportedForAccountId: ").append(toIndentedString(reportedForAccountId)).append("\n");
    sb.append("    reportedForAppId: ").append(toIndentedString(reportedForAppId)).append("\n");
    sb.append("    eventMessage: ").append(toIndentedString(eventMessage)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  public Map<String, Object> createFormData() throws ApiException {
    Map<String, Object> map = new HashMap<>();
    boolean fileTypeFound = false;
    try {
    if (relatedSignatureId != null) {
        if (isFileTypeOrListOfFiles(relatedSignatureId)) {
            fileTypeFound = true;
        }

        if (relatedSignatureId.getClass().equals(java.io.File.class) ||
            relatedSignatureId.getClass().equals(Integer.class) ||
            relatedSignatureId.getClass().equals(String.class) ||
            relatedSignatureId.getClass().isEnum()) {
            map.put("related_signature_id", relatedSignatureId);
        } else if (isListOfFile(relatedSignatureId)) {
            for(int i = 0; i< getListSize(relatedSignatureId); i++) {
                map.put("related_signature_id[" + i + "]", getFromList(relatedSignatureId, i));
            }
        }
        else {
            map.put("related_signature_id", JSON.getDefault().getMapper().writeValueAsString(relatedSignatureId));
        }
    }
    if (reportedForAccountId != null) {
        if (isFileTypeOrListOfFiles(reportedForAccountId)) {
            fileTypeFound = true;
        }

        if (reportedForAccountId.getClass().equals(java.io.File.class) ||
            reportedForAccountId.getClass().equals(Integer.class) ||
            reportedForAccountId.getClass().equals(String.class) ||
            reportedForAccountId.getClass().isEnum()) {
            map.put("reported_for_account_id", reportedForAccountId);
        } else if (isListOfFile(reportedForAccountId)) {
            for(int i = 0; i< getListSize(reportedForAccountId); i++) {
                map.put("reported_for_account_id[" + i + "]", getFromList(reportedForAccountId, i));
            }
        }
        else {
            map.put("reported_for_account_id", JSON.getDefault().getMapper().writeValueAsString(reportedForAccountId));
        }
    }
    if (reportedForAppId != null) {
        if (isFileTypeOrListOfFiles(reportedForAppId)) {
            fileTypeFound = true;
        }

        if (reportedForAppId.getClass().equals(java.io.File.class) ||
            reportedForAppId.getClass().equals(Integer.class) ||
            reportedForAppId.getClass().equals(String.class) ||
            reportedForAppId.getClass().isEnum()) {
            map.put("reported_for_app_id", reportedForAppId);
        } else if (isListOfFile(reportedForAppId)) {
            for(int i = 0; i< getListSize(reportedForAppId); i++) {
                map.put("reported_for_app_id[" + i + "]", getFromList(reportedForAppId, i));
            }
        }
        else {
            map.put("reported_for_app_id", JSON.getDefault().getMapper().writeValueAsString(reportedForAppId));
        }
    }
    if (eventMessage != null) {
        if (isFileTypeOrListOfFiles(eventMessage)) {
            fileTypeFound = true;
        }

        if (eventMessage.getClass().equals(java.io.File.class) ||
            eventMessage.getClass().equals(Integer.class) ||
            eventMessage.getClass().equals(String.class) ||
            eventMessage.getClass().isEnum()) {
            map.put("event_message", eventMessage);
        } else if (isListOfFile(eventMessage)) {
            for(int i = 0; i< getListSize(eventMessage); i++) {
                map.put("event_message[" + i + "]", getFromList(eventMessage, i));
            }
        }
        else {
            map.put("event_message", JSON.getDefault().getMapper().writeValueAsString(eventMessage));
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

