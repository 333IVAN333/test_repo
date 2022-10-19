package com.bet_planet.android.data.registration.sms

import com.bet_planet.components.domain.Mapper
import com.bet_planet.android.domain.registration.reset_password.SuccessSmsCodeResponse
import com.bet_planet.android.domain.registration.reset_password.UserInfoResponse
import com.bet_planet.android.domain.registration.reset_password.VerifySmsCodeSource
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class VerifySmsCodeDataModule {

    @Binds
    @Singleton
    internal abstract fun bindsSource(source: VerifySmsCodeDataSource): VerifySmsCodeSource

    @Binds
    @Singleton
    internal abstract fun bindsDataMapper(mapper: SendSmsCodeResponseMapper):
            Mapper<SendSmsCodeResponse, SuccessSmsCodeResponse>

    @Binds
    @Singleton
    internal abstract fun bindsUserMapper(mapper: UserInfoMapper):
            Mapper<UserResponse, UserInfoResponse>
}
