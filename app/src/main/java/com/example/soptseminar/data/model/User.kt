package com.example.soptseminar.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
data class User(
    @PrimaryKey(autoGenerate = true)
    var userid : Int = 0,
    var name : String,
    var id : String,
    var password : String
) {

}