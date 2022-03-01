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
 * WarningResponse
 */
@JsonPropertyOrder({
    WarningResponse.JSON_PROPERTY_WARNING_MSG,
    WarningResponse.JSON_PROPERTY_WARNING_NAME
})
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen")
public class WarningResponse {
  public static final String JSON_PROPERTY_WARNING_MSG = "warning_msg";
  private String warningMsg;

  public static final String JSON_PROPERTY_WARNING_NAME = "warning_name";
  private String warningName;

  public WarningResponse() { 
  }

  public WarningResponse warningMsg(String warningMsg) {
    this.warningMsg = warningMsg;
    return this;
  }

   /**
   * Get warningMsg
   * @return warningMsg
  **/
  @javax.annotation.Nonnull
  @ApiModelProperty(required = true, value = "")
  @JsonProperty(JSON_PROPERTY_WARNING_MSG)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)

  public String getWarningMsg() {
    return warningMsg;
  }


  @JsonProperty(JSON_PROPERTY_WARNING_MSG)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)
  public void setWarningMsg(String warningMsg) {
    this.warningMsg = warningMsg;
  }


  public WarningResponse warningName(String warningName) {
    this.warningName = warningName;
    return this;
  }

   /**
   * Get warningName
   * @return warningName
  **/
  @javax.annotation.Nonnull
  @ApiModelProperty(required = true, value = "")
  @JsonProperty(JSON_PROPERTY_WARNING_NAME)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)

  public String getWarningName() {
    return warningName;
  }


  @JsonProperty(JSON_PROPERTY_WARNING_NAME)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)
  public void setWarningName(String warningName) {
    this.warningName = warningName;
  }


  /**
   * Return true if this WarningResponse object is equal to o.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    WarningResponse warningResponse = (WarningResponse) o;
    return Objects.equals(this.warningMsg, warningResponse.warningMsg) &&
        Objects.equals(this.warningName, warningResponse.warningName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(warningMsg, warningName);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class WarningResponse {\n");
    sb.append("    warningMsg: ").append(toIndentedString(warningMsg)).append("\n");
    sb.append("    warningName: ").append(toIndentedString(warningName)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  public Map<String, Object> createFormData() throws ApiException {
    Map<String, Object> map = new HashMap<>();
    boolean fileTypeFound = false;
    try {
    if (warningMsg != null) {
        if (isFileTypeOrListOfFiles(warningMsg)) {
            fileTypeFound = true;
        }

        if (warningMsg.getClass().equals(java.io.File.class) ||
            warningMsg.getClass().equals(Integer.class) ||
            warningMsg.getClass().equals(String.class) ) {
            map.put("warning_msg", warningMsg);
        } else if (isListOfFile(warningMsg)) {
            for(int i = 0; i< getListSize(warningMsg); i++) {
                map.put("warning_msg[" + i + "]", getFromList(warningMsg, i));
            }
        }
        else {
            map.put("warning_msg", JSON.getDefault().getMapper().writeValueAsString(warningMsg));
        }
    }
    if (warningName != null) {
        if (isFileTypeOrListOfFiles(warningName)) {
            fileTypeFound = true;
        }

        if (warningName.getClass().equals(java.io.File.class) ||
            warningName.getClass().equals(Integer.class) ||
            warningName.getClass().equals(String.class) ) {
            map.put("warning_name", warningName);
        } else if (isListOfFile(warningName)) {
            for(int i = 0; i< getListSize(warningName); i++) {
                map.put("warning_name[" + i + "]", getFromList(warningName, i));
            }
        }
        else {
            map.put("warning_name", JSON.getDefault().getMapper().writeValueAsString(warningName));
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

