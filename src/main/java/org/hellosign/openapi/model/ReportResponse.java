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
import java.util.ArrayList;
import java.util.List;
import org.hellosign.openapi.model.WarningResponse;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.hellosign.openapi.JSON;


import org.hellosign.openapi.ApiException;
/**
 * ReportResponse
 */
@JsonPropertyOrder({
    ReportResponse.JSON_PROPERTY_SUCCESS,
    ReportResponse.JSON_PROPERTY_START_DATE,
    ReportResponse.JSON_PROPERTY_END_DATE,
    ReportResponse.JSON_PROPERTY_REPORT_TYPE,
    ReportResponse.JSON_PROPERTY_WARNINGS
})
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen")
public class ReportResponse {
  public static final String JSON_PROPERTY_SUCCESS = "success";
  private String success;

  public static final String JSON_PROPERTY_START_DATE = "start_date";
  private String startDate;

  public static final String JSON_PROPERTY_END_DATE = "end_date";
  private String endDate;

  /**
   * Gets or Sets reportType
   */
  public enum ReportTypeEnum {
    USER_ACTIVITY("user_activity"),
    
    DOCUMENT_STATUS("document_status");

    private String value;

    ReportTypeEnum(String value) {
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
    public static ReportTypeEnum fromValue(String value) {
      for (ReportTypeEnum b : ReportTypeEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
  }

  public static final String JSON_PROPERTY_REPORT_TYPE = "report_type";
  private List<ReportTypeEnum> reportType = null;

  public static final String JSON_PROPERTY_WARNINGS = "warnings";
  private List<WarningResponse> warnings = null;

  public ReportResponse() { 
  }

  public ReportResponse success(String success) {
    this.success = success;
    return this;
  }

   /**
   * A message indicating the requested operation&#39;s success
   * @return success
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "A message indicating the requested operation's success")
  @JsonProperty(JSON_PROPERTY_SUCCESS)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getSuccess() {
    return success;
  }


  @JsonProperty(JSON_PROPERTY_SUCCESS)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setSuccess(String success) {
    this.success = success;
  }


  public ReportResponse startDate(String startDate) {
    this.startDate = startDate;
    return this;
  }

   /**
   * The (inclusive) start date for the report data in MM/DD/YYYY format.
   * @return startDate
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "The (inclusive) start date for the report data in MM/DD/YYYY format.")
  @JsonProperty(JSON_PROPERTY_START_DATE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getStartDate() {
    return startDate;
  }


  @JsonProperty(JSON_PROPERTY_START_DATE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setStartDate(String startDate) {
    this.startDate = startDate;
  }


  public ReportResponse endDate(String endDate) {
    this.endDate = endDate;
    return this;
  }

   /**
   * The (inclusive) end date for the report data in MM/DD/YYYY format.
   * @return endDate
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "The (inclusive) end date for the report data in MM/DD/YYYY format.")
  @JsonProperty(JSON_PROPERTY_END_DATE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getEndDate() {
    return endDate;
  }


  @JsonProperty(JSON_PROPERTY_END_DATE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setEndDate(String endDate) {
    this.endDate = endDate;
  }


  public ReportResponse reportType(List<ReportTypeEnum> reportType) {
    this.reportType = reportType;
    return this;
  }

  public ReportResponse addReportTypeItem(ReportTypeEnum reportTypeItem) {
    if (this.reportType == null) {
      this.reportType = new ArrayList<>();
    }
    this.reportType.add(reportTypeItem);
    return this;
  }

   /**
   * The type(s) of the report you are requesting. Allowed values are \&quot;user_activity\&quot; and \&quot;document_status\&quot;. User activity reports contain list of all users and their activity during the specified date range. Document status report contain a list of signature requests created in the specified time range (and their status).
   * @return reportType
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "The type(s) of the report you are requesting. Allowed values are \"user_activity\" and \"document_status\". User activity reports contain list of all users and their activity during the specified date range. Document status report contain a list of signature requests created in the specified time range (and their status).")
  @JsonProperty(JSON_PROPERTY_REPORT_TYPE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public List<ReportTypeEnum> getReportType() {
    return reportType;
  }


  @JsonProperty(JSON_PROPERTY_REPORT_TYPE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setReportType(List<ReportTypeEnum> reportType) {
    this.reportType = reportType;
  }


  public ReportResponse warnings(List<WarningResponse> warnings) {
    this.warnings = warnings;
    return this;
  }

  public ReportResponse addWarningsItem(WarningResponse warningsItem) {
    if (this.warnings == null) {
      this.warnings = new ArrayList<>();
    }
    this.warnings.add(warningsItem);
    return this;
  }

   /**
   * Get warnings
   * @return warnings
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")
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
   * Return true if this ReportResponse object is equal to o.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ReportResponse reportResponse = (ReportResponse) o;
    return Objects.equals(this.success, reportResponse.success) &&
        Objects.equals(this.startDate, reportResponse.startDate) &&
        Objects.equals(this.endDate, reportResponse.endDate) &&
        Objects.equals(this.reportType, reportResponse.reportType) &&
        Objects.equals(this.warnings, reportResponse.warnings);
  }

  @Override
  public int hashCode() {
    return Objects.hash(success, startDate, endDate, reportType, warnings);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ReportResponse {\n");
    sb.append("    success: ").append(toIndentedString(success)).append("\n");
    sb.append("    startDate: ").append(toIndentedString(startDate)).append("\n");
    sb.append("    endDate: ").append(toIndentedString(endDate)).append("\n");
    sb.append("    reportType: ").append(toIndentedString(reportType)).append("\n");
    sb.append("    warnings: ").append(toIndentedString(warnings)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  public Map<String, Object> createFormData() throws ApiException {
    Map<String, Object> map = new HashMap<>();
    boolean fileTypeFound = false;
    try {
    if (success != null) {
        if (isFileTypeOrListOfFiles(success)) {
            fileTypeFound = true;
        }

        if (success.getClass().equals(java.io.File.class) ||
            success.getClass().equals(Integer.class) ||
            success.getClass().equals(String.class) ) {
            map.put("success", success);
        } else if (isListOfFile(success)) {
            for(int i = 0; i< getListSize(success); i++) {
                map.put("success[" + i + "]", getFromList(success, i));
            }
        }
        else {
            map.put("success", JSON.getDefault().getMapper().writeValueAsString(success));
        }
    }
    if (startDate != null) {
        if (isFileTypeOrListOfFiles(startDate)) {
            fileTypeFound = true;
        }

        if (startDate.getClass().equals(java.io.File.class) ||
            startDate.getClass().equals(Integer.class) ||
            startDate.getClass().equals(String.class) ) {
            map.put("start_date", startDate);
        } else if (isListOfFile(startDate)) {
            for(int i = 0; i< getListSize(startDate); i++) {
                map.put("start_date[" + i + "]", getFromList(startDate, i));
            }
        }
        else {
            map.put("start_date", JSON.getDefault().getMapper().writeValueAsString(startDate));
        }
    }
    if (endDate != null) {
        if (isFileTypeOrListOfFiles(endDate)) {
            fileTypeFound = true;
        }

        if (endDate.getClass().equals(java.io.File.class) ||
            endDate.getClass().equals(Integer.class) ||
            endDate.getClass().equals(String.class) ) {
            map.put("end_date", endDate);
        } else if (isListOfFile(endDate)) {
            for(int i = 0; i< getListSize(endDate); i++) {
                map.put("end_date[" + i + "]", getFromList(endDate, i));
            }
        }
        else {
            map.put("end_date", JSON.getDefault().getMapper().writeValueAsString(endDate));
        }
    }
    if (reportType != null) {
        if (isFileTypeOrListOfFiles(reportType)) {
            fileTypeFound = true;
        }

        if (reportType.getClass().equals(java.io.File.class) ||
            reportType.getClass().equals(Integer.class) ||
            reportType.getClass().equals(String.class) ) {
            map.put("report_type", reportType);
        } else if (isListOfFile(reportType)) {
            for(int i = 0; i< getListSize(reportType); i++) {
                map.put("report_type[" + i + "]", getFromList(reportType, i));
            }
        }
        else {
            map.put("report_type", JSON.getDefault().getMapper().writeValueAsString(reportType));
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

