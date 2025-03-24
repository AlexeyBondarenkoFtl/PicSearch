package com.alexeybondarenko.data.repository

import com.alexeybondarenko.data.local.dao.ImageDao
import com.alexeybondarenko.data.local.mapper.ImageDbModelMapper
import com.alexeybondarenko.domain.model.ImageEntity
import com.alexeybondarenko.domain.repository.ImageStorageService

class ImageStorageServiceImpl(
    private val imageDao: ImageDao,
) : ImageStorageService {

    private val imageDbModelMapper = ImageDbModelMapper()

    override suspend fun saveImage(imageEntity: ImageEntity) {
        val imageDbModel = imageDbModelMapper.mapFromEntity(imageEntity)
        imageDao.insertImage(imageDbModel)
    }

    override suspend fun getAllImages(): List<ImageEntity> {
        val imageDbModelList = imageDao.getImages()
        return imageDbModelList.map { imageDbModelMapper.mapToEntity(it) }
    }

    override suspend fun getImageById(id: String): ImageEntity {
        val imageDbModel = imageDao.getImageById(id)
        return imageDbModelMapper.mapToEntity(imageDbModel)
    }

    override suspend fun deleteAllImages() {
        imageDao.deleteAll()
    }

}