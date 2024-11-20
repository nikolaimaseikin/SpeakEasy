package com.maseykin.speakeasy.app.screen.favorites

import androidx.lifecycle.ViewModel
import com.maseykin.speakeasy.app.core.data.TranslationFavorites
import com.maseykin.speakeasy.app.core.data.TranslationFavoritesDao
import com.maseykin.speakeasy.app.core.data.TranslationHistory
import com.maseykin.speakeasy.app.core.data.TranslationHistoryDao
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    val translationFavoritesDao: TranslationFavoritesDao,
): ViewModel() {
    fun getFavorites(): Flow<List<TranslationFavorites>> {
        return translationFavoritesDao.getTranslationFavorites()
    }
}