package com.example.huytrinh.domain.user

import com.example.huytrinh.songs.domain.repository.UserRepository
import com.example.huytrinh.songs.domain.usecase.user.GetLoginStateUseCase
import com.nhaarman.mockito_kotlin.given
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.then
import com.nhaarman.mockito_kotlin.times
import io.reactivex.Single
import io.reactivex.observers.TestObserver
import org.junit.Before
import org.junit.Test

class GetLoginStateUseCaseTest {
    private lateinit var userRepository: UserRepository
    private lateinit var getLoginStateUseCase: GetLoginStateUseCase
    private lateinit var testObserver: TestObserver<Boolean>

    @Before
    fun setup() {
        userRepository = mock()
        getLoginStateUseCase = GetLoginStateUseCase(userRepository)
        testObserver = TestObserver()
    }

    @Test
    fun shouldReturnLoginState() {
        val loginState = true
        given(userRepository.getLoginState()).willReturn(Single.just(loginState))
        getLoginStateUseCase.execute().subscribe(testObserver)

        then(userRepository).should(times(1)).getLoginState()
        then(userRepository).shouldHaveNoMoreInteractions()

        testObserver.assertComplete()
        testObserver.assertValue(loginState)
    }
}