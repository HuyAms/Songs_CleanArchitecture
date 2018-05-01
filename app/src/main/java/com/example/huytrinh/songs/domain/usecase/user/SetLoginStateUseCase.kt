package com.example.huytrinh.songs.domain.usecase.user

import com.example.huytrinh.songs.domain.repository.UserRepository
import com.example.huytrinh.songs.domain.usecase.CompletableUseCase
import io.reactivex.Completable

class SetLoginStateUseCase(private val userRepository: UserRepository): CompletableUseCase {

    override fun execute(): Completable {
        return userRepository.setLoginState()
    }

}