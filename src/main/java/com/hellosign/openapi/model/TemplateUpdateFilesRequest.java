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
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.hellosign.openapi.JSON;


import com.hellosign.openapi.ApiException;
/**
 * TemplateUpdateFilesRequest
 */
@JsonPropertyOrder({
    TemplateUpdateFilesRequest.JSON_PROPERTY_CLIENT_ID,
    TemplateUpdateFilesRequest.JSON_PROPERTY_FILE,
    TemplateUpdateFilesRequest.JSON_PROPERTY_FILE_URL,
    TemplateUpdateFilesRequest.JSON_PROPERTY_MESSAGE,
    TemplateUpdateFilesRequest.JSON_PROPERTY_SUBJECT,
    TemplateUpdateFilesRequest.JSON_PROPERTY_TEST_MODE
})
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen")
public class TemplateUpdateFilesRequest {
  public static final String JSON_PROPERTY_CLIENT_ID = "client_id";
  private String clientId;

  public static final String JSON_PROPERTY_FILE = "file";
  private List<File> file = null;

  public static final String JSON_PROPERTY_FILE_URL = "file_url";
  private List<String> fileUrl = null;

  public static final String JSON_PROPERTY_MESSAGE = "message";
  private String message;

  public static final String JSON_PROPERTY_SUBJECT = "subject";
  private String subject;

  public static final String JSON_PROPERTY_TEST_MODE = "test_mode";
  private Boolean testMode = false;

  public TemplateUpdateFilesRequest() { 
  }

  public TemplateUpdateFilesRequest clientId(String clientId) {
    this.clientId = clientId;
    return this;
  }

   /**
   * Client id of the app you&#39;re using to update this template.
   * @return clientId
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Client id of the app you're using to update this template.")
  @JsonProperty(JSON_PROPERTY_CLIENT_ID)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getClientId() {
    return clientId;
  }


  @JsonProperty(JSON_PROPERTY_CLIENT_ID)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setClientId(String clientId) {
    this.clientId = clientId;
  }


  public TemplateUpdateFilesRequest file(List<File> file) {
    this.file = file;
    return this;
  }

  public TemplateUpdateFilesRequest addFileItem(File fileItem) {
    if (this.file == null) {
      this.file = new ArrayList<>();
    }
    this.file.add(fileItem);
    return this;
  }

   /**
   * Use &#x60;file[]&#x60; to indicate the uploaded file(s) to use for the template.  This endpoint requires either **file** or **file_url[]**, but not both.
   * @return file
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Use `file[]` to indicate the uploaded file(s) to use for the template.  This endpoint requires either **file** or **file_url[]**, but not both.")
  @JsonProperty(JSON_PROPERTY_FILE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public List<File> getFile() {
    return file;
  }


  @JsonProperty(JSON_PROPERTY_FILE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setFile(List<File> file) {
    this.file = file;
  }


  public TemplateUpdateFilesRequest fileUrl(List<String> fileUrl) {
    this.fileUrl = fileUrl;
    return this;
  }

  public TemplateUpdateFilesRequest addFileUrlItem(String fileUrlItem) {
    if (this.fileUrl == null) {
      this.fileUrl = new ArrayList<>();
    }
    this.fileUrl.add(fileUrlItem);
    return this;
  }

   /**
   * Use &#x60;file_url[]&#x60; to have HelloSign download the file(s) to use for the template.  This endpoint requires either **file** or **file_url[]**, but not both.
   * @return fileUrl
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Use `file_url[]` to have HelloSign download the file(s) to use for the template.  This endpoint requires either **file** or **file_url[]**, but not both.")
  @JsonProperty(JSON_PROPERTY_FILE_URL)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public List<String> getFileUrl() {
    return fileUrl;
  }


  @JsonProperty(JSON_PROPERTY_FILE_URL)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setFileUrl(List<String> fileUrl) {
    this.fileUrl = fileUrl;
  }


  public TemplateUpdateFilesRequest message(String message) {
    this.message = message;
    return this;
  }

   /**
   * The new default template email message.
   * @return message
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "The new default template email message.")
  @JsonProperty(JSON_PROPERTY_MESSAGE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getMessage() {
    return message;
  }


  @JsonProperty(JSON_PROPERTY_MESSAGE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setMessage(String message) {
    this.message = message;
  }


  public TemplateUpdateFilesRequest subject(String subject) {
    this.subject = subject;
    return this;
  }

   /**
   * The new default template email subject.
   * @return subject
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "The new default template email subject.")
  @JsonProperty(JSON_PROPERTY_SUBJECT)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getSubject() {
    return subject;
  }


  @JsonProperty(JSON_PROPERTY_SUBJECT)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setSubject(String subject) {
    this.subject = subject;
  }


  public TemplateUpdateFilesRequest testMode(Boolean testMode) {
    this.testMode = testMode;
    return this;
  }

   /**
   * Whether this is a test, the signature request created from this draft will not be legally binding if set to &#x60;true&#x60;. Defaults to &#x60;false&#x60;.
   * @return testMode
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Whether this is a test, the signature request created from this draft will not be legally binding if set to `true`. Defaults to `false`.")
  @JsonProperty(JSON_PROPERTY_TEST_MODE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public Boolean getTestMode() {
    return testMode;
  }


  @JsonProperty(JSON_PROPERTY_TEST_MODE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setTestMode(Boolean testMode) {
    this.testMode = testMode;
  }


  /**
   * Return true if this TemplateUpdateFilesRequest object is equal to o.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TemplateUpdateFilesRequest templateUpdateFilesRequest = (TemplateUpdateFilesRequest) o;
    return Objects.equals(this.clientId, templateUpdateFilesRequest.clientId) &&
        Objects.equals(this.file, templateUpdateFilesRequest.file) &&
        Objects.equals(this.fileUrl, templateUpdateFilesRequest.fileUrl) &&
        Objects.equals(this.message, templateUpdateFilesRequest.message) &&
        Objects.equals(this.subject, templateUpdateFilesRequest.subject) &&
        Objects.equals(this.testMode, templateUpdateFilesRequest.testMode);
  }

  @Override
  public int hashCode() {
    return Objects.hash(clientId, file, fileUrl, message, subject, testMode);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TemplateUpdateFilesRequest {\n");
    sb.append("    clientId: ").append(toIndentedString(clientId)).append("\n");
    sb.append("    file: ").append(toIndentedString(file)).append("\n");
    sb.append("    fileUrl: ").append(toIndentedString(fileUrl)).append("\n");
    sb.append("    message: ").append(toIndentedString(message)).append("\n");
    sb.append("    subject: ").append(toIndentedString(subject)).append("\n");
    sb.append("    testMode: ").append(toIndentedString(testMode)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  public Map<String, Object> createFormData() throws ApiException {
    Map<String, Object> map = new HashMap<>();
    boolean fileTypeFound = false;
    try {
    if (clientId != null) {
        if (isFileTypeOrListOfFiles(clientId)) {
            fileTypeFound = true;
        }

        if (clientId.getClass().equals(java.io.File.class) ||
            clientId.getClass().equals(Integer.class) ||
            clientId.getClass().equals(String.class) ||
            clientId.getClass().isEnum() ) {
            map.put("client_id", clientId);
        } else if (isListOfFile(clientId)) {
            for(int i = 0; i< getListSize(clientId); i++) {
                map.put("client_id[" + i + "]", getFromList(clientId, i));
            }
        }
        else {
            map.put("client_id", JSON.getDefault().getMapper().writeValueAsString(clientId));
        }
    }
    if (file != null) {
        if (isFileTypeOrListOfFiles(file)) {
            fileTypeFound = true;
        }

        if (file.getClass().equals(java.io.File.class) ||
            file.getClass().equals(Integer.class) ||
            file.getClass().equals(String.class) ||
            file.getClass().isEnum() ) {
            map.put("file", file);
        } else if (isListOfFile(file)) {
            for(int i = 0; i< getListSize(file); i++) {
                map.put("file[" + i + "]", getFromList(file, i));
            }
        }
        else {
            map.put("file", JSON.getDefault().getMapper().writeValueAsString(file));
        }
    }
    if (fileUrl != null) {
        if (isFileTypeOrListOfFiles(fileUrl)) {
            fileTypeFound = true;
        }

        if (fileUrl.getClass().equals(java.io.File.class) ||
            fileUrl.getClass().equals(Integer.class) ||
            fileUrl.getClass().equals(String.class) ||
            fileUrl.getClass().isEnum() ) {
            map.put("file_url", fileUrl);
        } else if (isListOfFile(fileUrl)) {
            for(int i = 0; i< getListSize(fileUrl); i++) {
                map.put("file_url[" + i + "]", getFromList(fileUrl, i));
            }
        }
        else {
            map.put("file_url", JSON.getDefault().getMapper().writeValueAsString(fileUrl));
        }
    }
    if (message != null) {
        if (isFileTypeOrListOfFiles(message)) {
            fileTypeFound = true;
        }

        if (message.getClass().equals(java.io.File.class) ||
            message.getClass().equals(Integer.class) ||
            message.getClass().equals(String.class) ||
            message.getClass().isEnum() ) {
            map.put("message", message);
        } else if (isListOfFile(message)) {
            for(int i = 0; i< getListSize(message); i++) {
                map.put("message[" + i + "]", getFromList(message, i));
            }
        }
        else {
            map.put("message", JSON.getDefault().getMapper().writeValueAsString(message));
        }
    }
    if (subject != null) {
        if (isFileTypeOrListOfFiles(subject)) {
            fileTypeFound = true;
        }

        if (subject.getClass().equals(java.io.File.class) ||
            subject.getClass().equals(Integer.class) ||
            subject.getClass().equals(String.class) ||
            subject.getClass().isEnum() ) {
            map.put("subject", subject);
        } else if (isListOfFile(subject)) {
            for(int i = 0; i< getListSize(subject); i++) {
                map.put("subject[" + i + "]", getFromList(subject, i));
            }
        }
        else {
            map.put("subject", JSON.getDefault().getMapper().writeValueAsString(subject));
        }
    }
    if (testMode != null) {
        if (isFileTypeOrListOfFiles(testMode)) {
            fileTypeFound = true;
        }

        if (testMode.getClass().equals(java.io.File.class) ||
            testMode.getClass().equals(Integer.class) ||
            testMode.getClass().equals(String.class) ||
            testMode.getClass().isEnum() ) {
            map.put("test_mode", testMode);
        } else if (isListOfFile(testMode)) {
            for(int i = 0; i< getListSize(testMode); i++) {
                map.put("test_mode[" + i + "]", getFromList(testMode, i));
            }
        }
        else {
            map.put("test_mode", JSON.getDefault().getMapper().writeValueAsString(testMode));
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

