package com.maseykin.speakeasy.app

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.maseykin.speakeasy.app.screen.favorites.FavoritesScreen
import com.maseykin.speakeasy.app.screen.history.HistoryScreen
import com.maseykin.speakeasy.app.screen.translation.TranslationScreen
import com.maseykin.speakeasy.app.ui.theme.SpeakEasyTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SpeakEasyTheme {
                MainScreen()
            }
        }
    }

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @Composable
    fun MainScreen() {
        val navController = rememberNavController()
        Scaffold (
            bottomBar = {
                BottomNavigationBar(navController = navController)
            }
        ) { innerPadding ->
            NavHost(
                navController,
                startDestination = "translate"
            ) {
                composable("translate") {
                    TranslationScreen(modifier = Modifier.padding(bottom = innerPadding.calculateBottomPadding()))
                }
                composable("history") {
                    HistoryScreen(modifier = Modifier.padding(bottom = innerPadding.calculateBottomPadding()))
                }
                composable("favorites") {
                    FavoritesScreen(modifier = Modifier.padding(bottom = innerPadding.calculateBottomPadding()))
                }
            }
        }
    }

    private val Destinations = listOf("translate", "history", "favorites")

    @Composable
    fun BottomNavigationBar(navController: NavController) {
        var selectedItem by rememberSaveable { mutableStateOf(0) }
        val icons = listOf(
            ImageVector.vectorResource(R.drawable.ic_translate),
            ImageVector.vectorResource(R.drawable.ic_history),
            ImageVector.vectorResource(R.drawable.ic_fav),
        )
        NavigationBar(
            content = {
                Destinations.forEachIndexed { index, item ->
                    BottomNavigationItem(
                        icon = {
                            Icon(
                                imageVector = icons[index],
                                contentDescription = item
                            )
                        },
                        selected = selectedItem == index,
                        onClick = {
                            selectedItem = index
                            navController.navigate(item)
                        },
                        label = {
                            Text(text = item)
                        },
                    )
                }
            }
        )
    }
        @Composable
        fun RowScope.BottomNavigationItem(
            selected: Boolean,
            onClick: () -> Unit,
            modifier: Modifier = Modifier,
            enabled: Boolean = true,
            alwaysShowLabel: Boolean = true,
            icon: @Composable () -> Unit,
            selectedIcon: @Composable () -> Unit = icon,
            label: @Composable (() -> Unit)? = null,
        ) {
            NavigationBarItem(
                selected = selected,
                onClick = onClick,
                modifier = modifier,
                enabled = enabled,
                alwaysShowLabel = alwaysShowLabel,
                icon = if (enabled) selectedIcon else icon,
                label = label,
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = NavigationDefaults.navigationSelectedItemColor(),
                    unselectedIconColor = NavigationDefaults.navigationContentColor(),
                    selectedTextColor = NavigationDefaults.navigationSelectedItemColor(),
                    unselectedTextColor = NavigationDefaults.navigationContentColor(),
                    indicatorColor = NavigationDefaults.navigationIndicatorColor()
                )
            )
        }

    object NavigationDefaults {
        @Composable
        fun navigationContentColor() = MaterialTheme.colorScheme.onSurfaceVariant

        @Composable
        fun navigationSelectedItemColor() = MaterialTheme.colorScheme.onPrimaryContainer

        @Composable
        fun navigationIndicatorColor() = MaterialTheme.colorScheme.primaryContainer
    }
}