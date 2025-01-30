package com.alexeybondarenko.data.repository

import android.util.Log
import com.alexeybondarenko.domain.model.User
import com.alexeybondarenko.domain.repository.SecondRepository
import com.alexeybondarenko.domain.repository.UserRepository

class UserRepositoryImpl : UserRepository {

    init {
        Log.d("impl", "UserRepositoryImpl init")
    }

    private val _users = arrayListOf<User>()

    override fun findUser(name: String): User? {
        return _users.firstOrNull { it.name == name }
    }

    override fun addUsers(users: List<User>) {
        _users.addAll(users)
    }

    override fun getUsers(): List<User> {
        return _users
    }
}

class SecondRepositoryImpl(
    private val userRepository: UserRepository,
) : SecondRepository {
    init {
        Log.d("impl", "SecondRepositoryImpl init")
    }

    override fun someAction(): String {
        return "users: ${userRepository.getUsers().size}"
    }
}