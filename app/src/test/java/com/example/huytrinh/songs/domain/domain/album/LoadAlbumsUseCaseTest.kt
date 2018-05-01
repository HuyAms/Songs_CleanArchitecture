package com.example.huytrinh.songs.domain.domain.album

import com.example.huytrinh.songs.domain.domain.TESTING_ALBUMS
import com.example.huytrinh.songs.domain.entity.Album
import com.example.huytrinh.songs.domain.repository.AlbumRepository
import com.example.huytrinh.songs.domain.usecase.album.LoadAlbumsUseCase
import com.nhaarman.mockito_kotlin.given
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.then
import com.nhaarman.mockito_kotlin.times
import io.reactivex.Single
import io.reactivex.observers.TestObserver
import org.junit.Before
import org.junit.Test

class LoadAlbumsUseCaseTest {
    private lateinit var albumRepository: AlbumRepository
    private lateinit var loadAlbumUseCase: LoadAlbumsUseCase
    private lateinit var testObserver: TestObserver<List<Album>>

    @Before
    fun setup() {
        albumRepository = mock()
        loadAlbumUseCase = LoadAlbumsUseCase(albumRepository)
        testObserver = TestObserver()
    }

    @Test
    fun shouldReturnAlbumsIfItIsAvailable() {
        given(albumRepository.loadAlbums()).willReturn(Single.just(TESTING_ALBUMS))

        loadAlbumUseCase.execute().subscribe(testObserver)


        then(albumRepository).should(times(1)).loadAlbums()
        then(albumRepository).shouldHaveNoMoreInteractions()

        testObserver.assertComplete()
        testObserver.assertValue(TESTING_ALBUMS)
    }
}