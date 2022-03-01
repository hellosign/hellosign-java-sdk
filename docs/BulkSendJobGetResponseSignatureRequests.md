

# BulkSendJobGetResponseSignatureRequests



## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
| `testMode` | ```Boolean``` |  Whether this is a test signature request. Test requests have no legal value. Defaults to `false`.  |  |
| `signatureRequestId` | ```String``` |  The id of the SignatureRequest.  |  |
| `requesterEmailAddress` | ```String``` |  The email address of the initiator of the SignatureRequest.  |  |
| `title` | ```String``` |  The title the specified Account uses for the SignatureRequest.  |  |
| `originalTitle` | ```String``` |  Default Label for account.  |  |
| `subject` | ```String``` |  The subject in the email that was initially sent to the signers.  |  |
| `message` | ```String``` |  The custom message in the email that was initially sent to the signers.  |  |
| `metadata` | ```Object``` |  The metadata attached to the signature request.  |  |
| `createdAt` | ```Integer``` |  Time the signature request was created.  |  |
| `isComplete` | ```Boolean``` |  Whether or not the SignatureRequest has been fully executed by all signers.  |  |
| `isDeclined` | ```Boolean``` |  Whether or not the SignatureRequest has been declined by a signer.  |  |
| `hasError` | ```Boolean``` |  Whether or not an error occurred (either during the creation of the SignatureRequest or during one of the signings).  |  |
| `finalCopyUri` | ```String``` |  (Deprecated) The relative URI where the PDF copy of the finalized documents can be downloaded. Only present when `is_complete &#x3D; true`. This will be removed at some point; use the files_url instead.  |  |
| `filesUrl` | ```String``` |  The URL where a copy of the request&#39;s documents can be downloaded.  |  |
| `signingUrl` | ```String``` |  The URL where a signer, after authenticating, can sign the documents. This should only be used by users with existing HelloSign accounts as they will be required to log in before signing.  |  |
| `detailsUrl` | ```String``` |  The URL where the requester and the signers can view the current status of the SignatureRequest.  |  |
| `ccEmailAddresses` | ```List<String>``` |  A list of email addresses that were CCed on the SignatureRequest. They will receive a copy of the final PDF once all the signers have signed.  |  |
| `signingRedirectUrl` | ```String``` |  The URL you want the signer redirected to after they successfully sign.  |  |
| `customFields` | [```List<SignatureRequestResponseCustomField>```](SignatureRequestResponseCustomField.md) |    |  |
| `responseData` | [```List<SignatureRequestResponseData>```](SignatureRequestResponseData.md) |    |  |
| `signatures` | [```List<SignatureRequestResponseSignatures>```](SignatureRequestResponseSignatures.md) |    |  |
| `warnings` | [```List<WarningResponse>```](WarningResponse.md) |    |  |
| `bulkSendJobId` | ```String``` |  The id of the BulkSendJob.  |  |



