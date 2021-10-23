package com.okila.auth.controller

import com.google.i18n.phonenumbers.PhoneNumberUtil
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/sign-in")
class SignInController {
    private val log = LoggerFactory.getLogger(SignInController::class.java)
    val phoneUtil: PhoneNumberUtil = PhoneNumberUtil.getInstance()
}