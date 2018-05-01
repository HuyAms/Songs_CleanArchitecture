package com.example.huytrinh.data.repository

import com.example.huytrinh.data.TESTING_API_ALBUM
import com.example.huytrinh.data.TESTING_API_ALBUMS
import com.example.huytrinh.data.TESTING_DOMAIN_ALBUM
import com.example.huytrinh.data.TESTING_DOMAIN_ALBUMS
import com.example.huytrinh.songs.data.api.AlbumService
import com.example.huytrinh.songs.data.mapper.AlbumMapper
import com.example.huytrinh.songs.data.repository.AlbumRepositoryImpl
import com.example.huytrinh.songs.domain.entity.Album
import com.example.huytrinh.songs.domain.repository.AlbumRepository
import com.nhaarman.mockito_kotlin.*
import io.reactivex.Single
import io.reactivex.observers.TestObserver
import org.junit.Before
import org.junit.Test

class AlbumRepositoryImplTest {
    private lateinit var albumMapper: AlbumMapper
    private lateinit var albumService: AlbumService

    private lateinit var albumRepositoryImpl: AlbumRepository

    private lateinit var testObserver: TestObserver<List<Album>>

    @Before
    fun setup(){
        albumMapper = mock()
        albumService = mock()
        albumRepositoryImpl = AlbumRepositoryImpl(albumService, albumMapper)
        testObserver = TestObserver()
    }

    @Test
    fun shouldReturnAlbumsIfItIsAvailable() {
        given(albumMapper.dataToDomain(any())).willReturn(TESTING_DOMAIN_ALBUM)
        given(albumService.loadAlbums()).willReturn(Single.just(TESTING_API_ALBUMS))

        albumRepositoryImpl.loadAlbums().subscribe(testObserver)

        then(albumService).should(times(1)).loadAlbums()
        then(albumMapper).should(times(1)).dataToDomain(TESTING_API_ALBUM)
        then(albumService).shouldHaveNoMoreInteractions()

        testObserver.assertComplete()
        testObserver.assertValue(TESTING_DOMAIN_ALBUMS)
    }
}