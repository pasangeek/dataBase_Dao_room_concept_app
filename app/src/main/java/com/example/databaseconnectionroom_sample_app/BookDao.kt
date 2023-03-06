package com.example.databaseconnectionroom_sample_app

import androidx.room.*

@Dao
interface BookDao{


    //crud operations -->Create(Insert),read(select),update,delete
    //@Query("INSERT INTO books_table(book_name,author_name)")
    //values("abc","pasan")WHERE id =10

    @Insert
    fun insertBook(book: Book)

    @Query("SELECT * FROM books_table")
    fun getAllBooks(): List<Book>

    //SET book_name = "DEF".author_name = "sam"
    //WHERE id = 2
    //@Query("UPDATE books_table SET books_name = :name WHERE author_name AKD")
    @Update
    fun updateBook(book: Book)

    //DELETE from books_table
    //WHERE id = 8
    @Delete
    fun deleteBook(book: Book)

    @Query("DELETE from books_table")
    fun deleteAllBooks()
}