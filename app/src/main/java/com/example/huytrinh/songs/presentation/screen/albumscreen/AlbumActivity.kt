package com.example.huytrinh.songs.presentation.screen.albumscreen

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.huytrinh.songs.R
import com.example.huytrinh.songs.domain.entity.Album

class AlbumActivity : AppCompatActivity(), AlbumContract.View {

    private lateinit var presenter: AlbumContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_album)
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

    override fun onError(error: String) {
    }

    override fun onLoadAlbumSuccess(albums: List<Album>) {
    }

    override fun onLogOutSuccess() {
    }
}
