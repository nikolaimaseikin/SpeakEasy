package com.maseykin.speakeasy.app.screen.translation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maseykin.speakeasy.app.core.TranslationApi.Language
import com.maseykin.speakeasy.app.core.domain.favorites.SaveFavoritesUseCase
import com.maseykin.speakeasy.app.core.domain.history.SaveHistoryUseCase
import com.maseykin.speakeasy.app.core.domain.translation.FetchLanguagesUseCase
import com.maseykin.speakeasy.app.core.domain.translation.TranslationUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TranslationViewModel @Inject constructor(
    val translationUseCase: TranslationUseCase,
    val saveHistoryUseCase: SaveHistoryUseCase,
    val fetchLanguagesUseCase: FetchLanguagesUseCase,
    val saveFavoritesUseCase: SaveFavoritesUseCase,
): ViewModel() {
    private val _state = MutableStateFlow(TranslationState())
    val state: StateFlow<TranslationState> = _state

    fun updateInputText(text: String) {
        _state.update {
            it.copy(
                inputText = text
            )
        }
    }

    fun clearInputText() {
        _state.update {
            it.copy(
                inputText = ""
            )
        }
    }

    //Установить исходный язык текста
    fun setSourceLanguage(language: Language) {
        _state.update {
            it.copy(
                sourceLang = language
            )
        }
    }

    //Установить целевой язык перевода
    fun setTargetLanguage(language: Language) {
        _state.update {
            it.copy(
                targetLang = language
            )
        }
    }

    //Смена языков местами
    fun swapLanguages() {
        _state.update {
            it.copy(
                sourceLang = it.targetLang,
                targetLang = it.sourceLang
            )
        }
    }

    fun translateText() {
        viewModelScope.launch(Dispatchers.IO) {
            val result = translationUseCase.translate(
                sl = _state.value.sourceLang,
                dl = _state.value.targetLang,
                text = _state.value.inputText
            )
            _state.update {
                it.copy(
                    translatedText = result.translations.possibleTranslations
                        .firstOrNull() ?: _state.value.inputText
                )
            }
            saveHistoryUseCase.save(
                _state.value.inputText,
                _state.value.translatedText.orEmpty()
            )
        }
    }

    //Загрузить список доступных языков
    fun fetchLanguages() {
        viewModelScope.launch(Dispatchers.IO) {
            val languages = fetchLanguagesUseCase.fetchLanguages()
            _state.update {
                it.copy(
                    languages = languages
                )
            }
            Log.d("Languages", "${_state.value.languages}") //TODO: Убрать логирование
        }
    }

   fun addToFavorites() {
       viewModelScope.launch(Dispatchers.IO) {
           saveFavoritesUseCase.save(
               sourceText = _state.value.inputText,
               translatedText = _state.value.translatedText.orEmpty()
           )
       }
    }
}