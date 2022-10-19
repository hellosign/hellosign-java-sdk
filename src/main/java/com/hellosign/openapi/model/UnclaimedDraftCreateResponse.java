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
import com.hellosign.openapi.model.UnclaimedDraftResponse;
import com.hellosign.openapi.model.WarningResponse;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.hellosign.openapi.JSON;


import com.hellosign.openapi.ApiException;
/**
 * UnclaimedDraftCreateResponse
 */
@JsonPropertyOrder({
    UnclaimedDraftCreateResponse.JSON_PROPERTY_UNCLAIMED_DRAFT,
    UnclaimedDraftCreateResponse.JSON_PROPERTY_WARNINGS
})
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen")
public class UnclaimedDraftCreateResponse {
  public static final String JSON_PROPERTY_UNCLAIMED_DRAFT = "unclaimed_draft";
  private UnclaimedDraftResponse unclaimedDraft;

  public static final String JSON_PROPERTY_WARNINGS = "warnings";
  private List<WarningResponse> warnings = null;

  public UnclaimedDraftCreateResponse() { 
  }

  public UnclaimedDraftCreateResponse unclaimedDraft(UnclaimedDraftResponse unclaimedDraft) {
    this.unclaimedDraft = unclaimedDraft;
    return this;
  }

   /**
   * Get unclaimedDraft
   * @return unclaimedDraft
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")
  @JsonProperty(JSON_PROPERTY_UNCLAIMED_DRAFT)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public UnclaimedDraftResponse getUnclaimedDraft() {
    return unclaimedDraft;
  }


  @JsonProperty(JSON_PROPERTY_UNCLAIMED_DRAFT)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setUnclaimedDraft(UnclaimedDraftResponse unclaimedDraft) {
    this.unclaimedDraft = unclaimedDraft;
  }


  public UnclaimedDraftCreateResponse warnings(List<WarningResponse> warnings) {
    this.warnings = warnings;
    return this;
  }

  public UnclaimedDraftCreateResponse addWarningsItem(WarningResponse warningsItem) {
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
   * Return true if this UnclaimedDraftCreateResponse object is equal to o.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UnclaimedDraftCreateResponse unclaimedDraftCreateResponse = (UnclaimedDraftCreateResponse) o;
    return Objects.equals(this.unclaimedDraft, unclaimedDraftCreateResponse.unclaimedDraft) &&
        Objects.equals(this.warnings, unclaimedDraftCreateResponse.warnings);
  }

  @Override
  public int hashCode() {
    return Objects.hash(unclaimedDraft, warnings);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class UnclaimedDraftCreateResponse {\n");
    sb.append("    unclaimedDraft: ").append(toIndentedString(unclaimedDraft)).append("\n");
    sb.append("    warnings: ").append(toIndentedString(warnings)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  public Map<String, Object> createFormData() throws ApiException {
    Map<String, Object> map = new HashMap<>();
    boolean fileTypeFound = false;
    try {
    if (unclaimedDraft != null) {
        if (isFileTypeOrListOfFiles(unclaimedDraft)) {
            fileTypeFound = true;
        }

        if (unclaimedDraft.getClass().equals(java.io.File.class) ||
            unclaimedDraft.getClass().equals(Integer.class) ||
            unclaimedDraft.getClass().equals(String.class) ||
            unclaimedDraft.getClass().isEnum()) {
            map.put("unclaimed_draft", unclaimedDraft);
        } else if (isListOfFile(unclaimedDraft)) {
            for(int i = 0; i< getListSize(unclaimedDraft); i++) {
                map.put("unclaimed_draft[" + i + "]", getFromList(unclaimedDraft, i));
            }
        }
        else {
            map.put("unclaimed_draft", JSON.getDefault().getMapper().writeValueAsString(unclaimedDraft));
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

