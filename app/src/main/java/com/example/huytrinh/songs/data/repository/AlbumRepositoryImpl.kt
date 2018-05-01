package com.example.huytrinh.songs.data.repository

import com.example.huytrinh.songs.data.api.AlbumService
import com.example.huytrinh.songs.data.mapper.AlbumMapper
import com.example.huytrinh.songs.domain.entity.Album
import com.example.huytrinh.songs.domain.repository.AlbumRepository
import io.reactivex.Flowable
import io.reactivex.Single

class AlbumRepositoryImpl(private val albumService: AlbumService, private val albumMapper: AlbumMapper): AlbumRepository {

    override fun loadAlbums(): Single<List<Album>> {
        return albumService.loadAlbums()
                .toFlowable()
                .flatMap { Flowable.fromIterable(it) }
                .map {
                    albumMapper.dataToDomain(it)
                }
                .toList()
    }

}