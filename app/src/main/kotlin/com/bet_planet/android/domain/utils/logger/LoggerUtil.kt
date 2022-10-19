package com.bet_planet.android.domain.utils.logger

import java.text.SimpleDateFormat
import java.util.*


object LoggerUtil {

    const val FILE_FOLDER = "logs"
    const val FILE_NAME = "apps_log.txt"
    const val FILE_NAME_FOR_SEND = "apps_log_to_send.txt"
    const val FILE_SIZE = (0.5 * 1024 * 1024).toLong() // 0.5mb
    val dateFormat = SimpleDateFormat("yyyy.MM.dd HH:mm:ss.SSSZ", Locale.ENGLISH)
}