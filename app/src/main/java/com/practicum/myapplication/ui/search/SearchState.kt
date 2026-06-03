package com.practicum.myapplication.ui.search

import com.practicum.myapplication.domain.model.Track

sealed class SearchState {

    data object Initial : SearchState()

    data object Searching : SearchState()

    data class Success(
        val tracks: List<Track>
    ) : SearchState()

    data class Fail(
        val error: String
    ) : SearchState()
}
