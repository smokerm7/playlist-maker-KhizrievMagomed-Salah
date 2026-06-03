package com.practicum.myapplication.ui.search

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.practicum.myapplication.R
import com.practicum.myapplication.ui.item.TrackListItem

private val MainTextColor = Color(0xFF111827)
private val SecondaryTextColor = Color(0xFF6B7280)
private val PlaceholderColor = Color(0xFF9CA3AF)
private val SearchFieldColor = Color(0xFFF3F4F6)

@Composable
fun SearchScreen(
    modifier: Modifier,
    viewModel: SearchViewModel,
    onNavigateBack: () -> Unit
) {
    val screenState by viewModel.searchScreenState.collectAsState()
    var query by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(horizontal = 20.dp, vertical = 16.dp)
    ) {

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {

            Icon(
                painter = painterResource(R.drawable.back),
                contentDescription = "Назад",
                modifier = Modifier
                    .size(18.dp)
                    .clickable { onNavigateBack() }
            )

            Spacer(modifier = Modifier.width(20.dp))

            Text(
                text = "Поиск",
                fontSize = 24.sp,
                fontWeight = FontWeight.SemiBold,
                color = MainTextColor
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        TextField(
            value = query,
            onValueChange = {
                query = it

                if (it.isNotBlank()) {
                    viewModel.search(it)
                }
            },
            placeholder = {
                Text(
                    text = "Поиск",
                    color = PlaceholderColor
                )
            },
            singleLine = true,

            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = null,
                    tint = PlaceholderColor
                )
            },

            trailingIcon = {
                if (query.isNotEmpty()) {
                    Icon(
                        imageVector = Icons.Default.Clear,
                        contentDescription = "Очистить",
                        tint = PlaceholderColor,
                        modifier = Modifier.clickable {
                            query = ""
                        }
                    )
                }
            },

            colors = TextFieldDefaults.colors(
                focusedContainerColor = SearchFieldColor,
                unfocusedContainerColor = SearchFieldColor,
                disabledContainerColor = SearchFieldColor,
                cursorColor = MainTextColor,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            ),

            shape = RoundedCornerShape(12.dp),

            modifier = Modifier
                .fillMaxWidth()
                .height(58.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))

        when (screenState) {

            SearchState.Initial -> {
                Box(
                    modifier = modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Начните вводить название трека",
                        color = SecondaryTextColor
                    )
                }
            }

            SearchState.Searching -> {
                Box(
                    modifier = modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }

            is SearchState.Success -> {

                val tracks = (screenState as SearchState.Success).list

                if (tracks.isEmpty()) {

                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {

                            Image(
                                painter = painterResource(R.drawable.empty_search),
                                contentDescription = null,
                                modifier = Modifier.size(140.dp)
                            )

                            Spacer(modifier = Modifier.height(16.dp))

                            Text(
                                text = stringResource(R.string.title_empty),
                                color = SecondaryTextColor,
                                fontSize = 16.sp
                            )
                        }
                    }

                } else {

                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(vertical = 8.dp)
                    ) {

                        items(tracks) { track ->
                            TrackListItem(track)
                            HorizontalDivider(thickness = 0.5.dp)
                        }
                    }
                }
            }

            is SearchState.Fail -> {

                val error = (screenState as SearchState.Fail).error

                Box(
                    modifier = modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = error,
                        color = Color.Red
                    )
                }
            }
        }
    }
}
