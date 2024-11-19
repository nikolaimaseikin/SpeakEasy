package com.maseykin.speakeasy.app.screen.translation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.maseykin.speakeasy.app.ui.TextInput
import com.maseykin.speakeasy.app.ui.TranslateButton
import com.maseykin.speakeasy.app.ui.TranslationResult

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TranslationScreen(
    viewModel: TranslationViewModel = hiltViewModel()
) {
    val uiState = viewModel.state.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        TopAppBar(title = { Text("Translation App") })

        TextInput(
            language = uiState.value.sourceLang,
            text = uiState.value.inputText,
            onTextChange = { viewModel.updateInputText(it) },
            onClearText = { viewModel.clearInputText() },
            modifier = Modifier.padding(horizontal = 16.dp)

        )

        Spacer(modifier = Modifier.height(16.dp))

        TranslateButton(
            onTranslate = { viewModel.translateText() },
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        uiState.value.translatedText?.let {
            TranslationResult(
                result = it,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }
    }
}