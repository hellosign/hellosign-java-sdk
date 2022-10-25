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
 * An object with options that override account settings.
 */
@ApiModel(description = "An object with options that override account settings.")
@JsonPropertyOrder({
    ApiAppResponseOptions.JSON_PROPERTY_CAN_INSERT_EVERYWHERE
})
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen")
public class ApiAppResponseOptions {
  public static final String JSON_PROPERTY_CAN_INSERT_EVERYWHERE = "can_insert_everywhere";
  private Boolean canInsertEverywhere;

  public ApiAppResponseOptions() { 
  }

  public ApiAppResponseOptions canInsertEverywhere(Boolean canInsertEverywhere) {
    this.canInsertEverywhere = canInsertEverywhere;
    return this;
  }

   /**
   * Boolean denoting if signers can \&quot;Insert Everywhere\&quot; in one click while signing a document
   * @return canInsertEverywhere
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Boolean denoting if signers can \"Insert Everywhere\" in one click while signing a document")
  @JsonProperty(JSON_PROPERTY_CAN_INSERT_EVERYWHERE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public Boolean getCanInsertEverywhere() {
    return canInsertEverywhere;
  }


  @JsonProperty(JSON_PROPERTY_CAN_INSERT_EVERYWHERE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setCanInsertEverywhere(Boolean canInsertEverywhere) {
    this.canInsertEverywhere = canInsertEverywhere;
  }


  /**
   * Return true if this ApiAppResponseOptions object is equal to o.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ApiAppResponseOptions apiAppResponseOptions = (ApiAppResponseOptions) o;
    return Objects.equals(this.canInsertEverywhere, apiAppResponseOptions.canInsertEverywhere);
  }

  @Override
  public int hashCode() {
    return Objects.hash(canInsertEverywhere);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ApiAppResponseOptions {\n");
    sb.append("    canInsertEverywhere: ").append(toIndentedString(canInsertEverywhere)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  public Map<String, Object> createFormData() throws ApiException {
    Map<String, Object> map = new HashMap<>();
    boolean fileTypeFound = false;
    try {
    if (canInsertEverywhere != null) {
        if (isFileTypeOrListOfFiles(canInsertEverywhere)) {
            fileTypeFound = true;
        }

        if (canInsertEverywhere.getClass().equals(java.io.File.class) ||
            canInsertEverywhere.getClass().equals(Integer.class) ||
            canInsertEverywhere.getClass().equals(String.class) ||
            canInsertEverywhere.getClass().isEnum() ) {
            map.put("can_insert_everywhere", canInsertEverywhere);
        } else if (isListOfFile(canInsertEverywhere)) {
            for(int i = 0; i< getListSize(canInsertEverywhere); i++) {
                map.put("can_insert_everywhere[" + i + "]", getFromList(canInsertEverywhere, i));
            }
        }
        else {
            map.put("can_insert_everywhere", JSON.getDefault().getMapper().writeValueAsString(canInsertEverywhere));
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

