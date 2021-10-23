package com.okila.common.entities

enum class ResultCode(val code: Int, val message: String) {
    SUCCESS(200, "Successful"),
    FAILED(500, "Failed"),
    UNAUTHORIZED(401, "Not logged in yet or token has expired"),
    FORBIDDEN(403, "No relevant permissions"),
    NOT_FOUND(404, "Not found"),
    EXPECTATION_FAILED(417, "Expectation Failed"),

    // Error
    SERVER_UNKNOWN_ERROR(1000, "Server unknown error."),
    WARNING_DATA_FORMAT(1018, "Warning data format.")
}

