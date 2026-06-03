package com.practicum.myapplication.domain

import com.practicum.myapplication.creator.Storage
import com.practicum.myapplication.data.network.LocalNetworkClient
import com.practicum.myapplication.data.network.TracksRepositoryImpl

object Creator {

    fun getTracksRepository(): TracksRepository {
        val storage = Storage()
        val networkClient = LocalNetworkClient(storage)

        return TracksRepositoryImpl(networkClient)
    }
}
