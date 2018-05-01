package com.example.huytrinh.songs.data.service

import io.reactivex.Completable

class LoginServiceImpl: LoginService {

    override fun performLogin(userName: String, password: String): Completable {
        val testUserName = "test"
        val testPassword = "test"

        if (userName.equals(testUserName) && password.equals(testPassword)) {
            return Completable.complete()
        } else {
            return Completable.error(Error("Fail to login"))
        }
    }
}