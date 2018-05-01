package com.example.huytrinh.data.repository

import com.example.huytrinh.data.LOGIN_STATE
import com.example.huytrinh.data.TEST_PASSWORD
import com.example.huytrinh.data.TEST_USERNAME
import com.example.huytrinh.songs.data.pref.SharedPref
import com.example.huytrinh.songs.data.repository.UserRepositoryImpl
import com.example.huytrinh.songs.data.service.LoginService
import com.example.huytrinh.songs.domain.repository.UserRepository
import com.nhaarman.mockito_kotlin.given
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.then
import com.nhaarman.mockito_kotlin.times
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.observers.TestObserver
import org.junit.Before
import org.junit.Test

class UserRepositoryImplTest {

    private lateinit var sharedPref: SharedPref
    private lateinit var loginService: LoginService
    private lateinit var userRepositoryImpl: UserRepository

    @Before
    fun setup(){
        sharedPref = mock()
        loginService = mock()
        userRepositoryImpl = UserRepositoryImpl(sharedPref, loginService)
    }

    @Test
    fun shouldSetLogInState() {
        val testObserver = TestObserver<Any>()
        given(sharedPref.setlogInState()).willReturn(Completable.complete())

        userRepositoryImpl.setLoginState().subscribe(testObserver)

        then(sharedPref).should(times(1)).setlogInState()
        then(sharedPref).shouldHaveNoMoreInteractions()

        testObserver.assertComplete()
    }

    @Test
    fun shouldLogIn() {
        val testObserver = TestObserver<Any>()
        given(loginService.performLogin(TEST_USERNAME, TEST_PASSWORD)).willReturn(Completable.complete())

        userRepositoryImpl.logIn(TEST_USERNAME, TEST_PASSWORD).subscribe(testObserver)

        then(loginService).should(times(1)).performLogin(TEST_USERNAME, TEST_PASSWORD)
        then(loginService).shouldHaveNoMoreInteractions()

        testObserver.assertComplete()
    }

    @Test
    fun shouldReturnLoginState() {
        val testObserver = TestObserver<Boolean>()
        given(sharedPref.getLoginState()).willReturn(Single.just(LOGIN_STATE))

        userRepositoryImpl.getLoginState().subscribe(testObserver)

        then(sharedPref).should(times(1)).getLoginState()
        then(sharedPref).shouldHaveNoMoreInteractions()

        testObserver.assertComplete()
        testObserver.assertValue(LOGIN_STATE)
    }

    @Test
    fun shouldClearLoginState() {
        val testObserver = TestObserver<Any>()
        given(sharedPref.clearLoginData()).willReturn(Completable.complete())

        userRepositoryImpl.clearLoginData().subscribe(testObserver)

        then(sharedPref).should(times(1)).clearLoginData()
        then(sharedPref).shouldHaveNoMoreInteractions()

        testObserver.assertComplete()
    }

}