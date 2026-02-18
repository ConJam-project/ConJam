package com.km.feature.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.km.feature.home.ConcertState
import com.km.feature.ui.theme.StateCompleted
import com.km.feature.ui.theme.StateOngoing
import com.km.feature.ui.theme.StateUpcoming

@Composable
fun ConcertStateBadge(
    state: ConcertState,
    modifier: Modifier = Modifier,
) {
    val color = when (state) {
        ConcertState.UPCOMING -> StateUpcoming
        ConcertState.ONGOING -> StateOngoing
        ConcertState.COMPLETED -> StateCompleted
    }

    Row(
        modifier = modifier
            .clip(RoundedCornerShape(4.dp))
            .background(color.copy(alpha = 0.1f))
            .padding(horizontal = 8.dp, vertical = 3.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Box(
            modifier = Modifier
                .size(6.dp)
                .clip(CircleShape)
                .background(color),
        )
        Spacer(modifier = Modifier.width(4.dp))
        Text(
            text = state.label,
            style = MaterialTheme.typography.labelSmall,
            color = color,
        )
    }
}
