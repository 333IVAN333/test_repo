package com.bet_planet.android.data.api

import com.bet_planet.android.data.registration.dto.SingularResponse
import com.bet_planet.android.data.registration.login.LoginNewDataSource
import com.bet_planet.android.data.utils.analitycs.AnalyticsUtil
import com.bet_planet.android.domain.user.auth.UserAuthSource
import io.reactivex.Single
import retrofit2.Call
import retrofit2.CallAdapter
import java.lang.reflect.Type

class RetryAuthApiCallAdapter<R> constructor(
        private val defaultCallAdapter: CallAdapter<R, Single<R>>,
        private val loginNewDataSource: LoginNewDataSource,
        private val userAuthSource: UserAuthSource,
        private val analyticsUtil: AnalyticsUtil
) : CallAdapter<R, Single<R>> {

    override fun responseType(): Type {
        return defaultCallAdapter.responseType()
    }

    override fun adapt(call: Call<R>): Single<R>? {
        return defaultCallAdapter.adapt(call)
                .flatMap { response ->
                    if (response is SingularResponse && response.code == 126) { // handle Errors of Response<T> return types
                        throw AuthCookieException()
                    }
                    Single.just(response) // handle "body" return types or successful requests
                }
                .retryWhen { throwableFlowable ->
                    throwableFlowable.flatMapSingle { throwable: Throwable ->
                        if (throwable is AuthCookieException) {
                            return@flatMapSingle loginNewDataSource
                                    .login(userAuthSource.getFullNumber(), userAuthSource.password)
                                    .map { response ->
                                        if (response.isSuccessLogin()) {
                                            analyticsUtil.logSuccessLogin(true)
                                            response
                                        } else {
                                            analyticsUtil.logErrorLogin(response.errorCode,true)
                                            throw WrongAuthDataException()
                                        }
                                    }
                        }
                        Single.error<Throwable>(throwable)
                    }
                }
    }

    class AuthCookieException : Throwable()

    class WrongAuthDataException : Throwable()
}
