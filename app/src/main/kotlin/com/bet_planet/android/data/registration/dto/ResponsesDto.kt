package com.bet_planet.android.data.registration.dto

import com.bet_planet.android.data.storelocator.StoreLocationDto
import com.bet_planet.android.domain.profile.details.DocumentStatus
import com.bet_planet.android.domain.profile.details.DocumentType
import com.bet_planet.android.domain.profile.details.documents.Document
import com.google.gson.annotations.SerializedName
import java.math.BigDecimal
import java.util.*

open class SingularResponse(
        @SerializedName("StatusCode") open val code: Int
)

class LoginResponse(
        code: Int,
        @SerializedName("UserID") val userId: String? = "",
        @SerializedName("ErrorCode") val errorCode: Int = 0
) : SingularResponse(code) {

    fun isSuccessLogin(): Boolean {
        return code == 10 && !userId.isNullOrEmpty()
    }
}

class BetResponse(
        code: Int,
        @SerializedName("UserID") val userId: String? = "",
        @SerializedName("ExpirationDate") val expirationDate: String? = ""
) : SingularResponse(code)

class GetTokenResponse(
        code: Int,
        @SerializedName("Token") val token: String
) : SingularResponse(code)

class UserLangResponse(
        code: Int,
        @SerializedName("Lang") val language: String = "en"
) : SingularResponse(code)

data class SendPhoneResponse(
        @SerializedName("maskedPhoneNumber") val maskedPhoneNumber: String,
        @SerializedName("smsRetryTimestamp") val smsRetrySeconds: Int,
        @SerializedName("guid") val guid: String
)

data class BalanceResponse(
        @SerializedName("StatusCode") val code: Int,
        @SerializedName("CurrencyID") val currencyID: Int,
        @SerializedName("BalanceAmount") val balance: BigDecimal?,
        @SerializedName("LockedAmount") val lockedAmount: BigDecimal?,
        @SerializedName("BonusAmount") val bonusAmount: BigDecimal?
)

class DocumentsResponse(
        code: Int,
        @SerializedName("IDDocuments") val documents: List<DocumentItem>
) : SingularResponse(code)

/**
 * @param docStatus статус документа, числовое значение:
 * 0 - Unverified, 1 - Verified, 2 - Under Review, 3 - Rejected
 */
data class DocumentItem(
        @SerializedName("ID") val id: Int,
        @SerializedName("DocumentTypeID") val docTypeId: String? = null,
        @SerializedName("DocumentNumber") val docNumber: String? = null,
        @SerializedName("PersonalID") val personalId: String? = null,
        @SerializedName("CountryID") val countryId: String? = null,
        @SerializedName("DateModified") val dateModified: Date? = null,
        @SerializedName("DocumentStatus") val docStatus: String? = null,
        @SerializedName("DocumentFrontImageURL") val frontImage: String? = null,
        @SerializedName("DocumentBackImageURL") val backImage: String? = null,
        @SerializedName("DocumentExpirationDate") val docExpDate: Date? = null,
        @SerializedName("DocumentIssueingAuthority") val documentIssuingAuthority: String? = null,
        @SerializedName("DocumentIssueingPlace") val documentIssueingPlace: String? = null,
        @SerializedName("DocumentIssuingDate") val documentIssuingDate: Date? = null
) {
    fun toDomain(): Document {
        return Document(
                id = id,
                docTypeId = DocumentType.getById(docTypeId),
                docNumber = docNumber ?: "",
                personalId = personalId ?: "",
                countryId = countryId ?: "",
                dateModified = dateModified,
                docStatus = DocumentStatus.getById(docStatus),
                frontImage = frontImage ?: "",
                backImage = backImage ?: "",
                docExpDate = docExpDate,
                issuingAuthority = documentIssuingAuthority ?: "",
                issuingPlace = documentIssueingPlace ?: "",
                issuingDate = documentIssuingDate
        )
    }
}

class DocumentsFileUploadResponse(
        @SerializedName("back") val back: String,
        @SerializedName("front") val front: String
)

class UserDataResponse(
        code: Int,
        @SerializedName("Name") val name: String?,
        @SerializedName("Surname") val surname: String?,
        @SerializedName("MiddleName") val middleName: String?,
        @SerializedName("Email") val email: String?,
        @SerializedName("Tel") val phone: String?,
        @SerializedName("CountryID") val countryId: Int?
) : SingularResponse(code)

data class StoreLocatorResponse(
        val status: String,
        val locations: HashMap<String, StoreLocationDto?>
)

data class MinVersionResponse(
        @SerializedName("android") val android: AndroidMinVersion,
        @SerializedName("countryId") val countryId: Int?
)

data class AndroidMinVersion(
        @SerializedName("minVersion") val minVersion: String,
        @SerializedName("appUrl") val appUrl: String,
        @SerializedName("curVersion") val curVersion: String? = null,
        @SerializedName("useLockScreen") val useLockScreen: Boolean?
)

