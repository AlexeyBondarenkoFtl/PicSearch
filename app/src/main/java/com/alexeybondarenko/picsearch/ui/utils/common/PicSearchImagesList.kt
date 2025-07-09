package com.alexeybondarenko.picsearch.ui.utils.common

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.lazy.staggeredgrid.rememberLazyStaggeredGridState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.compose.SubcomposeAsyncImage
import com.alexeybondarenko.picsearch.ui.imagesearch.data.ImageCard

@Composable
fun PicSearchImageList(
    modifier: Modifier = Modifier,
    images: List<ImageCard>,
    onClick: (id: String) -> Unit,
    onLastItemReached: () -> Unit = {},
    isSearchBarPaddingNeeded: Boolean = false,
) {
    val state = rememberLazyStaggeredGridState()
    val isLastReached by remember {
        derivedStateOf {
            val lastVisibleIndex =
                state.layoutInfo.visibleItemsInfo.lastOrNull()?.index

            lastVisibleIndex == state.layoutInfo.totalItemsCount - 1
        }
    }

    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Fixed(2),
        modifier = modifier.fillMaxSize(),
        state = state,
        contentPadding = calculateContentPadding(isSearchBarPaddingNeeded),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalItemSpacing = 8.dp
    ) {
        items(images) { image ->
            ImageCard(
                image = image,
                onClick = onClick,
            )
        }

        if (isLastReached) {
            onLastItemReached.invoke()
        }
    }
}

@Composable
private fun ImageCard(
    image: ImageCard,
    onClick: (id: String) -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .aspectRatio(image.aspectRatio)
            .clickable {
                image.id?.let { onClick.invoke(it) }
            }
    ) {
        SubcomposeAsyncImage(
            model = image.url,
            loading = {
                LoadingBox()
            },
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds
        )
    }
}

@Composable
private fun LoadingBox() {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
    }
}

private fun calculateContentPadding(
    isSearchBarPaddingNeeded: Boolean
): PaddingValues {
    return if (isSearchBarPaddingNeeded) {
        PaddingValues(
            top = (88 + 8).dp,
            bottom = 8.dp,
            start = 8.dp,
            end = 8.dp,
        )
    } else {
        PaddingValues(8.dp)
    }
}

@Preview
@Composable
private fun PicSearchImageListPreview() {
    PicSearchImageList(
        images = listOf(),
        onClick = {},
        onLastItemReached = {}
    )
}