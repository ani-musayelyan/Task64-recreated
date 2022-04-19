package com.example.task64.presentation.viewmodel.mainFragmentvm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.task64.data.repositories.ImagesRepository
import java.lang.IllegalArgumentException

class MainFragVmFactory constructor(private val repository: ImagesRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(MainFragViewModel::class.java)) {
            MainFragViewModel(this.repository) as T
        } else {
            throw IllegalArgumentException("View model not found")
        }
    }
}