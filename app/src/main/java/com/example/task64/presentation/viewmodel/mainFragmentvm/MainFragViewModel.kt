package com.example.task64.presentation.viewmodel.mainFragmentvm

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.task64.data.repositories.ImagesRepository
import com.example.task64.data.model.ImageModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainFragViewModel( val imagesRepository: ImagesRepository) : ViewModel() {
    init {
        Log.i("jok", "created vm")
    }

    val imagesList = MutableLiveData<List<ImageModel>>()

      fun getAllImages() {
        val response = imagesRepository.getAllImages()
        response.enqueue(object : Callback<MutableList<ImageModel>> {
        override fun onResponse(
         call: Call<MutableList<ImageModel>>?,
         response: Response<MutableList<ImageModel>>?) {
            response?.body().let {
                imagesList.postValue(it)
            }
        }
        override fun onFailure(
         call: Call<MutableList<ImageModel>>?,t: Throwable?) {
            Log.i("jok" , t?.message.toString())
        }
        })
 }

}