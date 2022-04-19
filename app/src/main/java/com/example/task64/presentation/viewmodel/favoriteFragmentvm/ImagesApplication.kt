package com.example.task64.presentation.viewmodel.favoriteFragmentvm

import android.app.Application
import com.example.task64.data.repositories.RoomImagesRepository
import dagger.hilt.android.HiltAndroidApp
import example.task64.roomDb.FavoriteImagesDB
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob


class ImagesApplication : Application() {
    val applicationScope = CoroutineScope(SupervisorJob())

    val database by lazy { FavoriteImagesDB.getDatabase(this , applicationScope) }
    val repository by lazy { RoomImagesRepository(database!!.favoriteImagesDao()) }

}