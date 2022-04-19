package com.example.task64.data.repositories

import androidx.annotation.WorkerThread
import com.example.task64.data.model.ImageModel
import example.task64.roomDb.ImagesDao
import kotlinx.coroutines.flow.Flow


class RoomImagesRepository constructor(val imagesDao : ImagesDao) {

    val favoriteImages : Flow<List<ImageModel>> = imagesDao.getAllImages()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(image : ImageModel) {
        imagesDao.insert(image)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun deleteAll() {
        imagesDao.deleteAll()
    }


}