package com.example.task64.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "favorite_images")
data class ImageModel (
    @SerializedName("id")
    @PrimaryKey @ColumnInfo(name = "id") var id : Int,
    @SerializedName("original")
    val original : String,
    @SerializedName("thumb")
   @ColumnInfo(name = "image") var thumb : String
    )