package com.example.huytrinh.presentation

import com.example.huytrinh.songs.domain.repository.AlbumRepository
import com.example.huytrinh.songs.domain.repository.UserRepository
import com.example.huytrinh.songs.domain.usecase.album.LoadAlbumsUseCase
import com.example.huytrinh.songs.domain.usecase.user.ClearLoginDataUseCase
import com.example.huytrinh.songs.domain.usecase.user.LoginUseCase
import com.example.huytrinh.songs.presentation.di.Injector.Companion.loginUseCase
import com.example.huytrinh.songs.presentation.screen.albumscreen.AlbumContract
import com.example.huytrinh.songs.presentation.screen.albumscreen.AlbumPresenter
import com.example.huytrinh.songs.presentation.util.SchedulerProvider
import com.nhaarman.mockito_kotlin.given
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.then
import com.nhaarman.mockito_kotlin.times
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.schedulers.TestScheduler
import org.junit.Before
import org.junit.Test

class AlbumPresenterTest {
    private lateinit var alubmRepository: AlbumRepository
    private lateinit var userRepository: UserRepository
    private lateinit var loadAlbumUseCase: LoadAlbumsUseCase
    private lateinit var clearLoginDataUseCase: ClearLoginDataUseCase
    private lateinit var schedulerProvider: SchedulerProvider

    private lateinit var albumView: AlbumContract.View
    private lateinit var albumPresenter: AlbumContract.Presenter

    @Before
    fun setup() {
        albumView = mock()
        userRepository = mock()
        alubmRepository = mock()
        loginUseCase = LoginUseCase(userRepository)
        loadAlbumUseCase = LoadAlbumsUseCase(alubmRepository)
        clearLoginDataUseCase = ClearLoginDataUseCase(userRepository)
        schedulerProvider = mock()
        albumPresenter = AlbumPresenter(albumView, loadAlbumUseCase, clearLoginDataUseCase, schedulerProvider)
    }

    @Test
    fun shouldReturnAlbums() {
        val testScheduer = TestScheduler()
        given(schedulerProvider.getIOScheduler()).willReturn(testScheduer)
        given(schedulerProvider.getUIScheduler()).willReturn(testScheduer)
        given(loadAlbumUseCase.execute()).willReturn(Single.just(TESTING_ALBUMS))

        albumPresenter.loadAlbums()
        testScheduer.triggerActions()

        then(albumView).should(times(1)).showLoading()
        then(albumView).should(times(1)).hideLoading()
        then(albumView).should(times(1)).onLoadAlbumSuccess(TESTING_ALBUMS)
    }

    @Test
    fun shouldPerformLogout() {
        val testScheduer = TestScheduler()
        given(schedulerProvider.getIOScheduler()).willReturn(testScheduer)
        given(schedulerProvider.getUIScheduler()).willReturn(testScheduer)
        given(clearLoginDataUseCase.execute()).willReturn(Completable.complete())

        albumPresenter.performLogOut()
        testScheduer.triggerActions()

        then(albumView).should(times(1)).showLoading()
        then(albumView).should(times(1)).hideLoading()
        then(albumView).should(times(1)).onLogOutSuccess()
    }
}