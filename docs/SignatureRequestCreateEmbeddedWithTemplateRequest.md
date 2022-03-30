

# SignatureRequestCreateEmbeddedWithTemplateRequest

Calls SignatureRequestSend in controller

## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
| `templateIds`<sup>*_required_</sup> | ```List<String>``` |  Use `template_ids` to create a SignatureRequest from one or more templates, in the order in which the template will be used.  |  |
| `clientId`<sup>*_required_</sup> | ```String``` |  Client id of the app you&#39;re using to create this embedded signature request. Visit our [embedded page](https://app.hellosign.com/api/embeddedSigningWalkthrough) to learn more about this parameter.  |  |
| `allowDecline` | ```Boolean``` |  Allows signers to decline to sign a document if `true`. Defaults to `false`.  |  |
| `ccs` | [```List<SubCC>```](SubCC.md) |  Add CC email recipients. Required when a CC role exists for the Template.  |  |
| `customFields` | [```List<SubCustomField>```](SubCustomField.md) |  An array defining values and options for custom fields. Required when defining when a custom field exists in the Template.  |  |
| `file` | ```List<File>``` |  **file** or **file_url** is required, but not both.<br><br>Use `file[]` to indicate the uploaded file(s) to send for signature.<br><br>Currently we only support use of either the `file[]` parameter or `file_url[]` parameter, not both.  |  |
| `fileUrl` | ```List<String>``` |  **file_url** or **file** is required, but not both.<br><br>Use `file_url[]` to have HelloSign download the file(s) to send for signature.<br><br>Currently we only support use of either the `file[]` parameter or `file_url[]` parameter, not both.  |  |
| `message` | ```String``` |  The custom message in the email that will be sent to the signers.  |  |
| `metadata` | ```Map<String, Object>``` |  Key-value data that should be attached to the signature request. This metadata is included in all API responses and events involving the signature request. For example, use the metadata field to store a signer&#39;s order number for look up when receiving events for the signature request.<br><br>Each request can include up to 10 metadata keys, with key names up to 40 characters long and values up to 1000 characters long.  |  |
| `signers` | [```List<SubSignatureRequestTemplateSigner>```](SubSignatureRequestTemplateSigner.md) |  Add Signers to your Templated-based Signature Request.  |  |
| `signingOptions` | [```SubSigningOptions```](SubSigningOptions.md) |    |  |
| `subject` | ```String``` |  The subject in the email that will be sent to the signers.  |  |
| `testMode` | ```Boolean``` |  Whether this is a test, the signature request will not be legally binding if set to `true`. Defaults to `false`.  |  |
| `title` | ```String``` |  The title you want to assign to the SignatureRequest.  |  |



