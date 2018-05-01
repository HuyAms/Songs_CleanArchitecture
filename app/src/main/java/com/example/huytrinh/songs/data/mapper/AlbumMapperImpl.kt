package com.example.huytrinh.songs.data.mapper

import com.example.huytrinh.songs.data.model.ApiAlbumModel
import com.example.huytrinh.songs.domain.entity.Album

class AlbumMapperImpl: AlbumMapper {
    override fun dataToDomain(apiAlbumModel: ApiAlbumModel): Album {
        return Album(apiAlbumModel.id, apiAlbumModel.name, apiAlbumModel.numberOfSongs, apiAlbumModel.albumImgUrl)
    }
}