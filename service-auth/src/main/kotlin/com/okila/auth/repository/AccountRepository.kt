package com.okila.auth.repository

import com.okila.repository.entities.AccountEntity
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface AccountRepository : MongoRepository<AccountEntity, String> {
    fun existsByPhoneNumber(phoneNumber: String): Boolean
}