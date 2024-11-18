package com.maseykin.speakeasy.app.core.domain

import com.maseykin.speakeasy.app.core.TranslationApi
import javax.inject.Inject

class TranslationUseCase @Inject constructor(
    val privateApi: TranslationApi
) {
    suspend fun translate() {
        TODO()
    }
}