package com.example.huytrinh.songs.domain.domain.user

import com.example.huytrinh.songs.domain.repository.UserRepository
import com.example.huytrinh.songs.domain.usecase.user.ClearLoginDataUseCase
import com.nhaarman.mockito_kotlin.given
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.then
import com.nhaarman.mockito_kotlin.times
import io.reactivex.Completable
import io.reactivex.observers.TestObserver
import org.junit.Before
import org.junit.Test

class ClearLoginDataUseCaseTest {
    private lateinit var userRepository: UserRepository
    private lateinit var clearLoginDataUseCase: ClearLoginDataUseCase
    private lateinit var testObserver: TestObserver<Any>

    @Before
    fun setup() {
        userRepository = mock()
        clearLoginDataUseCase = ClearLoginDataUseCase(userRepository)
        testObserver = TestObserver()
    }

    @Test
    fun shouldClearLoginData() {
        given(userRepository.clearLoginData()).willReturn(Completable.complete())
        clearLoginDataUseCase.execute().subscribe(testObserver)

        then(userRepository).should(times(1)).clearLoginData()
        then(userRepository).shouldHaveNoMoreInteractions()

        testObserver.assertComplete()
    }
}
