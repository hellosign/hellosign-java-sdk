package com.hellosign.sdk.resource.support.types

import java.lang.IllegalArgumentException

/**
 * Enumeration of allowable field types. These are used in the ResponseData and FormField objects.
 */
enum class FieldType(val value: String) {
    CHECKBOX("checkbox"),
    CHECKBOX_MERGE("checkbox-merge"),
    DATE_SIGNED("date_signed"),
    DROPDOWN("dropdown"),
    INITIALS("initials"),
    RADIO("radio"),
    SIGNATURE("signature"),
    TEXT("text"),
    TEXT_MERGE("text-merge");

    override fun toString(): String {
        return value
    }

    companion object {
        @JvmStatic
        fun getEnum(value: String): FieldType? {
            return enumValues<FieldType>().find { it.value === value }
        }
    }
}
