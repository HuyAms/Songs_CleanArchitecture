package com.example.huytrinh.songs.domain.usecase.user

import com.example.huytrinh.songs.domain.repository.UserRepository
import com.example.huytrinh.songs.domain.usecase.SingleUseCase
import io.reactivex.Single

class GetLoginStateUseCase(private val userRepository: UserRepository): SingleUseCase<Boolean> {

    override fun execute(): Single<Boolean> {
       return userRepository.getLoginState()
    }

}