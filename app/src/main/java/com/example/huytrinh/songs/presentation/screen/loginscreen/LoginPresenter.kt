package com.example.huytrinh.songs.presentation.screen.loginscreen

import com.example.huytrinh.songs.domain.usecase.user.LoginUseCase
import com.example.huytrinh.songs.domain.usecase.user.SetLoginStateUseCase
import com.example.huytrinh.songs.presentation.util.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable

class LoginPresenter(private val view: LoginContract.View,
                     private val setLoginStateUseCase: SetLoginStateUseCase,
                     private val loginUseCase: LoginUseCase,
                     private val schedulerProvider: SchedulerProvider): LoginContract.Presenter {

    private val compositeDisposable = CompositeDisposable()

    override fun performLogin(username: String, password: String) {

        if (username.isNullOrEmpty() || password.isNullOrEmpty()) {
            view.onShowLoginError("Field should not be empty")
        } else {
            view.showLoading()
            val disposable = loginUseCase.execute(username, password)
                    .subscribeOn(schedulerProvider.ioScheduler)
                    .observeOn(schedulerProvider.uiScheduler)
                    .andThen(setLoginStateUseCase.execute())
                    .subscribe({
                        view.hideLoading()
                        view.onLoginSuccess()
                    }, {error ->
                        view.hideLoading()
                        view.onShowLoginError(error.localizedMessage)
                    })
            compositeDisposable.add(disposable)
        }
    }

    override fun attach() {
    }

    override fun detach() {
        compositeDisposable.clear()
    }

}