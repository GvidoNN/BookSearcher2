package my.lovely.booksearcher2.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import my.lovely.booksearcher2.domain.models.database.FavouriteBook

@Dao
interface FavouriteBookDao {

    @Insert
    suspend fun insertBook(book: FavouriteBook)

    @Update
    suspend fun updateBook(book: FavouriteBook)

    @Delete
    suspend fun deleteBook(book: FavouriteBook)

    @Query("SELECT * FROM favourite_book_data_table")
    fun getAllBooks(): LiveData<List<FavouriteBook>>
}