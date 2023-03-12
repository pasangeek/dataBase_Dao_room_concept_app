package com.example.databaseconnectionroom_sample_app

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "books_table")
data class Book(


    @ColumnInfo(name = "books_name")
    var name: String,
    @ColumnInfo(name = "authors_name")
    var author: String,
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

) {

    companion object {
        const val TABLE_NAME = "books_table"
    }

}