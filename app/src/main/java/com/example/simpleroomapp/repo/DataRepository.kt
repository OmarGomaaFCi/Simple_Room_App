package com.example.simpleroomapp.repo


import androidx.lifecycle.LiveData
import com.example.simpleroomapp.data.local.UserDao
import com.example.simpleroomapp.pojo.UserModel

class DataRepository(private val userDao: UserDao) {

    val allUsersData : LiveData<List<UserModel>> = userDao.getUsers()

    suspend fun insertUser(userModel: UserModel) = userDao.insertUser(userModel)

    suspend fun updateUser(userModel: UserModel) = userDao.updateUser(userModel)

    suspend fun deleteUser(userModel: UserModel) = userDao.deleteUser(userModel)
}