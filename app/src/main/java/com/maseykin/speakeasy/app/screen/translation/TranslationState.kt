package com.maseykin.speakeasy.app.screen.translation

import com.maseykin.speakeasy.app.core.TranslationApi.Language

data class TranslationState(
    val sourceLang: Language = Language(code = "ru", name = "russian"),
    val targetLang: Language = Language(code = "en", name = "english"),
    val inputText: String = "",
    val translatedText: String? = null,
    val languages: List<Language>? = null
)
