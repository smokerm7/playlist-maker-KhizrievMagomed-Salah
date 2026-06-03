package com.practicum.myapplication.ui.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.practicum.myapplication.domain.Creator
import com.practicum.myapplication.domain.TracksRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SearchViewModel(
    private val tracksRepository: TracksRepository
) : ViewModel() {

    private val _searchScreenState = MutableStateFlow<SearchState>(SearchState.Initial)
    val searchScreenState = _searchScreenState.asStateFlow()

    fun search(query: String) {
        val trimmedQuery = query.trim()

        if (trimmedQuery.isEmpty()) {
            _searchScreenState.value = SearchState.Initial
            return
        }

        viewModelScope.launch(Dispatchers.IO) {
            try {
                _searchScreenState.value = SearchState.Searching

                val tracks = tracksRepository.searchTracks(trimmedQuery)

                _searchScreenState.value = SearchState.Success(
                    tracks = tracks
                )
            } catch (exception: Exception) {
                _searchScreenState.value = SearchState.Fail(
                    error = exception.message ?: "Не удалось выполнить поиск"
                )
            }
        }
    }

    companion object {

        fun getViewModelFactory(): ViewModelProvider.Factory {
            return object : ViewModelProvider.Factory {

                @Suppress("UNCHECKED_CAST")
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return SearchViewModel(
                        tracksRepository = Creator.getTracksRepository()
                    ) as T
                }
            }
        }
    }
}
