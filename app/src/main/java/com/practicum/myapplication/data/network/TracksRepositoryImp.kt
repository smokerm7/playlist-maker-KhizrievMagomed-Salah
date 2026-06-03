package com.practicum.myapplication.data.network

import com.practicum.myapplication.data.dto.TrackDto
import com.practicum.myapplication.data.dto.TracksSearchRequest
import com.practicum.myapplication.data.dto.TracksSearchResponse
import com.practicum.myapplication.domain.NetworkClient
import com.practicum.myapplication.domain.TracksRepository
import kotlinx.coroutines.delay

class TracksRepositoryImpl(
    private val networkClient: NetworkClient
) : TracksRepository {

    override suspend fun searchTracks(expression: String): List<Track> {
        val response = networkClient.doRequest(TracksSearchRequest(expression))

        delay(1000)

        return if (response.resultCode == 200) {
            response.results.map { trackDto ->
                mapTrackDtoToTrack(trackDto)
            }
        } else {
            emptyList()
        }
    }

    private fun mapTrackDtoToTrack(trackDto: TrackDto): Track {
        return Track(
            trackName = trackDto.trackName,
            artistName = trackDto.artistName,
            trackTime = formatTrackTime(trackDto.trackTimeMillis)
        )
    }

    private fun formatTrackTime(trackTimeMillis: Int): String {
        val totalSeconds = trackTimeMillis / 1000
        val minutes = totalSeconds / 60
        val seconds = totalSeconds % 60

        return "%02d:%02d".format(minutes, seconds)
    }
}
