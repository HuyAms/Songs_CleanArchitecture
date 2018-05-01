package com.example.huytrinh.songs.presentation.di

import android.app.Application
import com.example.huytrinh.songs.data.api.ServiceGenerator
import com.example.huytrinh.songs.data.mapper.AlbumMapperImpl
import com.example.huytrinh.songs.data.pref.SharedPrefImpl
import com.example.huytrinh.songs.data.repository.AlbumRepositoryImpl
import com.example.huytrinh.songs.data.repository.UserRepositoryImpl
import com.example.huytrinh.songs.data.service.LoginServiceImpl
import com.example.huytrinh.songs.domain.usecase.album.LoadAlbumsUseCase
import com.example.huytrinh.songs.domain.usecase.user.ClearLoginDataUseCase
import com.example.huytrinh.songs.domain.usecase.user.GetLoginStateUseCase
import com.example.huytrinh.songs.domain.usecase.user.LoginUseCase
import com.example.huytrinh.songs.domain.usecase.user.SetLoginStateUseCase
import com.example.huytrinh.songs.presentation.util.SchedulerProvider

class Injector {

    companion object {
        lateinit var loadAlbumUseCase: LoadAlbumsUseCase
        lateinit var clearLoadAlbumsUseCase: ClearLoginDataUseCase
        lateinit var getLoginStateUseCase: GetLoginStateUseCase
        lateinit var loginUseCase: LoginUseCase
        lateinit var setLoginUseCase: SetLoginStateUseCase
        lateinit var schedulerProvider: SchedulerProvider

        fun initialize(app: Application) {
            val albumService = ServiceGenerator.provideAlbumService()
            val albumMapper = AlbumMapperImpl()
            val albumRepository = AlbumRepositoryImpl(albumService, albumMapper)
            loadAlbumUseCase = LoadAlbumsUseCase(albumRepository)

            val sharedPref = SharedPrefImpl(app)
            val loginService = LoginServiceImpl()
            val userRepository = UserRepositoryImpl(sharedPref, loginService)

            clearLoadAlbumsUseCase = ClearLoginDataUseCase(userRepository)
            getLoginStateUseCase = GetLoginStateUseCase(userRepository)
            loginUseCase = LoginUseCase(userRepository)
            setLoginUseCase = SetLoginStateUseCase(userRepository)

            schedulerProvider = SchedulerProvider()
        }
    }
}