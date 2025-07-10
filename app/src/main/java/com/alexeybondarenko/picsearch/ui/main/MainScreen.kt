package com.alexeybondarenko.picsearch.ui.main

import androidx.compose.foundation.layout.RowScope
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
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.alexeybondarenko.picsearch.ui.utils.navigation.PicSearchNavHost

@Composable
fun MainScreen() {
    val navController = rememberNavController()

    Scaffold(
        content = { paddingValues ->
            PicSearchNavHost(
                modifier = Modifier.padding(paddingValues),
                navController = navController,
            )
        },
        bottomBar = {
            BottomNavBar(
                navController = navController,
            )
        },
    )
}

@Composable
private fun BottomNavBar(
    navController: NavController,
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    val navItems = remember { NavigationItem.entries }

    var selectedItemIndex by remember { mutableIntStateOf(0) }

    NavigationBar {
        navItems.forEachIndexed { index, item ->
            val isSelected =
                currentDestination?.route == navItems[selectedItemIndex].associatedRoute.getSimpleName()

            NavBarItem(
                item = item,
                selected = isSelected,
                onClick = {
                    if (selectedItemIndex != index) {
                        selectedItemIndex = index
                        navController.navigate(item.associatedRoute)
                    }
                },
                isThisIndexSelected = index == selectedItemIndex
            )
        }
    }
}

@Composable
private fun RowScope.NavBarItem(
    item: NavigationItem,
    selected: Boolean,
    onClick: () -> Unit,
    isThisIndexSelected: Boolean,
) {
    NavigationBarItem(
        icon = {
            Icon(
                if (isThisIndexSelected) item.selectIcon else item.unselectIcon,
                contentDescription = item.label
            )
        },
        label = { Text(item.label) },
        selected = selected,
        onClick = onClick,
    )
}


@Composable
@Preview
private fun MainScreenPreview() {
    MainScreen()
}