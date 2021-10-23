package com.okila.dto.base

enum class ResultCode(val code: Int, val message: String) {
    SUCCESS(200, "Successful"),
    FAILED(500, "Failed"),
    UNAUTHORIZED(401, "Not logged in yet or token has expired"),
    FORBIDDEN(403, "No relevant permissions"),
    NOT_FOUND(404, "Not found"),
    EXPECTATION_FAILED(417, "Expectation Failed"),

    // Error
    SERVER_UNKNOWN_ERROR(1000, "Server unknown error."),
    WARNING_DATA_FORMAT(1001, "Warning data format."),
    //Phone
    PHONE_NUMBER_IS_NULL_BLANK(2000, "Phone number is null or blank."),
    PHONE_NUMBER_IS_EXISTS(2001, "Phone number is exists."),
    PHONE_NUMBER_WRONG_FORMAT(2002, "Wrong phone number format."),
    //Password
    PASSWORD_IS_NULL_BLANK(3000, "Password is null or blank."),
}

