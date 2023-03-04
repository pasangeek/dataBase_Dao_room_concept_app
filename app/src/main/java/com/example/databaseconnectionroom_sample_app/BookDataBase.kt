package com.example.databaseconnectionroom_sample_app

import androidx.room.*

@Database(entities = [Book::class], version = 1)

abstract class BookDataBase():RoomDatabase(){
    abstract fun bookDao():BookDao


}