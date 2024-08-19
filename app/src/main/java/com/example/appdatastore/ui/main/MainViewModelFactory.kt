package com.example.appdatastore.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.appdatastore.data.repository.UserRepository
import java.lang.IllegalArgumentException

class MainViewModelFactory(private val userRepository: UserRepository): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)){
            return MainViewModel(userRepository) as T
        }
        throw IllegalArgumentException("View Model desconhecido")
    }




}