package com.example.huytrinh.songs.domain.domain.user

import com.example.huytrinh.songs.domain.repository.UserRepository
import com.example.huytrinh.songs.domain.usecase.user.LogInUseCase
import com.nhaarman.mockito_kotlin.given
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.then
import com.nhaarman.mockito_kotlin.times
import io.reactivex.Completable
import io.reactivex.observers.TestObserver
import org.junit.Before
import org.junit.Test

class LogInUseCaseTest {
    private lateinit var userRepository: UserRepository
    private lateinit var loginUseCase: LogInUseCase
    private lateinit var testObserver: TestObserver<Any>

    @Before
    fun setup() {
        userRepository = mock()
        loginUseCase = LogInUseCase(userRepository)
        testObserver = TestObserver()
    }

    @Test
    fun shouldLogInUser() {
        val testUserName = "testUserName"
        val testPassword = "testPassword"
        given(userRepository.logIn(testUserName, testPassword)).willReturn(Completable.complete())
        loginUseCase.execute(testUserName, testPassword).subscribe(testObserver)

        then(userRepository).should(times(1)).logIn(testUserName, testPassword)
        then(userRepository).shouldHaveNoMoreInteractions()

        testObserver.assertComplete()
    }
}