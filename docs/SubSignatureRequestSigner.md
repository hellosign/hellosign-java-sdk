

# SubSignatureRequestSigner



## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
| `name`<sup>*_required_</sup> | ```String``` |  The name of the signer.  |  |
| `emailAddress`<sup>*_required_</sup> | ```String``` |  The email address of the signer.  |  |
| `order` | ```Integer``` |  The order the signer is required to sign in.  |  |
| `pin` | ```String``` |  The 4- to 12-character access code that will secure this signer&#39;s signature page.  |  |
| `smsPhoneNumber` | ```String``` |  An E.164 formatted phone number that will receive a code via SMS to access this signer&#39;s signature page.<br><br>**Note**: Not available in test mode and requires a Standard plan or higher.  |  |



