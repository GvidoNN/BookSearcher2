package my.lovely.booksearcher2.domain.usecase

import my.lovely.booksearcher2.data.database.FavouriteBookDao
import my.lovely.booksearcher2.domain.repository.FavouriteBookReposotiry
import javax.inject.Inject

class GetDaoDbUseCase @Inject constructor(private val favouriteBookRepository: FavouriteBookReposotiry) {

    fun getDaoDb(): FavouriteBookDao {
        return favouriteBookRepository.getDaoDb()
    }
}