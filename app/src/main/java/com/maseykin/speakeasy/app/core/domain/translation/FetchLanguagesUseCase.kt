package com.maseykin.speakeasy.app.core.domain.translation

import com.maseykin.speakeasy.app.core.TranslationApi
import com.maseykin.speakeasy.app.core.TranslationApi.Language
import javax.inject.Inject

class FetchLanguagesUseCase @Inject constructor(
    val privateApi: TranslationApi
) {
    suspend fun fetchLanguages(): List<Language> {
        val response = privateApi.getLanguages()
        return response.map { (code, name) -> Language(code, name) }
    }
}