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

    @Query("UPDATE memo SET time = :time , title = :title, content = :content WHERE id = :id")
    fun update(title: String, time: String, content: String, id : Long)

    @Query("DELETE  FROM memo WHERE id = :id")
    fun delete(id : Long)

}