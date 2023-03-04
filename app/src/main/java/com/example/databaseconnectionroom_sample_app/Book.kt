package com.example.databaseconnectionroom_sample_app

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "books_table")
data class Book(


    @PrimaryKey(autoGenerate = false)
    var id: Int,
    @ColumnInfo(name = "books_name")
    var name: String,
    @ColumnInfo(name = "authors_name")
    var author: String

)