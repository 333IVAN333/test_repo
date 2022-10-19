package com.bet_planet.components.android

import com.bet_planet.components.domain.Schedulers
import io.reactivex.schedulers.Schedulers as RxSchedulers
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject

class AndroidSchedulers @Inject constructor() :
    Schedulers {

    override fun io() = RxSchedulers.io()

    override fun computation() = RxSchedulers.computation()

    override fun ui(): Scheduler = AndroidSchedulers.mainThread()
}
