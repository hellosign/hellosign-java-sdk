package com.hellosign.sdk.resource.support.types;

// Role codes are "a" for "admin" and "m" for "member"
public enum RoleType {
    ADMIN {
        @Override
        public String toString() {
            return "a";
        }
    },
    MEMBER {
        @Override
        public String toString() {
            return "m";
        }
    }
}
