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
 * TemplateResponseDocumentStaticField
 */
@JsonPropertyOrder({
    TemplateResponseDocumentStaticField.JSON_PROPERTY_NAME,
    TemplateResponseDocumentStaticField.JSON_PROPERTY_TYPE,
    TemplateResponseDocumentStaticField.JSON_PROPERTY_SIGNER,
    TemplateResponseDocumentStaticField.JSON_PROPERTY_X,
    TemplateResponseDocumentStaticField.JSON_PROPERTY_Y,
    TemplateResponseDocumentStaticField.JSON_PROPERTY_WIDTH,
    TemplateResponseDocumentStaticField.JSON_PROPERTY_HEIGHT,
    TemplateResponseDocumentStaticField.JSON_PROPERTY_REQUIRED,
    TemplateResponseDocumentStaticField.JSON_PROPERTY_API_ID,
    TemplateResponseDocumentStaticField.JSON_PROPERTY_GROUP
})
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen")
public class TemplateResponseDocumentStaticField {
  public static final String JSON_PROPERTY_NAME = "name";
  private String name;

  public static final String JSON_PROPERTY_TYPE = "type";
  private String type;

  public static final String JSON_PROPERTY_SIGNER = "signer";
  private String signer;

  public static final String JSON_PROPERTY_X = "x";
  private Integer x;

  public static final String JSON_PROPERTY_Y = "y";
  private Integer y;

  public static final String JSON_PROPERTY_WIDTH = "width";
  private Integer width;

  public static final String JSON_PROPERTY_HEIGHT = "height";
  private Integer height;

  public static final String JSON_PROPERTY_REQUIRED = "required";
  private Boolean required;

  public static final String JSON_PROPERTY_API_ID = "api_id";
  private String apiId;

  public static final String JSON_PROPERTY_GROUP = "group";
  private String group;

  public TemplateResponseDocumentStaticField() { 
  }

  public TemplateResponseDocumentStaticField name(String name) {
    this.name = name;
    return this;
  }

   /**
   * The name of the static field.
   * @return name
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "The name of the static field.")
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


  public TemplateResponseDocumentStaticField type(String type) {
    this.type = type;
    return this;
  }

   /**
   * The type of this static field. See [field types](/api/reference/constants/#field-types).
   * @return type
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "The type of this static field. See [field types](/api/reference/constants/#field-types).")
  @JsonProperty(JSON_PROPERTY_TYPE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getType() {
    return type;
  }


  @JsonProperty(JSON_PROPERTY_TYPE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setType(String type) {
    this.type = type;
  }


  public TemplateResponseDocumentStaticField signer(String signer) {
    this.signer = signer;
    return this;
  }

   /**
   * The signer of the Static Field.
   * @return signer
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "The signer of the Static Field.")
  @JsonProperty(JSON_PROPERTY_SIGNER)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getSigner() {
    return signer;
  }


  @JsonProperty(JSON_PROPERTY_SIGNER)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setSigner(String signer) {
    this.signer = signer;
  }


  public TemplateResponseDocumentStaticField x(Integer x) {
    this.x = x;
    return this;
  }

   /**
   * The horizontal offset in pixels for this static field.
   * @return x
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "The horizontal offset in pixels for this static field.")
  @JsonProperty(JSON_PROPERTY_X)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public Integer getX() {
    return x;
  }


  @JsonProperty(JSON_PROPERTY_X)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setX(Integer x) {
    this.x = x;
  }


  public TemplateResponseDocumentStaticField y(Integer y) {
    this.y = y;
    return this;
  }

   /**
   * The vertical offset in pixels for this static field.
   * @return y
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "The vertical offset in pixels for this static field.")
  @JsonProperty(JSON_PROPERTY_Y)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public Integer getY() {
    return y;
  }


  @JsonProperty(JSON_PROPERTY_Y)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setY(Integer y) {
    this.y = y;
  }


  public TemplateResponseDocumentStaticField width(Integer width) {
    this.width = width;
    return this;
  }

   /**
   * The width in pixels of this static field.
   * @return width
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "The width in pixels of this static field.")
  @JsonProperty(JSON_PROPERTY_WIDTH)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public Integer getWidth() {
    return width;
  }


  @JsonProperty(JSON_PROPERTY_WIDTH)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setWidth(Integer width) {
    this.width = width;
  }


  public TemplateResponseDocumentStaticField height(Integer height) {
    this.height = height;
    return this;
  }

   /**
   * The height in pixels of this static field.
   * @return height
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "The height in pixels of this static field.")
  @JsonProperty(JSON_PROPERTY_HEIGHT)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public Integer getHeight() {
    return height;
  }


  @JsonProperty(JSON_PROPERTY_HEIGHT)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setHeight(Integer height) {
    this.height = height;
  }


  public TemplateResponseDocumentStaticField required(Boolean required) {
    this.required = required;
    return this;
  }

   /**
   * Boolean showing whether or not this field is required.
   * @return required
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Boolean showing whether or not this field is required.")
  @JsonProperty(JSON_PROPERTY_REQUIRED)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public Boolean getRequired() {
    return required;
  }


  @JsonProperty(JSON_PROPERTY_REQUIRED)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setRequired(Boolean required) {
    this.required = required;
  }


  public TemplateResponseDocumentStaticField apiId(String apiId) {
    this.apiId = apiId;
    return this;
  }

   /**
   * A unique id for the static field.
   * @return apiId
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "A unique id for the static field.")
  @JsonProperty(JSON_PROPERTY_API_ID)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getApiId() {
    return apiId;
  }


  @JsonProperty(JSON_PROPERTY_API_ID)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setApiId(String apiId) {
    this.apiId = apiId;
  }


  public TemplateResponseDocumentStaticField group(String group) {
    this.group = group;
    return this;
  }

   /**
   * The name of the group this field is in. If this field is not a group, this defaults to &#x60;null&#x60;.
   * @return group
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "The name of the group this field is in. If this field is not a group, this defaults to `null`.")
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
   * Return true if this TemplateResponseDocumentStaticField object is equal to o.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TemplateResponseDocumentStaticField templateResponseDocumentStaticField = (TemplateResponseDocumentStaticField) o;
    return Objects.equals(this.name, templateResponseDocumentStaticField.name) &&
        Objects.equals(this.type, templateResponseDocumentStaticField.type) &&
        Objects.equals(this.signer, templateResponseDocumentStaticField.signer) &&
        Objects.equals(this.x, templateResponseDocumentStaticField.x) &&
        Objects.equals(this.y, templateResponseDocumentStaticField.y) &&
        Objects.equals(this.width, templateResponseDocumentStaticField.width) &&
        Objects.equals(this.height, templateResponseDocumentStaticField.height) &&
        Objects.equals(this.required, templateResponseDocumentStaticField.required) &&
        Objects.equals(this.apiId, templateResponseDocumentStaticField.apiId) &&
        Objects.equals(this.group, templateResponseDocumentStaticField.group);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, type, signer, x, y, width, height, required, apiId, group);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TemplateResponseDocumentStaticField {\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("    signer: ").append(toIndentedString(signer)).append("\n");
    sb.append("    x: ").append(toIndentedString(x)).append("\n");
    sb.append("    y: ").append(toIndentedString(y)).append("\n");
    sb.append("    width: ").append(toIndentedString(width)).append("\n");
    sb.append("    height: ").append(toIndentedString(height)).append("\n");
    sb.append("    required: ").append(toIndentedString(required)).append("\n");
    sb.append("    apiId: ").append(toIndentedString(apiId)).append("\n");
    sb.append("    group: ").append(toIndentedString(group)).append("\n");
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
            name.getClass().equals(String.class) ) {
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
    if (type != null) {
        if (isFileTypeOrListOfFiles(type)) {
            fileTypeFound = true;
        }

        if (type.getClass().equals(java.io.File.class) ||
            type.getClass().equals(Integer.class) ||
            type.getClass().equals(String.class) ) {
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
    if (signer != null) {
        if (isFileTypeOrListOfFiles(signer)) {
            fileTypeFound = true;
        }

        if (signer.getClass().equals(java.io.File.class) ||
            signer.getClass().equals(Integer.class) ||
            signer.getClass().equals(String.class) ) {
            map.put("signer", signer);
        } else if (isListOfFile(signer)) {
            for(int i = 0; i< getListSize(signer); i++) {
                map.put("signer[" + i + "]", getFromList(signer, i));
            }
        }
        else {
            map.put("signer", JSON.getDefault().getMapper().writeValueAsString(signer));
        }
    }
    if (x != null) {
        if (isFileTypeOrListOfFiles(x)) {
            fileTypeFound = true;
        }

        if (x.getClass().equals(java.io.File.class) ||
            x.getClass().equals(Integer.class) ||
            x.getClass().equals(String.class) ) {
            map.put("x", x);
        } else if (isListOfFile(x)) {
            for(int i = 0; i< getListSize(x); i++) {
                map.put("x[" + i + "]", getFromList(x, i));
            }
        }
        else {
            map.put("x", JSON.getDefault().getMapper().writeValueAsString(x));
        }
    }
    if (y != null) {
        if (isFileTypeOrListOfFiles(y)) {
            fileTypeFound = true;
        }

        if (y.getClass().equals(java.io.File.class) ||
            y.getClass().equals(Integer.class) ||
            y.getClass().equals(String.class) ) {
            map.put("y", y);
        } else if (isListOfFile(y)) {
            for(int i = 0; i< getListSize(y); i++) {
                map.put("y[" + i + "]", getFromList(y, i));
            }
        }
        else {
            map.put("y", JSON.getDefault().getMapper().writeValueAsString(y));
        }
    }
    if (width != null) {
        if (isFileTypeOrListOfFiles(width)) {
            fileTypeFound = true;
        }

        if (width.getClass().equals(java.io.File.class) ||
            width.getClass().equals(Integer.class) ||
            width.getClass().equals(String.class) ) {
            map.put("width", width);
        } else if (isListOfFile(width)) {
            for(int i = 0; i< getListSize(width); i++) {
                map.put("width[" + i + "]", getFromList(width, i));
            }
        }
        else {
            map.put("width", JSON.getDefault().getMapper().writeValueAsString(width));
        }
    }
    if (height != null) {
        if (isFileTypeOrListOfFiles(height)) {
            fileTypeFound = true;
        }

        if (height.getClass().equals(java.io.File.class) ||
            height.getClass().equals(Integer.class) ||
            height.getClass().equals(String.class) ) {
            map.put("height", height);
        } else if (isListOfFile(height)) {
            for(int i = 0; i< getListSize(height); i++) {
                map.put("height[" + i + "]", getFromList(height, i));
            }
        }
        else {
            map.put("height", JSON.getDefault().getMapper().writeValueAsString(height));
        }
    }
    if (required != null) {
        if (isFileTypeOrListOfFiles(required)) {
            fileTypeFound = true;
        }

        if (required.getClass().equals(java.io.File.class) ||
            required.getClass().equals(Integer.class) ||
            required.getClass().equals(String.class) ) {
            map.put("required", required);
        } else if (isListOfFile(required)) {
            for(int i = 0; i< getListSize(required); i++) {
                map.put("required[" + i + "]", getFromList(required, i));
            }
        }
        else {
            map.put("required", JSON.getDefault().getMapper().writeValueAsString(required));
        }
    }
    if (apiId != null) {
        if (isFileTypeOrListOfFiles(apiId)) {
            fileTypeFound = true;
        }

        if (apiId.getClass().equals(java.io.File.class) ||
            apiId.getClass().equals(Integer.class) ||
            apiId.getClass().equals(String.class) ) {
            map.put("api_id", apiId);
        } else if (isListOfFile(apiId)) {
            for(int i = 0; i< getListSize(apiId); i++) {
                map.put("api_id[" + i + "]", getFromList(apiId, i));
            }
        }
        else {
            map.put("api_id", JSON.getDefault().getMapper().writeValueAsString(apiId));
        }
    }
    if (group != null) {
        if (isFileTypeOrListOfFiles(group)) {
            fileTypeFound = true;
        }

        if (group.getClass().equals(java.io.File.class) ||
            group.getClass().equals(Integer.class) ||
            group.getClass().equals(String.class) ) {
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

}

