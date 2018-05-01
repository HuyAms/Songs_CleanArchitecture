package com.example.huytrinh.songs.domain.usecase.album

import com.example.huytrinh.songs.domain.entity.Album
import com.example.huytrinh.songs.domain.repository.AlbumRepository
import com.example.huytrinh.songs.domain.usecase.SingleUseCase
import io.reactivex.Single

class LoadAlbumsUseCase(private val albumRepository: AlbumRepository): SingleUseCase<List<Album>> {

    override fun execute(): Single<List<Album>> {
        return albumRepository.loadAlbums()
    }

}

