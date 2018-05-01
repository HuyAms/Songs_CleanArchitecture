package com.example.huytrinh.songs.domain.usecase.user

import com.example.huytrinh.songs.domain.repository.UserRepository
import com.example.huytrinh.songs.domain.usecase.CompletableUseCaseWithParams
import io.reactivex.Completable

class LoginUseCase(private val userRepository: UserRepository): CompletableUseCaseWithParams<String, String> {

    override fun execute(username: String, password: String): Completable {
        return userRepository.logIn(username, password)
    }

}