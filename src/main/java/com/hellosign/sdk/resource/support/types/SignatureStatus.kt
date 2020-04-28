package com.hellosign.sdk.resource.support.types

enum class SignatureStatus {
    success,
    on_hold,
    signed,
    awaiting_signature,
    declined,
    error_unknown,
    error_file,
    error_component_position,
    error_text_tag
}
