package com.alexeybondarenko.picsearch.ui.main

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.alexeybondarenko.picsearch.ui.imagesearch.ImageSearchRoute
import com.alexeybondarenko.picsearch.ui.saved.SavedScreen
import com.alexeybondarenko.picsearch.ui.settings.SettingsScreen
import com.alexeybondarenko.picsearch.ui.utils.navigation.Routes


@Composable
fun MainScreen() {
    var selectedItemIndex by remember { mutableIntStateOf(0) }
    val navItems = listOf(NavigationItem.SEARCH, NavigationItem.SAVED, NavigationItem.SETTINGS)

    val navController = rememberNavController()

    Scaffold(
        content = { paddingValues ->
            NavHost(
                navController = navController,
                startDestination = Routes.Search,
                modifier = Modifier.padding(paddingValues)
            ) {
                composable<Routes.Search> {
                    ImageSearchRoute()
                }

                composable<Routes.Saved> {
                    SavedScreen()
                }

                composable<Routes.Settings> {
                    SettingsScreen()
                }
            }
        },
        bottomBar = {
            BottomNavBar(
                selectedItemIndex = selectedItemIndex,
                navItems = navItems,
                onClick = { newIndex ->
                    if (selectedItemIndex == newIndex) return@BottomNavBar

                    selectedItemIndex = newIndex

                    val route: Routes? = when (newIndex) {
                        navItems.indexOf(NavigationItem.SEARCH) -> Routes.Search
                        navItems.indexOf(NavigationItem.SAVED) -> Routes.Saved
                        navItems.indexOf(NavigationItem.SETTINGS) -> Routes.Settings
                        else -> null
                    }

                    route?.let { navController.navigate(it) }
                }
            )
        },
    )
}

@Composable
private fun BottomNavBar(
    modifier: Modifier = Modifier,
    selectedItemIndex: Int,
    navItems: List<NavigationItem>,
    onClick: (Int) -> Unit,
) {
    NavigationBar(
        modifier = modifier
    ) {
        navItems.forEachIndexed { index, item ->
            NavigationBarItem(
                icon = {
                    Icon(
                        if (selectedItemIndex == index) item.selectIcon else item.unselectIcon,
                        contentDescription = item.label
                    )
                },
                label = { Text(item.label) },
                selected = selectedItemIndex == index,
                onClick = { onClick.invoke(index) }
            )
        }
    }
}

@Composable
@Preview
private fun MainScreenPreview() {
    MainScreen()
}