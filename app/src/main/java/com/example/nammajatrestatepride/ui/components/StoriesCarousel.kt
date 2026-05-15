// app/src/main/java/com/example/nammajatrestatepride/ui/components/StoriesCarousel.kt
package com.example.nammajatrestatepride.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import coil.compose.AsyncImage
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign

@Composable
fun StoriesCarousel(modifier: Modifier = Modifier, onStoryClick: (String) -> Unit) {
    val stories = listOf(
        StoryItem(
            id = "1",
            imageUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRtVgLCw4QX3a5DNpgma9Upp7TLSrtQw2b4vQ&s",
            title = "Temple Festival"
        ),
        StoryItem(
            id = "2",
            imageUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQaf7bjfGPB05vv4H4O_hom2Y8WvUfnlTH8LfRDrfaC9g&s",
            title = "Rathotsava"
        ),
        StoryItem(
            id = "3",
            imageUrl = "https://encrypted-tbn1.gstatic.com/images?q=tbn:ANd9GcQQMxToJae6KoD3f6b-jRS6PqiUDAxpzG2GyEq5jhh7tIRQznXW",
            title = "Wrestling"
        ),
        StoryItem(
            id = "4",
            imageUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRskMweAmQtX0iVDjKC16JUdNyyPBGZnt9CfMvWRc-QBMCZBfzo",
            title = "Drama"
        )
    )

    val listState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        while (true) {
            delay(3500L)
            val next = (listState.firstVisibleItemIndex + 1) % stories.size
            coroutineScope.launch {
                listState.animateScrollToItem(next)
            }
        }
    }

    LazyRow(state = listState, modifier = modifier.padding(horizontal = 12.dp)) {
        itemsIndexed(stories) { _, story ->
            Card(
                modifier = Modifier
                    .padding(8.dp)
                    .width(320.dp)
                    .height(200.dp)
                    .clickable { onStoryClick(story.id) },
                shape = RoundedCornerShape(16.dp)
            ) {
                Box(modifier = Modifier.fillMaxSize()) {
                    AsyncImage(
                        model = story.imageUrl,
                        contentDescription = story.title,
                        modifier = Modifier.fillMaxSize(),
                        contentScale = androidx.compose.ui.layout.ContentScale.Crop
                    )
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(
                                Brush.verticalGradient(
                                    colors = listOf(Color.Transparent, Color(0xAA000000)),
                                    startY = 120f
                                )
                            )
                    )
                    Text(
                        text = story.title,
                        modifier = Modifier
                            .align(Alignment.BottomStart)
                            .padding(12.dp),
                        color = Color.White,
                        textAlign = TextAlign.Start,
                        style = androidx.compose.material3.MaterialTheme.typography.titleMedium
                    )
                }
            }
        }
    }
}

private data class StoryItem(val id: String, val imageUrl: String, val title: String)