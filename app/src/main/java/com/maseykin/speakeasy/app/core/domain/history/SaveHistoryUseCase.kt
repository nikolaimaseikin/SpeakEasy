package com.maseykin.speakeasy.app.core.domain.history

import com.maseykin.speakeasy.app.core.data.TranslationHistory
import com.maseykin.speakeasy.app.core.data.TranslationHistoryDao
import okio.Source
import javax.inject.Inject

class SaveHistoryUseCase @Inject constructor(val translationHistoryDao: TranslationHistoryDao) {
    suspend fun save(sourceText: String, translatedText: String) {
        translationHistoryDao.insertHistory(
            TranslationHistory(
                sourceText = sourceText,
                translatedText = translatedText
            )
        )
    }
}