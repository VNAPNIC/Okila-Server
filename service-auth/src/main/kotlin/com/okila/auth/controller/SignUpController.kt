package com.okila.auth.controller

import com.google.i18n.phonenumbers.PhoneNumberUtil
import com.okila.auth.service.AuthService
import com.okila.dto.SignUpRequest
import com.okila.dto.base.Response
import com.okila.dto.base.ResultCode
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController
import java.lang.Exception

@RestController
@RequestMapping("/sign-up")
class SignUpController {
    private val log = LoggerFactory.getLogger(SignUpController::class.java)

    val phoneUtil: PhoneNumberUtil = PhoneNumberUtil.getInstance()

    @Autowired
    lateinit var authService: AuthService

    @RequestMapping(value = ["/client"], method = [RequestMethod.POST])
    fun signUp(request: SignUpRequest?): Response<*> {
        try {
            if (request == null) return Response.badRequest()

            if (request.phoneNumber.isNullOrEmpty())
                return Response.failed(error = ResultCode.PHONE_NUMBER_IS_NULL_BLANK)

            val phoneNumber = phoneUtil.parse(request.phoneNumber, request.alpha2Code?.toUpperCase())

            if (!phoneUtil.isValidNumber(phoneNumber))
                return Response.failed(error = ResultCode.PHONE_NUMBER_WRONG_FORMAT)

            if (request.password.isNullOrEmpty())
                return Response.failed(error = ResultCode.PASSWORD_IS_NULL_BLANK)

            val phoneInterNational = phoneUtil.format(phoneNumber, PhoneNumberUtil.PhoneNumberFormat.INTERNATIONAL)

            if (authService.existsByPhoneNumber(phoneInterNational))
                return Response.failed(error = ResultCode.PHONE_NUMBER_IS_EXISTS)

            return Response.success()
        } catch (e: Exception) {
            e.printStackTrace()
            return Response.failed(error = ResultCode.SERVER_UNKNOWN_ERROR)
        }
    }
}