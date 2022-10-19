package com.bet_planet.android.data.utils

import okhttp3.Headers
import okhttp3.Request
import okhttp3.Response
import java.nio.charset.StandardCharsets

object LogHelper {
    private fun headersWithoutJwt(strb: StringBuilder, headers: Headers?) {
        if (headers == null) {
            return
        }
        strb.append("\nHeaders:\n")
        var i = 0
        val size = headers.size()
        while (i < size) {
            val name = headers.name(i)
            val value = headers.value(i)
            if (!name.equals("Cookie", true) && !value.contains("JSESSIONID", true)) {
                strb.append(name).append(": ").append(value).append("\n")
            }
            i++
        }
    }

    fun logRequest(response: Response, request: Request, bodyBytes: ByteArray?): String {
        val contentType = if (response.body() != null) response.body()!!.contentType() else null
        val msg = StringBuilder()
        msg.append(request.toString())
        headersWithoutJwt(msg, request.headers())
        msg.append(response.toString())
        headersWithoutJwt(msg, response.headers())
        msg.append("\nBody:\n")
                .append(if (response.body() != null && contentType != null && contentType.subtype() == "json") String(bodyBytes!!, (if (contentType.charset() != null) contentType.charset()!! else StandardCharsets.UTF_8)!!) else null)
        return msg.toString()
    }
}