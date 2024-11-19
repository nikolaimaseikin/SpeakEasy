package com.maseykin.speakeasy.app.screen.history

import androidx.lifecycle.ViewModel
import com.maseykin.speakeasy.app.core.data.TranslationHistory
import com.maseykin.speakeasy.app.core.data.TranslationHistoryDao
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

//TODO: Сделать репозиторий и UseCase
@HiltViewModel
class HistoryViewModel @Inject constructor(
    val translationHistoryDao: TranslationHistoryDao,
): ViewModel() {
    fun getHistory(): Flow<List<TranslationHistory>> {
        return translationHistoryDao.getTranslationHistory()
    }
}