package com.example.huytrinh.domain.user

import com.example.huytrinh.songs.domain.repository.UserRepository
import com.example.huytrinh.songs.domain.usecase.user.LoginUseCase
import com.nhaarman.mockito_kotlin.given
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.then
import com.nhaarman.mockito_kotlin.times
import io.reactivex.Completable
import io.reactivex.observers.TestObserver
import org.junit.Before
import org.junit.Test

class LoginUseCaseTest {
    private lateinit var userRepository: UserRepository
    private lateinit var loginUseCaseUseCase: LoginUseCase
    private lateinit var testObserver: TestObserver<Any>

    @Before
    fun setup() {
        userRepository = mock()
        loginUseCaseUseCase = LoginUseCase(userRepository)
        testObserver = TestObserver()
    }

    @Test
    fun shouldLogInUser() {
        val username = "usernametest"
        val password = "passwordtest"
        given(userRepository.logIn(username, password)).willReturn(Completable.complete())
        loginUseCaseUseCase.execute(username, password).subscribe(testObserver)

        then(userRepository).should(times(1)).logIn(username, password)
        then(userRepository).shouldHaveNoMoreInteractions()

        testObserver.assertComplete()
    }
}