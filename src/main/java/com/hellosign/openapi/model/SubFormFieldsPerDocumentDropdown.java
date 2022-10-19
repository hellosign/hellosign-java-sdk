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
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.annotation.JsonValue;
import com.hellosign.openapi.model.SubFormFieldsPerDocumentBase;
import com.hellosign.openapi.model.SubFormFieldsPerDocumentCheckbox;
import com.hellosign.openapi.model.SubFormFieldsPerDocumentCheckboxMerge;
import com.hellosign.openapi.model.SubFormFieldsPerDocumentDateSigned;
import com.hellosign.openapi.model.SubFormFieldsPerDocumentDropdown;
import com.hellosign.openapi.model.SubFormFieldsPerDocumentHyperlink;
import com.hellosign.openapi.model.SubFormFieldsPerDocumentInitials;
import com.hellosign.openapi.model.SubFormFieldsPerDocumentRadio;
import com.hellosign.openapi.model.SubFormFieldsPerDocumentSignature;
import com.hellosign.openapi.model.SubFormFieldsPerDocumentText;
import com.hellosign.openapi.model.SubFormFieldsPerDocumentTextMerge;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.hellosign.openapi.JSON;


import com.hellosign.openapi.ApiException;
/**
 * This class extends &#x60;SubFormFieldsPerDocumentBase&#x60;.
 */
@ApiModel(description = "This class extends `SubFormFieldsPerDocumentBase`.")
@JsonPropertyOrder({
    SubFormFieldsPerDocumentDropdown.JSON_PROPERTY_TYPE,
    SubFormFieldsPerDocumentDropdown.JSON_PROPERTY_OPTIONS,
    SubFormFieldsPerDocumentDropdown.JSON_PROPERTY_CONTENT
})
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen")
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXISTING_PROPERTY, property = "type", visible = true)
@JsonSubTypes({
  @JsonSubTypes.Type(value = SubFormFieldsPerDocumentCheckbox.class, name = "checkbox"),
  @JsonSubTypes.Type(value = SubFormFieldsPerDocumentCheckboxMerge.class, name = "checkbox-merge"),
  @JsonSubTypes.Type(value = SubFormFieldsPerDocumentDateSigned.class, name = "date_signed"),
  @JsonSubTypes.Type(value = SubFormFieldsPerDocumentDropdown.class, name = "dropdown"),
  @JsonSubTypes.Type(value = SubFormFieldsPerDocumentHyperlink.class, name = "hyperlink"),
  @JsonSubTypes.Type(value = SubFormFieldsPerDocumentInitials.class, name = "initials"),
  @JsonSubTypes.Type(value = SubFormFieldsPerDocumentRadio.class, name = "radio"),
  @JsonSubTypes.Type(value = SubFormFieldsPerDocumentSignature.class, name = "signature"),
  @JsonSubTypes.Type(value = SubFormFieldsPerDocumentText.class, name = "text"),
  @JsonSubTypes.Type(value = SubFormFieldsPerDocumentTextMerge.class, name = "text-merge"),
})

public class SubFormFieldsPerDocumentDropdown extends SubFormFieldsPerDocumentBase {
  public static final String JSON_PROPERTY_TYPE = "type";
  private String type = "dropdown";

  public static final String JSON_PROPERTY_OPTIONS = "options";
  private List<String> options = new ArrayList<>();

  public static final String JSON_PROPERTY_CONTENT = "content";
  private String content;

  public SubFormFieldsPerDocumentDropdown() { 
  }

  public SubFormFieldsPerDocumentDropdown type(String type) {
    this.type = type;
    return this;
  }

   /**
   * An input field for dropdowns. Use the &#x60;SubFormFieldsPerDocumentDropdown&#x60; class.
   * @return type
  **/
  @javax.annotation.Nonnull
  @ApiModelProperty(required = true, value = "An input field for dropdowns. Use the `SubFormFieldsPerDocumentDropdown` class.")
  @JsonProperty(JSON_PROPERTY_TYPE)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)

  public String getType() {
    return type;
  }


  @JsonProperty(JSON_PROPERTY_TYPE)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)
  public void setType(String type) {
    this.type = type;
  }


  public SubFormFieldsPerDocumentDropdown options(List<String> options) {
    this.options = options;
    return this;
  }

  public SubFormFieldsPerDocumentDropdown addOptionsItem(String optionsItem) {
    this.options.add(optionsItem);
    return this;
  }

   /**
   * Array of string values representing dropdown values.
   * @return options
  **/
  @javax.annotation.Nonnull
  @ApiModelProperty(required = true, value = "Array of string values representing dropdown values.")
  @JsonProperty(JSON_PROPERTY_OPTIONS)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)

  public List<String> getOptions() {
    return options;
  }


  @JsonProperty(JSON_PROPERTY_OPTIONS)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)
  public void setOptions(List<String> options) {
    this.options = options;
  }


  public SubFormFieldsPerDocumentDropdown content(String content) {
    this.content = content;
    return this;
  }

   /**
   * Selected value in &#x60;options&#x60; array. Value must exist in array.
   * @return content
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Selected value in `options` array. Value must exist in array.")
  @JsonProperty(JSON_PROPERTY_CONTENT)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getContent() {
    return content;
  }


  @JsonProperty(JSON_PROPERTY_CONTENT)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setContent(String content) {
    this.content = content;
  }


  /**
   * Return true if this SubFormFieldsPerDocumentDropdown object is equal to o.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SubFormFieldsPerDocumentDropdown subFormFieldsPerDocumentDropdown = (SubFormFieldsPerDocumentDropdown) o;
    return Objects.equals(this.type, subFormFieldsPerDocumentDropdown.type) &&
        Objects.equals(this.options, subFormFieldsPerDocumentDropdown.options) &&
        Objects.equals(this.content, subFormFieldsPerDocumentDropdown.content) &&
        super.equals(o);
  }

  @Override
  public int hashCode() {
    return Objects.hash(type, options, content, super.hashCode());
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SubFormFieldsPerDocumentDropdown {\n");
    sb.append("    ").append(toIndentedString(super.toString())).append("\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("    options: ").append(toIndentedString(options)).append("\n");
    sb.append("    content: ").append(toIndentedString(content)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  public Map<String, Object> createFormData() throws ApiException {
    Map<String, Object> map = new HashMap<>();
    boolean fileTypeFound = false;
     map.putAll(super.createFormData());
    try {
    if (type != null) {
        if (isFileTypeOrListOfFiles(type)) {
            fileTypeFound = true;
        }

        if (type.getClass().equals(java.io.File.class) ||
            type.getClass().equals(Integer.class) ||
            type.getClass().equals(String.class) ||
            type.getClass().isEnum()) {
            map.put("type", type);
        } else if (isListOfFile(type)) {
            for(int i = 0; i< getListSize(type); i++) {
                map.put("type[" + i + "]", getFromList(type, i));
            }
        }
        else {
            map.put("type", JSON.getDefault().getMapper().writeValueAsString(type));
        }
    }
    if (options != null) {
        if (isFileTypeOrListOfFiles(options)) {
            fileTypeFound = true;
        }

        if (options.getClass().equals(java.io.File.class) ||
            options.getClass().equals(Integer.class) ||
            options.getClass().equals(String.class) ||
            options.getClass().isEnum()) {
            map.put("options", options);
        } else if (isListOfFile(options)) {
            for(int i = 0; i< getListSize(options); i++) {
                map.put("options[" + i + "]", getFromList(options, i));
            }
        }
        else {
            map.put("options", JSON.getDefault().getMapper().writeValueAsString(options));
        }
    }
    if (content != null) {
        if (isFileTypeOrListOfFiles(content)) {
            fileTypeFound = true;
        }

        if (content.getClass().equals(java.io.File.class) ||
            content.getClass().equals(Integer.class) ||
            content.getClass().equals(String.class) ||
            content.getClass().isEnum()) {
            map.put("content", content);
        } else if (isListOfFile(content)) {
            for(int i = 0; i< getListSize(content); i++) {
                map.put("content[" + i + "]", getFromList(content, i));
            }
        }
        else {
            map.put("content", JSON.getDefault().getMapper().writeValueAsString(content));
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

static {
  // Initialize and register the discriminator mappings.
  Map<String, Class<?>> mappings = new HashMap<String, Class<?>>();
  mappings.put("checkbox", SubFormFieldsPerDocumentCheckbox.class);
  mappings.put("checkbox-merge", SubFormFieldsPerDocumentCheckboxMerge.class);
  mappings.put("date_signed", SubFormFieldsPerDocumentDateSigned.class);
  mappings.put("dropdown", SubFormFieldsPerDocumentDropdown.class);
  mappings.put("hyperlink", SubFormFieldsPerDocumentHyperlink.class);
  mappings.put("initials", SubFormFieldsPerDocumentInitials.class);
  mappings.put("radio", SubFormFieldsPerDocumentRadio.class);
  mappings.put("signature", SubFormFieldsPerDocumentSignature.class);
  mappings.put("text", SubFormFieldsPerDocumentText.class);
  mappings.put("text-merge", SubFormFieldsPerDocumentTextMerge.class);
  mappings.put("SubFormFieldsPerDocumentDropdown", SubFormFieldsPerDocumentDropdown.class);
  JSON.registerDiscriminator(SubFormFieldsPerDocumentDropdown.class, "type", mappings);
}
}

