package com.bet_planet.android.data

import com.bet_planet.android.data.about_us.SectionsDataModule
import com.bet_planet.android.data.api.ParseExceptionDataModule
import com.bet_planet.android.data.bonuses.BonusesDataModule
import com.bet_planet.android.data.casino.CasinoDataModule
import com.bet_planet.android.data.casino.game.CasinoGameDataModule
import com.bet_planet.android.data.countries.CountryListDialogDataModule
import com.bet_planet.android.data.coupon.CouponDataModule
import com.bet_planet.android.data.coupon.booking.CouponBookingDataModule
import com.bet_planet.android.data.finance.FinanceDataModule
import com.bet_planet.android.data.freeBets.FreeBetsDataModule
import com.bet_planet.android.data.gameHistory.GameHistoryDataModule
import com.bet_planet.android.data.gameHistory.GameHistoryListDataModule
import com.bet_planet.android.data.guid.GuidDataModule
import com.bet_planet.android.data.kyc.docUpload.DocUploadDataModule
import com.bet_planet.android.data.kyc.email.SetUserEmailModule
import com.bet_planet.android.data.kyc.name.SetUserNameModule
import com.bet_planet.android.data.leagues.LeaguesDataModule
import com.bet_planet.android.data.locale.LocaleModule
import com.bet_planet.android.data.main.MainDataModule
import com.bet_planet.android.data.matches.GroupsDataModule
import com.bet_planet.android.data.matches.MatchesDataModule
import com.bet_planet.android.data.net.NetDataModule
import com.bet_planet.android.data.net.NetworkStateDataModule
import com.bet_planet.android.data.net.interceptor.NoInternetInterceptorDataModule
import com.bet_planet.android.data.notifications.NotificationSettingsDataModule
import com.bet_planet.android.data.preference.PreferenceWriteDataModule
import com.bet_planet.android.data.promotions.PromotionsDataModule
import com.bet_planet.android.data.quickBet.QuickBetDataModule
import com.bet_planet.android.data.registration.birthdate.BirthDateDataModule
import com.bet_planet.android.data.registration.country.CountryListDataModule
import com.bet_planet.android.data.registration.login.LoginNewDataModule
import com.bet_planet.android.data.registration.password.PasswordDataModule
import com.bet_planet.android.data.registration.phone.EnterPhoneNumberDataModule
import com.bet_planet.android.data.registration.sms.VerifySmsCodeDataModule
import com.bet_planet.android.data.resources.ResourcesDataModule
import com.bet_planet.android.data.sports.SportsDataModule
import com.bet_planet.android.data.time.TimeDataModule
import com.bet_planet.android.data.transactions.TransactionsListDataModule
import com.bet_planet.android.data.user.auth.UserDataModule
import com.bet_planet.android.data.user.balance.BalanceDataModule
import com.bet_planet.android.data.user.info.UserInfoDataModule
import com.bet_planet.android.data.user.logout.LogoutDataModule
import com.bet_planet.android.data.version.VersionDataModule
import com.bet_planet.feature.data.FeatureToggleDataModule
import dagger.Module

@Module(
        includes = [
            NetDataModule::class,
            LocaleModule::class,
            TimeDataModule::class,
            ResourcesDataModule::class,
            PreferenceWriteDataModule::class,
            EnterPhoneNumberDataModule::class,
            CountryListDataModule::class,
            CountryListDialogDataModule::class,
            BalanceDataModule::class,
            UserInfoDataModule::class,
            GameHistoryListDataModule::class,
            GameHistoryDataModule::class,
            CasinoDataModule::class,
            CasinoGameDataModule::class,
            FinanceDataModule::class,
            ParseExceptionDataModule::class,
            NetworkStateDataModule::class,
            NoInternetInterceptorDataModule::class,
            VerifySmsCodeDataModule::class,
            LoginNewDataModule::class,
            PasswordDataModule::class,
            UserDataModule::class,
            MainDataModule::class,
            GuidDataModule::class,
            FeatureToggleDataModule::class,
            SportsDataModule::class,
            VersionDataModule::class,
            MatchesDataModule::class,
            GroupsDataModule::class,
            LeaguesDataModule::class,
            BirthDateDataModule::class,
            SetUserNameModule::class,
            SetUserEmailModule::class,
            DocUploadDataModule::class,
            CouponDataModule::class,
            CouponBookingDataModule::class,
            FreeBetsDataModule::class,
            QuickBetDataModule::class,
            BonusesDataModule::class,
            LogoutDataModule::class,
            TransactionsListDataModule::class,
            NotificationSettingsDataModule::class,
            PromotionsDataModule::class,
            SectionsDataModule::class
        ]
)

interface DataModule
