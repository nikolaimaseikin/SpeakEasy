package com.maseykin.speakeasy.app.core.domain.translation

import com.maseykin.speakeasy.app.core.TranslationApi
import com.maseykin.speakeasy.app.core.TranslationApi.Language
import com.maseykin.speakeasy.app.core.TranslationApi.TranslationResponse
import javax.inject.Inject

class TranslationUseCase @Inject constructor(
    val privateApi: TranslationApi
) {
    suspend fun translate(sl: Language, dl: Language, text: String): TranslationResponse {
        return privateApi.translate(sl.code, dl.code, text)
    }
}