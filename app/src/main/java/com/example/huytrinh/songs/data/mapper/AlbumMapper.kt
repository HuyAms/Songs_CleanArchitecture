package com.example.huytrinh.songs.data.mapper

import com.example.huytrinh.songs.data.model.ApiAlbumModel
import com.example.huytrinh.songs.domain.entity.Album

interface AlbumMapper {

    fun dataToDomain(apiAlbumModel: ApiAlbumModel): Album
}