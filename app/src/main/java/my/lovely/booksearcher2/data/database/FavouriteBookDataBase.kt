package my.lovely.booksearcher2.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import my.lovely.booksearcher2.domain.models.database.FavouriteBook

@Database(entities = [FavouriteBook::class], version = 1, exportSchema = false)
abstract class FavouriteBookDataBase : RoomDatabase() {

    abstract fun favouriteBookDao(): FavouriteBookDao

}