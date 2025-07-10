package com.alexeybondarenko.domain.utils

abstract class Mapper<T, D> {
    open fun mapToEntity(from: T): D? {
        return null
    }

    open fun mapFromEntity(from: D): T? {
        return null
    }
}