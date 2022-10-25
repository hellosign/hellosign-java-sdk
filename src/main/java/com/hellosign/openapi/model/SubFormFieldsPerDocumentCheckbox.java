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
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.hellosign.openapi.JSON;


import com.hellosign.openapi.ApiException;
/**
 * This class extends &#x60;SubFormFieldsPerDocumentBase&#x60;.
 */
@ApiModel(description = "This class extends `SubFormFieldsPerDocumentBase`.")
@JsonPropertyOrder({
    SubFormFieldsPerDocumentCheckbox.JSON_PROPERTY_TYPE,
    SubFormFieldsPerDocumentCheckbox.JSON_PROPERTY_IS_CHECKED,
    SubFormFieldsPerDocumentCheckbox.JSON_PROPERTY_GROUP
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

public class SubFormFieldsPerDocumentCheckbox extends SubFormFieldsPerDocumentBase {
  public static final String JSON_PROPERTY_TYPE = "type";
  private String type = "checkbox";

  public static final String JSON_PROPERTY_IS_CHECKED = "is_checked";
  private Boolean isChecked;

  public static final String JSON_PROPERTY_GROUP = "group";
  private String group;

  public SubFormFieldsPerDocumentCheckbox() { 
  }

  public SubFormFieldsPerDocumentCheckbox type(String type) {
    this.type = type;
    return this;
  }

   /**
   * A yes/no checkbox. Use the &#x60;SubFormFieldsPerDocumentCheckbox&#x60; class.
   * @return type
  **/
  @javax.annotation.Nonnull
  @ApiModelProperty(required = true, value = "A yes/no checkbox. Use the `SubFormFieldsPerDocumentCheckbox` class.")
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


  public SubFormFieldsPerDocumentCheckbox isChecked(Boolean isChecked) {
    this.isChecked = isChecked;
    return this;
  }

   /**
   * &#x60;true&#x60; for checking the checkbox field by default, otherwise &#x60;false&#x60;.
   * @return isChecked
  **/
  @javax.annotation.Nonnull
  @ApiModelProperty(required = true, value = "`true` for checking the checkbox field by default, otherwise `false`.")
  @JsonProperty(JSON_PROPERTY_IS_CHECKED)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)

  public Boolean getIsChecked() {
    return isChecked;
  }


  @JsonProperty(JSON_PROPERTY_IS_CHECKED)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)
  public void setIsChecked(Boolean isChecked) {
    this.isChecked = isChecked;
  }


  public SubFormFieldsPerDocumentCheckbox group(String group) {
    this.group = group;
    return this;
  }

   /**
   * String referencing group defined in &#x60;form_field_groups&#x60; parameter.
   * @return group
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "String referencing group defined in `form_field_groups` parameter.")
  @JsonProperty(JSON_PROPERTY_GROUP)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getGroup() {
    return group;
  }


  @JsonProperty(JSON_PROPERTY_GROUP)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setGroup(String group) {
    this.group = group;
  }


  /**
   * Return true if this SubFormFieldsPerDocumentCheckbox object is equal to o.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SubFormFieldsPerDocumentCheckbox subFormFieldsPerDocumentCheckbox = (SubFormFieldsPerDocumentCheckbox) o;
    return Objects.equals(this.type, subFormFieldsPerDocumentCheckbox.type) &&
        Objects.equals(this.isChecked, subFormFieldsPerDocumentCheckbox.isChecked) &&
        Objects.equals(this.group, subFormFieldsPerDocumentCheckbox.group) &&
        super.equals(o);
  }

  @Override
  public int hashCode() {
    return Objects.hash(type, isChecked, group, super.hashCode());
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SubFormFieldsPerDocumentCheckbox {\n");
    sb.append("    ").append(toIndentedString(super.toString())).append("\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("    isChecked: ").append(toIndentedString(isChecked)).append("\n");
    sb.append("    group: ").append(toIndentedString(group)).append("\n");
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
            type.getClass().isEnum() ) {
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
    if (isChecked != null) {
        if (isFileTypeOrListOfFiles(isChecked)) {
            fileTypeFound = true;
        }

        if (isChecked.getClass().equals(java.io.File.class) ||
            isChecked.getClass().equals(Integer.class) ||
            isChecked.getClass().equals(String.class) ||
            isChecked.getClass().isEnum() ) {
            map.put("is_checked", isChecked);
        } else if (isListOfFile(isChecked)) {
            for(int i = 0; i< getListSize(isChecked); i++) {
                map.put("is_checked[" + i + "]", getFromList(isChecked, i));
            }
        }
        else {
            map.put("is_checked", JSON.getDefault().getMapper().writeValueAsString(isChecked));
        }
    }
    if (group != null) {
        if (isFileTypeOrListOfFiles(group)) {
            fileTypeFound = true;
        }

        if (group.getClass().equals(java.io.File.class) ||
            group.getClass().equals(Integer.class) ||
            group.getClass().equals(String.class) ||
            group.getClass().isEnum() ) {
            map.put("group", group);
        } else if (isListOfFile(group)) {
            for(int i = 0; i< getListSize(group); i++) {
                map.put("group[" + i + "]", getFromList(group, i));
            }
        }
        else {
            map.put("group", JSON.getDefault().getMapper().writeValueAsString(group));
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
  mappings.put("SubFormFieldsPerDocumentCheckbox", SubFormFieldsPerDocumentCheckbox.class);
  JSON.registerDiscriminator(SubFormFieldsPerDocumentCheckbox.class, "type", mappings);
}
}

