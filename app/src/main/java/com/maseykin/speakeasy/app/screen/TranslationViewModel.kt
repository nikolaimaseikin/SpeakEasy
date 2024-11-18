package com.maseykin.speakeasy.app.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maseykin.speakeasy.app.core.domain.TranslationUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TranslationViewModel @Inject constructor(
    val translationUseCase: TranslationUseCase,
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

    fun swapLanguages() {
        _state.update {
            it.copy(
                sourceLang = it.targetLang,
                targetLang = it.sourceLang
            )
        }
    }

    fun translateText() {
        viewModelScope.launch() {
            //var result = ...
        }
    }
}