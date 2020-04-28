package com.hellosign.sdk.resource.support.types

import java.lang.IllegalArgumentException

// Role codes are "a" for "admin" and "m" for "member"
enum class RoleType(val value: String) {
    ADMIN("a"),
    MEMBER("m");

    override fun toString(): String {
        return value
    }

    companion object {
        @JvmStatic
        fun getEnum(value: String): RoleType? {
            return enumValues<RoleType>().find { it.value === value }
        }
    }
}
