package com.maseykin.speakeasy.app.screen.favorites

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.maseykin.speakeasy.app.core.data.TranslationFavorites
import java.text.SimpleDateFormat

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoritesScreen(
    viewModel: FavoritesViewModel = hiltViewModel(),
    modifier: Modifier = Modifier,
) {
    val favoritesList = viewModel.getFavorites().collectAsState(initial = emptyList())

    Column(
        modifier = modifier.fillMaxSize()
    ) {
        TopAppBar(title = { Text("Favorites") })

        LazyColumn(modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)) {
            items(favoritesList.value) { it ->
                FavoritesItem(it)
            }
        }
    }
}

@SuppressLint("SimpleDateFormat")
@Composable
fun FavoritesItem(favorites: TranslationFavorites) {
    Column(modifier = Modifier.padding(vertical = 8.dp)) {
        Text(text = "Source: ${favorites.sourceText}")
        Text(text = "Translation: ${favorites.translatedText}")
        Text(text = "Timestamp: ${SimpleDateFormat().format(favorites.timestamp)}")
    }
}