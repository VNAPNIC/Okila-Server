package com.okila.repository.enums

import com.fasterxml.jackson.annotation.JsonInclude
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "role")
@JsonInclude(JsonInclude.Include.NON_NULL)
enum class Role {
    STAFF,
    CLIENT,
    ADMIN,
    UNKNOWN
}