package com.okila.auth.controller

import com.google.i18n.phonenumbers.PhoneNumberUtil
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/otp")
class OTPController {
    private val log = LoggerFactory.getLogger(SignUpController::class.java)
    val phoneUtil: PhoneNumberUtil = PhoneNumberUtil.getInstance()
}