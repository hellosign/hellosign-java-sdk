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
import com.hellosign.openapi.model.EventCallbackAccountRequestPayload;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.hellosign.openapi.JSON;


import com.hellosign.openapi.ApiException;
/**
 * EventCallbackAccountRequest
 */
@JsonPropertyOrder({
    EventCallbackAccountRequest.JSON_PROPERTY_JSON
})
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen")
public class EventCallbackAccountRequest {
  public static final String JSON_PROPERTY_JSON = "json";
  private EventCallbackAccountRequestPayload json;

  public EventCallbackAccountRequest() { 
  }

  public EventCallbackAccountRequest json(EventCallbackAccountRequestPayload json) {
    this.json = json;
    return this;
  }

   /**
   * Get json
   * @return json
  **/
  @javax.annotation.Nonnull
  @ApiModelProperty(required = true, value = "")
  @JsonProperty(JSON_PROPERTY_JSON)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)

  public EventCallbackAccountRequestPayload getJson() {
    return json;
  }


  @JsonProperty(JSON_PROPERTY_JSON)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)
  public void setJson(EventCallbackAccountRequestPayload json) {
    this.json = json;
  }


  /**
   * Return true if this EventCallbackAccountRequest object is equal to o.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    EventCallbackAccountRequest eventCallbackAccountRequest = (EventCallbackAccountRequest) o;
    return Objects.equals(this.json, eventCallbackAccountRequest.json);
  }

  @Override
  public int hashCode() {
    return Objects.hash(json);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class EventCallbackAccountRequest {\n");
    sb.append("    json: ").append(toIndentedString(json)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  public Map<String, Object> createFormData() throws ApiException {
    Map<String, Object> map = new HashMap<>();
    boolean fileTypeFound = false;
    try {
    if (json != null) {
        if (isFileTypeOrListOfFiles(json)) {
            fileTypeFound = true;
        }

        if (json.getClass().equals(java.io.File.class) ||
            json.getClass().equals(Integer.class) ||
            json.getClass().equals(String.class) ||
            json.getClass().isEnum()) {
            map.put("json", json);
        } else if (isListOfFile(json)) {
            for(int i = 0; i< getListSize(json); i++) {
                map.put("json[" + i + "]", getFromList(json, i));
            }
        }
        else {
            map.put("json", JSON.getDefault().getMapper().writeValueAsString(json));
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

