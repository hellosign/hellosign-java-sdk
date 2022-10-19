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
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.hellosign.openapi.JSON;


import com.hellosign.openapi.ApiException;
/**
 * TeamInviteResponse
 */
@JsonPropertyOrder({
    TeamInviteResponse.JSON_PROPERTY_EMAIL_ADDRESS,
    TeamInviteResponse.JSON_PROPERTY_TEAM_ID,
    TeamInviteResponse.JSON_PROPERTY_ROLE,
    TeamInviteResponse.JSON_PROPERTY_SENT_AT,
    TeamInviteResponse.JSON_PROPERTY_REDEEMED_AT,
    TeamInviteResponse.JSON_PROPERTY_EXPIRES_AT
})
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen")
public class TeamInviteResponse {
  public static final String JSON_PROPERTY_EMAIL_ADDRESS = "email_address";
  private String emailAddress;

  public static final String JSON_PROPERTY_TEAM_ID = "team_id";
  private String teamId;

  public static final String JSON_PROPERTY_ROLE = "role";
  private String role;

  public static final String JSON_PROPERTY_SENT_AT = "sent_at";
  private Integer sentAt;

  public static final String JSON_PROPERTY_REDEEMED_AT = "redeemed_at";
  private Integer redeemedAt;

  public static final String JSON_PROPERTY_EXPIRES_AT = "expires_at";
  private Integer expiresAt;

  public TeamInviteResponse() { 
  }

  public TeamInviteResponse emailAddress(String emailAddress) {
    this.emailAddress = emailAddress;
    return this;
  }

   /**
   * Email address of the user invited to this team.
   * @return emailAddress
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Email address of the user invited to this team.")
  @JsonProperty(JSON_PROPERTY_EMAIL_ADDRESS)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getEmailAddress() {
    return emailAddress;
  }


  @JsonProperty(JSON_PROPERTY_EMAIL_ADDRESS)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setEmailAddress(String emailAddress) {
    this.emailAddress = emailAddress;
  }


  public TeamInviteResponse teamId(String teamId) {
    this.teamId = teamId;
    return this;
  }

   /**
   * Id of the team.
   * @return teamId
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Id of the team.")
  @JsonProperty(JSON_PROPERTY_TEAM_ID)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getTeamId() {
    return teamId;
  }


  @JsonProperty(JSON_PROPERTY_TEAM_ID)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setTeamId(String teamId) {
    this.teamId = teamId;
  }


  public TeamInviteResponse role(String role) {
    this.role = role;
    return this;
  }

   /**
   * Role of the user invited to this team.
   * @return role
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Role of the user invited to this team.")
  @JsonProperty(JSON_PROPERTY_ROLE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getRole() {
    return role;
  }


  @JsonProperty(JSON_PROPERTY_ROLE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setRole(String role) {
    this.role = role;
  }


  public TeamInviteResponse sentAt(Integer sentAt) {
    this.sentAt = sentAt;
    return this;
  }

   /**
   * Timestamp when the invitation was sent.
   * @return sentAt
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Timestamp when the invitation was sent.")
  @JsonProperty(JSON_PROPERTY_SENT_AT)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public Integer getSentAt() {
    return sentAt;
  }


  @JsonProperty(JSON_PROPERTY_SENT_AT)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setSentAt(Integer sentAt) {
    this.sentAt = sentAt;
  }


  public TeamInviteResponse redeemedAt(Integer redeemedAt) {
    this.redeemedAt = redeemedAt;
    return this;
  }

   /**
   * Timestamp when the invitation was redeemed.
   * @return redeemedAt
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Timestamp when the invitation was redeemed.")
  @JsonProperty(JSON_PROPERTY_REDEEMED_AT)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public Integer getRedeemedAt() {
    return redeemedAt;
  }


  @JsonProperty(JSON_PROPERTY_REDEEMED_AT)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setRedeemedAt(Integer redeemedAt) {
    this.redeemedAt = redeemedAt;
  }


  public TeamInviteResponse expiresAt(Integer expiresAt) {
    this.expiresAt = expiresAt;
    return this;
  }

   /**
   * Timestamp when the invitation is expiring.
   * @return expiresAt
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Timestamp when the invitation is expiring.")
  @JsonProperty(JSON_PROPERTY_EXPIRES_AT)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public Integer getExpiresAt() {
    return expiresAt;
  }


  @JsonProperty(JSON_PROPERTY_EXPIRES_AT)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setExpiresAt(Integer expiresAt) {
    this.expiresAt = expiresAt;
  }


  /**
   * Return true if this TeamInviteResponse object is equal to o.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TeamInviteResponse teamInviteResponse = (TeamInviteResponse) o;
    return Objects.equals(this.emailAddress, teamInviteResponse.emailAddress) &&
        Objects.equals(this.teamId, teamInviteResponse.teamId) &&
        Objects.equals(this.role, teamInviteResponse.role) &&
        Objects.equals(this.sentAt, teamInviteResponse.sentAt) &&
        Objects.equals(this.redeemedAt, teamInviteResponse.redeemedAt) &&
        Objects.equals(this.expiresAt, teamInviteResponse.expiresAt);
  }

  @Override
  public int hashCode() {
    return Objects.hash(emailAddress, teamId, role, sentAt, redeemedAt, expiresAt);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TeamInviteResponse {\n");
    sb.append("    emailAddress: ").append(toIndentedString(emailAddress)).append("\n");
    sb.append("    teamId: ").append(toIndentedString(teamId)).append("\n");
    sb.append("    role: ").append(toIndentedString(role)).append("\n");
    sb.append("    sentAt: ").append(toIndentedString(sentAt)).append("\n");
    sb.append("    redeemedAt: ").append(toIndentedString(redeemedAt)).append("\n");
    sb.append("    expiresAt: ").append(toIndentedString(expiresAt)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  public Map<String, Object> createFormData() throws ApiException {
    Map<String, Object> map = new HashMap<>();
    boolean fileTypeFound = false;
    try {
    if (emailAddress != null) {
        if (isFileTypeOrListOfFiles(emailAddress)) {
            fileTypeFound = true;
        }

        if (emailAddress.getClass().equals(java.io.File.class) ||
            emailAddress.getClass().equals(Integer.class) ||
            emailAddress.getClass().equals(String.class) ||
            emailAddress.getClass().isEnum()) {
            map.put("email_address", emailAddress);
        } else if (isListOfFile(emailAddress)) {
            for(int i = 0; i< getListSize(emailAddress); i++) {
                map.put("email_address[" + i + "]", getFromList(emailAddress, i));
            }
        }
        else {
            map.put("email_address", JSON.getDefault().getMapper().writeValueAsString(emailAddress));
        }
    }
    if (teamId != null) {
        if (isFileTypeOrListOfFiles(teamId)) {
            fileTypeFound = true;
        }

        if (teamId.getClass().equals(java.io.File.class) ||
            teamId.getClass().equals(Integer.class) ||
            teamId.getClass().equals(String.class) ||
            teamId.getClass().isEnum()) {
            map.put("team_id", teamId);
        } else if (isListOfFile(teamId)) {
            for(int i = 0; i< getListSize(teamId); i++) {
                map.put("team_id[" + i + "]", getFromList(teamId, i));
            }
        }
        else {
            map.put("team_id", JSON.getDefault().getMapper().writeValueAsString(teamId));
        }
    }
    if (role != null) {
        if (isFileTypeOrListOfFiles(role)) {
            fileTypeFound = true;
        }

        if (role.getClass().equals(java.io.File.class) ||
            role.getClass().equals(Integer.class) ||
            role.getClass().equals(String.class) ||
            role.getClass().isEnum()) {
            map.put("role", role);
        } else if (isListOfFile(role)) {
            for(int i = 0; i< getListSize(role); i++) {
                map.put("role[" + i + "]", getFromList(role, i));
            }
        }
        else {
            map.put("role", JSON.getDefault().getMapper().writeValueAsString(role));
        }
    }
    if (sentAt != null) {
        if (isFileTypeOrListOfFiles(sentAt)) {
            fileTypeFound = true;
        }

        if (sentAt.getClass().equals(java.io.File.class) ||
            sentAt.getClass().equals(Integer.class) ||
            sentAt.getClass().equals(String.class) ||
            sentAt.getClass().isEnum()) {
            map.put("sent_at", sentAt);
        } else if (isListOfFile(sentAt)) {
            for(int i = 0; i< getListSize(sentAt); i++) {
                map.put("sent_at[" + i + "]", getFromList(sentAt, i));
            }
        }
        else {
            map.put("sent_at", JSON.getDefault().getMapper().writeValueAsString(sentAt));
        }
    }
    if (redeemedAt != null) {
        if (isFileTypeOrListOfFiles(redeemedAt)) {
            fileTypeFound = true;
        }

        if (redeemedAt.getClass().equals(java.io.File.class) ||
            redeemedAt.getClass().equals(Integer.class) ||
            redeemedAt.getClass().equals(String.class) ||
            redeemedAt.getClass().isEnum()) {
            map.put("redeemed_at", redeemedAt);
        } else if (isListOfFile(redeemedAt)) {
            for(int i = 0; i< getListSize(redeemedAt); i++) {
                map.put("redeemed_at[" + i + "]", getFromList(redeemedAt, i));
            }
        }
        else {
            map.put("redeemed_at", JSON.getDefault().getMapper().writeValueAsString(redeemedAt));
        }
    }
    if (expiresAt != null) {
        if (isFileTypeOrListOfFiles(expiresAt)) {
            fileTypeFound = true;
        }

        if (expiresAt.getClass().equals(java.io.File.class) ||
            expiresAt.getClass().equals(Integer.class) ||
            expiresAt.getClass().equals(String.class) ||
            expiresAt.getClass().isEnum()) {
            map.put("expires_at", expiresAt);
        } else if (isListOfFile(expiresAt)) {
            for(int i = 0; i< getListSize(expiresAt); i++) {
                map.put("expires_at[" + i + "]", getFromList(expiresAt, i));
            }
        }
        else {
            map.put("expires_at", JSON.getDefault().getMapper().writeValueAsString(expiresAt));
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

