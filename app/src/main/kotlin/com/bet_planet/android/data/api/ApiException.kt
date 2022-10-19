package com.bet_planet.android.data.api

data class ApiException(val httpCode: Int, val serverCode: Int?, val description: String?) :
    RuntimeException()
