package com.example.soptseminar.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.soptseminar.data.model.User

@Dao
interface userDao {
    @Insert
    fun insertUser(user : User)

    @Delete
    fun deleteUser(user : User)

    @Query("SELECT * FROM user_table")
    fun getAllUser() : LiveData<List<User>>
}