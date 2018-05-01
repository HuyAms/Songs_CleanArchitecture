package com.example.huytrinh.domain.user

import com.example.huytrinh.songs.domain.repository.UserRepository
import com.example.huytrinh.songs.domain.usecase.user.SetLoginStateUseCase
import com.nhaarman.mockito_kotlin.given
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.then
import com.nhaarman.mockito_kotlin.times
import io.reactivex.Completable
import io.reactivex.observers.TestObserver
import org.junit.Before
import org.junit.Test

class SetLoginStateUseCaseTest {
    private lateinit var userRepository: UserRepository
    private lateinit var setLoginUseCase: SetLoginStateUseCase
    private lateinit var testObserver: TestObserver<Any>

    @Before
    fun setup() {
        userRepository = mock()
        setLoginUseCase = SetLoginStateUseCase(userRepository)
        testObserver = TestObserver()
    }

    @Test
    fun shouldLogInUser() {
        given(userRepository.setLoginState()).willReturn(Completable.complete())
        setLoginUseCase.execute().subscribe(testObserver)

        then(userRepository).should(times(1)).setLoginState()
        then(userRepository).shouldHaveNoMoreInteractions()

        testObserver.assertComplete()
    }
}