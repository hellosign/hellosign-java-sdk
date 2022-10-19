

# SignatureRequestUpdateRequest



## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
| `signatureId`<sup>*_required_</sup> | ```String``` |  The signature ID for the recipient.  |  |
| `emailAddress` | ```String``` |  The new email address for the recipient.<br><br>**NOTE**: Optional if `name` is provided.  |  |
| `name` | ```String``` |  The new name for the recipient.<br><br>**NOTE**: Optional if `email_address` is provided.  |  |
| `expiresAt` | ```Integer``` |  _t__SignatureRequestUpdate::EXPIRES_AT  |  |



