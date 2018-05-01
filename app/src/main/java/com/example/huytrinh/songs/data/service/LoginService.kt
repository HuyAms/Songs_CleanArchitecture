package com.example.huytrinh.songs.data.service

import io.reactivex.Completable

interface LoginService {

    fun performLogin(userName: String, password: String): Completable

}