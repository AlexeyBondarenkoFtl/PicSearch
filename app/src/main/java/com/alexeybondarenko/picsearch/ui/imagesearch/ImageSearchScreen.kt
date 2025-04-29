package com.alexeybondarenko.picsearch.ui.imagesearch

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.foundation.text.input.setTextAndPlaceCursorAtEnd
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.isTraversalGroup
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.traversalIndex
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.alexeybondarenko.picsearch.ui.imagesearch.data.ImageCard
import com.alexeybondarenko.picsearch.ui.imagesearch.data.SearchHistoryItem
import com.alexeybondarenko.picsearch.ui.utils.common.PicSearchAlertDialog
import com.alexeybondarenko.picsearch.ui.utils.common.PicSearchImageList
import org.koin.androidx.compose.koinViewModel

@Composable
fun ImageSearchRoute(
    viewModel: ImageSearchViewModel = koinViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val searchHistoryUiState by viewModel.searchHistoryUiState.collectAsStateWithLifecycle()

    ImageSearchScreen(
        uiState = uiState,
        searchHistory = searchHistoryUiState,
        onSearchClick = viewModel::searchByQuery,
        onImageClick = viewModel::saveImage,
        onRequestNextItems = viewModel::loadNextImages
    )

}

@Composable
fun ImageSearchScreen(
    uiState: ImageSearchUiState,
    searchHistory: List<SearchHistoryItem>,
    onSearchClick: (query: String) -> Unit,
    onImageClick: (id: String) -> Unit,
    onRequestNextItems: (query: String) -> Unit,
) {
    var query by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .semantics { isTraversalGroup = true }
    ) {
        PicSearchSearchBar(
            searchHistory = searchHistory,
            query = query,
            onSearchClick = { onSearchClick.invoke(query) },
            onQueryChange = { query = it }
        )

        when (uiState) {
            is ImageSearchUiState.ImageSearchLoaded -> {
                SearchResult(
                    loadedState = uiState,
                    onImageClick = onImageClick,
                    onLastItemReached = { onRequestNextItems.invoke(query) }
                )
            }

            ImageSearchUiState.ImageSearchLoading -> {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }

            is ImageSearchUiState.ImageSearchLoadingError -> {
                uiState.errorMessage?.let { error ->
                    PicSearchAlertDialog(error = error)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BoxScope.PicSearchSearchBar(
    modifier: Modifier = Modifier,
    query: String,
    searchHistory: List<SearchHistoryItem>,
    onSearchClick: () -> Unit,
    onQueryChange: (query: String) -> Unit,
) {
    val textFieldState = rememberTextFieldState()

    var expanded by rememberSaveable { mutableStateOf(false) }

    SearchBar(
        modifier = modifier
            .align(Alignment.TopCenter)
            .semantics { traversalIndex = 0f },
        inputField = {
            SearchBarDefaults.InputField(
                query = query,
                onQueryChange = onQueryChange,
                onSearch = {
                    onSearchClick.invoke()
                    expanded = false
                },
                expanded = expanded,
                onExpandedChange = { expanded = it },
                placeholder = { Text("Hinted search text") },
                leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
            )
        },
        expanded = expanded,
        onExpandedChange = { expanded = it },
    ) {
        // Suggestions
        Column(Modifier.verticalScroll(rememberScrollState())) {
            searchHistory.forEach { historyItem ->
                ListItem(
                    headlineContent = { Text(historyItem.entry) },
                    supportingContent = { Text("Additional info") },
                    leadingContent = { Icon(Icons.Filled.Star, contentDescription = null) },
                    colors = ListItemDefaults.colors(containerColor = Color.Transparent),
                    modifier =
                        Modifier
                            .clickable {
                                textFieldState.setTextAndPlaceCursorAtEnd(historyItem.entry)
                                expanded = false
                            }
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 4.dp)
                )
            }
        }
    }
}

@Composable
fun SearchResult(
    modifier: Modifier = Modifier,
    loadedState: ImageSearchUiState.ImageSearchLoaded,
    onImageClick: (id: String) -> Unit,
    onLastItemReached: () -> Unit,
) {
    Box(
        modifier = modifier.fillMaxSize()
    ) {
        when {
            loadedState.searchResults == null -> {
                Text(
                    text = "Use a search bar for search",
                    modifier = Modifier.align(Alignment.Center)
                )
            }

            loadedState.searchResults.isEmpty() -> {
                Text(
                    text = "Search has no results",
                    modifier = Modifier.align(Alignment.Center)
                )
            }

            else -> {
                SearchResultsList(
                    searchResult = loadedState.searchResults,
                    onClick = onImageClick,
                    onLastItemReached = onLastItemReached,
                )
            }
        }

        loadedState.operationErrorMessage?.let { error ->
            PicSearchAlertDialog(error = error)
        }
    }
}

@Composable
private fun SearchResultsList(
    modifier: Modifier = Modifier,
    searchResult: List<ImageCard>?,
    onClick: (id: String) -> Unit,
    onLastItemReached: () -> Unit,
) {
    if (searchResult != null) {
        PicSearchImageList(
            images = searchResult,
            onClick = onClick,
            onLastItemReached = onLastItemReached,
        )
    }
}


@Preview(showBackground = true)
@Composable
private fun ImageSearchScreenPreview() {
    ImageSearchScreen(
        uiState = ImageSearchUiState.ImageSearchLoaded(
            searchResults = null,
            operationErrorMessage = null
        ),
        searchHistory = emptyList(),
        onSearchClick = {},
        onImageClick = {},
        onRequestNextItems = {}
    )
}