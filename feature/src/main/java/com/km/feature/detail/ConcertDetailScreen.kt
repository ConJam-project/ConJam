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
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.km.feature.home.Concert
import com.km.feature.home.ConcertState
import com.km.feature.ui.component.ConcertStateBadge
import com.km.feature.ui.component.GenreBadge
import com.km.feature.ui.theme.BackgroundDark
import com.km.feature.ui.theme.CardDark
import com.km.feature.ui.theme.ConJamTheme
import com.km.feature.ui.theme.DividerColor
import com.km.feature.ui.theme.Primary
import com.km.feature.ui.theme.SurfaceVariantDark
import com.km.feature.ui.theme.TextSecondary
import com.km.feature.ui.theme.TextTertiary

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
            .background(MaterialTheme.colorScheme.background)
            .verticalScroll(rememberScrollState()),
    ) {
        // Poster Area with overlay toolbar
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(400.dp)
                .background(Primary.copy(alpha = 0.08f)),
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
                        .background(MaterialTheme.colorScheme.surface.copy(alpha = 0.85f)),
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
                        .background(MaterialTheme.colorScheme.surface.copy(alpha = 0.85f)),
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

        SectionDivider()

        // Price Info Section
        Column(
            modifier = Modifier.padding(20.dp),
        ) {
            SectionTitle("가격 정보")
            Spacer(modifier = Modifier.height(10.dp))

            PriceRow("VIP석", "176,000원")
            PriceRow("R석", "154,000원")
            PriceRow("S석", "132,000원")
            PriceRow("A석", "110,000원")
        }

        SectionDivider()

        // Alarm Section
        Column(
            modifier = Modifier.padding(20.dp),
        ) {
            SectionTitle("알림 설정")
            Spacer(modifier = Modifier.height(12.dp))

            OutlinedButton(
                onClick = { },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.outlinedButtonColors(
                    contentColor = Primary,
                ),
            ) {
                Icon(
                    imageVector = Icons.Default.Notifications,
                    contentDescription = null,
                    modifier = Modifier.size(18.dp),
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text("알람 수신하기")
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "공연 일정, 티켓 오픈 등의 알림을 받을 수 있습니다",
                style = MaterialTheme.typography.bodySmall,
                color = TextTertiary,
            )
        }

        SectionDivider()

        // Venue Access & Accommodation Section
        Column(
            modifier = Modifier.padding(20.dp),
        ) {
            SectionTitle("공연장 교통 & 숙소")
            Spacer(modifier = Modifier.height(12.dp))

            // Map placeholder
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(160.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(SurfaceVariantDark),
                contentAlignment = Alignment.Center,
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(
                        imageVector = Icons.Default.LocationOn,
                        contentDescription = null,
                        tint = Primary,
                        modifier = Modifier.size(32.dp),
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = concert.venue,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onBackground,
                    )
                    Text(
                        text = "지도 연동 예정",
                        style = MaterialTheme.typography.bodySmall,
                        color = TextTertiary,
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Transport methods
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                QuickInfoCard(
                    title = "교통편",
                    subtitle = "지하철 2호선\n잠실역 8번 출구",
                    modifier = Modifier.weight(1f),
                )
                QuickInfoCard(
                    title = "근처 숙소",
                    subtitle = "롯데호텔, 파크하얏트\n외 12개",
                    modifier = Modifier.weight(1f),
                )
                QuickInfoCard(
                    title = "주변 맛집",
                    subtitle = "공연 전후\n추천 식당 8곳",
                    modifier = Modifier.weight(1f),
                )
            }
        }

        SectionDivider()

        // Seat View Section
        Column(
            modifier = Modifier.padding(20.dp),
        ) {
            SectionTitle("좌석 시야 확인")
            Spacer(modifier = Modifier.height(12.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(SurfaceVariantDark),
                contentAlignment = Alignment.Center,
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = "STAGE",
                        style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                        color = Primary,
                    )
                    Spacer(modifier = Modifier.height(12.dp))

                    // Simple seat zone representation
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly,
                    ) {
                        SeatZone(label = "VIP", color = Primary)
                        SeatZone(label = "R석", color = Primary.copy(alpha = 0.7f))
                        SeatZone(label = "S석", color = Primary.copy(alpha = 0.5f))
                        SeatZone(label = "A석", color = Primary.copy(alpha = 0.3f))
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        text = "좌석을 선택하면 실제 시야를 확인할 수 있습니다",
                        style = MaterialTheme.typography.bodySmall,
                        color = TextTertiary,
                    )
                }
            }
        }

        SectionDivider()

        // Artist Info & Setlist Section
        Column(
            modifier = Modifier.padding(20.dp),
        ) {
            SectionTitle("아티스트 & 셋리스트")
            Spacer(modifier = Modifier.height(12.dp))

            // Artist card
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp))
                    .background(CardDark)
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Box(
                    modifier = Modifier
                        .size(56.dp)
                        .clip(CircleShape)
                        .background(Primary.copy(alpha = 0.12f)),
                    contentAlignment = Alignment.Center,
                ) {
                    Text(
                        text = concert.title.take(1),
                        style = MaterialTheme.typography.titleLarge,
                        color = Primary,
                    )
                }
                Spacer(modifier = Modifier.width(14.dp))
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = "아티스트",
                        style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold),
                        color = MaterialTheme.colorScheme.onBackground,
                    )
                    Text(
                        text = "팔로워 120만",
                        style = MaterialTheme.typography.bodySmall,
                        color = TextSecondary,
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Famous songs
            Text(
                text = "유명 노래",
                style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold),
                color = MaterialTheme.colorScheme.onBackground,
            )
            Spacer(modifier = Modifier.height(8.dp))

            val songs = listOf(
                "1. 대표곡 A - 2023",
                "2. 대표곡 B - 2022",
                "3. 대표곡 C - 2021",
                "4. 대표곡 D - 2020",
                "5. 대표곡 E - 2019",
            )
            songs.forEach { song ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                ) {
                    Icon(
                        imageVector = Icons.Default.PlayArrow,
                        contentDescription = null,
                        tint = Primary,
                        modifier = Modifier.size(16.dp),
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = song,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onBackground,
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Previous setlist
            Text(
                text = "이전 공연 셋리스트",
                style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold),
                color = MaterialTheme.colorScheme.onBackground,
            )
            Spacer(modifier = Modifier.height(8.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp))
                    .background(SurfaceVariantDark)
                    .padding(16.dp),
            ) {
                Column {
                    Text(
                        text = "2024 투어 셋리스트 (22곡)",
                        style = MaterialTheme.typography.labelLarge,
                        color = Primary,
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "Opening → 대표곡 A → 대표곡 B → ... → Encore: 대표곡 E",
                        style = MaterialTheme.typography.bodySmall,
                        color = TextSecondary,
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "전체 셋리스트 보기 >",
                        style = MaterialTheme.typography.labelMedium,
                        color = Primary,
                        modifier = Modifier.clickable { },
                    )
                }
            }
        }

        SectionDivider()

        // Community Section (Preview)
        Column(
            modifier = Modifier.padding(20.dp),
        ) {
            SectionTitle("같이 가요 (커뮤니티)")
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "같은 공연을 가는 사람들과 소통해보세요",
                style = MaterialTheme.typography.bodySmall,
                color = TextTertiary,
            )
            Spacer(modifier = Modifier.height(12.dp))

            // Sample community posts
            CommunityPost(
                author = "음악좋아",
                content = "S석 2연석 양도합니다 (정가)",
                tag = "양도",
                timeAgo = "30분 전",
            )
            Spacer(modifier = Modifier.height(8.dp))
            CommunityPost(
                author = "콘서트매니아",
                content = "같이 갈 분 구합니다! 서울에서 출발해요",
                tag = "같이가요",
                timeAgo = "1시간 전",
            )
            Spacer(modifier = Modifier.height(8.dp))
            CommunityPost(
                author = "맛집탐험가",
                content = "공연 끝나고 근처에서 같이 밥 먹어요~",
                tag = "놀아요",
                timeAgo = "2시간 전",
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Booking Button
        Button(
            onClick = { },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .height(52.dp),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Primary,
                contentColor = MaterialTheme.colorScheme.surface,
            ),
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
private fun SectionDivider() {
    HorizontalDivider(
        color = DividerColor,
        modifier = Modifier.padding(horizontal = 20.dp),
    )
}

@Composable
private fun SectionTitle(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
        color = MaterialTheme.colorScheme.onBackground,
    )
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

@Composable
private fun QuickInfoCard(
    title: String,
    subtitle: String,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(12.dp))
            .background(CardDark)
            .padding(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.Bold),
            color = Primary,
        )
        Spacer(modifier = Modifier.height(6.dp))
        Text(
            text = subtitle,
            style = MaterialTheme.typography.labelSmall,
            color = TextSecondary,
            textAlign = TextAlign.Center,
        )
    }
}

@Composable
private fun SeatZone(
    label: String,
    color: Color,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Box(
            modifier = Modifier
                .size(width = 50.dp, height = 30.dp)
                .clip(RoundedCornerShape(6.dp))
                .background(color.copy(alpha = 0.2f)),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                text = label,
                style = MaterialTheme.typography.labelSmall,
                color = color,
            )
        }
    }
}

@Composable
private fun CommunityPost(
    author: String,
    content: String,
    tag: String,
    timeAgo: String,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(CardDark)
            .clickable { }
            .padding(14.dp),
        verticalAlignment = Alignment.Top,
    ) {
        // Avatar
        Box(
            modifier = Modifier
                .size(36.dp)
                .clip(CircleShape)
                .background(Primary.copy(alpha = 0.12f)),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                text = author.take(1),
                style = MaterialTheme.typography.labelLarge,
                color = Primary,
            )
        }
        Spacer(modifier = Modifier.width(12.dp))
        Column(modifier = Modifier.weight(1f)) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(6.dp),
            ) {
                Text(
                    text = author,
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.onBackground,
                )
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(4.dp))
                        .background(Primary.copy(alpha = 0.12f))
                        .padding(horizontal = 6.dp, vertical = 2.dp),
                ) {
                    Text(
                        text = tag,
                        style = MaterialTheme.typography.labelSmall,
                        color = Primary,
                    )
                }
            }
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = content,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onBackground,
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = timeAgo,
                style = MaterialTheme.typography.labelSmall,
                color = TextTertiary,
            )
        }
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

@Preview(showBackground = true, backgroundColor = 0xFFF5F9FF)
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
