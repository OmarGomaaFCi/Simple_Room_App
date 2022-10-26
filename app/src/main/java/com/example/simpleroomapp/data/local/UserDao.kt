package com.example.simpleroomapp.data.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.simpleroomapp.pojo.UserModel

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertUser(userModel: UserModel)

    @Update
    suspend fun updateUser(userModel: UserModel)

    @Delete
    suspend fun deleteUser(userModel: UserModel)

    @Query("SELECT * FROM users_table ORDER BY id ASC")
    fun getUsers(): LiveData<List<UserModel>>

}