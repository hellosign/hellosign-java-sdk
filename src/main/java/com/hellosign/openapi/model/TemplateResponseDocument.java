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
import com.hellosign.openapi.model.TemplateResponseDocumentCustomField;
import com.hellosign.openapi.model.TemplateResponseDocumentFieldGroup;
import com.hellosign.openapi.model.TemplateResponseDocumentFormField;
import com.hellosign.openapi.model.TemplateResponseDocumentStaticField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.hellosign.openapi.JSON;


import com.hellosign.openapi.ApiException;
/**
 * TemplateResponseDocument
 */
@JsonPropertyOrder({
    TemplateResponseDocument.JSON_PROPERTY_NAME,
    TemplateResponseDocument.JSON_PROPERTY_INDEX,
    TemplateResponseDocument.JSON_PROPERTY_FIELD_GROUPS,
    TemplateResponseDocument.JSON_PROPERTY_FORM_FIELDS,
    TemplateResponseDocument.JSON_PROPERTY_CUSTOM_FIELDS,
    TemplateResponseDocument.JSON_PROPERTY_STATIC_FIELDS
})
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen")
public class TemplateResponseDocument {
  public static final String JSON_PROPERTY_NAME = "name";
  private String name;

  public static final String JSON_PROPERTY_INDEX = "index";
  private Integer index;

  public static final String JSON_PROPERTY_FIELD_GROUPS = "field_groups";
  private List<TemplateResponseDocumentFieldGroup> fieldGroups = null;

  public static final String JSON_PROPERTY_FORM_FIELDS = "form_fields";
  private List<TemplateResponseDocumentFormField> formFields = null;

  public static final String JSON_PROPERTY_CUSTOM_FIELDS = "custom_fields";
  private List<TemplateResponseDocumentCustomField> customFields = null;

  public static final String JSON_PROPERTY_STATIC_FIELDS = "static_fields";
  private List<TemplateResponseDocumentStaticField> staticFields = null;

  public TemplateResponseDocument() { 
  }

  public TemplateResponseDocument name(String name) {
    this.name = name;
    return this;
  }

   /**
   * Name of the associated file.
   * @return name
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Name of the associated file.")
  @JsonProperty(JSON_PROPERTY_NAME)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getName() {
    return name;
  }


  @JsonProperty(JSON_PROPERTY_NAME)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setName(String name) {
    this.name = name;
  }


  public TemplateResponseDocument index(Integer index) {
    this.index = index;
    return this;
  }

   /**
   * Document ordering, the lowest index is displayed first and the highest last (0-based indexing).
   * @return index
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Document ordering, the lowest index is displayed first and the highest last (0-based indexing).")
  @JsonProperty(JSON_PROPERTY_INDEX)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public Integer getIndex() {
    return index;
  }


  @JsonProperty(JSON_PROPERTY_INDEX)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setIndex(Integer index) {
    this.index = index;
  }


  public TemplateResponseDocument fieldGroups(List<TemplateResponseDocumentFieldGroup> fieldGroups) {
    this.fieldGroups = fieldGroups;
    return this;
  }

  public TemplateResponseDocument addFieldGroupsItem(TemplateResponseDocumentFieldGroup fieldGroupsItem) {
    if (this.fieldGroups == null) {
      this.fieldGroups = new ArrayList<>();
    }
    this.fieldGroups.add(fieldGroupsItem);
    return this;
  }

   /**
   * An array of Form Field Group objects.
   * @return fieldGroups
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "An array of Form Field Group objects.")
  @JsonProperty(JSON_PROPERTY_FIELD_GROUPS)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public List<TemplateResponseDocumentFieldGroup> getFieldGroups() {
    return fieldGroups;
  }


  @JsonProperty(JSON_PROPERTY_FIELD_GROUPS)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setFieldGroups(List<TemplateResponseDocumentFieldGroup> fieldGroups) {
    this.fieldGroups = fieldGroups;
  }


  public TemplateResponseDocument formFields(List<TemplateResponseDocumentFormField> formFields) {
    this.formFields = formFields;
    return this;
  }

  public TemplateResponseDocument addFormFieldsItem(TemplateResponseDocumentFormField formFieldsItem) {
    if (this.formFields == null) {
      this.formFields = new ArrayList<>();
    }
    this.formFields.add(formFieldsItem);
    return this;
  }

   /**
   * An array of Form Field objects containing the name and type of each named textbox and checkmark field.
   * @return formFields
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "An array of Form Field objects containing the name and type of each named textbox and checkmark field.")
  @JsonProperty(JSON_PROPERTY_FORM_FIELDS)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public List<TemplateResponseDocumentFormField> getFormFields() {
    return formFields;
  }


  @JsonProperty(JSON_PROPERTY_FORM_FIELDS)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setFormFields(List<TemplateResponseDocumentFormField> formFields) {
    this.formFields = formFields;
  }


  public TemplateResponseDocument customFields(List<TemplateResponseDocumentCustomField> customFields) {
    this.customFields = customFields;
    return this;
  }

  public TemplateResponseDocument addCustomFieldsItem(TemplateResponseDocumentCustomField customFieldsItem) {
    if (this.customFields == null) {
      this.customFields = new ArrayList<>();
    }
    this.customFields.add(customFieldsItem);
    return this;
  }

   /**
   * An array of Document Custom Field objects.
   * @return customFields
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "An array of Document Custom Field objects.")
  @JsonProperty(JSON_PROPERTY_CUSTOM_FIELDS)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public List<TemplateResponseDocumentCustomField> getCustomFields() {
    return customFields;
  }


  @JsonProperty(JSON_PROPERTY_CUSTOM_FIELDS)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setCustomFields(List<TemplateResponseDocumentCustomField> customFields) {
    this.customFields = customFields;
  }


  public TemplateResponseDocument staticFields(List<TemplateResponseDocumentStaticField> staticFields) {
    this.staticFields = staticFields;
    return this;
  }

  public TemplateResponseDocument addStaticFieldsItem(TemplateResponseDocumentStaticField staticFieldsItem) {
    if (this.staticFields == null) {
      this.staticFields = new ArrayList<>();
    }
    this.staticFields.add(staticFieldsItem);
    return this;
  }

   /**
   * An array describing static overlay fields. &lt;b&gt;Note&lt;/b&gt; only available for certain subscriptions.
   * @return staticFields
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "An array describing static overlay fields. <b>Note</b> only available for certain subscriptions.")
  @JsonProperty(JSON_PROPERTY_STATIC_FIELDS)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public List<TemplateResponseDocumentStaticField> getStaticFields() {
    return staticFields;
  }


  @JsonProperty(JSON_PROPERTY_STATIC_FIELDS)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setStaticFields(List<TemplateResponseDocumentStaticField> staticFields) {
    this.staticFields = staticFields;
  }


  /**
   * Return true if this TemplateResponseDocument object is equal to o.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TemplateResponseDocument templateResponseDocument = (TemplateResponseDocument) o;
    return Objects.equals(this.name, templateResponseDocument.name) &&
        Objects.equals(this.index, templateResponseDocument.index) &&
        Objects.equals(this.fieldGroups, templateResponseDocument.fieldGroups) &&
        Objects.equals(this.formFields, templateResponseDocument.formFields) &&
        Objects.equals(this.customFields, templateResponseDocument.customFields) &&
        Objects.equals(this.staticFields, templateResponseDocument.staticFields);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, index, fieldGroups, formFields, customFields, staticFields);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TemplateResponseDocument {\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    index: ").append(toIndentedString(index)).append("\n");
    sb.append("    fieldGroups: ").append(toIndentedString(fieldGroups)).append("\n");
    sb.append("    formFields: ").append(toIndentedString(formFields)).append("\n");
    sb.append("    customFields: ").append(toIndentedString(customFields)).append("\n");
    sb.append("    staticFields: ").append(toIndentedString(staticFields)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  public Map<String, Object> createFormData() throws ApiException {
    Map<String, Object> map = new HashMap<>();
    boolean fileTypeFound = false;
    try {
    if (name != null) {
        if (isFileTypeOrListOfFiles(name)) {
            fileTypeFound = true;
        }

        if (name.getClass().equals(java.io.File.class) ||
            name.getClass().equals(Integer.class) ||
            name.getClass().equals(String.class) ||
            name.getClass().isEnum()) {
            map.put("name", name);
        } else if (isListOfFile(name)) {
            for(int i = 0; i< getListSize(name); i++) {
                map.put("name[" + i + "]", getFromList(name, i));
            }
        }
        else {
            map.put("name", JSON.getDefault().getMapper().writeValueAsString(name));
        }
    }
    if (index != null) {
        if (isFileTypeOrListOfFiles(index)) {
            fileTypeFound = true;
        }

        if (index.getClass().equals(java.io.File.class) ||
            index.getClass().equals(Integer.class) ||
            index.getClass().equals(String.class) ||
            index.getClass().isEnum()) {
            map.put("index", index);
        } else if (isListOfFile(index)) {
            for(int i = 0; i< getListSize(index); i++) {
                map.put("index[" + i + "]", getFromList(index, i));
            }
        }
        else {
            map.put("index", JSON.getDefault().getMapper().writeValueAsString(index));
        }
    }
    if (fieldGroups != null) {
        if (isFileTypeOrListOfFiles(fieldGroups)) {
            fileTypeFound = true;
        }

        if (fieldGroups.getClass().equals(java.io.File.class) ||
            fieldGroups.getClass().equals(Integer.class) ||
            fieldGroups.getClass().equals(String.class) ||
            fieldGroups.getClass().isEnum()) {
            map.put("field_groups", fieldGroups);
        } else if (isListOfFile(fieldGroups)) {
            for(int i = 0; i< getListSize(fieldGroups); i++) {
                map.put("field_groups[" + i + "]", getFromList(fieldGroups, i));
            }
        }
        else {
            map.put("field_groups", JSON.getDefault().getMapper().writeValueAsString(fieldGroups));
        }
    }
    if (formFields != null) {
        if (isFileTypeOrListOfFiles(formFields)) {
            fileTypeFound = true;
        }

        if (formFields.getClass().equals(java.io.File.class) ||
            formFields.getClass().equals(Integer.class) ||
            formFields.getClass().equals(String.class) ||
            formFields.getClass().isEnum()) {
            map.put("form_fields", formFields);
        } else if (isListOfFile(formFields)) {
            for(int i = 0; i< getListSize(formFields); i++) {
                map.put("form_fields[" + i + "]", getFromList(formFields, i));
            }
        }
        else {
            map.put("form_fields", JSON.getDefault().getMapper().writeValueAsString(formFields));
        }
    }
    if (customFields != null) {
        if (isFileTypeOrListOfFiles(customFields)) {
            fileTypeFound = true;
        }

        if (customFields.getClass().equals(java.io.File.class) ||
            customFields.getClass().equals(Integer.class) ||
            customFields.getClass().equals(String.class) ||
            customFields.getClass().isEnum()) {
            map.put("custom_fields", customFields);
        } else if (isListOfFile(customFields)) {
            for(int i = 0; i< getListSize(customFields); i++) {
                map.put("custom_fields[" + i + "]", getFromList(customFields, i));
            }
        }
        else {
            map.put("custom_fields", JSON.getDefault().getMapper().writeValueAsString(customFields));
        }
    }
    if (staticFields != null) {
        if (isFileTypeOrListOfFiles(staticFields)) {
            fileTypeFound = true;
        }

        if (staticFields.getClass().equals(java.io.File.class) ||
            staticFields.getClass().equals(Integer.class) ||
            staticFields.getClass().equals(String.class) ||
            staticFields.getClass().isEnum()) {
            map.put("static_fields", staticFields);
        } else if (isListOfFile(staticFields)) {
            for(int i = 0; i< getListSize(staticFields); i++) {
                map.put("static_fields[" + i + "]", getFromList(staticFields, i));
            }
        }
        else {
            map.put("static_fields", JSON.getDefault().getMapper().writeValueAsString(staticFields));
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

