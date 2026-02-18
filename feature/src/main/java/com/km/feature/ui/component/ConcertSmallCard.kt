package com.km.feature.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.km.feature.home.Concert
import com.km.feature.home.ConcertState
import com.km.feature.ui.theme.CardDark
import com.km.feature.ui.theme.ConJamTheme
import com.km.feature.ui.theme.Primary
import com.km.feature.ui.theme.TextSecondary

@Composable
fun ConcertSmallCard(
    concert: Concert,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
) {
    Column(
        modifier = modifier
            .width(140.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(CardDark)
            .clickable(onClick = onClick)
            .padding(bottom = 10.dp),
    ) {
        // Poster placeholder
        Box(
            modifier = Modifier
                .size(width = 140.dp, height = 180.dp)
                .clip(RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp))
                .background(Primary.copy(alpha = 0.12f)),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                text = concert.genre.take(2),
                style = MaterialTheme.typography.headlineSmall,
                color = Primary.copy(alpha = 0.6f),
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = concert.title,
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.onBackground,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.padding(horizontal = 10.dp),
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = concert.startDate,
            style = MaterialTheme.typography.labelSmall,
            color = TextSecondary,
            modifier = Modifier.padding(horizontal = 10.dp),
        )

        Spacer(modifier = Modifier.height(2.dp))

        Text(
            text = concert.venue,
            style = MaterialTheme.typography.labelSmall,
            color = TextSecondary,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.padding(horizontal = 10.dp),
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF121212)
@Composable
private fun ConcertSmallCardPreview() {
    ConJamTheme {
        ConcertSmallCard(
            concert = Concert(
                id = "1",
                title = "DAY6 CONCERT <FOREVER YOUNG>",
                startDate = "2025.06.21",
                endDate = "2025.06.22",
                venue = "올림픽공원 체조경기장",
                genre = "콘서트",
                state = ConcertState.UPCOMING,
            ),
        )
    }
}
