package com.example.huytrinh.songs.data.repository

import com.example.huytrinh.songs.data.pref.SharedPref
import com.example.huytrinh.songs.data.service.LoginService
import com.example.huytrinh.songs.domain.repository.UserRepository
import io.reactivex.Completable
import io.reactivex.Single

class UserRepositoryImpl(val sharedPref: SharedPref, val loginService: LoginService): UserRepository {

    override fun setLoginState(): Completable = sharedPref.setlogInState()

    override fun logIn(username: String, password: String): Completable = loginService.performLogin(username, password)

    override fun getLoginState(): Single<Boolean> =sharedPref.getLoginState()

    override fun clearLoginData(): Completable = sharedPref.clearLoginData()

}