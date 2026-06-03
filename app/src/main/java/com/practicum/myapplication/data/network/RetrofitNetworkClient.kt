package com.practicum.myapplication.data.network

import com.practicum.myapplication.creator.Storage
import com.practicum.myapplication.data.dto.TracksSearchRequest
import com.practicum.myapplication.data.dto.TracksSearchResponse
import com.practicum.myapplication.domain.NetworkClient

class RetrofitNetworkClient(
    private val storage: Storage
) : NetworkClient {

    override fun doRequest(request: Any): TracksSearchResponse {

        if (request !is TracksSearchRequest) {
            return TracksSearchResponse(emptyList()).apply {
                resultCode = 400
            }
        }

        val tracks = storage.search(request.expression)

        return TracksSearchResponse(tracks).apply {
            resultCode = 200
        }
    }
}
