package com.km.feature.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.km.feature.home.Concert
import com.km.feature.home.ConcertState
import com.km.feature.ui.theme.ConJamTheme
import com.km.feature.ui.theme.Primary
import com.km.feature.ui.theme.TextSecondary

@Composable
fun FeaturedBanner(
    concerts: List<Concert>,
    modifier: Modifier = Modifier,
    onConcertClick: (Concert) -> Unit = {},
) {
    if (concerts.isEmpty()) return

    val pagerState = rememberPagerState(pageCount = { concerts.size })

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp),
    ) {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),
        ) { page ->
            val concert = concerts[page]
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 4.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(Primary.copy(alpha = 0.12f))
                    .clickable { onConcertClick(concert) },
            ) {
                // Content overlay at bottom
                Column(
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .fillMaxWidth()
                        .background(
                            brush = Brush.verticalGradient(
                                colors = listOf(
                                    Color.Transparent,
                                    Color.Black.copy(alpha = 0.6f),
                                ),
                            ),
                        )
                        .padding(16.dp),
                ) {
                    GenreBadge(genre = concert.genre)
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = concert.title,
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onBackground,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = "${concert.startDate} | ${concert.venue}",
                        style = MaterialTheme.typography.bodySmall,
                        color = TextSecondary,
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        // Page indicators
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
        ) {
            repeat(concerts.size) { index ->
                Box(
                    modifier = Modifier
                        .padding(horizontal = 3.dp)
                        .size(if (pagerState.currentPage == index) 8.dp else 6.dp)
                        .clip(CircleShape)
                        .background(
                            if (pagerState.currentPage == index) Primary
                            else Primary.copy(alpha = 0.3f),
                        ),
                )
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFF5F9FF)
@Composable
private fun FeaturedBannerPreview() {
    ConJamTheme {
        FeaturedBanner(
            concerts = listOf(
                Concert(
                    id = "1",
                    title = "2025 IU Concert 'The Winning'",
                    startDate = "2025.04.12",
                    endDate = "2025.04.13",
                    venue = "KSPO DOME",
                    genre = "콘서트",
                    state = ConcertState.UPCOMING,
                ),
            ),
        )
    }
}
