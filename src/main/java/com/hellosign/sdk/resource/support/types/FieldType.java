package com.hellosign.sdk.resource.support.types;

/**
 * Enumeration of allowable field types. These are used in the ResponseData and FormField objects.
 */
public enum FieldType {
    CHECKBOX("checkbox"),
    CHECKBOX_MERGE("checkbox-merge"),
    DATE_SIGNED("date_signed"),
    DROPDOWN("dropdown"),
    INITIALS("initials"),
    RADIO("radio"),
    SIGNATURE("signature"),
    TEXT("text"),
    TEXT_MERGE("text-merge");

    public final String value;

    FieldType(String value) {
        this.value = value;
    }

    public static FieldType getEnum(String string) {
        for (FieldType type : values()) {
            if (type.value.equals(string)) {
                return type;
            }
        }
        throw new IllegalArgumentException();
    }

    @Override
    public String toString() {
        return this.value;
    }
}
