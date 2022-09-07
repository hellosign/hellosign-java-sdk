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
import com.hellosign.openapi.model.SubCC;
import com.hellosign.openapi.model.SubCustomField;
import com.hellosign.openapi.model.SubSignatureRequestTemplateSigner;
import com.hellosign.openapi.model.SubSigningOptions;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.hellosign.openapi.JSON;


import com.hellosign.openapi.ApiException;
/**
 * SignatureRequestSendWithTemplateRequest
 */
@JsonPropertyOrder({
    SignatureRequestSendWithTemplateRequest.JSON_PROPERTY_TEMPLATE_IDS,
    SignatureRequestSendWithTemplateRequest.JSON_PROPERTY_SIGNERS,
    SignatureRequestSendWithTemplateRequest.JSON_PROPERTY_ALLOW_DECLINE,
    SignatureRequestSendWithTemplateRequest.JSON_PROPERTY_CCS,
    SignatureRequestSendWithTemplateRequest.JSON_PROPERTY_CLIENT_ID,
    SignatureRequestSendWithTemplateRequest.JSON_PROPERTY_CUSTOM_FIELDS,
    SignatureRequestSendWithTemplateRequest.JSON_PROPERTY_FILE,
    SignatureRequestSendWithTemplateRequest.JSON_PROPERTY_FILE_URL,
    SignatureRequestSendWithTemplateRequest.JSON_PROPERTY_IS_QUALIFIED_SIGNATURE,
    SignatureRequestSendWithTemplateRequest.JSON_PROPERTY_MESSAGE,
    SignatureRequestSendWithTemplateRequest.JSON_PROPERTY_METADATA,
    SignatureRequestSendWithTemplateRequest.JSON_PROPERTY_SIGNING_OPTIONS,
    SignatureRequestSendWithTemplateRequest.JSON_PROPERTY_SIGNING_REDIRECT_URL,
    SignatureRequestSendWithTemplateRequest.JSON_PROPERTY_SUBJECT,
    SignatureRequestSendWithTemplateRequest.JSON_PROPERTY_TEST_MODE,
    SignatureRequestSendWithTemplateRequest.JSON_PROPERTY_TITLE
})
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen")
public class SignatureRequestSendWithTemplateRequest {
  public static final String JSON_PROPERTY_TEMPLATE_IDS = "template_ids";
  private List<String> templateIds = new ArrayList<>();

  public static final String JSON_PROPERTY_SIGNERS = "signers";
  private List<SubSignatureRequestTemplateSigner> signers = new ArrayList<>();

  public static final String JSON_PROPERTY_ALLOW_DECLINE = "allow_decline";
  private Boolean allowDecline = false;

  public static final String JSON_PROPERTY_CCS = "ccs";
  private List<SubCC> ccs = null;

  public static final String JSON_PROPERTY_CLIENT_ID = "client_id";
  private String clientId;

  public static final String JSON_PROPERTY_CUSTOM_FIELDS = "custom_fields";
  private List<SubCustomField> customFields = null;

  public static final String JSON_PROPERTY_FILE = "file";
  private List<File> file = null;

  public static final String JSON_PROPERTY_FILE_URL = "file_url";
  private List<String> fileUrl = null;

  public static final String JSON_PROPERTY_IS_QUALIFIED_SIGNATURE = "is_qualified_signature";
  private Boolean isQualifiedSignature = false;

  public static final String JSON_PROPERTY_MESSAGE = "message";
  private String message;

  public static final String JSON_PROPERTY_METADATA = "metadata";
  private Map<String, Object> metadata = null;

  public static final String JSON_PROPERTY_SIGNING_OPTIONS = "signing_options";
  private SubSigningOptions signingOptions;

  public static final String JSON_PROPERTY_SIGNING_REDIRECT_URL = "signing_redirect_url";
  private String signingRedirectUrl;

  public static final String JSON_PROPERTY_SUBJECT = "subject";
  private String subject;

  public static final String JSON_PROPERTY_TEST_MODE = "test_mode";
  private Boolean testMode = false;

  public static final String JSON_PROPERTY_TITLE = "title";
  private String title;

  public SignatureRequestSendWithTemplateRequest() { 
  }

  public SignatureRequestSendWithTemplateRequest templateIds(List<String> templateIds) {
    this.templateIds = templateIds;
    return this;
  }

  public SignatureRequestSendWithTemplateRequest addTemplateIdsItem(String templateIdsItem) {
    this.templateIds.add(templateIdsItem);
    return this;
  }

   /**
   * Use &#x60;template_ids&#x60; to create a SignatureRequest from one or more templates, in the order in which the template will be used.
   * @return templateIds
  **/
  @javax.annotation.Nonnull
  @ApiModelProperty(required = true, value = "Use `template_ids` to create a SignatureRequest from one or more templates, in the order in which the template will be used.")
  @JsonProperty(JSON_PROPERTY_TEMPLATE_IDS)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)

  public List<String> getTemplateIds() {
    return templateIds;
  }


  @JsonProperty(JSON_PROPERTY_TEMPLATE_IDS)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)
  public void setTemplateIds(List<String> templateIds) {
    this.templateIds = templateIds;
  }


  public SignatureRequestSendWithTemplateRequest signers(List<SubSignatureRequestTemplateSigner> signers) {
    this.signers = signers;
    return this;
  }

  public SignatureRequestSendWithTemplateRequest addSignersItem(SubSignatureRequestTemplateSigner signersItem) {
    this.signers.add(signersItem);
    return this;
  }

   /**
   * Add Signers to your Templated-based Signature Request.
   * @return signers
  **/
  @javax.annotation.Nonnull
  @ApiModelProperty(required = true, value = "Add Signers to your Templated-based Signature Request.")
  @JsonProperty(JSON_PROPERTY_SIGNERS)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)

  public List<SubSignatureRequestTemplateSigner> getSigners() {
    return signers;
  }


  @JsonProperty(JSON_PROPERTY_SIGNERS)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)
  public void setSigners(List<SubSignatureRequestTemplateSigner> signers) {
    this.signers = signers;
  }


  public SignatureRequestSendWithTemplateRequest allowDecline(Boolean allowDecline) {
    this.allowDecline = allowDecline;
    return this;
  }

   /**
   * Allows signers to decline to sign a document if &#x60;true&#x60;. Defaults to &#x60;false&#x60;.
   * @return allowDecline
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Allows signers to decline to sign a document if `true`. Defaults to `false`.")
  @JsonProperty(JSON_PROPERTY_ALLOW_DECLINE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public Boolean getAllowDecline() {
    return allowDecline;
  }


  @JsonProperty(JSON_PROPERTY_ALLOW_DECLINE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setAllowDecline(Boolean allowDecline) {
    this.allowDecline = allowDecline;
  }


  public SignatureRequestSendWithTemplateRequest ccs(List<SubCC> ccs) {
    this.ccs = ccs;
    return this;
  }

  public SignatureRequestSendWithTemplateRequest addCcsItem(SubCC ccsItem) {
    if (this.ccs == null) {
      this.ccs = new ArrayList<>();
    }
    this.ccs.add(ccsItem);
    return this;
  }

   /**
   * Add CC email recipients. Required when a CC role exists for the Template.
   * @return ccs
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Add CC email recipients. Required when a CC role exists for the Template.")
  @JsonProperty(JSON_PROPERTY_CCS)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public List<SubCC> getCcs() {
    return ccs;
  }


  @JsonProperty(JSON_PROPERTY_CCS)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setCcs(List<SubCC> ccs) {
    this.ccs = ccs;
  }


  public SignatureRequestSendWithTemplateRequest clientId(String clientId) {
    this.clientId = clientId;
    return this;
  }

   /**
   * Client id of the app to associate with the signature request. Used to apply the branding and callback url defined for the app.
   * @return clientId
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Client id of the app to associate with the signature request. Used to apply the branding and callback url defined for the app.")
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


  public SignatureRequestSendWithTemplateRequest customFields(List<SubCustomField> customFields) {
    this.customFields = customFields;
    return this;
  }

  public SignatureRequestSendWithTemplateRequest addCustomFieldsItem(SubCustomField customFieldsItem) {
    if (this.customFields == null) {
      this.customFields = new ArrayList<>();
    }
    this.customFields.add(customFieldsItem);
    return this;
  }

   /**
   * An array defining values and options for custom fields. Required when a custom field exists in the Template.
   * @return customFields
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "An array defining values and options for custom fields. Required when a custom field exists in the Template.")
  @JsonProperty(JSON_PROPERTY_CUSTOM_FIELDS)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public List<SubCustomField> getCustomFields() {
    return customFields;
  }


  @JsonProperty(JSON_PROPERTY_CUSTOM_FIELDS)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setCustomFields(List<SubCustomField> customFields) {
    this.customFields = customFields;
  }


  public SignatureRequestSendWithTemplateRequest file(List<File> file) {
    this.file = file;
    return this;
  }

  public SignatureRequestSendWithTemplateRequest addFileItem(File fileItem) {
    if (this.file == null) {
      this.file = new ArrayList<>();
    }
    this.file.add(fileItem);
    return this;
  }

   /**
   * Use &#x60;file[]&#x60; to indicate the uploaded file(s) to send for signature.  This endpoint requires either **file** or **file_url[]**, but not both.
   * @return file
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Use `file[]` to indicate the uploaded file(s) to send for signature.  This endpoint requires either **file** or **file_url[]**, but not both.")
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


  public SignatureRequestSendWithTemplateRequest fileUrl(List<String> fileUrl) {
    this.fileUrl = fileUrl;
    return this;
  }

  public SignatureRequestSendWithTemplateRequest addFileUrlItem(String fileUrlItem) {
    if (this.fileUrl == null) {
      this.fileUrl = new ArrayList<>();
    }
    this.fileUrl.add(fileUrlItem);
    return this;
  }

   /**
   * Use &#x60;file_url[]&#x60; to have HelloSign download the file(s) to send for signature.  This endpoint requires either **file** or **file_url[]**, but not both.
   * @return fileUrl
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Use `file_url[]` to have HelloSign download the file(s) to send for signature.  This endpoint requires either **file** or **file_url[]**, but not both.")
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


  public SignatureRequestSendWithTemplateRequest isQualifiedSignature(Boolean isQualifiedSignature) {
    this.isQualifiedSignature = isQualifiedSignature;
    return this;
  }

   /**
   * Send with a value of &#x60;true&#x60; if you wish to enable [Qualified Electronic Signatures](https://www.hellosign.com/features/qualified-electronic-signatures) (QES), which requires a face-to-face call to verify the signer&#39;s identity.&lt;br&gt; **Note**: QES is only available on the Premium API plan as an add-on purchase. Cannot be used in &#x60;test_mode&#x60;. Only works on requests with one signer.
   * @return isQualifiedSignature
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Send with a value of `true` if you wish to enable [Qualified Electronic Signatures](https://www.hellosign.com/features/qualified-electronic-signatures) (QES), which requires a face-to-face call to verify the signer's identity.<br> **Note**: QES is only available on the Premium API plan as an add-on purchase. Cannot be used in `test_mode`. Only works on requests with one signer.")
  @JsonProperty(JSON_PROPERTY_IS_QUALIFIED_SIGNATURE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public Boolean getIsQualifiedSignature() {
    return isQualifiedSignature;
  }


  @JsonProperty(JSON_PROPERTY_IS_QUALIFIED_SIGNATURE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setIsQualifiedSignature(Boolean isQualifiedSignature) {
    this.isQualifiedSignature = isQualifiedSignature;
  }


  public SignatureRequestSendWithTemplateRequest message(String message) {
    this.message = message;
    return this;
  }

   /**
   * The custom message in the email that will be sent to the signers.
   * @return message
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "The custom message in the email that will be sent to the signers.")
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


  public SignatureRequestSendWithTemplateRequest metadata(Map<String, Object> metadata) {
    this.metadata = metadata;
    return this;
  }

  public SignatureRequestSendWithTemplateRequest putMetadataItem(String key, Object metadataItem) {
    if (this.metadata == null) {
      this.metadata = new HashMap<>();
    }
    this.metadata.put(key, metadataItem);
    return this;
  }

   /**
   * Key-value data that should be attached to the signature request. This metadata is included in all API responses and events involving the signature request. For example, use the metadata field to store a signer&#39;s order number for look up when receiving events for the signature request.  Each request can include up to 10 metadata keys (or 50 nested metadata keys), with key names up to 40 characters long and values up to 1000 characters long.
   * @return metadata
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Key-value data that should be attached to the signature request. This metadata is included in all API responses and events involving the signature request. For example, use the metadata field to store a signer's order number for look up when receiving events for the signature request.  Each request can include up to 10 metadata keys (or 50 nested metadata keys), with key names up to 40 characters long and values up to 1000 characters long.")
  @JsonProperty(JSON_PROPERTY_METADATA)
  @JsonInclude(content = JsonInclude.Include.ALWAYS, value = JsonInclude.Include.USE_DEFAULTS)

  public Map<String, Object> getMetadata() {
    return metadata;
  }


  @JsonProperty(JSON_PROPERTY_METADATA)
  @JsonInclude(content = JsonInclude.Include.ALWAYS, value = JsonInclude.Include.USE_DEFAULTS)
  public void setMetadata(Map<String, Object> metadata) {
    this.metadata = metadata;
  }


  public SignatureRequestSendWithTemplateRequest signingOptions(SubSigningOptions signingOptions) {
    this.signingOptions = signingOptions;
    return this;
  }

   /**
   * Get signingOptions
   * @return signingOptions
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")
  @JsonProperty(JSON_PROPERTY_SIGNING_OPTIONS)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public SubSigningOptions getSigningOptions() {
    return signingOptions;
  }


  @JsonProperty(JSON_PROPERTY_SIGNING_OPTIONS)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setSigningOptions(SubSigningOptions signingOptions) {
    this.signingOptions = signingOptions;
  }


  public SignatureRequestSendWithTemplateRequest signingRedirectUrl(String signingRedirectUrl) {
    this.signingRedirectUrl = signingRedirectUrl;
    return this;
  }

   /**
   * The URL you want signers redirected to after they successfully sign.
   * @return signingRedirectUrl
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "The URL you want signers redirected to after they successfully sign.")
  @JsonProperty(JSON_PROPERTY_SIGNING_REDIRECT_URL)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getSigningRedirectUrl() {
    return signingRedirectUrl;
  }


  @JsonProperty(JSON_PROPERTY_SIGNING_REDIRECT_URL)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setSigningRedirectUrl(String signingRedirectUrl) {
    this.signingRedirectUrl = signingRedirectUrl;
  }


  public SignatureRequestSendWithTemplateRequest subject(String subject) {
    this.subject = subject;
    return this;
  }

   /**
   * The subject in the email that will be sent to the signers.
   * @return subject
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "The subject in the email that will be sent to the signers.")
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


  public SignatureRequestSendWithTemplateRequest testMode(Boolean testMode) {
    this.testMode = testMode;
    return this;
  }

   /**
   * Whether this is a test, the signature request will not be legally binding if set to &#x60;true&#x60;. Defaults to &#x60;false&#x60;.
   * @return testMode
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Whether this is a test, the signature request will not be legally binding if set to `true`. Defaults to `false`.")
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


  public SignatureRequestSendWithTemplateRequest title(String title) {
    this.title = title;
    return this;
  }

   /**
   * The title you want to assign to the SignatureRequest.
   * @return title
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "The title you want to assign to the SignatureRequest.")
  @JsonProperty(JSON_PROPERTY_TITLE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getTitle() {
    return title;
  }


  @JsonProperty(JSON_PROPERTY_TITLE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setTitle(String title) {
    this.title = title;
  }


  /**
   * Return true if this SignatureRequestSendWithTemplateRequest object is equal to o.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SignatureRequestSendWithTemplateRequest signatureRequestSendWithTemplateRequest = (SignatureRequestSendWithTemplateRequest) o;
    return Objects.equals(this.templateIds, signatureRequestSendWithTemplateRequest.templateIds) &&
        Objects.equals(this.signers, signatureRequestSendWithTemplateRequest.signers) &&
        Objects.equals(this.allowDecline, signatureRequestSendWithTemplateRequest.allowDecline) &&
        Objects.equals(this.ccs, signatureRequestSendWithTemplateRequest.ccs) &&
        Objects.equals(this.clientId, signatureRequestSendWithTemplateRequest.clientId) &&
        Objects.equals(this.customFields, signatureRequestSendWithTemplateRequest.customFields) &&
        Objects.equals(this.file, signatureRequestSendWithTemplateRequest.file) &&
        Objects.equals(this.fileUrl, signatureRequestSendWithTemplateRequest.fileUrl) &&
        Objects.equals(this.isQualifiedSignature, signatureRequestSendWithTemplateRequest.isQualifiedSignature) &&
        Objects.equals(this.message, signatureRequestSendWithTemplateRequest.message) &&
        Objects.equals(this.metadata, signatureRequestSendWithTemplateRequest.metadata) &&
        Objects.equals(this.signingOptions, signatureRequestSendWithTemplateRequest.signingOptions) &&
        Objects.equals(this.signingRedirectUrl, signatureRequestSendWithTemplateRequest.signingRedirectUrl) &&
        Objects.equals(this.subject, signatureRequestSendWithTemplateRequest.subject) &&
        Objects.equals(this.testMode, signatureRequestSendWithTemplateRequest.testMode) &&
        Objects.equals(this.title, signatureRequestSendWithTemplateRequest.title);
  }

  @Override
  public int hashCode() {
    return Objects.hash(templateIds, signers, allowDecline, ccs, clientId, customFields, file, fileUrl, isQualifiedSignature, message, metadata, signingOptions, signingRedirectUrl, subject, testMode, title);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SignatureRequestSendWithTemplateRequest {\n");
    sb.append("    templateIds: ").append(toIndentedString(templateIds)).append("\n");
    sb.append("    signers: ").append(toIndentedString(signers)).append("\n");
    sb.append("    allowDecline: ").append(toIndentedString(allowDecline)).append("\n");
    sb.append("    ccs: ").append(toIndentedString(ccs)).append("\n");
    sb.append("    clientId: ").append(toIndentedString(clientId)).append("\n");
    sb.append("    customFields: ").append(toIndentedString(customFields)).append("\n");
    sb.append("    file: ").append(toIndentedString(file)).append("\n");
    sb.append("    fileUrl: ").append(toIndentedString(fileUrl)).append("\n");
    sb.append("    isQualifiedSignature: ").append(toIndentedString(isQualifiedSignature)).append("\n");
    sb.append("    message: ").append(toIndentedString(message)).append("\n");
    sb.append("    metadata: ").append(toIndentedString(metadata)).append("\n");
    sb.append("    signingOptions: ").append(toIndentedString(signingOptions)).append("\n");
    sb.append("    signingRedirectUrl: ").append(toIndentedString(signingRedirectUrl)).append("\n");
    sb.append("    subject: ").append(toIndentedString(subject)).append("\n");
    sb.append("    testMode: ").append(toIndentedString(testMode)).append("\n");
    sb.append("    title: ").append(toIndentedString(title)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  public Map<String, Object> createFormData() throws ApiException {
    Map<String, Object> map = new HashMap<>();
    boolean fileTypeFound = false;
    try {
    if (templateIds != null) {
        if (isFileTypeOrListOfFiles(templateIds)) {
            fileTypeFound = true;
        }

        if (templateIds.getClass().equals(java.io.File.class) ||
            templateIds.getClass().equals(Integer.class) ||
            templateIds.getClass().equals(String.class) ) {
            map.put("template_ids", templateIds);
        } else if (isListOfFile(templateIds)) {
            for(int i = 0; i< getListSize(templateIds); i++) {
                map.put("template_ids[" + i + "]", getFromList(templateIds, i));
            }
        }
        else {
            map.put("template_ids", JSON.getDefault().getMapper().writeValueAsString(templateIds));
        }
    }
    if (signers != null) {
        if (isFileTypeOrListOfFiles(signers)) {
            fileTypeFound = true;
        }

        if (signers.getClass().equals(java.io.File.class) ||
            signers.getClass().equals(Integer.class) ||
            signers.getClass().equals(String.class) ) {
            map.put("signers", signers);
        } else if (isListOfFile(signers)) {
            for(int i = 0; i< getListSize(signers); i++) {
                map.put("signers[" + i + "]", getFromList(signers, i));
            }
        }
        else {
            map.put("signers", JSON.getDefault().getMapper().writeValueAsString(signers));
        }
    }
    if (allowDecline != null) {
        if (isFileTypeOrListOfFiles(allowDecline)) {
            fileTypeFound = true;
        }

        if (allowDecline.getClass().equals(java.io.File.class) ||
            allowDecline.getClass().equals(Integer.class) ||
            allowDecline.getClass().equals(String.class) ) {
            map.put("allow_decline", allowDecline);
        } else if (isListOfFile(allowDecline)) {
            for(int i = 0; i< getListSize(allowDecline); i++) {
                map.put("allow_decline[" + i + "]", getFromList(allowDecline, i));
            }
        }
        else {
            map.put("allow_decline", JSON.getDefault().getMapper().writeValueAsString(allowDecline));
        }
    }
    if (ccs != null) {
        if (isFileTypeOrListOfFiles(ccs)) {
            fileTypeFound = true;
        }

        if (ccs.getClass().equals(java.io.File.class) ||
            ccs.getClass().equals(Integer.class) ||
            ccs.getClass().equals(String.class) ) {
            map.put("ccs", ccs);
        } else if (isListOfFile(ccs)) {
            for(int i = 0; i< getListSize(ccs); i++) {
                map.put("ccs[" + i + "]", getFromList(ccs, i));
            }
        }
        else {
            map.put("ccs", JSON.getDefault().getMapper().writeValueAsString(ccs));
        }
    }
    if (clientId != null) {
        if (isFileTypeOrListOfFiles(clientId)) {
            fileTypeFound = true;
        }

        if (clientId.getClass().equals(java.io.File.class) ||
            clientId.getClass().equals(Integer.class) ||
            clientId.getClass().equals(String.class) ) {
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
    if (customFields != null) {
        if (isFileTypeOrListOfFiles(customFields)) {
            fileTypeFound = true;
        }

        if (customFields.getClass().equals(java.io.File.class) ||
            customFields.getClass().equals(Integer.class) ||
            customFields.getClass().equals(String.class) ) {
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
    if (file != null) {
        if (isFileTypeOrListOfFiles(file)) {
            fileTypeFound = true;
        }

        if (file.getClass().equals(java.io.File.class) ||
            file.getClass().equals(Integer.class) ||
            file.getClass().equals(String.class) ) {
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
            fileUrl.getClass().equals(String.class) ) {
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
    if (isQualifiedSignature != null) {
        if (isFileTypeOrListOfFiles(isQualifiedSignature)) {
            fileTypeFound = true;
        }

        if (isQualifiedSignature.getClass().equals(java.io.File.class) ||
            isQualifiedSignature.getClass().equals(Integer.class) ||
            isQualifiedSignature.getClass().equals(String.class) ) {
            map.put("is_qualified_signature", isQualifiedSignature);
        } else if (isListOfFile(isQualifiedSignature)) {
            for(int i = 0; i< getListSize(isQualifiedSignature); i++) {
                map.put("is_qualified_signature[" + i + "]", getFromList(isQualifiedSignature, i));
            }
        }
        else {
            map.put("is_qualified_signature", JSON.getDefault().getMapper().writeValueAsString(isQualifiedSignature));
        }
    }
    if (message != null) {
        if (isFileTypeOrListOfFiles(message)) {
            fileTypeFound = true;
        }

        if (message.getClass().equals(java.io.File.class) ||
            message.getClass().equals(Integer.class) ||
            message.getClass().equals(String.class) ) {
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
    if (metadata != null) {
        if (isFileTypeOrListOfFiles(metadata)) {
            fileTypeFound = true;
        }

        if (metadata.getClass().equals(java.io.File.class) ||
            metadata.getClass().equals(Integer.class) ||
            metadata.getClass().equals(String.class) ) {
            map.put("metadata", metadata);
        } else if (isListOfFile(metadata)) {
            for(int i = 0; i< getListSize(metadata); i++) {
                map.put("metadata[" + i + "]", getFromList(metadata, i));
            }
        }
        else {
            map.put("metadata", JSON.getDefault().getMapper().writeValueAsString(metadata));
        }
    }
    if (signingOptions != null) {
        if (isFileTypeOrListOfFiles(signingOptions)) {
            fileTypeFound = true;
        }

        if (signingOptions.getClass().equals(java.io.File.class) ||
            signingOptions.getClass().equals(Integer.class) ||
            signingOptions.getClass().equals(String.class) ) {
            map.put("signing_options", signingOptions);
        } else if (isListOfFile(signingOptions)) {
            for(int i = 0; i< getListSize(signingOptions); i++) {
                map.put("signing_options[" + i + "]", getFromList(signingOptions, i));
            }
        }
        else {
            map.put("signing_options", JSON.getDefault().getMapper().writeValueAsString(signingOptions));
        }
    }
    if (signingRedirectUrl != null) {
        if (isFileTypeOrListOfFiles(signingRedirectUrl)) {
            fileTypeFound = true;
        }

        if (signingRedirectUrl.getClass().equals(java.io.File.class) ||
            signingRedirectUrl.getClass().equals(Integer.class) ||
            signingRedirectUrl.getClass().equals(String.class) ) {
            map.put("signing_redirect_url", signingRedirectUrl);
        } else if (isListOfFile(signingRedirectUrl)) {
            for(int i = 0; i< getListSize(signingRedirectUrl); i++) {
                map.put("signing_redirect_url[" + i + "]", getFromList(signingRedirectUrl, i));
            }
        }
        else {
            map.put("signing_redirect_url", JSON.getDefault().getMapper().writeValueAsString(signingRedirectUrl));
        }
    }
    if (subject != null) {
        if (isFileTypeOrListOfFiles(subject)) {
            fileTypeFound = true;
        }

        if (subject.getClass().equals(java.io.File.class) ||
            subject.getClass().equals(Integer.class) ||
            subject.getClass().equals(String.class) ) {
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
            testMode.getClass().equals(String.class) ) {
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
    if (title != null) {
        if (isFileTypeOrListOfFiles(title)) {
            fileTypeFound = true;
        }

        if (title.getClass().equals(java.io.File.class) ||
            title.getClass().equals(Integer.class) ||
            title.getClass().equals(String.class) ) {
            map.put("title", title);
        } else if (isListOfFile(title)) {
            for(int i = 0; i< getListSize(title); i++) {
                map.put("title[" + i + "]", getFromList(title, i));
            }
        }
        else {
            map.put("title", JSON.getDefault().getMapper().writeValueAsString(title));
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

