package com.okila.repository.entities

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import com.okila.repository.entities.files.AvatarInfoEntity
import com.okila.repository.enums.Gender
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "user")
@JsonInclude(JsonInclude.Include.NON_NULL)
data class UserEntity(
        @Id
        @JsonProperty("_id")
        var id: String? = null,
        @JsonProperty("firstName")
        var firstName: String? = null,
        @JsonProperty("lastName")
        var lastName: String? = null,
        @JsonProperty("gender")
        var gender: Gender? = Gender.OTHER,
        @JsonProperty("description")
        var description: String? = null,
        @JsonProperty("avatar")
        var avatar: AvatarInfoEntity? = null
)