package com.example.task64.data.remote


import com.example.task64.data.model.ImageModel
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET


interface ApiInterface {

    @GET("android-test/sample.json")
    fun getData() : Call<MutableList<ImageModel>>

    companion object {
        private const val BASE_URL = "http://lab64.am/"

        fun create() : ApiInterface {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(ApiInterface::class.java)
        }

    }

}