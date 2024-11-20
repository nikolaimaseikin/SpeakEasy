package com.maseykin.speakeasy.app.screen.translation

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import com.maseykin.speakeasy.app.R
import com.maseykin.speakeasy.app.core.TranslationApi.Language

@Composable
fun LanguageSelector(
    languages: List<Language>?,
    sourceLanguage: Language,
    targetLanguage: Language,
    onSelectSourceLanguage: (language: Language) -> Unit,
    onSelectTargetLanguage: (language: Language) -> Unit,
    onSwapLanguages: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        LanguageDropdown(
            title = "Source Language",
            languages = languages,
            currentLanguage = sourceLanguage,
            onChangeLanguage = { it ->
                onSelectSourceLanguage(it)
            },
            modifier = Modifier.weight(1f),
        )
        IconButton(
            onClick = { onSwapLanguages() }
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.ic_round_swap_horiz),
                contentDescription = "Swap languages",
            )
        }
        LanguageDropdown(
            title = "Target language",
            languages = languages,
            currentLanguage = targetLanguage,
            onChangeLanguage = { it ->
                onSelectTargetLanguage(it)
            },
            modifier = Modifier.weight(1f),
        )
    }
}