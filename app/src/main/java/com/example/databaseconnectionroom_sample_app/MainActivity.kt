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
            }
        }

        init()
    }

    fun init() {

        val db =
            Room.databaseBuilder(applicationContext, BookDataBase::class.java, "book_database")
                .fallbackToDestructiveMigration()    .build()
        bookDao = db.bookDao()
    }

    fun testDB() {
//insert
        Log.i("LNBTI", "inseting...")
        val book1 = Book(0, "java", "sam")
        val book2 = Book(0, "cam", "joe")
        bookDao.apply {
            insertBook(book1)
            insertBook(book2)
            insertBook(Book(4, "jhhk", "ewf"))
        }



        Log.i("LNBTI", "Reading..")
        val books = bookDao.getAllBooks()
        for (book in books)
            Log.i("LNBTI", "id :${book.id}name:${book.name}author : ${book.author}")

        //update

        Log.i("LNBTI", "updating3...")
        bookDao.updateBook(Book(1, "java_version2.", "sam>>>"))

        //read/updated quary

        Log.i("LNBTI", "Reading..")
        val booksupdated = bookDao.getAllBooks()
        for (book in booksupdated)
            Log.i("LNBTI", "id :${book.id}name:${book.name}author : ${book.author}")

//deletebook
        Log.i("LNBTI", "updating3...")
        bookDao.deleteBook(Book(1, "", ""))
        Log.i("LNBTI", "Deleting..")
        val bookAfterdeleted = bookDao.getAllBooks()
        for (book in bookAfterdeleted)
            Log.i("LNBTI", "id :${book.id}name:${book.name}author : ${book.author}")
    }


    private fun deleteAll() {

        bookDao.deleteAllBooks()
    }
}