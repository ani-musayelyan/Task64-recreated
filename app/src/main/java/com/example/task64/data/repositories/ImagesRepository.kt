package com.example.task64.data.repositories

import com.example.task64.data.remote.ApiInterface
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ImagesRepository constructor(private val apiInterface: ApiInterface) {
    fun getAllImages() = apiInterface.getData()
}