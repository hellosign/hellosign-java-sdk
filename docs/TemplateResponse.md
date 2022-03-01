

# TemplateResponse



## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
| `templateId` | ```String``` |  The id of the Template.  |  |
| `title` | ```String``` |  The title of the Template. This will also be the default subject of the message sent to signers when using this Template to send a SignatureRequest. This can be overridden when sending the SignatureRequest.  |  |
| `message` | ```String``` |  The default message that will be sent to signers when using this Template to send a SignatureRequest. This can be overridden when sending the SignatureRequest.  |  |
| `updatedAt` | ```Integer``` |  Time the template was last updated.  |  |
| `isEmbedded` | ```Boolean``` |  `true` if this template was created using an embedded flow, `false` if it was created on our website.  |  |
| `isCreator` | ```Boolean``` |  `true` if you are the owner of this template, `false` if it&#39;s been shared with you by a team member.  |  |
| `canEdit` | ```Boolean``` |  Indicates whether edit rights have been granted to you by the owner (always `true` if that&#39;s you).  |  |
| `isLocked` | ```Boolean``` |  `true` if you exceed Template quota; these can only be used in test mode. `false` if the template is included with the Template quota; these can be used at any time.  |  |
| `metadata` | ```Object``` |  The metadata attached to the template.  |  |
| `signerRoles` | [```List<TemplateResponseSignerRole>```](TemplateResponseSignerRole.md) |    |  |
| `ccRoles` | [```List<TemplateResponseCCRole>```](TemplateResponseCCRole.md) |    |  |
| `documents` | [```List<TemplateResponseDocument>```](TemplateResponseDocument.md) |    |  |
| `customFields` | [```List<TemplateResponseCustomField>```](TemplateResponseCustomField.md) |    |  |
| `accounts` | [```List<TemplateResponseAccount>```](TemplateResponseAccount.md) |    |  |
| `warnings` | [```List<WarningResponse>```](WarningResponse.md) |    |  |



