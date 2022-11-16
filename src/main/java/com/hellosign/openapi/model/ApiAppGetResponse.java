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
import com.hellosign.openapi.model.ApiAppResponse;
import com.hellosign.openapi.model.WarningResponse;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.hellosign.openapi.JSON;


import com.hellosign.openapi.ApiException;
/**
 * ApiAppGetResponse
 */
@JsonPropertyOrder({
    ApiAppGetResponse.JSON_PROPERTY_API_APP,
    ApiAppGetResponse.JSON_PROPERTY_WARNINGS
})
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen")
public class ApiAppGetResponse {
  public static final String JSON_PROPERTY_API_APP = "api_app";
  private ApiAppResponse apiApp;

  public static final String JSON_PROPERTY_WARNINGS = "warnings";
  private List<WarningResponse> warnings = null;

  public ApiAppGetResponse() { 
  }

  public ApiAppGetResponse apiApp(ApiAppResponse apiApp) {
    this.apiApp = apiApp;
    return this;
  }

   /**
   * Get apiApp
   * @return apiApp
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")
  @JsonProperty(JSON_PROPERTY_API_APP)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public ApiAppResponse getApiApp() {
    return apiApp;
  }


  @JsonProperty(JSON_PROPERTY_API_APP)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setApiApp(ApiAppResponse apiApp) {
    this.apiApp = apiApp;
  }


  public ApiAppGetResponse warnings(List<WarningResponse> warnings) {
    this.warnings = warnings;
    return this;
  }

  public ApiAppGetResponse addWarningsItem(WarningResponse warningsItem) {
    if (this.warnings == null) {
      this.warnings = new ArrayList<>();
    }
    this.warnings.add(warningsItem);
    return this;
  }

   /**
   * A list of warnings.
   * @return warnings
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "A list of warnings.")
  @JsonProperty(JSON_PROPERTY_WARNINGS)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public List<WarningResponse> getWarnings() {
    return warnings;
  }


  @JsonProperty(JSON_PROPERTY_WARNINGS)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setWarnings(List<WarningResponse> warnings) {
    this.warnings = warnings;
  }


  /**
   * Return true if this ApiAppGetResponse object is equal to o.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ApiAppGetResponse apiAppGetResponse = (ApiAppGetResponse) o;
    return Objects.equals(this.apiApp, apiAppGetResponse.apiApp) &&
        Objects.equals(this.warnings, apiAppGetResponse.warnings);
  }

  @Override
  public int hashCode() {
    return Objects.hash(apiApp, warnings);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ApiAppGetResponse {\n");
    sb.append("    apiApp: ").append(toIndentedString(apiApp)).append("\n");
    sb.append("    warnings: ").append(toIndentedString(warnings)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  public Map<String, Object> createFormData() throws ApiException {
    Map<String, Object> map = new HashMap<>();
    boolean fileTypeFound = false;
    try {
    if (apiApp != null) {
        if (isFileTypeOrListOfFiles(apiApp)) {
            fileTypeFound = true;
        }

        if (apiApp.getClass().equals(java.io.File.class) ||
            apiApp.getClass().equals(Integer.class) ||
            apiApp.getClass().equals(String.class) ) {
            map.put("api_app", apiApp);
        } else if (isListOfFile(apiApp)) {
            for(int i = 0; i< getListSize(apiApp); i++) {
                map.put("api_app[" + i + "]", getFromList(apiApp, i));
            }
        }
        else {
            map.put("api_app", JSON.getDefault().getMapper().writeValueAsString(apiApp));
        }
    }
    if (warnings != null) {
        if (isFileTypeOrListOfFiles(warnings)) {
            fileTypeFound = true;
        }

        if (warnings.getClass().equals(java.io.File.class) ||
            warnings.getClass().equals(Integer.class) ||
            warnings.getClass().equals(String.class) ) {
            map.put("warnings", warnings);
        } else if (isListOfFile(warnings)) {
            for(int i = 0; i< getListSize(warnings); i++) {
                map.put("warnings[" + i + "]", getFromList(warnings, i));
            }
        }
        else {
            map.put("warnings", JSON.getDefault().getMapper().writeValueAsString(warnings));
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

