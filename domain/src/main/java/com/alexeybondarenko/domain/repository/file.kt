package com.alexeybondarenko.domain.repository

import com.alexeybondarenko.domain.model.User


interface UserRepository {
    fun findUser(name: String): User?
    fun addUsers(users: List<User>)
    fun getUsers(): List<User>
}



interface SecondRepository {
    fun someAction(): String
}