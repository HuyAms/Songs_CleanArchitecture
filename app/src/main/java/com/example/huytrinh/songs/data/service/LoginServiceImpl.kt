package com.example.huytrinh.songs.data.service

import io.reactivex.Completable

class LoginServiceImpl: LoginService {

    override fun performLogin(userName: String, password: String): Completable {
        val testUserName = "username"
        val testPassword = "pass"

        if (userName.equals(testUserName) && password.equals(testPassword)) {
            return Completable.complete()
        } else {
            return Completable.error(Error("Fail to login"))
        }
    }
}