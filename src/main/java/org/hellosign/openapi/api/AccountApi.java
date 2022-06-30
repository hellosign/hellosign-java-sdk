package org.hellosign.openapi.api;

import org.hellosign.openapi.ApiException;
import org.hellosign.openapi.ApiClient;
import org.hellosign.openapi.ApiResponse;
import org.hellosign.openapi.Configuration;
import org.hellosign.openapi.Pair;

import javax.ws.rs.core.GenericType;

import org.hellosign.openapi.model.AccountCreateRequest;
import org.hellosign.openapi.model.AccountCreateResponse;
import org.hellosign.openapi.model.AccountGetResponse;
import org.hellosign.openapi.model.AccountUpdateRequest;
import org.hellosign.openapi.model.AccountVerifyRequest;
import org.hellosign.openapi.model.AccountVerifyResponse;
import org.hellosign.openapi.model.ErrorResponse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen")
public class AccountApi {
  private ApiClient apiClient;

  public AccountApi() {
    this(Configuration.getDefaultApiClient());
  }

  public AccountApi(ApiClient apiClient) {
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
   * Create Account
   * Creates a new HelloSign Account that is associated with the specified &#x60;email_address&#x60;.
   * @param accountCreateRequest  (required)
   * @return AccountCreateResponse
   * @throws ApiException if fails to make API call
   * @http.response.details
     <table summary="Response Details" border="1">
       <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
       <tr><td> 200 </td><td> successful operation </td><td>  -  </td></tr>
       <tr><td> 4XX </td><td> failed_operation </td><td>  -  </td></tr>
     </table>
   */
  public AccountCreateResponse accountCreate(AccountCreateRequest accountCreateRequest) throws ApiException {
    return accountCreateWithHttpInfo(accountCreateRequest).getData();
  }

  /**
   * Create Account
   * Creates a new HelloSign Account that is associated with the specified &#x60;email_address&#x60;.
   * @param accountCreateRequest  (required)
   * @return ApiResponse&lt;AccountCreateResponse&gt;
   * @throws ApiException if fails to make API call
   * @http.response.details
     <table summary="Response Details" border="1">
       <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
       <tr><td> 200 </td><td> successful operation </td><td>  -  </td></tr>
       <tr><td> 4XX </td><td> failed_operation </td><td>  -  </td></tr>
     </table>
   */
  public ApiResponse<AccountCreateResponse> accountCreateWithHttpInfo(AccountCreateRequest accountCreateRequest) throws ApiException {
    
    Object localVarPostBody = accountCreateRequest;
    
    // verify the required parameter 'accountCreateRequest' is set
    if (accountCreateRequest == null) {
      throw new ApiException(400, "Missing the required parameter 'accountCreateRequest' when calling accountCreate");
    }
    
    // create path and map variables
    String localVarPath = "/account/create";

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

    localVarFormParams = accountCreateRequest.createFormData();
    boolean isFileTypeFound = !localVarFormParams.isEmpty();

    final String localVarContentType = isFileTypeFound? "multipart/form-data" : apiClient.selectHeaderContentType(localVarContentTypes);

    String[] localVarAuthNames = new String[] { "api_key", "oauth2" };

    GenericType<AccountCreateResponse> localVarReturnType = new GenericType<AccountCreateResponse>() {};

    return apiClient.invokeAPI("AccountApi.accountCreate", localVarPath, "POST", localVarQueryParams, localVarPostBody,
                               localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType,
                               localVarAuthNames, localVarReturnType, false);
  }
  /**
   * Get Account
   * Returns the properties and settings of your Account.
   * @param accountId The ID of the Account (optional)
   * @return AccountGetResponse
   * @throws ApiException if fails to make API call
   * @http.response.details
     <table summary="Response Details" border="1">
       <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
       <tr><td> 200 </td><td> successful operation </td><td>  -  </td></tr>
       <tr><td> 4XX </td><td> failed_operation </td><td>  -  </td></tr>
     </table>
   */
  public AccountGetResponse accountGet(String accountId) throws ApiException {
    return accountGetWithHttpInfo(accountId).getData();
  }

  /**
   * Get Account
   * Returns the properties and settings of your Account.
   * @param accountId The ID of the Account (optional)
   * @return ApiResponse&lt;AccountGetResponse&gt;
   * @throws ApiException if fails to make API call
   * @http.response.details
     <table summary="Response Details" border="1">
       <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
       <tr><td> 200 </td><td> successful operation </td><td>  -  </td></tr>
       <tr><td> 4XX </td><td> failed_operation </td><td>  -  </td></tr>
     </table>
   */
  public ApiResponse<AccountGetResponse> accountGetWithHttpInfo(String accountId) throws ApiException {
    
    Object localVarPostBody = null;
    
    // create path and map variables
    String localVarPath = "/account";

    // query params
    List<Pair> localVarQueryParams = new ArrayList<Pair>();
    Map<String, String> localVarHeaderParams = new HashMap<String, String>();
    Map<String, String> localVarCookieParams = new HashMap<String, String>();
    Map<String, Object> localVarFormParams = new HashMap<String, Object>();

    localVarQueryParams.addAll(apiClient.parameterToPairs("", "account_id", accountId));

    
    
    
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

    GenericType<AccountGetResponse> localVarReturnType = new GenericType<AccountGetResponse>() {};

    return apiClient.invokeAPI("AccountApi.accountGet", localVarPath, "GET", localVarQueryParams, localVarPostBody,
                               localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType,
                               localVarAuthNames, localVarReturnType, false);
  }
  /**
   * Update Account
   * Updates the properties and settings of your Account. Currently only allows for updates to the [Callback URL](/api/reference/tag/Callbacks-and-Events) and locale.
   * @param accountUpdateRequest  (required)
   * @return AccountGetResponse
   * @throws ApiException if fails to make API call
   * @http.response.details
     <table summary="Response Details" border="1">
       <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
       <tr><td> 200 </td><td> successful operation </td><td>  -  </td></tr>
       <tr><td> 4XX </td><td> failed_operation </td><td>  -  </td></tr>
     </table>
   */
  public AccountGetResponse accountUpdate(AccountUpdateRequest accountUpdateRequest) throws ApiException {
    return accountUpdateWithHttpInfo(accountUpdateRequest).getData();
  }

  /**
   * Update Account
   * Updates the properties and settings of your Account. Currently only allows for updates to the [Callback URL](/api/reference/tag/Callbacks-and-Events) and locale.
   * @param accountUpdateRequest  (required)
   * @return ApiResponse&lt;AccountGetResponse&gt;
   * @throws ApiException if fails to make API call
   * @http.response.details
     <table summary="Response Details" border="1">
       <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
       <tr><td> 200 </td><td> successful operation </td><td>  -  </td></tr>
       <tr><td> 4XX </td><td> failed_operation </td><td>  -  </td></tr>
     </table>
   */
  public ApiResponse<AccountGetResponse> accountUpdateWithHttpInfo(AccountUpdateRequest accountUpdateRequest) throws ApiException {
    
    Object localVarPostBody = accountUpdateRequest;
    
    // verify the required parameter 'accountUpdateRequest' is set
    if (accountUpdateRequest == null) {
      throw new ApiException(400, "Missing the required parameter 'accountUpdateRequest' when calling accountUpdate");
    }
    
    // create path and map variables
    String localVarPath = "/account";

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

    localVarFormParams = accountUpdateRequest.createFormData();
    boolean isFileTypeFound = !localVarFormParams.isEmpty();

    final String localVarContentType = isFileTypeFound? "multipart/form-data" : apiClient.selectHeaderContentType(localVarContentTypes);

    String[] localVarAuthNames = new String[] { "api_key", "oauth2" };

    GenericType<AccountGetResponse> localVarReturnType = new GenericType<AccountGetResponse>() {};

    return apiClient.invokeAPI("AccountApi.accountUpdate", localVarPath, "PUT", localVarQueryParams, localVarPostBody,
                               localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType,
                               localVarAuthNames, localVarReturnType, false);
  }
  /**
   * Verify Account
   * Verifies whether an HelloSign Account exists for the given email address.
   * @param accountVerifyRequest  (required)
   * @return AccountVerifyResponse
   * @throws ApiException if fails to make API call
   * @http.response.details
     <table summary="Response Details" border="1">
       <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
       <tr><td> 200 </td><td> successful operation </td><td>  -  </td></tr>
       <tr><td> 4XX </td><td> failed_operation </td><td>  -  </td></tr>
     </table>
   */
  public AccountVerifyResponse accountVerify(AccountVerifyRequest accountVerifyRequest) throws ApiException {
    return accountVerifyWithHttpInfo(accountVerifyRequest).getData();
  }

  /**
   * Verify Account
   * Verifies whether an HelloSign Account exists for the given email address.
   * @param accountVerifyRequest  (required)
   * @return ApiResponse&lt;AccountVerifyResponse&gt;
   * @throws ApiException if fails to make API call
   * @http.response.details
     <table summary="Response Details" border="1">
       <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
       <tr><td> 200 </td><td> successful operation </td><td>  -  </td></tr>
       <tr><td> 4XX </td><td> failed_operation </td><td>  -  </td></tr>
     </table>
   */
  public ApiResponse<AccountVerifyResponse> accountVerifyWithHttpInfo(AccountVerifyRequest accountVerifyRequest) throws ApiException {
    
    Object localVarPostBody = accountVerifyRequest;
    
    // verify the required parameter 'accountVerifyRequest' is set
    if (accountVerifyRequest == null) {
      throw new ApiException(400, "Missing the required parameter 'accountVerifyRequest' when calling accountVerify");
    }
    
    // create path and map variables
    String localVarPath = "/account/verify";

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

    localVarFormParams = accountVerifyRequest.createFormData();
    boolean isFileTypeFound = !localVarFormParams.isEmpty();

    final String localVarContentType = isFileTypeFound? "multipart/form-data" : apiClient.selectHeaderContentType(localVarContentTypes);

    String[] localVarAuthNames = new String[] { "api_key", "oauth2" };

    GenericType<AccountVerifyResponse> localVarReturnType = new GenericType<AccountVerifyResponse>() {};

    return apiClient.invokeAPI("AccountApi.accountVerify", localVarPath, "POST", localVarQueryParams, localVarPostBody,
                               localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType,
                               localVarAuthNames, localVarReturnType, false);
  }
}
