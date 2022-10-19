package com.bet_planet.android.data.utils

import android.util.Base64
import com.bet_planet.android.domain.utils.AppBase64
import javax.inject.Inject

class AndroidBase64 @Inject constructor() : AppBase64 {
    override fun encode(bytes: ByteArray): ByteArray =
        Base64.encode(bytes, Base64.DEFAULT)

    override fun decode(bytes: ByteArray): ByteArray =
        Base64.decode(bytes, Base64.DEFAULT)
}
