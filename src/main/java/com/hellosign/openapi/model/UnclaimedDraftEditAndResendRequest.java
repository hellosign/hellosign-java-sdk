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
import com.hellosign.openapi.model.SubEditorOptions;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.hellosign.openapi.JSON;


import com.hellosign.openapi.ApiException;
/**
 * UnclaimedDraftEditAndResendRequest
 */
@JsonPropertyOrder({
    UnclaimedDraftEditAndResendRequest.JSON_PROPERTY_CLIENT_ID,
    UnclaimedDraftEditAndResendRequest.JSON_PROPERTY_EDITOR_OPTIONS,
    UnclaimedDraftEditAndResendRequest.JSON_PROPERTY_IS_FOR_EMBEDDED_SIGNING,
    UnclaimedDraftEditAndResendRequest.JSON_PROPERTY_REQUESTER_EMAIL_ADDRESS,
    UnclaimedDraftEditAndResendRequest.JSON_PROPERTY_REQUESTING_REDIRECT_URL,
    UnclaimedDraftEditAndResendRequest.JSON_PROPERTY_SHOW_PROGRESS_STEPPER,
    UnclaimedDraftEditAndResendRequest.JSON_PROPERTY_SIGNING_REDIRECT_URL,
    UnclaimedDraftEditAndResendRequest.JSON_PROPERTY_TEST_MODE
})
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen")
public class UnclaimedDraftEditAndResendRequest {
  public static final String JSON_PROPERTY_CLIENT_ID = "client_id";
  private String clientId;

  public static final String JSON_PROPERTY_EDITOR_OPTIONS = "editor_options";
  private SubEditorOptions editorOptions;

  public static final String JSON_PROPERTY_IS_FOR_EMBEDDED_SIGNING = "is_for_embedded_signing";
  private Boolean isForEmbeddedSigning;

  public static final String JSON_PROPERTY_REQUESTER_EMAIL_ADDRESS = "requester_email_address";
  private String requesterEmailAddress;

  public static final String JSON_PROPERTY_REQUESTING_REDIRECT_URL = "requesting_redirect_url";
  private String requestingRedirectUrl;

  public static final String JSON_PROPERTY_SHOW_PROGRESS_STEPPER = "show_progress_stepper";
  private Boolean showProgressStepper = true;

  public static final String JSON_PROPERTY_SIGNING_REDIRECT_URL = "signing_redirect_url";
  private String signingRedirectUrl;

  public static final String JSON_PROPERTY_TEST_MODE = "test_mode";
  private Boolean testMode = false;

  public UnclaimedDraftEditAndResendRequest() { 
  }

  public UnclaimedDraftEditAndResendRequest clientId(String clientId) {
    this.clientId = clientId;
    return this;
  }

   /**
   * Client id of the app used to create the draft. Used to apply the branding and callback url defined for the app.
   * @return clientId
  **/
  @javax.annotation.Nonnull
  @ApiModelProperty(required = true, value = "Client id of the app used to create the draft. Used to apply the branding and callback url defined for the app.")
  @JsonProperty(JSON_PROPERTY_CLIENT_ID)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)

  public String getClientId() {
    return clientId;
  }


  @JsonProperty(JSON_PROPERTY_CLIENT_ID)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)
  public void setClientId(String clientId) {
    this.clientId = clientId;
  }


  public UnclaimedDraftEditAndResendRequest editorOptions(SubEditorOptions editorOptions) {
    this.editorOptions = editorOptions;
    return this;
  }

   /**
   * Get editorOptions
   * @return editorOptions
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")
  @JsonProperty(JSON_PROPERTY_EDITOR_OPTIONS)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public SubEditorOptions getEditorOptions() {
    return editorOptions;
  }


  @JsonProperty(JSON_PROPERTY_EDITOR_OPTIONS)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setEditorOptions(SubEditorOptions editorOptions) {
    this.editorOptions = editorOptions;
  }


  public UnclaimedDraftEditAndResendRequest isForEmbeddedSigning(Boolean isForEmbeddedSigning) {
    this.isForEmbeddedSigning = isForEmbeddedSigning;
    return this;
  }

   /**
   * The request created from this draft will also be signable in embedded mode if set to &#x60;true&#x60;.
   * @return isForEmbeddedSigning
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "The request created from this draft will also be signable in embedded mode if set to `true`.")
  @JsonProperty(JSON_PROPERTY_IS_FOR_EMBEDDED_SIGNING)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public Boolean getIsForEmbeddedSigning() {
    return isForEmbeddedSigning;
  }


  @JsonProperty(JSON_PROPERTY_IS_FOR_EMBEDDED_SIGNING)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setIsForEmbeddedSigning(Boolean isForEmbeddedSigning) {
    this.isForEmbeddedSigning = isForEmbeddedSigning;
  }


  public UnclaimedDraftEditAndResendRequest requesterEmailAddress(String requesterEmailAddress) {
    this.requesterEmailAddress = requesterEmailAddress;
    return this;
  }

   /**
   * The email address of the user that should be designated as the requester of this draft. If not set, original requester&#39;s email address will be used.
   * @return requesterEmailAddress
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "The email address of the user that should be designated as the requester of this draft. If not set, original requester's email address will be used.")
  @JsonProperty(JSON_PROPERTY_REQUESTER_EMAIL_ADDRESS)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getRequesterEmailAddress() {
    return requesterEmailAddress;
  }


  @JsonProperty(JSON_PROPERTY_REQUESTER_EMAIL_ADDRESS)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setRequesterEmailAddress(String requesterEmailAddress) {
    this.requesterEmailAddress = requesterEmailAddress;
  }


  public UnclaimedDraftEditAndResendRequest requestingRedirectUrl(String requestingRedirectUrl) {
    this.requestingRedirectUrl = requestingRedirectUrl;
    return this;
  }

   /**
   * The URL you want signers redirected to after they successfully request a signature.
   * @return requestingRedirectUrl
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "The URL you want signers redirected to after they successfully request a signature.")
  @JsonProperty(JSON_PROPERTY_REQUESTING_REDIRECT_URL)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getRequestingRedirectUrl() {
    return requestingRedirectUrl;
  }


  @JsonProperty(JSON_PROPERTY_REQUESTING_REDIRECT_URL)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setRequestingRedirectUrl(String requestingRedirectUrl) {
    this.requestingRedirectUrl = requestingRedirectUrl;
  }


  public UnclaimedDraftEditAndResendRequest showProgressStepper(Boolean showProgressStepper) {
    this.showProgressStepper = showProgressStepper;
    return this;
  }

   /**
   * When only one step remains in the signature request process and this parameter is set to &#x60;false&#x60; then the progress stepper will be hidden.
   * @return showProgressStepper
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "When only one step remains in the signature request process and this parameter is set to `false` then the progress stepper will be hidden.")
  @JsonProperty(JSON_PROPERTY_SHOW_PROGRESS_STEPPER)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public Boolean getShowProgressStepper() {
    return showProgressStepper;
  }


  @JsonProperty(JSON_PROPERTY_SHOW_PROGRESS_STEPPER)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setShowProgressStepper(Boolean showProgressStepper) {
    this.showProgressStepper = showProgressStepper;
  }


  public UnclaimedDraftEditAndResendRequest signingRedirectUrl(String signingRedirectUrl) {
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


  public UnclaimedDraftEditAndResendRequest testMode(Boolean testMode) {
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
   * Return true if this UnclaimedDraftEditAndResendRequest object is equal to o.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UnclaimedDraftEditAndResendRequest unclaimedDraftEditAndResendRequest = (UnclaimedDraftEditAndResendRequest) o;
    return Objects.equals(this.clientId, unclaimedDraftEditAndResendRequest.clientId) &&
        Objects.equals(this.editorOptions, unclaimedDraftEditAndResendRequest.editorOptions) &&
        Objects.equals(this.isForEmbeddedSigning, unclaimedDraftEditAndResendRequest.isForEmbeddedSigning) &&
        Objects.equals(this.requesterEmailAddress, unclaimedDraftEditAndResendRequest.requesterEmailAddress) &&
        Objects.equals(this.requestingRedirectUrl, unclaimedDraftEditAndResendRequest.requestingRedirectUrl) &&
        Objects.equals(this.showProgressStepper, unclaimedDraftEditAndResendRequest.showProgressStepper) &&
        Objects.equals(this.signingRedirectUrl, unclaimedDraftEditAndResendRequest.signingRedirectUrl) &&
        Objects.equals(this.testMode, unclaimedDraftEditAndResendRequest.testMode);
  }

  @Override
  public int hashCode() {
    return Objects.hash(clientId, editorOptions, isForEmbeddedSigning, requesterEmailAddress, requestingRedirectUrl, showProgressStepper, signingRedirectUrl, testMode);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class UnclaimedDraftEditAndResendRequest {\n");
    sb.append("    clientId: ").append(toIndentedString(clientId)).append("\n");
    sb.append("    editorOptions: ").append(toIndentedString(editorOptions)).append("\n");
    sb.append("    isForEmbeddedSigning: ").append(toIndentedString(isForEmbeddedSigning)).append("\n");
    sb.append("    requesterEmailAddress: ").append(toIndentedString(requesterEmailAddress)).append("\n");
    sb.append("    requestingRedirectUrl: ").append(toIndentedString(requestingRedirectUrl)).append("\n");
    sb.append("    showProgressStepper: ").append(toIndentedString(showProgressStepper)).append("\n");
    sb.append("    signingRedirectUrl: ").append(toIndentedString(signingRedirectUrl)).append("\n");
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
    if (editorOptions != null) {
        if (isFileTypeOrListOfFiles(editorOptions)) {
            fileTypeFound = true;
        }

        if (editorOptions.getClass().equals(java.io.File.class) ||
            editorOptions.getClass().equals(Integer.class) ||
            editorOptions.getClass().equals(String.class) ||
            editorOptions.getClass().isEnum() ) {
            map.put("editor_options", editorOptions);
        } else if (isListOfFile(editorOptions)) {
            for(int i = 0; i< getListSize(editorOptions); i++) {
                map.put("editor_options[" + i + "]", getFromList(editorOptions, i));
            }
        }
        else {
            map.put("editor_options", JSON.getDefault().getMapper().writeValueAsString(editorOptions));
        }
    }
    if (isForEmbeddedSigning != null) {
        if (isFileTypeOrListOfFiles(isForEmbeddedSigning)) {
            fileTypeFound = true;
        }

        if (isForEmbeddedSigning.getClass().equals(java.io.File.class) ||
            isForEmbeddedSigning.getClass().equals(Integer.class) ||
            isForEmbeddedSigning.getClass().equals(String.class) ||
            isForEmbeddedSigning.getClass().isEnum() ) {
            map.put("is_for_embedded_signing", isForEmbeddedSigning);
        } else if (isListOfFile(isForEmbeddedSigning)) {
            for(int i = 0; i< getListSize(isForEmbeddedSigning); i++) {
                map.put("is_for_embedded_signing[" + i + "]", getFromList(isForEmbeddedSigning, i));
            }
        }
        else {
            map.put("is_for_embedded_signing", JSON.getDefault().getMapper().writeValueAsString(isForEmbeddedSigning));
        }
    }
    if (requesterEmailAddress != null) {
        if (isFileTypeOrListOfFiles(requesterEmailAddress)) {
            fileTypeFound = true;
        }

        if (requesterEmailAddress.getClass().equals(java.io.File.class) ||
            requesterEmailAddress.getClass().equals(Integer.class) ||
            requesterEmailAddress.getClass().equals(String.class) ||
            requesterEmailAddress.getClass().isEnum() ) {
            map.put("requester_email_address", requesterEmailAddress);
        } else if (isListOfFile(requesterEmailAddress)) {
            for(int i = 0; i< getListSize(requesterEmailAddress); i++) {
                map.put("requester_email_address[" + i + "]", getFromList(requesterEmailAddress, i));
            }
        }
        else {
            map.put("requester_email_address", JSON.getDefault().getMapper().writeValueAsString(requesterEmailAddress));
        }
    }
    if (requestingRedirectUrl != null) {
        if (isFileTypeOrListOfFiles(requestingRedirectUrl)) {
            fileTypeFound = true;
        }

        if (requestingRedirectUrl.getClass().equals(java.io.File.class) ||
            requestingRedirectUrl.getClass().equals(Integer.class) ||
            requestingRedirectUrl.getClass().equals(String.class) ||
            requestingRedirectUrl.getClass().isEnum() ) {
            map.put("requesting_redirect_url", requestingRedirectUrl);
        } else if (isListOfFile(requestingRedirectUrl)) {
            for(int i = 0; i< getListSize(requestingRedirectUrl); i++) {
                map.put("requesting_redirect_url[" + i + "]", getFromList(requestingRedirectUrl, i));
            }
        }
        else {
            map.put("requesting_redirect_url", JSON.getDefault().getMapper().writeValueAsString(requestingRedirectUrl));
        }
    }
    if (showProgressStepper != null) {
        if (isFileTypeOrListOfFiles(showProgressStepper)) {
            fileTypeFound = true;
        }

        if (showProgressStepper.getClass().equals(java.io.File.class) ||
            showProgressStepper.getClass().equals(Integer.class) ||
            showProgressStepper.getClass().equals(String.class) ||
            showProgressStepper.getClass().isEnum() ) {
            map.put("show_progress_stepper", showProgressStepper);
        } else if (isListOfFile(showProgressStepper)) {
            for(int i = 0; i< getListSize(showProgressStepper); i++) {
                map.put("show_progress_stepper[" + i + "]", getFromList(showProgressStepper, i));
            }
        }
        else {
            map.put("show_progress_stepper", JSON.getDefault().getMapper().writeValueAsString(showProgressStepper));
        }
    }
    if (signingRedirectUrl != null) {
        if (isFileTypeOrListOfFiles(signingRedirectUrl)) {
            fileTypeFound = true;
        }

        if (signingRedirectUrl.getClass().equals(java.io.File.class) ||
            signingRedirectUrl.getClass().equals(Integer.class) ||
            signingRedirectUrl.getClass().equals(String.class) ||
            signingRedirectUrl.getClass().isEnum() ) {
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

