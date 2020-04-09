package com.example.memo.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface Dao {
    @Query("SELECT * FROM memo")
    fun getAll(): List<memo>

    @Insert
    fun insert(memo: memo)

    @Query("UPDATE memo SET time = :time , title = :title, content = :content WHERE title = :original_title")
    fun update(title: String, time: String, content: String, original_title : String)

    @Query("DELETE  FROM memo WHERE title = :title AND time = :time" )
    fun delete(title: String, time: String)

}