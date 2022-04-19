package com.example.task64.presentation.viewmodel.favoriteFragmentvm

import android.util.Log
import androidx.lifecycle.*
import com.example.task64.data.model.ImageModel
import com.example.task64.data.repositories.RoomImagesRepository
import kotlinx.coroutines.launch

class FavoriteFragViewModel(private val roomImagesRepository: RoomImagesRepository) : ViewModel() {

    init {
    Log.i("loop" , "favorite fragment view model created")
    }
    val favoriteImages : LiveData<List<ImageModel>> = roomImagesRepository.favoriteImages.asLiveData()

   fun insert(image : ImageModel) = viewModelScope.launch {
       roomImagesRepository.insert(image)
   }

    fun deleteAll() = viewModelScope.launch {
        roomImagesRepository.deleteAll()
    }



}