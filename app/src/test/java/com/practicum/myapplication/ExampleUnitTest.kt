package com.practicum.myapplication

import com.practicum.myapplication.creator.Storage
import org.junit.Assert.assertTrue
import org.junit.Test

class StorageTest {

    @Test
    fun search_returns_tracks() {
        val storage = Storage()

        val result = storage.search("Miyagi")

        assertTrue(result.isNotEmpty())
    }
}
