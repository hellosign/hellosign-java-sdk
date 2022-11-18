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
 * Average text length in this field.
 */
@ApiModel(description = "Average text length in this field.")
@JsonPropertyOrder({
    TemplateResponseFieldAvgTextLength.JSON_PROPERTY_NUM_LINES,
    TemplateResponseFieldAvgTextLength.JSON_PROPERTY_NUM_CHARS_PER_LINE
})
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen")
public class TemplateResponseFieldAvgTextLength {
  public static final String JSON_PROPERTY_NUM_LINES = "num_lines";
  private Integer numLines;

  public static final String JSON_PROPERTY_NUM_CHARS_PER_LINE = "num_chars_per_line";
  private Integer numCharsPerLine;

  public TemplateResponseFieldAvgTextLength() { 
  }

  public TemplateResponseFieldAvgTextLength numLines(Integer numLines) {
    this.numLines = numLines;
    return this;
  }

   /**
   * Number of lines.
   * @return numLines
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Number of lines.")
  @JsonProperty(JSON_PROPERTY_NUM_LINES)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public Integer getNumLines() {
    return numLines;
  }


  @JsonProperty(JSON_PROPERTY_NUM_LINES)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setNumLines(Integer numLines) {
    this.numLines = numLines;
  }


  public TemplateResponseFieldAvgTextLength numCharsPerLine(Integer numCharsPerLine) {
    this.numCharsPerLine = numCharsPerLine;
    return this;
  }

   /**
   * Number of character per line.
   * @return numCharsPerLine
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Number of character per line.")
  @JsonProperty(JSON_PROPERTY_NUM_CHARS_PER_LINE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public Integer getNumCharsPerLine() {
    return numCharsPerLine;
  }


  @JsonProperty(JSON_PROPERTY_NUM_CHARS_PER_LINE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setNumCharsPerLine(Integer numCharsPerLine) {
    this.numCharsPerLine = numCharsPerLine;
  }


  /**
   * Return true if this TemplateResponseFieldAvgTextLength object is equal to o.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TemplateResponseFieldAvgTextLength templateResponseFieldAvgTextLength = (TemplateResponseFieldAvgTextLength) o;
    return Objects.equals(this.numLines, templateResponseFieldAvgTextLength.numLines) &&
        Objects.equals(this.numCharsPerLine, templateResponseFieldAvgTextLength.numCharsPerLine);
  }

  @Override
  public int hashCode() {
    return Objects.hash(numLines, numCharsPerLine);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TemplateResponseFieldAvgTextLength {\n");
    sb.append("    numLines: ").append(toIndentedString(numLines)).append("\n");
    sb.append("    numCharsPerLine: ").append(toIndentedString(numCharsPerLine)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  public Map<String, Object> createFormData() throws ApiException {
    Map<String, Object> map = new HashMap<>();
    boolean fileTypeFound = false;
    try {
    if (numLines != null) {
        if (isFileTypeOrListOfFiles(numLines)) {
            fileTypeFound = true;
        }

        if (numLines.getClass().equals(java.io.File.class) ||
            numLines.getClass().equals(Integer.class) ||
            numLines.getClass().equals(String.class) ||
            numLines.getClass().isEnum()) {
            map.put("num_lines", numLines);
        } else if (isListOfFile(numLines)) {
            for(int i = 0; i< getListSize(numLines); i++) {
                map.put("num_lines[" + i + "]", getFromList(numLines, i));
            }
        }
        else {
            map.put("num_lines", JSON.getDefault().getMapper().writeValueAsString(numLines));
        }
    }
    if (numCharsPerLine != null) {
        if (isFileTypeOrListOfFiles(numCharsPerLine)) {
            fileTypeFound = true;
        }

        if (numCharsPerLine.getClass().equals(java.io.File.class) ||
            numCharsPerLine.getClass().equals(Integer.class) ||
            numCharsPerLine.getClass().equals(String.class) ||
            numCharsPerLine.getClass().isEnum()) {
            map.put("num_chars_per_line", numCharsPerLine);
        } else if (isListOfFile(numCharsPerLine)) {
            for(int i = 0; i< getListSize(numCharsPerLine); i++) {
                map.put("num_chars_per_line[" + i + "]", getFromList(numCharsPerLine, i));
            }
        }
        else {
            map.put("num_chars_per_line", JSON.getDefault().getMapper().writeValueAsString(numCharsPerLine));
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

