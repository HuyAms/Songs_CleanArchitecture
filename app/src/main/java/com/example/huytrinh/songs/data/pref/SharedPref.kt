package com.example.huytrinh.songs.data.pref

import io.reactivex.Completable
import io.reactivex.Single

interface SharedPref {

    fun setlogInState(): Completable

    fun getLoginState(): Single<Boolean>

    fun clearLoginData(): Completable

}