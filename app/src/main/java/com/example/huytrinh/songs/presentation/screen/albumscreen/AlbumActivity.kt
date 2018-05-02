package com.example.huytrinh.songs.presentation.screen.albumscreen

import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.view.Menu
import android.view.MenuItem
import com.example.huytrinh.songs.R
import com.example.huytrinh.songs.domain.entity.Album
import com.example.huytrinh.songs.presentation.base.BaseActivity
import com.example.huytrinh.songs.presentation.di.Injector
import com.example.huytrinh.songs.presentation.screen.loginscreen.LoginActivity
import com.example.huytrinh.songs.presentation.util.hide
import com.example.huytrinh.songs.presentation.util.show
import kotlinx.android.synthetic.main.activity_album.*
import org.jetbrains.anko.*

class AlbumActivity : BaseActivity(), AlbumContract.View, AnkoLogger {

    private lateinit var presenter: AlbumContract.Presenter
    private lateinit var adapter: AlbumAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_album)
        setUpPresenter()
        setupViews()
    }

    override fun onResume() {
        super.onResume()
        presenter.attach()
    }

    override fun onPause() {
        super.onPause()
        presenter.detach()
    }

    fun setupViews() {
        adapter = AlbumAdapter(emptyList())
        val layoutManager = GridLayoutManager(this, 2)
        albumRecyclerView.layoutManager = layoutManager
        albumRecyclerView.adapter = adapter
    }

    fun setUpPresenter() {
        presenter = AlbumPresenter(
                this,
                Injector.loadAlbumUseCase,
                Injector.clearLoadAlbumsUseCase,
                Injector.schedulerProvider
        )
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.activity_album, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.logOutMenu -> {
                startActivity(intentFor<LoginActivity>().clearTask().newTask())
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun showLoading() {
        progressBar.show()
    }

    override fun hideLoading() {
        progressBar.hide()
    }

    override fun onError(error: String) {
        info { "onError: ${error}" }
    }

    override fun onLoadAlbumSuccess(albums: List<Album>) {
        info { "onLoadAlbumSuccess: ${albums}" }
        adapter.replaceData(albums)
    }

    override fun onLogOutSuccess() {
        info { "onLogOutSuccess" }

    }
}
