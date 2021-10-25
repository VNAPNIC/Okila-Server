package com.okila.auth.service

import org.springframework.stereotype.Service
import java.security.SecureRandom

@Service
class OtpGenerator {

    /**
     * Method for generating OTP and put it in cache.
     *
     * @param key - cache key
     * @return cache value (generated OTP number)
     */
    fun generateOTP(key: String?): Int? {
        val random = SecureRandom()
        val otp: Int = 100000 + random.nextInt(900000)

        return otp
    }

    /**
     * Method for getting OTP value by key.
     *
     * @param key - target key
     * @return OTP value
     */
    fun getOPTByKey(key: String?): Int? {
        return 1
    }

    /**
     * Method for removing key from cache.
     *
     * @param key - target key
     */
    fun clearOTPFromCache(key: String?) {
    }
}