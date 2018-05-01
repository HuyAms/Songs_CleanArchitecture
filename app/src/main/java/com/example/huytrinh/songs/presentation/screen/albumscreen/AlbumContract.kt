package com.example.huytrinh.songs.presentation.screen.albumscreen

import com.example.huytrinh.songs.domain.entity.Album
import com.example.huytrinh.songs.presentation.base.BasePresenter
import com.example.huytrinh.songs.presentation.base.BaseView

interface AlbumContract {

    interface View: BaseView {

        fun showLoading()

        fun hideLoading()

        fun onError(error: String)

        fun onLoadAlbumSuccess(albums: List<Album>)

        fun onLogOutSuccess()
    }

    interface Presenter: BasePresenter {

        fun loadAlbums()

        fun performLogOut()

    }
}