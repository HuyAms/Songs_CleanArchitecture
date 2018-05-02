package com.example.huytrinh.presentation

import com.example.huytrinh.songs.domain.repository.UserRepository
import com.example.huytrinh.songs.domain.usecase.user.LoginUseCase
import com.example.huytrinh.songs.domain.usecase.user.SetLoginStateUseCase
import com.example.huytrinh.songs.presentation.screen.loginscreen.LoginContract
import com.example.huytrinh.songs.presentation.screen.loginscreen.LoginPresenter
import com.example.huytrinh.songs.presentation.util.SchedulerProvider
import com.nhaarman.mockito_kotlin.given
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.then
import com.nhaarman.mockito_kotlin.times
import io.reactivex.Completable
import io.reactivex.schedulers.TestScheduler
import org.junit.Before
import org.junit.Test

class LoginPresenterTest {
    private lateinit var userRepository: UserRepository
    private lateinit var loginUseCase: LoginUseCase
    private lateinit var setLoginStateUseCase: SetLoginStateUseCase
    private lateinit var schedulerProvider: SchedulerProvider

    private lateinit var loginView: LoginContract.View
    private lateinit var loginPresenter: LoginContract.Presenter

    @Before
    fun setup() {
        loginView = mock()
        userRepository = mock()
        loginUseCase = LoginUseCase(userRepository)
        setLoginStateUseCase = SetLoginStateUseCase(userRepository)
        schedulerProvider = mock()
        loginPresenter = LoginPresenter(loginView, setLoginStateUseCase, loginUseCase, schedulerProvider)
    }

    @Test
    fun shouldPerformLogin() {
        val testScheduer = TestScheduler()
        given(schedulerProvider.getIOScheduler()).willReturn(testScheduer)
        given(schedulerProvider.getUIScheduler()).willReturn(testScheduer)
        given(loginUseCase.execute(TEST_USERNAME, TEST_PASSWORD)).willReturn(Completable.complete())
        given(setLoginStateUseCase.execute()).willReturn(Completable.complete())

        loginPresenter.performLogin(TEST_USERNAME, TEST_PASSWORD)
        testScheduer.triggerActions()

        then(loginView).should(times(1)).showLoading()
        then(loginView).should(times(1)).hideLoading()
        then(loginView).should(times(1)).onLoginSuccess()
    }
}