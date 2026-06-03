package com.practicum.myapplication.data.dto

data class TracksSearchResponse(
    val results: List<TrackDto>
) : BaseResponse()
