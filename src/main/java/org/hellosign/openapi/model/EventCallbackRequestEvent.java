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
import org.hellosign.openapi.model.EventCallbackRequestEventMetadata;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.hellosign.openapi.JSON;


import org.hellosign.openapi.ApiException;
/**
 * EventCallbackRequestEvent
 */
@JsonPropertyOrder({
    EventCallbackRequestEvent.JSON_PROPERTY_EVENT_TIME,
    EventCallbackRequestEvent.JSON_PROPERTY_EVENT_TYPE,
    EventCallbackRequestEvent.JSON_PROPERTY_EVENT_HASH,
    EventCallbackRequestEvent.JSON_PROPERTY_EVENT_METADATA
})
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen")
public class EventCallbackRequestEvent {
  public static final String JSON_PROPERTY_EVENT_TIME = "event_time";
  private String eventTime;

  /**
   * Gets or Sets eventType
   */
  public enum EventTypeEnum {
    ACCOUNT_CONFIRMED("account_confirmed"),
    
    UNKNOWN_ERROR("unknown_error"),
    
    FILE_ERROR("file_error"),
    
    SIGN_URL_INVALID("sign_url_invalid"),
    
    SIGNATURE_REQUEST_VIEWED("signature_request_viewed"),
    
    SIGNATURE_REQUEST_SIGNED("signature_request_signed"),
    
    SIGNATURE_REQUEST_SENT("signature_request_sent"),
    
    SIGNATURE_REQUEST_ALL_SIGNED("signature_request_all_signed"),
    
    SIGNATURE_REQUEST_EMAIL_BOUNCE("signature_request_email_bounce"),
    
    SIGNATURE_REQUEST_REMIND("signature_request_remind"),
    
    SIGNATURE_REQUEST_INCOMPLETE_QES("signature_request_incomplete_qes"),
    
    SIGNATURE_REQUEST_DESTROYED("signature_request_destroyed"),
    
    SIGNATURE_REQUEST_CANCELED("signature_request_canceled"),
    
    SIGNATURE_REQUEST_DOWNLOADABLE("signature_request_downloadable"),
    
    SIGNATURE_REQUEST_DECLINED("signature_request_declined"),
    
    SIGNATURE_REQUEST_REASSIGNED("signature_request_reassigned"),
    
    SIGNATURE_REQUEST_INVALID("signature_request_invalid"),
    
    SIGNATURE_REQUEST_PREPARED("signature_request_prepared"),
    
    TEMPLATE_CREATED("template_created"),
    
    TEMPLATE_ERROR("template_error");

    private String value;

    EventTypeEnum(String value) {
      this.value = value;
    }

    @JsonValue
    public String getValue() {
      return value;
    }

    @Override
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static EventTypeEnum fromValue(String value) {
      for (EventTypeEnum b : EventTypeEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
  }

  public static final String JSON_PROPERTY_EVENT_TYPE = "event_type";
  private EventTypeEnum eventType;

  public static final String JSON_PROPERTY_EVENT_HASH = "event_hash";
  private String eventHash;

  public static final String JSON_PROPERTY_EVENT_METADATA = "event_metadata";
  private EventCallbackRequestEventMetadata eventMetadata;

  public EventCallbackRequestEvent() { 
  }

  public EventCallbackRequestEvent eventTime(String eventTime) {
    this.eventTime = eventTime;
    return this;
  }

   /**
   * Get eventTime
   * @return eventTime
  **/
  @javax.annotation.Nonnull
  @ApiModelProperty(required = true, value = "")
  @JsonProperty(JSON_PROPERTY_EVENT_TIME)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)

  public String getEventTime() {
    return eventTime;
  }


  @JsonProperty(JSON_PROPERTY_EVENT_TIME)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)
  public void setEventTime(String eventTime) {
    this.eventTime = eventTime;
  }


  public EventCallbackRequestEvent eventType(EventTypeEnum eventType) {
    this.eventType = eventType;
    return this;
  }

   /**
   * Get eventType
   * @return eventType
  **/
  @javax.annotation.Nonnull
  @ApiModelProperty(required = true, value = "")
  @JsonProperty(JSON_PROPERTY_EVENT_TYPE)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)

  public EventTypeEnum getEventType() {
    return eventType;
  }


  @JsonProperty(JSON_PROPERTY_EVENT_TYPE)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)
  public void setEventType(EventTypeEnum eventType) {
    this.eventType = eventType;
  }


  public EventCallbackRequestEvent eventHash(String eventHash) {
    this.eventHash = eventHash;
    return this;
  }

   /**
   * Get eventHash
   * @return eventHash
  **/
  @javax.annotation.Nonnull
  @ApiModelProperty(required = true, value = "")
  @JsonProperty(JSON_PROPERTY_EVENT_HASH)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)

  public String getEventHash() {
    return eventHash;
  }


  @JsonProperty(JSON_PROPERTY_EVENT_HASH)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)
  public void setEventHash(String eventHash) {
    this.eventHash = eventHash;
  }


  public EventCallbackRequestEvent eventMetadata(EventCallbackRequestEventMetadata eventMetadata) {
    this.eventMetadata = eventMetadata;
    return this;
  }

   /**
   * Get eventMetadata
   * @return eventMetadata
  **/
  @javax.annotation.Nonnull
  @ApiModelProperty(required = true, value = "")
  @JsonProperty(JSON_PROPERTY_EVENT_METADATA)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)

  public EventCallbackRequestEventMetadata getEventMetadata() {
    return eventMetadata;
  }


  @JsonProperty(JSON_PROPERTY_EVENT_METADATA)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)
  public void setEventMetadata(EventCallbackRequestEventMetadata eventMetadata) {
    this.eventMetadata = eventMetadata;
  }


  /**
   * Return true if this EventCallbackRequestEvent object is equal to o.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    EventCallbackRequestEvent eventCallbackRequestEvent = (EventCallbackRequestEvent) o;
    return Objects.equals(this.eventTime, eventCallbackRequestEvent.eventTime) &&
        Objects.equals(this.eventType, eventCallbackRequestEvent.eventType) &&
        Objects.equals(this.eventHash, eventCallbackRequestEvent.eventHash) &&
        Objects.equals(this.eventMetadata, eventCallbackRequestEvent.eventMetadata);
  }

  @Override
  public int hashCode() {
    return Objects.hash(eventTime, eventType, eventHash, eventMetadata);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class EventCallbackRequestEvent {\n");
    sb.append("    eventTime: ").append(toIndentedString(eventTime)).append("\n");
    sb.append("    eventType: ").append(toIndentedString(eventType)).append("\n");
    sb.append("    eventHash: ").append(toIndentedString(eventHash)).append("\n");
    sb.append("    eventMetadata: ").append(toIndentedString(eventMetadata)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  public Map<String, Object> createFormData() throws ApiException {
    Map<String, Object> map = new HashMap<>();
    boolean fileTypeFound = false;
    try {
    if (eventTime != null) {
        if (isFileTypeOrListOfFiles(eventTime)) {
            fileTypeFound = true;
        }

        if (eventTime.getClass().equals(java.io.File.class) ||
            eventTime.getClass().equals(Integer.class) ||
            eventTime.getClass().equals(String.class) ) {
            map.put("event_time", eventTime);
        } else if (isListOfFile(eventTime)) {
            for(int i = 0; i< getListSize(eventTime); i++) {
                map.put("event_time[" + i + "]", getFromList(eventTime, i));
            }
        }
        else {
            map.put("event_time", JSON.getDefault().getMapper().writeValueAsString(eventTime));
        }
    }
    if (eventType != null) {
        if (isFileTypeOrListOfFiles(eventType)) {
            fileTypeFound = true;
        }

        if (eventType.getClass().equals(java.io.File.class) ||
            eventType.getClass().equals(Integer.class) ||
            eventType.getClass().equals(String.class) ) {
            map.put("event_type", eventType);
        } else if (isListOfFile(eventType)) {
            for(int i = 0; i< getListSize(eventType); i++) {
                map.put("event_type[" + i + "]", getFromList(eventType, i));
            }
        }
        else {
            map.put("event_type", JSON.getDefault().getMapper().writeValueAsString(eventType));
        }
    }
    if (eventHash != null) {
        if (isFileTypeOrListOfFiles(eventHash)) {
            fileTypeFound = true;
        }

        if (eventHash.getClass().equals(java.io.File.class) ||
            eventHash.getClass().equals(Integer.class) ||
            eventHash.getClass().equals(String.class) ) {
            map.put("event_hash", eventHash);
        } else if (isListOfFile(eventHash)) {
            for(int i = 0; i< getListSize(eventHash); i++) {
                map.put("event_hash[" + i + "]", getFromList(eventHash, i));
            }
        }
        else {
            map.put("event_hash", JSON.getDefault().getMapper().writeValueAsString(eventHash));
        }
    }
    if (eventMetadata != null) {
        if (isFileTypeOrListOfFiles(eventMetadata)) {
            fileTypeFound = true;
        }

        if (eventMetadata.getClass().equals(java.io.File.class) ||
            eventMetadata.getClass().equals(Integer.class) ||
            eventMetadata.getClass().equals(String.class) ) {
            map.put("event_metadata", eventMetadata);
        } else if (isListOfFile(eventMetadata)) {
            for(int i = 0; i< getListSize(eventMetadata); i++) {
                map.put("event_metadata[" + i + "]", getFromList(eventMetadata, i));
            }
        }
        else {
            map.put("event_metadata", JSON.getDefault().getMapper().writeValueAsString(eventMetadata));
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

