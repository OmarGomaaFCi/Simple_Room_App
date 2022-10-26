package com.example.simpleroomapp.ui.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.simpleroomapp.data.local.UserDatabase
import com.example.simpleroomapp.pojo.UserModel
import com.example.simpleroomapp.repo.DataRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: DataRepository
    val allUsersData: LiveData<List<UserModel>>

    init {
        val userDao = UserDatabase.getUserDatabase(application.applicationContext).getDao()

        repository = DataRepository(userDao)
        allUsersData = repository.allUsersData
    }

    fun insertUser(userModel: UserModel) = viewModelScope.launch(Dispatchers.IO) {
        repository.insertUser(userModel)
    }

    fun updateUser(userModel: UserModel) = viewModelScope.launch(Dispatchers.IO) {
        repository.updateUser(userModel)
    }


    fun deleteUser(userModel: UserModel) = viewModelScope.launch(Dispatchers.IO) {
        repository.deleteUser(userModel)
    }
}