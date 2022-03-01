# TeamApi

All URIs are relative to *https://api.hellosign.com/v3*

Method | HTTP request | Description
------------- | ------------- | -------------
[**teamAddMember**](TeamApi.md#teamAddMember) | **PUT** /team/add_member | Adds or invites a user to your Team.
[**teamCreate**](TeamApi.md#teamCreate) | **POST** /team/create | Creates a new Team.
[**teamDelete**](TeamApi.md#teamDelete) | **DELETE** /team/destroy | Deletes your Team.
[**teamGet**](TeamApi.md#teamGet) | **GET** /team | Gets your Team and a list of its members.
[**teamRemoveMember**](TeamApi.md#teamRemoveMember) | **POST** /team/remove_member | Removes a user from your Team.
[**teamUpdate**](TeamApi.md#teamUpdate) | **PUT** /team | Updates a Team&#39;s name.



## teamAddMember

> TeamGetResponse teamAddMember(teamAddMemberRequest)

Adds or invites a user to your Team.

Invites a user (specified using the `email_address` parameter) to your Team. If the user does not currently have a HelloSign Account, a new one will be created for them. If a user is already a part of another Team, a `team_invite_failed` error will be returned.

### Example

```java
import org.hellosign.openapi.ApiClient;
import org.hellosign.openapi.ApiException;
import org.hellosign.openapi.Configuration;
import org.hellosign.openapi.api.*;
import org.hellosign.openapi.auth.HttpBasicAuth;
import org.hellosign.openapi.model.*;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();

        // Configure HTTP basic authorization: api_key
        HttpBasicAuth api_key = (HttpBasicAuth) defaultClient
            .getAuthentication("api_key");
        api_key.setUsername("YOUR_API_KEY");

        // or, configure Bearer (JWT) authorization: oauth2
/*      HttpBearerAuth oauth2 = (HttpBearerAuth) defaultClient
            .getAuthentication("oauth2");

        oauth2.setBearerToken("YOUR_ACCESS_TOKEN");*/

        TeamApi api = new TeamApi(defaultClient);

        TeamAddMemberRequest data = new TeamAddMemberRequest()
            .emailAddress("george@example.com");

        try {
            TeamGetResponse result = api.teamAddMember(data);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling AccountApi#accountCreate");
            System.err.println("Status code: " + e.getCode());
            System.err.println("Reason: " + e.getResponseBody());
            System.err.println("Response headers: " + e.getResponseHeaders());
            e.printStackTrace();
        }
    }
}

```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **teamAddMemberRequest** | [**TeamAddMemberRequest**](TeamAddMemberRequest.md)|  |

### Return type

[**TeamGetResponse**](TeamGetResponse.md)

### Authorization

[api_key](../README.md#api_key), [oauth2](../README.md#oauth2)

### HTTP request headers

- **Content-Type**: application/json
- **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | successful operation |  -  |
| **400** | failed operation |  -  |


## teamCreate

> TeamGetResponse teamCreate(teamCreateRequest)

Creates a new Team.

Creates a new Team and makes you a member. You must not currently belong to a Team to invoke.

### Example

```java
import org.hellosign.openapi.ApiClient;
import org.hellosign.openapi.ApiException;
import org.hellosign.openapi.Configuration;
import org.hellosign.openapi.api.*;
import org.hellosign.openapi.auth.HttpBasicAuth;
import org.hellosign.openapi.model.*;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();

        // Configure HTTP basic authorization: api_key
        HttpBasicAuth api_key = (HttpBasicAuth) defaultClient
            .getAuthentication("api_key");
        api_key.setUsername("YOUR_API_KEY");

        // or, configure Bearer (JWT) authorization: oauth2
/*      HttpBearerAuth oauth2 = (HttpBearerAuth) defaultClient
            .getAuthentication("oauth2");

        oauth2.setBearerToken("YOUR_ACCESS_TOKEN");*/

        TeamApi api = new TeamApi(defaultClient);

        TeamCreateRequest data = new TeamCreateRequest()
            .name("New Team Name");

        try {
            TeamGetResponse result = api.teamCreate(data);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling AccountApi#accountCreate");
            System.err.println("Status code: " + e.getCode());
            System.err.println("Reason: " + e.getResponseBody());
            System.err.println("Response headers: " + e.getResponseHeaders());
            e.printStackTrace();
        }
    }
}

```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **teamCreateRequest** | [**TeamCreateRequest**](TeamCreateRequest.md)|  |

### Return type

[**TeamGetResponse**](TeamGetResponse.md)

### Authorization

[api_key](../README.md#api_key), [oauth2](../README.md#oauth2)

### HTTP request headers

- **Content-Type**: application/json
- **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | successful operation |  -  |
| **400** | failed operation |  -  |


## teamDelete

> teamDelete()

Deletes your Team.

Deletes your Team. Can only be invoked when you have a Team with only one member (yourself).

### Example

```java
import org.hellosign.openapi.ApiClient;
import org.hellosign.openapi.ApiException;
import org.hellosign.openapi.Configuration;
import org.hellosign.openapi.api.*;
import org.hellosign.openapi.auth.HttpBasicAuth;
import org.hellosign.openapi.model.*;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();

        // Configure HTTP basic authorization: api_key
        HttpBasicAuth api_key = (HttpBasicAuth) defaultClient
            .getAuthentication("api_key");
        api_key.setUsername("YOUR_API_KEY");

        // or, configure Bearer (JWT) authorization: oauth2
/*      HttpBearerAuth oauth2 = (HttpBearerAuth) defaultClient
            .getAuthentication("oauth2");

        oauth2.setBearerToken("YOUR_ACCESS_TOKEN");*/

        TeamApi api = new TeamApi(defaultClient);

        try {
            api.teamDelete();
        } catch (ApiException e) {
            System.err.println("Exception when calling AccountApi#accountCreate");
            System.err.println("Status code: " + e.getCode());
            System.err.println("Reason: " + e.getResponseBody());
            System.err.println("Response headers: " + e.getResponseHeaders());
            e.printStackTrace();
        }
    }
}
```

### Parameters

This endpoint does not need any parameter.

### Return type

null (empty response body)

### Authorization

[api_key](../README.md#api_key), [oauth2](../README.md#oauth2)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | successful operation |  -  |
| **400** | failed operation |  -  |


## teamGet

> TeamGetResponse teamGet()

Gets your Team and a list of its members.

Returns information about your Team as well as a list of its members. If you do not belong to a Team, a 404 error with an error_name of "not_found" will be returned.

### Example

```java
import org.hellosign.openapi.ApiClient;
import org.hellosign.openapi.ApiException;
import org.hellosign.openapi.Configuration;
import org.hellosign.openapi.api.*;
import org.hellosign.openapi.auth.HttpBasicAuth;
import org.hellosign.openapi.model.*;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();

        // Configure HTTP basic authorization: api_key
        HttpBasicAuth api_key = (HttpBasicAuth) defaultClient
            .getAuthentication("api_key");
        api_key.setUsername("YOUR_API_KEY");

        // or, configure Bearer (JWT) authorization: oauth2
/*      HttpBearerAuth oauth2 = (HttpBearerAuth) defaultClient
            .getAuthentication("oauth2");

        oauth2.setBearerToken("YOUR_ACCESS_TOKEN");*/

        TeamApi api = new TeamApi(defaultClient);

        try {
            TeamGetResponse result = api.teamGet();
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling AccountApi#accountCreate");
            System.err.println("Status code: " + e.getCode());
            System.err.println("Reason: " + e.getResponseBody());
            System.err.println("Response headers: " + e.getResponseHeaders());
            e.printStackTrace();
        }
    }
}
```

### Parameters

This endpoint does not need any parameter.

### Return type

[**TeamGetResponse**](TeamGetResponse.md)

### Authorization

[api_key](../README.md#api_key), [oauth2](../README.md#oauth2)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | successful operation |  -  |
| **400** | failed operation |  -  |
| **404** | not found |  -  |


## teamRemoveMember

> TeamGetResponse teamRemoveMember(teamRemoveMemberRequest)

Removes a user from your Team.

Removes the provided user Account from your Team. If the Account had an outstanding invitation to your Team, the invitation will be expired. If you choose to transfer documents from the removed Account to an Account provided in the `new_owner_email_address` parameter (available only for Enterprise plans), the response status code will be 201, which indicates that your request has been queued but not fully executed.

### Example

```java
import org.hellosign.openapi.ApiClient;
import org.hellosign.openapi.ApiException;
import org.hellosign.openapi.Configuration;
import org.hellosign.openapi.api.*;
import org.hellosign.openapi.auth.HttpBasicAuth;
import org.hellosign.openapi.model.*;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();

        // Configure HTTP basic authorization: api_key
        HttpBasicAuth api_key = (HttpBasicAuth) defaultClient
            .getAuthentication("api_key");
        api_key.setUsername("YOUR_API_KEY");

        // or, configure Bearer (JWT) authorization: oauth2
/*      HttpBearerAuth oauth2 = (HttpBearerAuth) defaultClient
            .getAuthentication("oauth2");

        oauth2.setBearerToken("YOUR_ACCESS_TOKEN");*/

        TeamApi api = new TeamApi(defaultClient);

        TeamRemoveMemberRequest data = new TeamRemoveMemberRequest()
            .emailAddress("teammate@hellosign.com")
            .newOwnerEmailAddress("new_teammate@hellosign.com");

        try {
            TeamGetResponse result = api.teamRemoveMember(data);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling AccountApi#accountCreate");
            System.err.println("Status code: " + e.getCode());
            System.err.println("Reason: " + e.getResponseBody());
            System.err.println("Response headers: " + e.getResponseHeaders());
            e.printStackTrace();
        }
    }
}
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **teamRemoveMemberRequest** | [**TeamRemoveMemberRequest**](TeamRemoveMemberRequest.md)|  |

### Return type

[**TeamGetResponse**](TeamGetResponse.md)

### Authorization

[api_key](../README.md#api_key), [oauth2](../README.md#oauth2)

### HTTP request headers

- **Content-Type**: application/json
- **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | successful operation |  -  |
| **400** | failed operation |  -  |


## teamUpdate

> TeamGetResponse teamUpdate(teamUpdateRequest)

Updates a Team&#39;s name.

Updates the name of your Team.

### Example

```java
import org.hellosign.openapi.ApiClient;
import org.hellosign.openapi.ApiException;
import org.hellosign.openapi.Configuration;
import org.hellosign.openapi.api.*;
import org.hellosign.openapi.auth.HttpBasicAuth;
import org.hellosign.openapi.model.*;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();

        // Configure HTTP basic authorization: api_key
        HttpBasicAuth api_key = (HttpBasicAuth) defaultClient
            .getAuthentication("api_key");
        api_key.setUsername("YOUR_API_KEY");

        // or, configure Bearer (JWT) authorization: oauth2
/*      HttpBearerAuth oauth2 = (HttpBearerAuth) defaultClient
            .getAuthentication("oauth2");

        oauth2.setBearerToken("YOUR_ACCESS_TOKEN");*/

        TeamApi api = new TeamApi(defaultClient);
        TeamUpdateRequest data = new TeamUpdateRequest()
            .name("New Team Name");

        try {
            TeamGetResponse result = api.teamUpdate(data);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling AccountApi#accountCreate");
            System.err.println("Status code: " + e.getCode());
            System.err.println("Reason: " + e.getResponseBody());
            System.err.println("Response headers: " + e.getResponseHeaders());
            e.printStackTrace();
        }
    }
}
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **teamUpdateRequest** | [**TeamUpdateRequest**](TeamUpdateRequest.md)|  |

### Return type

[**TeamGetResponse**](TeamGetResponse.md)

### Authorization

[api_key](../README.md#api_key), [oauth2](../README.md#oauth2)

### HTTP request headers

- **Content-Type**: application/json
- **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | successful operation |  -  |
| **400** | failed operation |  -  |

