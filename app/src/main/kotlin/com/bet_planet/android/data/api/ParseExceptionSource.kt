package com.bet_planet.android.data.api

interface ParseExceptionSource {

    fun parseException(throwable: Throwable): String
}
