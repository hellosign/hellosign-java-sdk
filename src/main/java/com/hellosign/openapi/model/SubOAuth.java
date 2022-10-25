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
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.hellosign.openapi.JSON;


import com.hellosign.openapi.ApiException;
/**
 * OAuth related parameters.
 */
@ApiModel(description = "OAuth related parameters.")
@JsonPropertyOrder({
    SubOAuth.JSON_PROPERTY_CALLBACK_URL,
    SubOAuth.JSON_PROPERTY_SCOPES
})
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen")
public class SubOAuth {
  public static final String JSON_PROPERTY_CALLBACK_URL = "callback_url";
  private String callbackUrl;

  /**
   * Gets or Sets scopes
   */
  public enum ScopesEnum {
    REQUEST_SIGNATURE("request_signature"),
    
    BASIC_ACCOUNT_INFO("basic_account_info"),
    
    ACCOUNT_ACCESS("account_access"),
    
    SIGNATURE_REQUEST_ACCESS("signature_request_access"),
    
    TEMPLATE_ACCESS("template_access"),
    
    TEAM_ACCESS("team_access"),
    
    API_APP_ACCESS("api_app_access"),
    
    EMPTY("");

    private String value;

    ScopesEnum(String value) {
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
    public static ScopesEnum fromValue(String value) {
      for (ScopesEnum b : ScopesEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
  }

  public static final String JSON_PROPERTY_SCOPES = "scopes";
  private List<ScopesEnum> scopes = null;

  public SubOAuth() { 
  }

  public SubOAuth callbackUrl(String callbackUrl) {
    this.callbackUrl = callbackUrl;
    return this;
  }

   /**
   * The callback URL to be used for OAuth flows. (Required if &#x60;oauth[scopes]&#x60; is provided)
   * @return callbackUrl
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "The callback URL to be used for OAuth flows. (Required if `oauth[scopes]` is provided)")
  @JsonProperty(JSON_PROPERTY_CALLBACK_URL)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getCallbackUrl() {
    return callbackUrl;
  }


  @JsonProperty(JSON_PROPERTY_CALLBACK_URL)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setCallbackUrl(String callbackUrl) {
    this.callbackUrl = callbackUrl;
  }


  public SubOAuth scopes(List<ScopesEnum> scopes) {
    this.scopes = scopes;
    return this;
  }

  public SubOAuth addScopesItem(ScopesEnum scopesItem) {
    if (this.scopes == null) {
      this.scopes = new ArrayList<>();
    }
    this.scopes.add(scopesItem);
    return this;
  }

   /**
   * A list of [OAuth scopes](/api/reference/tag/OAuth) to be granted to the app. (Required if &#x60;oauth[callback_url]&#x60; is provided).
   * @return scopes
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "A list of [OAuth scopes](/api/reference/tag/OAuth) to be granted to the app. (Required if `oauth[callback_url]` is provided).")
  @JsonProperty(JSON_PROPERTY_SCOPES)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public List<ScopesEnum> getScopes() {
    return scopes;
  }


  @JsonProperty(JSON_PROPERTY_SCOPES)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setScopes(List<ScopesEnum> scopes) {
    this.scopes = scopes;
  }


  /**
   * Return true if this SubOAuth object is equal to o.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SubOAuth subOAuth = (SubOAuth) o;
    return Objects.equals(this.callbackUrl, subOAuth.callbackUrl) &&
        Objects.equals(this.scopes, subOAuth.scopes);
  }

  @Override
  public int hashCode() {
    return Objects.hash(callbackUrl, scopes);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SubOAuth {\n");
    sb.append("    callbackUrl: ").append(toIndentedString(callbackUrl)).append("\n");
    sb.append("    scopes: ").append(toIndentedString(scopes)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  public Map<String, Object> createFormData() throws ApiException {
    Map<String, Object> map = new HashMap<>();
    boolean fileTypeFound = false;
    try {
    if (callbackUrl != null) {
        if (isFileTypeOrListOfFiles(callbackUrl)) {
            fileTypeFound = true;
        }

        if (callbackUrl.getClass().equals(java.io.File.class) ||
            callbackUrl.getClass().equals(Integer.class) ||
            callbackUrl.getClass().equals(String.class) ||
            callbackUrl.getClass().isEnum() ) {
            map.put("callback_url", callbackUrl);
        } else if (isListOfFile(callbackUrl)) {
            for(int i = 0; i< getListSize(callbackUrl); i++) {
                map.put("callback_url[" + i + "]", getFromList(callbackUrl, i));
            }
        }
        else {
            map.put("callback_url", JSON.getDefault().getMapper().writeValueAsString(callbackUrl));
        }
    }
    if (scopes != null) {
        if (isFileTypeOrListOfFiles(scopes)) {
            fileTypeFound = true;
        }

        if (scopes.getClass().equals(java.io.File.class) ||
            scopes.getClass().equals(Integer.class) ||
            scopes.getClass().equals(String.class) ||
            scopes.getClass().isEnum() ) {
            map.put("scopes", scopes);
        } else if (isListOfFile(scopes)) {
            for(int i = 0; i< getListSize(scopes); i++) {
                map.put("scopes[" + i + "]", getFromList(scopes, i));
            }
        }
        else {
            map.put("scopes", JSON.getDefault().getMapper().writeValueAsString(scopes));
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

