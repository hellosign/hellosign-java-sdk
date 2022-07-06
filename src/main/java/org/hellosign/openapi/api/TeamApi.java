package org.hellosign.openapi.api;

import org.hellosign.openapi.ApiException;
import org.hellosign.openapi.ApiClient;
import org.hellosign.openapi.ApiResponse;
import org.hellosign.openapi.Configuration;
import org.hellosign.openapi.Pair;

import javax.ws.rs.core.GenericType;

import org.hellosign.openapi.model.ErrorResponse;
import org.hellosign.openapi.model.TeamAddMemberRequest;
import org.hellosign.openapi.model.TeamCreateRequest;
import org.hellosign.openapi.model.TeamGetResponse;
import org.hellosign.openapi.model.TeamRemoveMemberRequest;
import org.hellosign.openapi.model.TeamUpdateRequest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen")
public class TeamApi {
  private ApiClient apiClient;

  public TeamApi() {
    this(Configuration.getDefaultApiClient());
  }

  public TeamApi(ApiClient apiClient) {
    this.apiClient = apiClient;
  }

  /**
   * Get the API client
   *
   * @return API client
   */
  public ApiClient getApiClient() {
    return apiClient;
  }

  /**
   * Set the API client
   *
   * @param apiClient an instance of API client
   */
  public void setApiClient(ApiClient apiClient) {
    this.apiClient = apiClient;
  }

  /**
   * Add User to Team
   * Invites a user (specified using the &#x60;email_address&#x60; parameter) to your Team. If the user does not currently have a HelloSign Account, a new one will be created for them. If a user is already a part of another Team, a &#x60;team_invite_failed&#x60; error will be returned.
   * @param teamAddMemberRequest  (required)
   * @param teamId The id of the team. (optional)
   * @return TeamGetResponse
   * @throws ApiException if fails to make API call
   * @http.response.details
     <table summary="Response Details" border="1">
       <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
       <tr><td> 200 </td><td> successful operation </td><td>  -  </td></tr>
       <tr><td> 4XX </td><td> failed_operation </td><td>  -  </td></tr>
     </table>
   */
  public TeamGetResponse teamAddMember(TeamAddMemberRequest teamAddMemberRequest, String teamId) throws ApiException {
    return teamAddMemberWithHttpInfo(teamAddMemberRequest, teamId).getData();
  }

  /**
   * Add User to Team
   * Invites a user (specified using the &#x60;email_address&#x60; parameter) to your Team. If the user does not currently have a HelloSign Account, a new one will be created for them. If a user is already a part of another Team, a &#x60;team_invite_failed&#x60; error will be returned.
   * @param teamAddMemberRequest  (required)
   * @param teamId The id of the team. (optional)
   * @return ApiResponse&lt;TeamGetResponse&gt;
   * @throws ApiException if fails to make API call
   * @http.response.details
     <table summary="Response Details" border="1">
       <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
       <tr><td> 200 </td><td> successful operation </td><td>  -  </td></tr>
       <tr><td> 4XX </td><td> failed_operation </td><td>  -  </td></tr>
     </table>
   */
  public ApiResponse<TeamGetResponse> teamAddMemberWithHttpInfo(TeamAddMemberRequest teamAddMemberRequest, String teamId) throws ApiException {
    
    Object localVarPostBody = teamAddMemberRequest;
    
    // verify the required parameter 'teamAddMemberRequest' is set
    if (teamAddMemberRequest == null) {
      throw new ApiException(400, "Missing the required parameter 'teamAddMemberRequest' when calling teamAddMember");
    }
    
    // create path and map variables
    String localVarPath = "/team/add_member";

    // query params
    List<Pair> localVarQueryParams = new ArrayList<Pair>();
    Map<String, String> localVarHeaderParams = new HashMap<String, String>();
    Map<String, String> localVarCookieParams = new HashMap<String, String>();
    Map<String, Object> localVarFormParams = new HashMap<String, Object>();

    localVarQueryParams.addAll(apiClient.parameterToPairs("", "team_id", teamId));

    
    
    
    final String[] localVarAccepts = {
      "application/json"
    };
    final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);

    final String[] localVarContentTypes = {
      "application/json"
    };

    localVarFormParams = teamAddMemberRequest.createFormData();
    boolean isFileTypeFound = !localVarFormParams.isEmpty();

    final String localVarContentType = isFileTypeFound? "multipart/form-data" : apiClient.selectHeaderContentType(localVarContentTypes);

    String[] localVarAuthNames = new String[] { "api_key", "oauth2" };

    GenericType<TeamGetResponse> localVarReturnType = new GenericType<TeamGetResponse>() {};

    return apiClient.invokeAPI("TeamApi.teamAddMember", localVarPath, "PUT", localVarQueryParams, localVarPostBody,
                               localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType,
                               localVarAuthNames, localVarReturnType, false);
  }
  /**
   * Create Team
   * Creates a new Team and makes you a member. You must not currently belong to a Team to invoke.
   * @param teamCreateRequest  (required)
   * @return TeamGetResponse
   * @throws ApiException if fails to make API call
   * @http.response.details
     <table summary="Response Details" border="1">
       <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
       <tr><td> 200 </td><td> successful operation </td><td>  -  </td></tr>
       <tr><td> 4XX </td><td> failed_operation </td><td>  -  </td></tr>
     </table>
   */
  public TeamGetResponse teamCreate(TeamCreateRequest teamCreateRequest) throws ApiException {
    return teamCreateWithHttpInfo(teamCreateRequest).getData();
  }

  /**
   * Create Team
   * Creates a new Team and makes you a member. You must not currently belong to a Team to invoke.
   * @param teamCreateRequest  (required)
   * @return ApiResponse&lt;TeamGetResponse&gt;
   * @throws ApiException if fails to make API call
   * @http.response.details
     <table summary="Response Details" border="1">
       <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
       <tr><td> 200 </td><td> successful operation </td><td>  -  </td></tr>
       <tr><td> 4XX </td><td> failed_operation </td><td>  -  </td></tr>
     </table>
   */
  public ApiResponse<TeamGetResponse> teamCreateWithHttpInfo(TeamCreateRequest teamCreateRequest) throws ApiException {
    
    Object localVarPostBody = teamCreateRequest;
    
    // verify the required parameter 'teamCreateRequest' is set
    if (teamCreateRequest == null) {
      throw new ApiException(400, "Missing the required parameter 'teamCreateRequest' when calling teamCreate");
    }
    
    // create path and map variables
    String localVarPath = "/team/create";

    // query params
    List<Pair> localVarQueryParams = new ArrayList<Pair>();
    Map<String, String> localVarHeaderParams = new HashMap<String, String>();
    Map<String, String> localVarCookieParams = new HashMap<String, String>();
    Map<String, Object> localVarFormParams = new HashMap<String, Object>();


    
    
    
    final String[] localVarAccepts = {
      "application/json"
    };
    final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);

    final String[] localVarContentTypes = {
      "application/json"
    };

    localVarFormParams = teamCreateRequest.createFormData();
    boolean isFileTypeFound = !localVarFormParams.isEmpty();

    final String localVarContentType = isFileTypeFound? "multipart/form-data" : apiClient.selectHeaderContentType(localVarContentTypes);

    String[] localVarAuthNames = new String[] { "api_key", "oauth2" };

    GenericType<TeamGetResponse> localVarReturnType = new GenericType<TeamGetResponse>() {};

    return apiClient.invokeAPI("TeamApi.teamCreate", localVarPath, "POST", localVarQueryParams, localVarPostBody,
                               localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType,
                               localVarAuthNames, localVarReturnType, false);
  }
  /**
   * Delete Team
   * Deletes your Team. Can only be invoked when you have a Team with only one member (yourself).
   * @throws ApiException if fails to make API call
   * @http.response.details
     <table summary="Response Details" border="1">
       <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
       <tr><td> 200 </td><td> successful operation </td><td>  -  </td></tr>
       <tr><td> 4XX </td><td> failed_operation </td><td>  -  </td></tr>
     </table>
   */
  public void teamDelete() throws ApiException {
    teamDeleteWithHttpInfo();
  }

  /**
   * Delete Team
   * Deletes your Team. Can only be invoked when you have a Team with only one member (yourself).
   * @return ApiResponse&lt;Void&gt;
   * @throws ApiException if fails to make API call
   * @http.response.details
     <table summary="Response Details" border="1">
       <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
       <tr><td> 200 </td><td> successful operation </td><td>  -  </td></tr>
       <tr><td> 4XX </td><td> failed_operation </td><td>  -  </td></tr>
     </table>
   */
  public ApiResponse<Void> teamDeleteWithHttpInfo() throws ApiException {
    
    Object localVarPostBody = null;
    
    // create path and map variables
    String localVarPath = "/team/destroy";

    // query params
    List<Pair> localVarQueryParams = new ArrayList<Pair>();
    Map<String, String> localVarHeaderParams = new HashMap<String, String>();
    Map<String, String> localVarCookieParams = new HashMap<String, String>();
    Map<String, Object> localVarFormParams = new HashMap<String, Object>();


    
    
    
    final String[] localVarAccepts = {
      "application/json"
    };
    final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);

    final String[] localVarContentTypes = {
      
    };

    localVarFormParams = new HashMap<String, Object>();
    boolean isFileTypeFound = !localVarFormParams.isEmpty();

    final String localVarContentType = isFileTypeFound? "multipart/form-data" : apiClient.selectHeaderContentType(localVarContentTypes);

    String[] localVarAuthNames = new String[] { "api_key", "oauth2" };

    return apiClient.invokeAPI("TeamApi.teamDelete", localVarPath, "DELETE", localVarQueryParams, localVarPostBody,
                               localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType,
                               localVarAuthNames, null, false);
  }
  /**
   * Get Team
   * Returns information about your Team as well as a list of its members. If you do not belong to a Team, a 404 error with an error_name of \&quot;not_found\&quot; will be returned.
   * @return TeamGetResponse
   * @throws ApiException if fails to make API call
   * @http.response.details
     <table summary="Response Details" border="1">
       <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
       <tr><td> 200 </td><td> successful operation </td><td>  -  </td></tr>
       <tr><td> 4XX </td><td> failed_operation </td><td>  -  </td></tr>
     </table>
   */
  public TeamGetResponse teamGet() throws ApiException {
    return teamGetWithHttpInfo().getData();
  }

  /**
   * Get Team
   * Returns information about your Team as well as a list of its members. If you do not belong to a Team, a 404 error with an error_name of \&quot;not_found\&quot; will be returned.
   * @return ApiResponse&lt;TeamGetResponse&gt;
   * @throws ApiException if fails to make API call
   * @http.response.details
     <table summary="Response Details" border="1">
       <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
       <tr><td> 200 </td><td> successful operation </td><td>  -  </td></tr>
       <tr><td> 4XX </td><td> failed_operation </td><td>  -  </td></tr>
     </table>
   */
  public ApiResponse<TeamGetResponse> teamGetWithHttpInfo() throws ApiException {
    
    Object localVarPostBody = null;
    
    // create path and map variables
    String localVarPath = "/team";

    // query params
    List<Pair> localVarQueryParams = new ArrayList<Pair>();
    Map<String, String> localVarHeaderParams = new HashMap<String, String>();
    Map<String, String> localVarCookieParams = new HashMap<String, String>();
    Map<String, Object> localVarFormParams = new HashMap<String, Object>();


    
    
    
    final String[] localVarAccepts = {
      "application/json"
    };
    final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);

    final String[] localVarContentTypes = {
      
    };

    localVarFormParams = new HashMap<String, Object>();
    boolean isFileTypeFound = !localVarFormParams.isEmpty();

    final String localVarContentType = isFileTypeFound? "multipart/form-data" : apiClient.selectHeaderContentType(localVarContentTypes);

    String[] localVarAuthNames = new String[] { "api_key", "oauth2" };

    GenericType<TeamGetResponse> localVarReturnType = new GenericType<TeamGetResponse>() {};

    return apiClient.invokeAPI("TeamApi.teamGet", localVarPath, "GET", localVarQueryParams, localVarPostBody,
                               localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType,
                               localVarAuthNames, localVarReturnType, false);
  }
  /**
   * Remove User from Team
   * Removes the provided user Account from your Team. If the Account had an outstanding invitation to your Team, the invitation will be expired. If you choose to transfer documents from the removed Account to an Account provided in the &#x60;new_owner_email_address&#x60; parameter (available only for Enterprise plans), the response status code will be 201, which indicates that your request has been queued but not fully executed.
   * @param teamRemoveMemberRequest  (required)
   * @return TeamGetResponse
   * @throws ApiException if fails to make API call
   * @http.response.details
     <table summary="Response Details" border="1">
       <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
       <tr><td> 200 </td><td> successful operation </td><td>  -  </td></tr>
       <tr><td> 4XX </td><td> failed_operation </td><td>  -  </td></tr>
     </table>
   */
  public TeamGetResponse teamRemoveMember(TeamRemoveMemberRequest teamRemoveMemberRequest) throws ApiException {
    return teamRemoveMemberWithHttpInfo(teamRemoveMemberRequest).getData();
  }

  /**
   * Remove User from Team
   * Removes the provided user Account from your Team. If the Account had an outstanding invitation to your Team, the invitation will be expired. If you choose to transfer documents from the removed Account to an Account provided in the &#x60;new_owner_email_address&#x60; parameter (available only for Enterprise plans), the response status code will be 201, which indicates that your request has been queued but not fully executed.
   * @param teamRemoveMemberRequest  (required)
   * @return ApiResponse&lt;TeamGetResponse&gt;
   * @throws ApiException if fails to make API call
   * @http.response.details
     <table summary="Response Details" border="1">
       <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
       <tr><td> 200 </td><td> successful operation </td><td>  -  </td></tr>
       <tr><td> 4XX </td><td> failed_operation </td><td>  -  </td></tr>
     </table>
   */
  public ApiResponse<TeamGetResponse> teamRemoveMemberWithHttpInfo(TeamRemoveMemberRequest teamRemoveMemberRequest) throws ApiException {
    
    Object localVarPostBody = teamRemoveMemberRequest;
    
    // verify the required parameter 'teamRemoveMemberRequest' is set
    if (teamRemoveMemberRequest == null) {
      throw new ApiException(400, "Missing the required parameter 'teamRemoveMemberRequest' when calling teamRemoveMember");
    }
    
    // create path and map variables
    String localVarPath = "/team/remove_member";

    // query params
    List<Pair> localVarQueryParams = new ArrayList<Pair>();
    Map<String, String> localVarHeaderParams = new HashMap<String, String>();
    Map<String, String> localVarCookieParams = new HashMap<String, String>();
    Map<String, Object> localVarFormParams = new HashMap<String, Object>();


    
    
    
    final String[] localVarAccepts = {
      "application/json"
    };
    final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);

    final String[] localVarContentTypes = {
      "application/json"
    };

    localVarFormParams = teamRemoveMemberRequest.createFormData();
    boolean isFileTypeFound = !localVarFormParams.isEmpty();

    final String localVarContentType = isFileTypeFound? "multipart/form-data" : apiClient.selectHeaderContentType(localVarContentTypes);

    String[] localVarAuthNames = new String[] { "api_key", "oauth2" };

    GenericType<TeamGetResponse> localVarReturnType = new GenericType<TeamGetResponse>() {};

    return apiClient.invokeAPI("TeamApi.teamRemoveMember", localVarPath, "POST", localVarQueryParams, localVarPostBody,
                               localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType,
                               localVarAuthNames, localVarReturnType, false);
  }
  /**
   * Update Team
   * Updates the name of your Team.
   * @param teamUpdateRequest  (required)
   * @return TeamGetResponse
   * @throws ApiException if fails to make API call
   * @http.response.details
     <table summary="Response Details" border="1">
       <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
       <tr><td> 200 </td><td> successful operation </td><td>  -  </td></tr>
       <tr><td> 4XX </td><td> failed_operation </td><td>  -  </td></tr>
     </table>
   */
  public TeamGetResponse teamUpdate(TeamUpdateRequest teamUpdateRequest) throws ApiException {
    return teamUpdateWithHttpInfo(teamUpdateRequest).getData();
  }

  /**
   * Update Team
   * Updates the name of your Team.
   * @param teamUpdateRequest  (required)
   * @return ApiResponse&lt;TeamGetResponse&gt;
   * @throws ApiException if fails to make API call
   * @http.response.details
     <table summary="Response Details" border="1">
       <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
       <tr><td> 200 </td><td> successful operation </td><td>  -  </td></tr>
       <tr><td> 4XX </td><td> failed_operation </td><td>  -  </td></tr>
     </table>
   */
  public ApiResponse<TeamGetResponse> teamUpdateWithHttpInfo(TeamUpdateRequest teamUpdateRequest) throws ApiException {
    
    Object localVarPostBody = teamUpdateRequest;
    
    // verify the required parameter 'teamUpdateRequest' is set
    if (teamUpdateRequest == null) {
      throw new ApiException(400, "Missing the required parameter 'teamUpdateRequest' when calling teamUpdate");
    }
    
    // create path and map variables
    String localVarPath = "/team";

    // query params
    List<Pair> localVarQueryParams = new ArrayList<Pair>();
    Map<String, String> localVarHeaderParams = new HashMap<String, String>();
    Map<String, String> localVarCookieParams = new HashMap<String, String>();
    Map<String, Object> localVarFormParams = new HashMap<String, Object>();


    
    
    
    final String[] localVarAccepts = {
      "application/json"
    };
    final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);

    final String[] localVarContentTypes = {
      "application/json"
    };

    localVarFormParams = teamUpdateRequest.createFormData();
    boolean isFileTypeFound = !localVarFormParams.isEmpty();

    final String localVarContentType = isFileTypeFound? "multipart/form-data" : apiClient.selectHeaderContentType(localVarContentTypes);

    String[] localVarAuthNames = new String[] { "api_key", "oauth2" };

    GenericType<TeamGetResponse> localVarReturnType = new GenericType<TeamGetResponse>() {};

    return apiClient.invokeAPI("TeamApi.teamUpdate", localVarPath, "PUT", localVarQueryParams, localVarPostBody,
                               localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType,
                               localVarAuthNames, localVarReturnType, false);
  }
}
