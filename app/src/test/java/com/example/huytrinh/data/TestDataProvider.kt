package com.example.huytrinh.data

import com.example.huytrinh.songs.data.model.ApiAlbumModel
import com.example.huytrinh.songs.domain.entity.Album

val TESTING_API_ALBUM = ApiAlbumModel("1", "Avatar Country", 5, "testUrl")
val TESTING_DOMAIN_ALBUM = Album("1", "Avatar Country", 5, "testUrl")

val TESTING_API_ALBUMS = listOf<ApiAlbumModel>(
        ApiAlbumModel("1", "Avatar Country", 5, "testUrl")
)

val TESTING_DOMAIN_ALBUMS = listOf<Album>(
        Album("1", "Avatar Country", 5, "testUrl")
)

val TEST_USERNAME = "username"
val TEST_PASSWORD = "pass"
val LOGIN_STATE = true


