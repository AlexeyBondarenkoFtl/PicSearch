package com.alexeybondarenko.picsearch.ui.utils.common

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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.alexeybondarenko.picsearch.ui.imagesearch.data.ImageCard

@Composable
fun PicSearchImageList(
    modifier: Modifier = Modifier,
    images: List<ImageCard>,
    onLastItemReached: () -> Unit,
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
        contentPadding = PaddingValues(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalItemSpacing = 8.dp
    ) {
        items(images) { image ->
            ImageCard(
                image = image
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
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .aspectRatio(image.aspectRatio)
    ) {
        AsyncImage(
            model = image.url,
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds
        )
    }
}

@Preview
@Composable
private fun PicSearchImageListPreview() {
    PicSearchImageList(
        images = listOf(),
        onLastItemReached = {}
    )
}