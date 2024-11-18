package com.maseykin.speakeasy.app.screen

data class TranslationState(
    val sourceLang: String = "Russian",
    val targetLang: String = "English",
    val inputText: String = "",
    val translatedText: String? = null
)
