package com.bet_planet.android.presentation

import com.bet_planet.android.presentation.about_us.AboutUsMainFragment
import com.bet_planet.android.presentation.about_us.AboutUsMainFragmentModule
import com.bet_planet.android.presentation.casino.CasinoFragment
import com.bet_planet.android.presentation.casino.CasinoFragmentModule
import com.bet_planet.android.presentation.casino.details.CasinoDetailsFragment
import com.bet_planet.android.presentation.casino.details.CasinoDetailsFragmentModule
import com.bet_planet.android.presentation.casino.favorites.CasinoFavoritesFragment
import com.bet_planet.android.presentation.casino.favorites.CasinoFavoritesFragmentModule
import com.bet_planet.android.presentation.casino.game.CasinoGameFragment
import com.bet_planet.android.presentation.casino.game.CasinoGameFragmentModule
import com.bet_planet.android.presentation.common.countries.CountryListDialogFragment
import com.bet_planet.android.presentation.common.countries.CountryListDialogFragmentModule
import com.bet_planet.android.presentation.coupon.CouponPageFragment
import com.bet_planet.android.presentation.coupon.CouponPageFragmentModule
import com.bet_planet.android.presentation.eventPage.EventPageFragment
import com.bet_planet.android.presentation.eventPage.EventPageFragmentModule
import com.bet_planet.android.presentation.favorites.FavoritesListFragment
import com.bet_planet.android.presentation.favorites.FavoritesListFragmentModule
import com.bet_planet.android.presentation.finance.deposit.DepositFragment
import com.bet_planet.android.presentation.finance.deposit.DepositFragmentModule
import com.bet_planet.android.presentation.finance.withdrawal.WithdrawalFragment
import com.bet_planet.android.presentation.finance.withdrawal.WithdrawalFragmentModule
import com.bet_planet.android.presentation.gameHistory.details.GameHistoryDetailsFragment
import com.bet_planet.android.presentation.gameHistory.details.GameHistoryDetailsFragmentModule
import com.bet_planet.android.presentation.gameHistory.list.GameHistoryListFragment
import com.bet_planet.android.presentation.gameHistory.list.GameHistoryListFragmentModule
import com.bet_planet.android.presentation.homePage.HomePageFragment
import com.bet_planet.android.presentation.homePage.HomePageFragmentModule
import com.bet_planet.android.presentation.kyc.docPhoto.DocPhotoFragment
import com.bet_planet.android.presentation.kyc.docPhoto.DocPhotoFragmentModule
import com.bet_planet.android.presentation.kyc.docType.DocTypeFragment
import com.bet_planet.android.presentation.kyc.docType.DocTypeFragmentModule
import com.bet_planet.android.presentation.kyc.docUpload.DocUploadFragment
import com.bet_planet.android.presentation.kyc.docUpload.DocUploadFragmentModule
import com.bet_planet.android.presentation.kyc.email.SetEmailFragment
import com.bet_planet.android.presentation.kyc.email.SetEmailFragmentModule
import com.bet_planet.android.presentation.kyc.name.SetUserNameFragment
import com.bet_planet.android.presentation.kyc.name.SetUserNameFragmentModule
import com.bet_planet.android.presentation.kyc.success.DocSuccessFragment
import com.bet_planet.android.presentation.kyc.success.DocSuccessFragmentModule
import com.bet_planet.android.presentation.pincode.PinCodeFragment
import com.bet_planet.android.presentation.pincode.PinCodeFragmentModule
import com.bet_planet.android.presentation.profile.ProfileFragment
import com.bet_planet.android.presentation.profile.ProfileFragmentModule
import com.bet_planet.android.presentation.profile.details.AccountDetailsFragment
import com.bet_planet.android.presentation.profile.details.AccountDetailsFragmentModule
import com.bet_planet.android.presentation.profile.details.documents.DocumentsInformationFragment
import com.bet_planet.android.presentation.profile.details.documents.DocumentsInformationFragmentModule
import com.bet_planet.android.presentation.profile.details.documents.details.DocumentDetailsFragment
import com.bet_planet.android.presentation.profile.details.documents.details.DocumentDetailsFragmentModule
import com.bet_planet.android.presentation.profile.details.email_change.ChangeEmailDialogFragment
import com.bet_planet.android.presentation.profile.details.email_change.ChangeEmailDialogFragmentModule
import com.bet_planet.android.presentation.profile.details.password_change.ChangePasswordDialogFragment
import com.bet_planet.android.presentation.profile.details.password_change.ChangePasswordDialogFragmentModule
import com.bet_planet.android.presentation.profile.details.phone.ChangePhoneNumberFragment
import com.bet_planet.android.presentation.profile.details.phone.ChangePhoneNumberFragmentModule
import com.bet_planet.android.presentation.profile.details.phone_change.ChangePhoneNumberDialogFragment
import com.bet_planet.android.presentation.profile.details.phone_change.ChangePhoneNumberDialogFragmentModule
import com.bet_planet.android.presentation.profile.notifications.NotificationsSettingFragment
import com.bet_planet.android.presentation.profile.notifications.NotificationsSettingFragmentModule
import com.bet_planet.android.presentation.profile.responsible_gaming.ResponsibleGamingFragment
import com.bet_planet.android.presentation.profile.responsible_gaming.ResponsibleGamingFragmentModule
import com.bet_planet.android.presentation.profile.responsible_gaming.deposit_limits.DepositLimitsFragment
import com.bet_planet.android.presentation.profile.responsible_gaming.deposit_limits.DepositLimitsFragmentModule
import com.bet_planet.android.presentation.profile.security.SecurityFragment
import com.bet_planet.android.presentation.profile.security.SecurityFragmentModule
import com.bet_planet.android.presentation.promotions.PromotionsListFragment
import com.bet_planet.android.presentation.promotions.PromotionsListFragmentModule
import com.bet_planet.android.presentation.promotions.details.PromotionDetailsFragment
import com.bet_planet.android.presentation.promotions.details.PromotionDetailsFragmentModule
import com.bet_planet.android.presentation.quickBet.QuickBetSettingsFragment
import com.bet_planet.android.presentation.quickBet.QuickBetSettingsFragmentModule
import com.bet_planet.android.presentation.signInFlow.birthdate.BirthDateFragment
import com.bet_planet.android.presentation.signInFlow.birthdate.SendBirthDateFragmentModule
import com.bet_planet.android.presentation.signInFlow.country.CountryListFragment
import com.bet_planet.android.presentation.signInFlow.country.CountryListFragmentModule
import com.bet_planet.android.presentation.signInFlow.password.PasswordFragment
import com.bet_planet.android.presentation.signInFlow.password.PasswordFragmentModule
import com.bet_planet.android.presentation.signInFlow.phone.EnterPhoneNumberFragment
import com.bet_planet.android.presentation.signInFlow.phone.EnterPhoneNumberFragmentModule
import com.bet_planet.android.presentation.signInFlow.reset_password.ResetPasswordFragment
import com.bet_planet.android.presentation.signInFlow.reset_password.ResetPasswordFragmentModule
import com.bet_planet.android.presentation.signInFlow.setPincode.question.QuestionSetPinFragment
import com.bet_planet.android.presentation.signInFlow.setPincode.question.QuestionSetPinFragmentModule
import com.bet_planet.android.presentation.splash.SplashFragment
import com.bet_planet.android.presentation.splash.SplashFragmentModule
import com.bet_planet.android.presentation.transactions.TransactionsListFragment
import com.bet_planet.android.presentation.transactions.TransactionsListFragmentModule
import com.bet_planet.android.presentation.upcomingMatches.UpcomingMatchesFragment
import com.bet_planet.android.presentation.upcomingMatches.UpcomingMatchesFragmentModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@SuppressWarnings("Detekt.TooManyFunctions")
@Module
interface MainFragmentModule {

    @ContributesAndroidInjector(modules = [CasinoFavoritesFragmentModule::class])
    fun contributeCasinoFavoritesFragment(): CasinoFavoritesFragment

    @ContributesAndroidInjector(modules = [CasinoFragmentModule::class])
    fun contributeCasinoFragment(): CasinoFragment

    @ContributesAndroidInjector(modules = [CasinoDetailsFragmentModule::class])
    fun contributeCasinoDetailsFragment(): CasinoDetailsFragment

    @ContributesAndroidInjector(modules = [CasinoGameFragmentModule::class])
    fun contributeCasinoGameFragment(): CasinoGameFragment

    @ContributesAndroidInjector(modules = [HomePageFragmentModule::class])
    fun contributeHomePageFragment(): HomePageFragment

    @ContributesAndroidInjector(modules = [UpcomingMatchesFragmentModule::class])
    fun contributeUpcomingMatchesFragment(): UpcomingMatchesFragment

    @ContributesAndroidInjector(modules = [EventPageFragmentModule::class])
    fun contributeEventPageFragment(): EventPageFragment

    @ContributesAndroidInjector(modules = [CouponPageFragmentModule::class])
    fun contributeCouponPageFragment(): CouponPageFragment

    @ContributesAndroidInjector(modules = [QuickBetSettingsFragmentModule::class])
    fun contributeQuickBetSettingsFragment(): QuickBetSettingsFragment

    @ContributesAndroidInjector(
            modules = [PinCodeFragmentModule::class]
    )
    fun contributeChangePinCodeStateFragment(): PinCodeFragment

    @ContributesAndroidInjector(modules = [EnterPhoneNumberFragmentModule::class])
    fun contributeEnterPhoneNumberFragment(): EnterPhoneNumberFragment

    @ContributesAndroidInjector(modules = [PasswordFragmentModule::class])
    fun contributePasswordFragment(): PasswordFragment

    @ContributesAndroidInjector(modules = [QuestionSetPinFragmentModule::class])
    fun contributeQuestionSetPinFragment(): QuestionSetPinFragment

    @ContributesAndroidInjector(modules = [CountryListFragmentModule::class])
    fun contributeCountryListFragment(): CountryListFragment

    @ContributesAndroidInjector(modules = [CountryListDialogFragmentModule::class])
    fun contributeCountryLisDialogFragment(): CountryListDialogFragment

    @ContributesAndroidInjector(modules = [DepositFragmentModule::class])
    fun contributeDepositFragment(): DepositFragment

    @ContributesAndroidInjector(modules = [SetUserNameFragmentModule::class])
    fun contributeSetUserNameFragment(): SetUserNameFragment

    @ContributesAndroidInjector(modules = [SetEmailFragmentModule::class])
    fun contributeSetEmailFragment(): SetEmailFragment

    @ContributesAndroidInjector(modules = [DocTypeFragmentModule::class])
    fun contributeDocTypeFragment(): DocTypeFragment

    @ContributesAndroidInjector(modules = [DocSuccessFragmentModule::class])
    fun contributeDocSuccessFragment(): DocSuccessFragment

    @ContributesAndroidInjector(modules = [DocUploadFragmentModule::class])
    fun contributeDocUploadFragment(): DocUploadFragment

    @ContributesAndroidInjector(modules = [DocPhotoFragmentModule::class])
    fun contributeDocPhotoFragment(): DocPhotoFragment

    @ContributesAndroidInjector(modules = [WithdrawalFragmentModule::class])
    fun contributeWithdrawalFragment(): WithdrawalFragment

    @ContributesAndroidInjector(modules = [GameHistoryDetailsFragmentModule::class])
    fun contributeGameHistoryDetailsFragment(): GameHistoryDetailsFragment

    @ContributesAndroidInjector(modules = [GameHistoryListFragmentModule::class])
    fun contributeGameHistoryListFragment(): GameHistoryListFragment

    @ContributesAndroidInjector(modules = [ProfileFragmentModule::class])
    fun contributeProfileFragment(): ProfileFragment

    @ContributesAndroidInjector(modules = [NotificationsSettingFragmentModule::class])
    fun contributeNotificationsSettingFragment(): NotificationsSettingFragment

    @ContributesAndroidInjector(modules = [SecurityFragmentModule::class])
    fun contributeSecurityFragment(): SecurityFragment

    @ContributesAndroidInjector(modules = [AccountDetailsFragmentModule::class])
    fun contributeAccountDetailsFragment(): AccountDetailsFragment

    @ContributesAndroidInjector(modules = [ResponsibleGamingFragmentModule::class])
    fun contributeResponsibleGamingFragment(): ResponsibleGamingFragment

    @ContributesAndroidInjector(modules = [DepositLimitsFragmentModule::class])
    fun contributeDepositLimitsFragment(): DepositLimitsFragment

    @ContributesAndroidInjector(modules = [ChangePhoneNumberFragmentModule::class])
    fun contributeChangePhoneNumberFragment(): ChangePhoneNumberFragment

    @ContributesAndroidInjector(modules = [ChangePasswordDialogFragmentModule::class])
    fun contributeChangePasswordDialogFragment(): ChangePasswordDialogFragment

    @ContributesAndroidInjector(modules = [ChangePhoneNumberDialogFragmentModule::class])
    fun contributeChangePhoneNumberDialogFragment(): ChangePhoneNumberDialogFragment

    @ContributesAndroidInjector(modules = [ChangeEmailDialogFragmentModule::class])
    fun contributeChangeEmailDialogFragment(): ChangeEmailDialogFragment

    @ContributesAndroidInjector(modules = [DocumentsInformationFragmentModule::class])
    fun contributeDocumentInformationFragment(): DocumentsInformationFragment

    @ContributesAndroidInjector(modules = [DocumentDetailsFragmentModule::class])
    fun contributeDocumentDetailsFragment(): DocumentDetailsFragment

    @ContributesAndroidInjector(modules = [TransactionsListFragmentModule::class])
    fun contributeTransactionsListFragment(): TransactionsListFragment

    @ContributesAndroidInjector(modules = [PromotionsListFragmentModule::class])
    fun contributePromotionsListFragment(): PromotionsListFragment

    @ContributesAndroidInjector(modules = [AboutUsMainFragmentModule::class])
    fun contributeAboutUsMainFragment(): AboutUsMainFragment

    @ContributesAndroidInjector(modules = [FavoritesListFragmentModule::class])
    fun contributeFavoritesListFragment(): FavoritesListFragment

    @ContributesAndroidInjector(modules = [PromotionDetailsFragmentModule::class])
    fun contributePromotionDetailsFragment(): PromotionDetailsFragment

    @ContributesAndroidInjector(modules = [SendBirthDateFragmentModule::class])
    fun contributeBirthDateFragment(): BirthDateFragment

    @ContributesAndroidInjector(modules = [ResetPasswordFragmentModule::class])
    fun contributeVerifySmsCodeFragment(): ResetPasswordFragment

    @ContributesAndroidInjector(modules = [SplashFragmentModule::class])
    fun contributeSplashFragment(): SplashFragment

}
