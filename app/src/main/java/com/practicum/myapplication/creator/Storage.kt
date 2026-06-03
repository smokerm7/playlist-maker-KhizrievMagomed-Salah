package com.practicum.myapplication.creator

import com.practicum.myapplication.data.dto.TrackDto

class Storage {

    private val listTracks = listOf(
        TrackDto(
            trackName = "По барам",
            artistName = "ANNA ASTI",
            trackTimeMillis = 220000
        ),
        TrackDto(
            trackName = "Медляк",
            artistName = "JONY",
            trackTimeMillis = 185000
        ),
        TrackDto(
            trackName = "Птичка",
            artistName = "MiyaGi & Эндшпиль",
            trackTimeMillis = 258000
        ),
        TrackDto(
            trackName = "Кукла",
            artistName = "XOLIDAYBOY",
            trackTimeMillis = 194000
        ),
        TrackDto(
            trackName = "Никаких больше вечеринок",
            artistName = "Cream Soda",
            trackTimeMillis = 242000
        ),
        TrackDto(
            trackName = "Зари",
            artistName = "MONA",
            trackTimeMillis = 201000
        ),
        TrackDto(
            trackName = "Юность",
            artistName = "Dabro",
            trackTimeMillis = 221000
        ),
        TrackDto(
            trackName = "Седая ночь",
            artistName = "Моя Мишель",
            trackTimeMillis = 214000
        ),
        TrackDto(
            trackName = "Венера-Юпитер",
            artistName = "Ваня Дмитриенко",
            trackTimeMillis = 178000
        ),
        TrackDto(
            trackName = "Дежавю",
            artistName = "Mary Gu",
            trackTimeMillis = 205000
        )
    )

    fun search(request: String): List<TrackDto> {
        val query = request.trim().lowercase()

        if (query.isEmpty()) {
            return emptyList()
        }

        return listTracks.filter { track ->
            track.trackName.lowercase().contains(query) ||
            track.artistName.lowercase().contains(query)
        }
    }
}
