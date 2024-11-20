package com.maseykin.speakeasy.app.core.domain.favorites

import com.maseykin.speakeasy.app.core.data.TranslationFavorites
import com.maseykin.speakeasy.app.core.data.TranslationFavoritesDao
import javax.inject.Inject

class SaveFavoritesUseCase @Inject constructor(val translationFavoritesDao: TranslationFavoritesDao) {
    suspend fun save(sourceText: String, translatedText: String) {
        translationFavoritesDao.insertFavorites(
            TranslationFavorites(
                sourceText = sourceText,
                translatedText = translatedText
            )
        )
    }
}