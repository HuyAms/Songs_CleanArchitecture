package com.example.huytrinh.data.service

import com.example.huytrinh.data.TEST_PASSWORD
import com.example.huytrinh.data.TEST_USERNAME
import com.example.huytrinh.songs.data.service.LoginService
import com.example.huytrinh.songs.data.service.LoginServiceImpl
import io.reactivex.observers.TestObserver
import org.junit.Before
import org.junit.Test

class LoginServiceImplTest {
    private lateinit var loginService: LoginService
    private lateinit var testObserver: TestObserver<Any>

    @Before
    fun setup() {
        loginService = LoginServiceImpl()
        testObserver = TestObserver()
    }

    @Test
    fun shouldPerformLogin() {
        loginService.performLogin(TEST_USERNAME, TEST_PASSWORD).subscribe(testObserver)
        testObserver.assertComplete()
    }
}