package com.maseykin.speakeasy.app.screen.translation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import com.maseykin.speakeasy.app.core.TranslationApi.Language

@Composable
fun LanguageDropdown(
    title: String,
    languages: List<Language>?,
    currentLanguage: Language,
    onChangeLanguage: (language: Language) -> Unit,
    modifier: Modifier = Modifier,
) {
    var sourceDropdownExpanded by rememberSaveable { mutableStateOf(false) }
    val focusManager = LocalFocusManager.current

    Column(modifier = modifier.fillMaxWidth()) {
        OutlinedTextField(
            value = currentLanguage.name,
            onValueChange = {},
            readOnly = true,
            label = { Text(title) },
            trailingIcon = {
                Icon(
                    imageVector = Icons.Default.ArrowDropDown,
                    contentDescription = null,
                    Modifier.clickable { sourceDropdownExpanded = true }
                )
            },
            modifier = Modifier
                .clickable { sourceDropdownExpanded = true }
                .fillMaxWidth(),
            keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
            singleLine = true
        )
        DropdownMenu(
            expanded = sourceDropdownExpanded,
            onDismissRequest = { sourceDropdownExpanded = false },
            modifier = Modifier
        ) {
            languages?.forEach { item ->
                DropdownMenuItem(
                    text = { Text(text = item.name) },
                    onClick = {
                        onChangeLanguage(item)
                        sourceDropdownExpanded = false
                    },
                )
            }
        }
    }
}