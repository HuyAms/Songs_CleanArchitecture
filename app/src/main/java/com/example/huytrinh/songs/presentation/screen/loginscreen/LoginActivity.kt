package com.example.huytrinh.songs.presentation.screen.loginscreen

import android.os.Bundle
import com.example.huytrinh.songs.R
import com.example.huytrinh.songs.presentation.base.BaseActivity
import com.example.huytrinh.songs.presentation.di.Injector
import com.example.huytrinh.songs.presentation.screen.albumscreen.AlbumActivity
import com.example.huytrinh.songs.presentation.util.hide
import com.example.huytrinh.songs.presentation.util.show
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk25.coroutines.onClick

class LoginActivity : BaseActivity(), LoginContract.View, AnkoLogger {

    private lateinit var presenter: LoginContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        setUpPresenter()

        loginBtn.onClick {
            val username = usernnameEditText.text.toString()
            val password = passwordEditText.text.toString()
            presenter.performLogin(username, password)
        }
    }

    fun setUpPresenter() {
        presenter = LoginPresenter(
                this,
                Injector.setLoginUseCase,
                Injector.loginUseCase,
                Injector.schedulerProvider
        )
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
        progressBar.show()
    }

    override fun hideLoading() {
        progressBar.hide()
    }

    override fun onShowLoginError(error: String) {
        info { "onShowLoginError: ${error}" }
    }

    override fun onLoginSuccess() {
        info { "login success" }
        startActivity(intentFor<AlbumActivity>().clearTask().newTask())
    }
}
