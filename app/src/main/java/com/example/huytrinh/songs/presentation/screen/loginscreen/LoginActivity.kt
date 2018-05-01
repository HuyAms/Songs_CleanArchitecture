package com.example.huytrinh.songs.presentation.screen.loginscreen

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.huytrinh.songs.R
import com.example.huytrinh.songs.presentation.di.Injector
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.jetbrains.anko.sdk25.coroutines.onClick

class LoginActivity : AppCompatActivity(), LoginContract.View, AnkoLogger {

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
        info { "showLoading" }
    }

    override fun hideLoading() {
        info { "hideLoading" }
    }

    override fun onShowLoginError(error: String) {
        info { "error: ${error}" }
    }

    override fun onLoginSuccess() {
        info { "login success" }
    }
}
