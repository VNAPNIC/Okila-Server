package com.okila.common.extenstions

fun String.isEmail(): Boolean = "^[A-Za-z](.*)([@])(.+)(\\.)(.+)".toRegex().matches(this)

fun String.toToken(): String? = substring(7, length)
