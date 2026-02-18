package com.km.feature.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.km.feature.ui.component.ConcertCard
import com.km.feature.ui.component.ConcertSmallCard
import com.km.feature.ui.component.FeaturedBanner
import com.km.feature.ui.component.SectionHeader
import com.km.feature.ui.theme.ConJamTheme
import com.km.feature.ui.theme.Primary
import com.km.feature.ui.theme.TextSecondary

@Composable
fun HomeRoute(
    viewModel: HomeViewModel = viewModel(),
    padding: PaddingValues,
    onConcertClick: (String) -> Unit = {},
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    HomeScreen(
        modifier = Modifier
            .padding(padding)
            .fillMaxSize(),
        state = state,
        onConcertClick = onConcertClick,
    )
}

@Composable
private fun HomeScreen(
    modifier: Modifier = Modifier,
    state: HomeState,
    onConcertClick: (String) -> Unit = {},
) {
    LazyColumn(
        modifier = modifier
            .background(MaterialTheme.colorScheme.background),
    ) {
        // Top Bar
        item {
            HomeTopBar()
        }

        // Featured Banner
        item {
            Spacer(modifier = Modifier.height(8.dp))
            FeaturedBanner(
                concerts = state.featuredConcerts,
                onConcertClick = { concert -> onConcertClick(concert.id) },
            )
        }

        // Ticket Open Soon Section
        item {
            Spacer(modifier = Modifier.height(24.dp))
            SectionHeader(
                title = "티켓 오픈 임박",
                onMoreClick = { },
            )
        }

        item {
            LazyRow(
                contentPadding = PaddingValues(horizontal = 20.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
            ) {
                items(state.ticketOpenSoonConcerts, key = { it.id }) { concert ->
                    ConcertSmallCard(
                        concert = concert,
                        onClick = { onConcertClick(concert.id) },
                    )
                }
            }
        }

        // New Concerts Section
        item {
            Spacer(modifier = Modifier.height(24.dp))
            SectionHeader(
                title = "새로 등록된 공연",
                onMoreClick = { },
            )
        }

        items(state.newConcerts, key = { it.id }) { concert ->
            ConcertCard(
                concert = concert,
                modifier = Modifier.padding(horizontal = 20.dp, vertical = 6.dp),
                onClick = { onConcertClick(concert.id) },
            )
        }

        // Bottom spacing
        item {
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
private fun HomeTopBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 12.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Column {
            Text(
                text = "ConJam",
                style = MaterialTheme.typography.headlineMedium.copy(
                    fontWeight = FontWeight.Bold,
                ),
                color = Primary,
            )
            Text(
                text = "모든 공연을 한눈에",
                style = MaterialTheme.typography.bodySmall,
                color = TextSecondary,
            )
        }

        IconButton(onClick = { }) {
            Icon(
                imageVector = Icons.Outlined.Notifications,
                contentDescription = "알림",
                modifier = Modifier.size(26.dp),
                tint = MaterialTheme.colorScheme.onBackground,
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF121212)
@Composable
private fun HomeScreenPreview() {
    ConJamTheme {
        HomeScreen(
            state = HomeState(
                featuredConcerts = listOf(
                    Concert(
                        id = "1",
                        title = "2025 IU Concert",
                        startDate = "2025.04.12",
                        endDate = "2025.04.13",
                        venue = "KSPO DOME",
                        genre = "콘서트",
                    ),
                ),
                ticketOpenSoonConcerts = listOf(
                    Concert(
                        id = "2",
                        title = "DAY6 CONCERT",
                        startDate = "2025.06.21",
                        endDate = "2025.06.22",
                        venue = "체조경기장",
                        genre = "콘서트",
                    ),
                ),
                newConcerts = listOf(
                    Concert(
                        id = "3",
                        title = "뮤지컬 레미제라블",
                        startDate = "2025.09.01",
                        endDate = "2025.12.31",
                        venue = "블루스퀘어",
                        genre = "뮤지컬",
                        state = ConcertState.UPCOMING,
                    ),
                ),
            ),
        )
    }
}
