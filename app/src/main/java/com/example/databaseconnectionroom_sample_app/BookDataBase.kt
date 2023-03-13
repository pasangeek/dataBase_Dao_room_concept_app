package com.example.databaseconnectionroom_sample_app

import android.content.Context
import androidx.room.*
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
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

                    )//.fallbackToDestructiveMigration()
                        .addMigrations(MIGRATION_1_2)
                        .build()
                        .also {
                            INSTANCE = it
                        }
                }



        }

        private val MIGRATION_1_2 = object :Migration(1,2){
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE books_new RENAME TO Books_Table")
            }
            private val MIGRATION_2_3 = object :Migration(2,3){
                override fun migrate(database: SupportSQLiteDatabase) {
                    database.execSQL("ALTER TABLE books_new RENAME TO Books_Table")
                }
            }
        }
    }
}