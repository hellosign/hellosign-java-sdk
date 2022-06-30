

# AccountResponse



## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
| `accountId` | ```String``` |  The ID of the Account  |  |
| `emailAddress` | ```String``` |  The email address associated with the Account.  |  |
| `isLocked` | ```Boolean``` |  Returns `true` if the user has been locked out of their account by a team admin.  |  |
| `isPaidHs` | ```Boolean``` |  Returns `true` if the user has a paid HelloSign account.  |  |
| `isPaidHf` | ```Boolean``` |  Returns `true` if the user has a paid HelloFax account.  |  |
| `quotas` | [```AccountResponseQuotas```](AccountResponseQuotas.md) |    |  |
| `callbackUrl` | ```String``` |  The URL that HelloSign events will `POST` to.  |  |
| `roleCode` | ```String``` |  The membership role for the team.  |  |
| `locale` | ```String``` |  The locale used in this Account. Check out the list of [supported locales](/api/reference/constants/#supported-locales) to learn more about the possible values.  |  |



