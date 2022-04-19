package com.example.task64.presentation.viewmodel.favoriteFragmentvm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.task64.data.repositories.RoomImagesRepository
import java.lang.IllegalArgumentException

class FavoriteViewModelFactory(private val roomImagesRepository: RoomImagesRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FavoriteFragViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return FavoriteFragViewModel(roomImagesRepository) as T
        }
        throw IllegalArgumentException("unknown vm class")
    }


}