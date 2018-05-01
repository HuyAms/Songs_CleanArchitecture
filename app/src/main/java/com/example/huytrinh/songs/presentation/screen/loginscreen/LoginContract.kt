package com.example.huytrinh.songs.presentation.screen.loginscreen

import com.example.huytrinh.songs.presentation.base.BasePresenter
import com.example.huytrinh.songs.presentation.base.BaseView

interface LoginContract {

    interface View: BaseView {

        fun showLoading()

        fun hideLoading()

        fun onShowLoginError(error: String)

        fun onLoginSuccess()
    }

    interface Presenter: BasePresenter {

        fun performLogin(username: String, password: String)
    }
}