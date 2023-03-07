package com.example.databaseconnectionroom_sample_app

import androidx.room.*

@Database(entities = [Book::class], version = 2)

abstract class BookDataBase():RoomDatabase(){
    abstract fun bookDao():BookDao


}