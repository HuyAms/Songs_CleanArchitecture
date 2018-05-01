package com.example.huytrinh.data.mapper

import com.example.huytrinh.data.TESTING_API_ALBUM
import com.example.huytrinh.songs.data.mapper.AlbumMapper
import com.example.huytrinh.songs.data.mapper.AlbumMapperImpl
import junit.framework.Assert
import org.junit.Before
import org.junit.Test

class AlbumMapperTest {
    private lateinit var albumMapper: AlbumMapper

    @Before
    fun setup() {
        albumMapper = AlbumMapperImpl()
    }

    @Test
    fun shouldMapApiAlbumModelToDomainModel() {
        val dataTestAlbum = TESTING_API_ALBUM
        val domainTestAlbum = albumMapper.dataToDomain(dataTestAlbum)
        Assert.assertEquals(domainTestAlbum.id, dataTestAlbum.id)
        Assert.assertEquals(domainTestAlbum.name, dataTestAlbum.name)
        Assert.assertEquals(domainTestAlbum.albumImgUrl, dataTestAlbum.albumImgUrl)
        Assert.assertEquals(domainTestAlbum.numberOfSongs, dataTestAlbum.numberOfSongs)
    }
}