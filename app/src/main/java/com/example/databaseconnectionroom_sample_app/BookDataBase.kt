package com.example.databaseconnectionroom_sample_app

import android.content.Context
import androidx.room.*
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.internal.synchronized

@Database(entities = [Book::class], version = 2)

abstract class BookDataBase() : RoomDatabase() {
    abstract fun bookDao(): BookDao

    companion object {

        @Volatile
        private var INSTANCE: BookDataBase? = null

        @OptIn(InternalCoroutinesApi::class)
        fun getInstance(context: Context): BookDataBase {
            return INSTANCE
                ?: synchronized(this) {
                    INSTANCE ?: Room.databaseBuilder(

                        context.applicationContext,
                        BookDataBase::class.java, "book_database"

                    ).fallbackToDestructiveMigration()
                        .build()
                        .also {
                            INSTANCE = it
                        }
                }


        }
    }
}