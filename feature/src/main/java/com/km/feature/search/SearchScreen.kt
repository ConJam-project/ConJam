package com.km.feature.search

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.km.feature.ui.component.ConcertCard
import com.km.feature.ui.theme.ConJamTheme
import com.km.feature.ui.theme.Primary
import com.km.feature.ui.theme.SurfaceVariantDark
import com.km.feature.ui.theme.TextSecondary
import com.km.feature.ui.theme.TextTertiary

@Composable
fun SearchRoute(
    padding: PaddingValues,
    viewModel: SearchViewModel = viewModel(),
    onConcertClick: (String) -> Unit = {},
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    SearchScreen(
        modifier = Modifier
            .padding(padding)
            .fillMaxSize(),
        state = state,
        onQueryChange = viewModel::onQueryChange,
        onGenreSelect = viewModel::onGenreSelect,
        onRegionSelect = viewModel::onRegionSelect,
        onStatusSelect = viewModel::onStatusSelect,
        onSortSelect = viewModel::onSortSelect,
        onBookmarkToggle = viewModel::onBookmarkToggle,
        onRegionGroupSelect = viewModel::onRegionGroupSelect,
        onConcertClick = onConcertClick,
    )
}

@Composable
private fun SearchScreen(
    modifier: Modifier = Modifier,
    state: SearchState,
    onQueryChange: (String) -> Unit = {},
    onGenreSelect: (String) -> Unit = {},
    onRegionSelect: (String) -> Unit = {},
    onStatusSelect: (String) -> Unit = {},
    onSortSelect: (SearchSort) -> Unit = {},
    onBookmarkToggle: () -> Unit = {},
    onRegionGroupSelect: (String) -> Unit = {},
    onConcertClick: (String) -> Unit = {},
) {
    Column(
        modifier = modifier
            .background(MaterialTheme.colorScheme.background),
    ) {
        // Search Bar
        SearchBar(
            query = state.query,
            onQueryChange = onQueryChange,
            modifier = Modifier.padding(horizontal = 20.dp, vertical = 12.dp),
        )

        // Genre Filter Chips
        GenreFilterRow(
            genres = SearchViewModel.GENRES,
            selectedGenre = state.selectedGenre,
            onGenreSelect = onGenreSelect,
        )

        Spacer(modifier = Modifier.height(10.dp))

        // Sort Filter Row
        SortFilterRow(
            options = SearchViewModel.SORTS,
            selected = state.selectedSort,
            onSelect = onSortSelect,
        )

        Spacer(modifier = Modifier.height(10.dp))

        // Interest & Region Group Filter Row
        InterestRegionFilterRow(
            onlyBookmarked = state.onlyBookmarked,
            regionGroups = SearchViewModel.REGION_GROUPS,
            selectedRegionGroup = state.selectedRegionGroup,
            onBookmarkToggle = onBookmarkToggle,
            onRegionGroupSelect = onRegionGroupSelect,
        )

        Spacer(modifier = Modifier.height(10.dp))

        // Dropdown Filters
        Row(
            modifier = Modifier.padding(horizontal = 20.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            FilterDropdown(
                label = "지역",
                options = SearchViewModel.REGIONS,
                selected = state.selectedRegion,
                onSelect = onRegionSelect,
            )
            FilterDropdown(
                label = "공연상태",
                options = SearchViewModel.STATUSES,
                selected = state.selectedStatus,
                onSelect = onStatusSelect,
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Result count
        Text(
            text = "검색결과 ${state.results.size}건",
            style = MaterialTheme.typography.bodySmall,
            color = TextSecondary,
            modifier = Modifier.padding(horizontal = 20.dp),
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Results
        LazyColumn(
            contentPadding = PaddingValues(horizontal = 20.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            items(state.results, key = { it.id }) { concert ->
                ConcertCard(
                    concert = concert,
                    onClick = { onConcertClick(concert.id) },
                )
            }
            item { Spacer(modifier = Modifier.height(16.dp)) }
        }
    }
}

@Composable
private fun SearchBar(
    query: String,
    onQueryChange: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(SurfaceVariantDark)
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            imageVector = Icons.Default.Search,
            contentDescription = "검색",
            tint = TextTertiary,
            modifier = Modifier.size(20.dp),
        )
        Spacer(modifier = Modifier.width(10.dp))
        Box(modifier = Modifier.weight(1f)) {
            if (query.isEmpty()) {
                Text(
                    text = "공연명, 아티스트 검색",
                    style = MaterialTheme.typography.bodyMedium,
                    color = TextTertiary,
                )
            }
            BasicTextField(
                value = query,
                onValueChange = onQueryChange,
                textStyle = MaterialTheme.typography.bodyMedium.copy(
                    color = MaterialTheme.colorScheme.onBackground,
                ),
                cursorBrush = SolidColor(Primary),
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
            )
        }
    }
}

@Composable
private fun GenreFilterRow(
    genres: List<String>,
    selectedGenre: String,
    onGenreSelect: (String) -> Unit,
) {
    Row(
        modifier = Modifier
            .horizontalScroll(rememberScrollState())
            .padding(horizontal = 20.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        genres.forEach { genre ->
            FilterChip(
                label = genre,
                selected = genre == selectedGenre,
                onClick = { onGenreSelect(genre) },
            )
        }
    }
}

@Composable
private fun SortFilterRow(
    options: List<SearchSort>,
    selected: SearchSort,
    onSelect: (SearchSort) -> Unit,
) {
    Row(
        modifier = Modifier
            .horizontalScroll(rememberScrollState())
            .padding(horizontal = 20.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        options.forEach { option ->
            FilterChip(
                label = option.label,
                selected = option == selected,
                onClick = { onSelect(option) },
            )
        }
    }
}

@Composable
private fun InterestRegionFilterRow(
    onlyBookmarked: Boolean,
    regionGroups: List<String>,
    selectedRegionGroup: String,
    onBookmarkToggle: () -> Unit,
    onRegionGroupSelect: (String) -> Unit,
) {
    Row(
        modifier = Modifier
            .horizontalScroll(rememberScrollState())
            .padding(horizontal = 20.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        FilterChip(
            label = "관심 목록",
            selected = onlyBookmarked,
            onClick = onBookmarkToggle,
        )
        regionGroups.forEach { group ->
            FilterChip(
                label = group,
                selected = group == selectedRegionGroup,
                onClick = { onRegionGroupSelect(group) },
            )
        }
    }
}

@Composable
private fun FilterChip(
    label: String,
    selected: Boolean,
    onClick: () -> Unit,
) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(20.dp))
            .then(
                if (selected) {
                    Modifier.background(Primary)
                } else {
                    Modifier.border(1.dp, TextTertiary, RoundedCornerShape(20.dp))
                }
            )
            .clickable { onClick() }
            .padding(horizontal = 14.dp, vertical = 8.dp),
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.labelLarge,
            color = if (selected) {
                MaterialTheme.colorScheme.onPrimary
            } else {
                TextSecondary
            },
        )
    }
}

@Composable
private fun FilterDropdown(
    label: String,
    options: List<String>,
    selected: String,
    onSelect: (String) -> Unit,
) {
    var expanded by remember { mutableStateOf(false) }

    Box {
        Row(
            modifier = Modifier
                .clip(RoundedCornerShape(8.dp))
                .border(1.dp, TextTertiary, RoundedCornerShape(8.dp))
                .clickable { expanded = true }
                .padding(horizontal = 12.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = "$label: $selected",
                style = MaterialTheme.typography.bodySmall,
                color = TextSecondary,
            )
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
        ) {
            options.forEach { option ->
                DropdownMenuItem(
                    text = {
                        Text(
                            text = option,
                            style = MaterialTheme.typography.bodySmall,
                        )
                    },
                    onClick = {
                        onSelect(option)
                        expanded = false
                    },
                )
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFF5F9FF)
@Composable
private fun SearchScreenPreview() {
    ConJamTheme {
        SearchScreen(state = SearchState())
    }
}
