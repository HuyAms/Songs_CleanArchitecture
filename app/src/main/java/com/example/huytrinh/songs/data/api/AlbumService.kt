package com.example.huytrinh.songs.data.api

import com.example.huytrinh.songs.data.model.ApiAlbumModel
import io.reactivex.Single
import retrofit2.http.GET

interface AlbumService {

    @GET("albums")
    fun loadAlbums(): Single<List<ApiAlbumModel>>

}