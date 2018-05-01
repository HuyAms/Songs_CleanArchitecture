package com.example.huytrinh.songs.presentation.screen.loginscreen

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.huytrinh.songs.R
import com.example.huytrinh.songs.presentation.screen.albumscreen.AlbumContract

class LoginActivity : AppCompatActivity(), LoginContract.View {

    private lateinit var presenter: AlbumContract.Presenter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }

    override fun onResume() {
        super.onResume()
        presenter.attach()
    }

    override fun onPause() {
        super.onPause()
        presenter.detach()
    }

    override fun showLoading() {
    }

    override fun hideLoading() {
    }

    override fun onShowLoginError(error: String) {
    }

    override fun onLoginSuccess() {
    }
}
