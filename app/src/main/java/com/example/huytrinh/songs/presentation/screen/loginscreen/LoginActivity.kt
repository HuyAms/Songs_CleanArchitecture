package com.example.huytrinh.songs.presentation.screen.loginscreen

import android.os.Bundle
import com.example.huytrinh.songs.R
import com.example.huytrinh.songs.presentation.base.BaseActivity
import com.example.huytrinh.songs.presentation.di.Injector
import com.example.huytrinh.songs.presentation.screen.albumscreen.AlbumActivity
import com.example.huytrinh.songs.presentation.util.hide
import com.example.huytrinh.songs.presentation.util.show
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.clearTask
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.newTask
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.toast

class LoginActivity : BaseActivity(), LoginContract.View {

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
        toast(error)
    }

    override fun onLoginSuccess() {
        startActivity(intentFor<AlbumActivity>().clearTask().newTask())
    }
}
