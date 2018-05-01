package com.example.huytrinh.songs.domain.repository

import com.example.huytrinh.songs.domain.entity.Album
import io.reactivex.Single

interface AlbumRepository {

    fun loadAlbums(): Single<List<Album>>

}