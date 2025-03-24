package com.alexeybondarenko.picsearch.ui.utils.common

import androidx.compose.foundation.background
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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlin.random.Random

@Composable
fun PicSearchImageList(
    modifier: Modifier = Modifier,
    images: List<String>,
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
            BlankImage(
                name = image
            )
        }

        if (isLastReached) {
            onLastItemReached.invoke()
        }
    }
}

@Preview
@Composable
private fun PicSearchImageListPreview() {
    PicSearchImageList(
        images = listOf("1", "2"),
        onLastItemReached = {}
    )
}

// todo temp fun, remove
@Composable
private fun BlankImage(
    name: String,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(Color.Cyan)
            .aspectRatio(randFloatInRange(.25f, 1f))
    ) {
        Text(
            text = "BlankImage $name",
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

// todo temp fun, remove
private fun randFloatInRange(
    min: Float,
    max: Float,
): Float {
    require(max > min) {
        "max must be > min"
    }

    return min + Random.nextFloat() * (max - min)
}