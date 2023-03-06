package com.example.databaseconnectionroom_sample_app

import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var btnTestdb: Button
    private lateinit var btndeleteAll: Button
    private lateinit var bookDao: BookDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnTestdb = findViewById(R.id.buttontest)
        btndeleteAll = findViewById(R.id.buttonClear)
        btnTestdb.setOnClickListener {

            lifecycleScope.launch(Dispatchers.IO) {
                testDB()
            }
        }
        btndeleteAll.setOnClickListener {
            lifecycleScope.launch(Dispatchers.IO) {
deleteAll()
        }}

        init()
    }

    fun init() {

        val db =
            Room.databaseBuilder(applicationContext, BookDataBase::class.java, "book_database")
                .build()
        bookDao = db.bookDao()
    }

    fun testDB() {

        Log.i("LNBTI", "inseting...")
        val book1 = Book(1, "java", "sam")
        val book2 = Book(2, "cam", "joe")


        bookDao.insertBook(book1)
        bookDao.insertBook(book2)
        bookDao.insertBook(Book(4, "jhhk", "ewf"))
    }

private fun deleteAll(){

    bookDao.deleteAllBooks()
}
}