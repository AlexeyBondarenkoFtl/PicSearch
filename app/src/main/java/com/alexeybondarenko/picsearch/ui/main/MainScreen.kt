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
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.alexeybondarenko.picsearch.ui.imagesearch.ImageSearchRoute
import com.alexeybondarenko.picsearch.ui.saved.SavedScreen
import com.alexeybondarenko.picsearch.ui.settings.SettingsScreen
import com.alexeybondarenko.picsearch.ui.utils.navigation.Routes

@Composable
fun MainScreen() {
    val navController = rememberNavController()

    val navItems = remember { NavigationItem.entries }

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
                navController = navController,
                navItems = navItems,
            )
        },
    )
}

@Composable
private fun BottomNavBar(
    navController: NavController,
    navItems: List<NavigationItem>
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    var selectedItemIndex by remember { mutableIntStateOf(0) }

    NavigationBar {
        navItems.forEachIndexed { index, item ->
            val isSelected =
                currentDestination?.route == navItems[selectedItemIndex].associatedRoute.getSimpleName()

            NavigationBarItem(
                icon = {
                    Icon(
                        if (index == selectedItemIndex) item.selectIcon else item.unselectIcon,
                        contentDescription = item.label
                    )
                },
                label = { Text(item.label) },
                selected = isSelected,
                onClick = {
                    if (selectedItemIndex == index) return@NavigationBarItem
                    selectedItemIndex = index

                    navController.navigate(item.associatedRoute)
                }
            )
        }
    }
}

@Composable
@Preview
private fun MainScreenPreview() {
    MainScreen()
}