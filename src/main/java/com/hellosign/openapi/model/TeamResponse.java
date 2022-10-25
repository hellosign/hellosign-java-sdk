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
import com.hellosign.openapi.model.AccountResponse;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.hellosign.openapi.JSON;


import com.hellosign.openapi.ApiException;
/**
 * Contains information about your team and its members
 */
@ApiModel(description = "Contains information about your team and its members")
@JsonPropertyOrder({
    TeamResponse.JSON_PROPERTY_NAME,
    TeamResponse.JSON_PROPERTY_ACCOUNTS,
    TeamResponse.JSON_PROPERTY_INVITED_ACCOUNTS
})
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen")
public class TeamResponse {
  public static final String JSON_PROPERTY_NAME = "name";
  private String name;

  public static final String JSON_PROPERTY_ACCOUNTS = "accounts";
  private List<AccountResponse> accounts = null;

  public static final String JSON_PROPERTY_INVITED_ACCOUNTS = "invited_accounts";
  private List<AccountResponse> invitedAccounts = null;

  public TeamResponse() { 
  }

  public TeamResponse name(String name) {
    this.name = name;
    return this;
  }

   /**
   * The name of your Team
   * @return name
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "The name of your Team")
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


  public TeamResponse accounts(List<AccountResponse> accounts) {
    this.accounts = accounts;
    return this;
  }

  public TeamResponse addAccountsItem(AccountResponse accountsItem) {
    if (this.accounts == null) {
      this.accounts = new ArrayList<>();
    }
    this.accounts.add(accountsItem);
    return this;
  }

   /**
   * Get accounts
   * @return accounts
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")
  @JsonProperty(JSON_PROPERTY_ACCOUNTS)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public List<AccountResponse> getAccounts() {
    return accounts;
  }


  @JsonProperty(JSON_PROPERTY_ACCOUNTS)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setAccounts(List<AccountResponse> accounts) {
    this.accounts = accounts;
  }


  public TeamResponse invitedAccounts(List<AccountResponse> invitedAccounts) {
    this.invitedAccounts = invitedAccounts;
    return this;
  }

  public TeamResponse addInvitedAccountsItem(AccountResponse invitedAccountsItem) {
    if (this.invitedAccounts == null) {
      this.invitedAccounts = new ArrayList<>();
    }
    this.invitedAccounts.add(invitedAccountsItem);
    return this;
  }

   /**
   * A list of all Accounts that have an outstanding invitation to join your Team. Note that this response is a subset of the response parameters found in &#x60;GET /account&#x60;.
   * @return invitedAccounts
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "A list of all Accounts that have an outstanding invitation to join your Team. Note that this response is a subset of the response parameters found in `GET /account`.")
  @JsonProperty(JSON_PROPERTY_INVITED_ACCOUNTS)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public List<AccountResponse> getInvitedAccounts() {
    return invitedAccounts;
  }


  @JsonProperty(JSON_PROPERTY_INVITED_ACCOUNTS)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setInvitedAccounts(List<AccountResponse> invitedAccounts) {
    this.invitedAccounts = invitedAccounts;
  }


  /**
   * Return true if this TeamResponse object is equal to o.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TeamResponse teamResponse = (TeamResponse) o;
    return Objects.equals(this.name, teamResponse.name) &&
        Objects.equals(this.accounts, teamResponse.accounts) &&
        Objects.equals(this.invitedAccounts, teamResponse.invitedAccounts);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, accounts, invitedAccounts);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TeamResponse {\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    accounts: ").append(toIndentedString(accounts)).append("\n");
    sb.append("    invitedAccounts: ").append(toIndentedString(invitedAccounts)).append("\n");
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
            name.getClass().isEnum() ) {
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
    if (accounts != null) {
        if (isFileTypeOrListOfFiles(accounts)) {
            fileTypeFound = true;
        }

        if (accounts.getClass().equals(java.io.File.class) ||
            accounts.getClass().equals(Integer.class) ||
            accounts.getClass().equals(String.class) ||
            accounts.getClass().isEnum() ) {
            map.put("accounts", accounts);
        } else if (isListOfFile(accounts)) {
            for(int i = 0; i< getListSize(accounts); i++) {
                map.put("accounts[" + i + "]", getFromList(accounts, i));
            }
        }
        else {
            map.put("accounts", JSON.getDefault().getMapper().writeValueAsString(accounts));
        }
    }
    if (invitedAccounts != null) {
        if (isFileTypeOrListOfFiles(invitedAccounts)) {
            fileTypeFound = true;
        }

        if (invitedAccounts.getClass().equals(java.io.File.class) ||
            invitedAccounts.getClass().equals(Integer.class) ||
            invitedAccounts.getClass().equals(String.class) ||
            invitedAccounts.getClass().isEnum() ) {
            map.put("invited_accounts", invitedAccounts);
        } else if (isListOfFile(invitedAccounts)) {
            for(int i = 0; i< getListSize(invitedAccounts); i++) {
                map.put("invited_accounts[" + i + "]", getFromList(invitedAccounts, i));
            }
        }
        else {
            map.put("invited_accounts", JSON.getDefault().getMapper().writeValueAsString(invitedAccounts));
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

