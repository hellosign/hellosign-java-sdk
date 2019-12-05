package com.hellosign.sdk.resource.support.types;

public enum EventType {
    signature_request_viewed,
    signature_request_signed,
    signature_request_sent,
    signature_request_all_signed,
    signature_request_invalid,
    signature_request_remind,
    signature_request_destroyed,
    signature_request_canceled,
    signature_request_declined,
    account_confirmed,
    template_created,
    template_error,
    file_error,
    test,
    callback_test,
    unknown_error,
    sign_url_invalid,
    signature_request_email_bounce,
    signature_request_downloadable,
    signature_request_delegated
}
