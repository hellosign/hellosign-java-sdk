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
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.annotation.JsonValue;
import com.hellosign.openapi.model.SignatureRequestResponseCustomFieldBase;
import com.hellosign.openapi.model.SignatureRequestResponseCustomFieldCheckbox;
import com.hellosign.openapi.model.SignatureRequestResponseCustomFieldText;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.hellosign.openapi.JSON;


import com.hellosign.openapi.ApiException;
/**
 * This class extends &#x60;SignatureRequestResponseCustomFieldBase&#x60;.
 */
@ApiModel(description = "This class extends `SignatureRequestResponseCustomFieldBase`.")
@JsonPropertyOrder({
    SignatureRequestResponseCustomFieldCheckbox.JSON_PROPERTY_TYPE,
    SignatureRequestResponseCustomFieldCheckbox.JSON_PROPERTY_VALUE
})
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen")
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXISTING_PROPERTY, property = "type", visible = true)
@JsonSubTypes({
  @JsonSubTypes.Type(value = SignatureRequestResponseCustomFieldCheckbox.class, name = "checkbox"),
  @JsonSubTypes.Type(value = SignatureRequestResponseCustomFieldText.class, name = "text"),
})

public class SignatureRequestResponseCustomFieldCheckbox extends SignatureRequestResponseCustomFieldBase {
  public static final String JSON_PROPERTY_TYPE = "type";
  private String type = "checkbox";

  public static final String JSON_PROPERTY_VALUE = "value";
  private Boolean value;

  public SignatureRequestResponseCustomFieldCheckbox() { 
  }

  public SignatureRequestResponseCustomFieldCheckbox type(String type) {
    this.type = type;
    return this;
  }

   /**
   * The type of this Custom Field. Only &#39;text&#39; and &#39;checkbox&#39; are currently supported.
   * @return type
  **/
  @javax.annotation.Nonnull
  @ApiModelProperty(required = true, value = "The type of this Custom Field. Only 'text' and 'checkbox' are currently supported.")
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


  public SignatureRequestResponseCustomFieldCheckbox value(Boolean value) {
    this.value = value;
    return this;
  }

   /**
   * A true/false for checkbox fields
   * @return value
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "A true/false for checkbox fields")
  @JsonProperty(JSON_PROPERTY_VALUE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public Boolean getValue() {
    return value;
  }


  @JsonProperty(JSON_PROPERTY_VALUE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setValue(Boolean value) {
    this.value = value;
  }


  /**
   * Return true if this SignatureRequestResponseCustomFieldCheckbox object is equal to o.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SignatureRequestResponseCustomFieldCheckbox signatureRequestResponseCustomFieldCheckbox = (SignatureRequestResponseCustomFieldCheckbox) o;
    return Objects.equals(this.type, signatureRequestResponseCustomFieldCheckbox.type) &&
        Objects.equals(this.value, signatureRequestResponseCustomFieldCheckbox.value) &&
        super.equals(o);
  }

  @Override
  public int hashCode() {
    return Objects.hash(type, value, super.hashCode());
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SignatureRequestResponseCustomFieldCheckbox {\n");
    sb.append("    ").append(toIndentedString(super.toString())).append("\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("    value: ").append(toIndentedString(value)).append("\n");
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
    if (value != null) {
        if (isFileTypeOrListOfFiles(value)) {
            fileTypeFound = true;
        }

        if (value.getClass().equals(java.io.File.class) ||
            value.getClass().equals(Integer.class) ||
            value.getClass().equals(String.class) ||
            value.getClass().isEnum() ) {
            map.put("value", value);
        } else if (isListOfFile(value)) {
            for(int i = 0; i< getListSize(value); i++) {
                map.put("value[" + i + "]", getFromList(value, i));
            }
        }
        else {
            map.put("value", JSON.getDefault().getMapper().writeValueAsString(value));
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
  mappings.put("checkbox", SignatureRequestResponseCustomFieldCheckbox.class);
  mappings.put("text", SignatureRequestResponseCustomFieldText.class);
  mappings.put("SignatureRequestResponseCustomFieldCheckbox", SignatureRequestResponseCustomFieldCheckbox.class);
  JSON.registerDiscriminator(SignatureRequestResponseCustomFieldCheckbox.class, "type", mappings);
}
}

