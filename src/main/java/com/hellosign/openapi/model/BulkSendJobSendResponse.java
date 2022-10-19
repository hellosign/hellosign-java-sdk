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
import com.hellosign.openapi.model.BulkSendJobResponse;
import com.hellosign.openapi.model.WarningResponse;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.hellosign.openapi.JSON;


import com.hellosign.openapi.ApiException;
/**
 * BulkSendJobSendResponse
 */
@JsonPropertyOrder({
    BulkSendJobSendResponse.JSON_PROPERTY_BULK_SEND_JOB,
    BulkSendJobSendResponse.JSON_PROPERTY_WARNINGS
})
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen")
public class BulkSendJobSendResponse {
  public static final String JSON_PROPERTY_BULK_SEND_JOB = "bulk_send_job";
  private BulkSendJobResponse bulkSendJob;

  public static final String JSON_PROPERTY_WARNINGS = "warnings";
  private List<WarningResponse> warnings = null;

  public BulkSendJobSendResponse() { 
  }

  public BulkSendJobSendResponse bulkSendJob(BulkSendJobResponse bulkSendJob) {
    this.bulkSendJob = bulkSendJob;
    return this;
  }

   /**
   * Get bulkSendJob
   * @return bulkSendJob
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")
  @JsonProperty(JSON_PROPERTY_BULK_SEND_JOB)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public BulkSendJobResponse getBulkSendJob() {
    return bulkSendJob;
  }


  @JsonProperty(JSON_PROPERTY_BULK_SEND_JOB)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setBulkSendJob(BulkSendJobResponse bulkSendJob) {
    this.bulkSendJob = bulkSendJob;
  }


  public BulkSendJobSendResponse warnings(List<WarningResponse> warnings) {
    this.warnings = warnings;
    return this;
  }

  public BulkSendJobSendResponse addWarningsItem(WarningResponse warningsItem) {
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
   * Return true if this BulkSendJobSendResponse object is equal to o.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    BulkSendJobSendResponse bulkSendJobSendResponse = (BulkSendJobSendResponse) o;
    return Objects.equals(this.bulkSendJob, bulkSendJobSendResponse.bulkSendJob) &&
        Objects.equals(this.warnings, bulkSendJobSendResponse.warnings);
  }

  @Override
  public int hashCode() {
    return Objects.hash(bulkSendJob, warnings);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class BulkSendJobSendResponse {\n");
    sb.append("    bulkSendJob: ").append(toIndentedString(bulkSendJob)).append("\n");
    sb.append("    warnings: ").append(toIndentedString(warnings)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  public Map<String, Object> createFormData() throws ApiException {
    Map<String, Object> map = new HashMap<>();
    boolean fileTypeFound = false;
    try {
    if (bulkSendJob != null) {
        if (isFileTypeOrListOfFiles(bulkSendJob)) {
            fileTypeFound = true;
        }

        if (bulkSendJob.getClass().equals(java.io.File.class) ||
            bulkSendJob.getClass().equals(Integer.class) ||
            bulkSendJob.getClass().equals(String.class) ||
            bulkSendJob.getClass().isEnum()) {
            map.put("bulk_send_job", bulkSendJob);
        } else if (isListOfFile(bulkSendJob)) {
            for(int i = 0; i< getListSize(bulkSendJob); i++) {
                map.put("bulk_send_job[" + i + "]", getFromList(bulkSendJob, i));
            }
        }
        else {
            map.put("bulk_send_job", JSON.getDefault().getMapper().writeValueAsString(bulkSendJob));
        }
    }
    if (warnings != null) {
        if (isFileTypeOrListOfFiles(warnings)) {
            fileTypeFound = true;
        }

        if (warnings.getClass().equals(java.io.File.class) ||
            warnings.getClass().equals(Integer.class) ||
            warnings.getClass().equals(String.class) ||
            warnings.getClass().isEnum()) {
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

