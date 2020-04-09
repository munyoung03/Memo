
package com.example.memo.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "memo")
class memo(
    @PrimaryKey(autoGenerate = true) var id: Long,
    @ColumnInfo(name = "title") var title: String?,
    @ColumnInfo(name = "time") var time: String,
    @ColumnInfo(name = "content") var content: String
)
