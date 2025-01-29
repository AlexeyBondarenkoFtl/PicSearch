package com.alexeybondarenko.picsearch.ui.main

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.SizeTransform
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
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
import com.alexeybondarenko.picsearch.ui.saved.SavedScreen
import com.alexeybondarenko.picsearch.ui.imagesearch.ImageSearchScreenRoot
import com.alexeybondarenko.picsearch.ui.settings.SettingsScreen

@Composable
fun MainScreen(
    imageSearchScreen: @Composable () -> Unit,
    savedScreen: @Composable () -> Unit,
    settingsScreen: @Composable () -> Unit,
) {
    var selectedItemIndex by remember { mutableIntStateOf(0) }
    val navItems = listOf(NavigationItem.SEARCH, NavigationItem.SAVED, NavigationItem.SETTINGS)

    Scaffold(
        bottomBar = {
            BottomNavBar(
                selectedItemIndex = selectedItemIndex,
                navItems = navItems,
                onClick = { newIndex -> selectedItemIndex = newIndex }
            )
        },
        content = { paddingValues ->
            AnimatedContentContainer(
                modifier = Modifier.padding(paddingValues),
                selectedItemIndex = selectedItemIndex,
                navItems = navItems,
                imageSearchScreen = imageSearchScreen,
                savedScreen = savedScreen,
                settingsScreen = settingsScreen,
            )
        }
    )
}

@Composable
private fun BottomNavBar(
    modifier: Modifier = Modifier,
    selectedItemIndex: Int,
    navItems: List<NavigationItem>,
    onClick: (Int) -> Unit,
) {
    NavigationBar {
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
private fun AnimatedContentContainer(
    modifier: Modifier = Modifier,
    selectedItemIndex: Int,
    navItems: List<NavigationItem>,
    imageSearchScreen: @Composable () -> Unit,
    savedScreen: @Composable () -> Unit,
    settingsScreen: @Composable () -> Unit,
) {
    AnimatedContent(
        modifier = modifier,
        targetState = selectedItemIndex,
        transitionSpec = {
            if (targetState > initialState) {
                slideInHorizontally { height -> height } + fadeIn() togetherWith
                        slideOutHorizontally { width -> -width }
            } else {
                slideInHorizontally { height -> -height } + fadeIn() togetherWith
                        slideOutHorizontally { width -> width }
            }.using(SizeTransform(clip = false))
        }, label = "animated content"
    ) { targetCount ->
        when (targetCount) {
            navItems.indexOf(NavigationItem.SEARCH) -> imageSearchScreen.invoke()
            navItems.indexOf(NavigationItem.SAVED) -> savedScreen.invoke()
            navItems.indexOf(NavigationItem.SETTINGS) -> settingsScreen.invoke()
        }
    }
}

@Composable
@Preview
private fun MainScreenPreview() {
    MainScreen(
        imageSearchScreen = { ImageSearchScreenRoot() },
        savedScreen = { SavedScreen() },
        settingsScreen = { SettingsScreen() },
    )
}