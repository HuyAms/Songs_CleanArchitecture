package com.example.huytrinh.songs.domain.repository

import io.reactivex.Completable
import io.reactivex.Single

interface UserRepository {

    fun logIn(username: String, password: String): Completable

    fun setLoginState(): Completable

    fun getLoginState(): Single<Boolean>

    fun clearLoginData(): Completable

}