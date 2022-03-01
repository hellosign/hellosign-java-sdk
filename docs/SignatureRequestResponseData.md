

# SignatureRequestResponseData

An array of form field objects containing the name, value, and type of each textbox or checkmark field filled in by the signers.

## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
| `apiId` | ```String``` |  The unique ID for this field.  |  |
| `signatureId` | ```String``` |  The ID of the signature to which this response is linked.  |  |
| `name` | ```String``` |  The name of the form field.  |  |
| `value` | ```String``` |  The value of the form field.  |  |
| `required` | ```Boolean``` |  A boolean value denoting if this field is required.  |  |
| `type` | [```TypeEnum```](#TypeEnum) |  - `text`: A text input field - `checkbox`: A yes/no checkbox - `date_signed`: A date - `dropdown`: An input field for dropdowns - `initials`: An input field for initials - `radio`: An input field for radios - `signature`: A signature input field - `text-merge`: A text field that has default text set by the api - `checkbox-merge`: A checkbox field that has default value set by the api  |  |



## Enum: TypeEnum

Name | Value
---- | -----
| TEXT | &quot;text&quot; |
| CHECKBOX | &quot;checkbox&quot; |
| DATE_SIGNED | &quot;date_signed&quot; |
| DROPDOWN | &quot;dropdown&quot; |
| INITIALS | &quot;initials&quot; |
| RADIO | &quot;radio&quot; |
| SIGNATURE | &quot;signature&quot; |
| TEXT_MERGE | &quot;text-merge&quot; |
| CHECKBOX_MERGE | &quot;checkbox-merge&quot; |



