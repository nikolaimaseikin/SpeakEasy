package com.maseykin.speakeasy.app.screen.translation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.maseykin.speakeasy.app.ui.TextInput
import com.maseykin.speakeasy.app.ui.TranslateButton
import com.maseykin.speakeasy.app.ui.TranslationResult
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TranslationScreen(
    viewModel: TranslationViewModel = hiltViewModel(),
    modifier: Modifier = Modifier,
) {
    val uiState = viewModel.state.collectAsState()
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(Unit) {
        viewModel.fetchLanguages()
    }

    Scaffold(
        topBar = { TopAppBar(title = { Text("Translation App") }) },
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        },
        modifier = modifier.fillMaxSize(),
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize().padding(innerPadding)
        ) {
            LazyColumn {
                item {
                    LanguageSelector(
                        languages = uiState.value.languages,
                        sourceLanguage = uiState.value.sourceLang,
                        targetLanguage = uiState.value.targetLang,
                        onSelectSourceLanguage = {
                            viewModel.setSourceLanguage(it)
                        },
                        onSelectTargetLanguage = {
                            viewModel.setTargetLanguage(it)
                        },
                        onSwapLanguages = { viewModel.swapLanguages() },
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                }

                item {
                    TextInput(
                        text = uiState.value.inputText,
                        onTextChange = { viewModel.updateInputText(it) },
                        onClearText = { viewModel.clearInputText() },
                        modifier = Modifier.padding(horizontal = 16.dp)

                    )
                    Spacer(modifier = Modifier.height(16.dp))
                }

                item {
                    TranslateButton(
                        onTranslate = { viewModel.translateText() },
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                }

                item {
                    uiState.value.translatedText?.let {
                        TranslationResult(
                            result = it,
                            onAddToFavorites = {
                                viewModel.addToFavorites()
                                scope.launch {
                                    snackbarHostState.showSnackbar("Added to favorites")
                                }
                            },
                            modifier = Modifier.padding(horizontal = 16.dp),
                        )
                    }
                }
            }
        }
    }
}