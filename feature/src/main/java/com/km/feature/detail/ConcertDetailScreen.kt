package com.km.feature.detail

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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.km.feature.home.Concert
import com.km.feature.home.ConcertState
import com.km.feature.ui.component.ConcertStateBadge
import com.km.feature.ui.component.GenreBadge
import com.km.feature.ui.theme.BackgroundDark
import com.km.feature.ui.theme.CardDark
import com.km.feature.ui.theme.ConJamTheme
import com.km.feature.ui.theme.Primary
import com.km.feature.ui.theme.SurfaceVariantDark
import com.km.feature.ui.theme.TextSecondary

@Composable
fun ConcertDetailRoute(
    concertId: String,
    onBackClick: () -> Unit,
) {
    // In a real app, we'd fetch the concert by ID from a repository
    val concert = getDummyConcert(concertId)

    ConcertDetailScreen(
        concert = concert,
        onBackClick = onBackClick,
    )
}

@Composable
private fun ConcertDetailScreen(
    concert: Concert,
    onBackClick: () -> Unit = {},
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundDark)
            .verticalScroll(rememberScrollState()),
    ) {
        // Poster Area with overlay toolbar
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(400.dp)
                .background(Primary.copy(alpha = 0.1f)),
        ) {
            // Poster placeholder text
            Text(
                text = concert.genre,
                style = MaterialTheme.typography.headlineLarge,
                color = Primary.copy(alpha = 0.3f),
                modifier = Modifier.align(Alignment.Center),
            )

            // Top bar overlay
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp, vertical = 48.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                IconButton(
                    onClick = onBackClick,
                    modifier = Modifier
                        .clip(CircleShape)
                        .background(BackgroundDark.copy(alpha = 0.5f)),
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "뒤로",
                        tint = MaterialTheme.colorScheme.onBackground,
                    )
                }
                IconButton(
                    onClick = { },
                    modifier = Modifier
                        .clip(CircleShape)
                        .background(BackgroundDark.copy(alpha = 0.5f)),
                ) {
                    Icon(
                        imageVector = Icons.Default.FavoriteBorder,
                        contentDescription = "북마크",
                        tint = MaterialTheme.colorScheme.onBackground,
                    )
                }
            }
        }

        // Concert Info
        Column(
            modifier = Modifier.padding(20.dp),
        ) {
            // Title
            Text(
                text = concert.title,
                style = MaterialTheme.typography.headlineSmall.copy(
                    fontWeight = FontWeight.Bold,
                ),
                color = MaterialTheme.colorScheme.onBackground,
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Badges
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                GenreBadge(genre = concert.genre)
                ConcertStateBadge(state = concert.state)
                if (concert.isOpenRun) {
                    GenreBadge(genre = "오픈런")
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Info items
            InfoItem(
                icon = Icons.Default.DateRange,
                label = "공연 기간",
                value = "${concert.startDate} ~ ${concert.endDate}",
            )
            Spacer(modifier = Modifier.height(14.dp))
            InfoItem(
                icon = Icons.Default.LocationOn,
                label = "공연장",
                value = concert.venue,
            )
            Spacer(modifier = Modifier.height(14.dp))
            InfoItem(
                icon = Icons.Default.Star,
                label = "장르",
                value = concert.genre,
            )
            Spacer(modifier = Modifier.height(14.dp))
            InfoItem(
                icon = Icons.Default.PlayArrow,
                label = "공연 시간",
                value = "약 120분 (인터미션 포함)",
            )
        }

        HorizontalDivider(
            color = SurfaceVariantDark,
            modifier = Modifier.padding(horizontal = 20.dp),
        )

        // Additional Info Section
        Column(
            modifier = Modifier.padding(20.dp),
        ) {
            Text(
                text = "가격 정보",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onBackground,
            )
            Spacer(modifier = Modifier.height(10.dp))

            PriceRow("VIP석", "176,000원")
            PriceRow("R석", "154,000원")
            PriceRow("S석", "132,000원")
            PriceRow("A석", "110,000원")
        }

        HorizontalDivider(
            color = SurfaceVariantDark,
            modifier = Modifier.padding(horizontal = 20.dp),
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Booking Button
        Button(
            onClick = { },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .height(52.dp),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Primary),
        ) {
            Text(
                text = "예매하러 가기",
                style = MaterialTheme.typography.titleMedium,
            )
        }

        Spacer(modifier = Modifier.height(32.dp))
    }
}

@Composable
private fun InfoItem(
    icon: ImageVector,
    label: String,
    value: String,
) {
    Row(
        verticalAlignment = Alignment.Top,
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = Primary,
            modifier = Modifier.size(20.dp),
        )
        Spacer(modifier = Modifier.width(12.dp))
        Column {
            Text(
                text = label,
                style = MaterialTheme.typography.labelMedium,
                color = TextSecondary,
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = value,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onBackground,
            )
        }
    }
}

@Composable
private fun PriceRow(
    seatType: String,
    price: String,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Text(
            text = seatType,
            style = MaterialTheme.typography.bodyMedium,
            color = TextSecondary,
        )
        Text(
            text = price,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onBackground,
        )
    }
}

private fun getDummyConcert(concertId: String): Concert {
    val concerts = mapOf(
        "PF001" to Concert("PF001", "2025 IU Concert 'The Winning'", "2025.04.12", "2025.04.13", "KSPO DOME", genre = "콘서트", state = ConcertState.UPCOMING),
        "PF002" to Concert("PF002", "SEVENTEEN WORLD TOUR [FOLLOW] AGAIN", "2025.05.01", "2025.05.04", "고척스카이돔", genre = "콘서트", state = ConcertState.UPCOMING),
        "PF003" to Concert("PF003", "뮤지컬 <오페라의 유령>", "2025.03.01", "2025.06.30", "블루스퀘어 신한카드홀", genre = "뮤지컬", state = ConcertState.ONGOING, isOpenRun = true),
    )
    return concerts[concertId] ?: Concert("PF001", "2025 IU Concert 'The Winning'", "2025.04.12", "2025.04.13", "KSPO DOME", genre = "콘서트", state = ConcertState.UPCOMING)
}

@Preview(showBackground = true, backgroundColor = 0xFF121212)
@Composable
private fun ConcertDetailScreenPreview() {
    ConJamTheme {
        ConcertDetailScreen(
            concert = Concert(
                id = "PF003",
                title = "뮤지컬 <오페라의 유령>",
                startDate = "2025.03.01",
                endDate = "2025.06.30",
                venue = "블루스퀘어 신한카드홀",
                genre = "뮤지컬",
                state = ConcertState.ONGOING,
                isOpenRun = true,
            ),
        )
    }
}
