package my.lovely.booksearcher2.domain.usecase

import my.lovely.booksearcher2.domain.models.api.*
import my.lovely.booksearcher2.domain.repository.SearchInsideRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class SearchInsideUseCaseTest {

    // Создаем экземпляр класса репозитория с передачей mock dataService
    private val searchInsideRepository = mock<SearchInsideRepository>()
    private val searchInsideUseCase = SearchInsideUseCase(searchInsideRepository)

    // Подготавливаем тестовые данные для проверки
    private val testText = "test"
    private val testDataResponse = mock<retrofit2.Response<DataResponse>>()

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `getSearchInside from usecase returns DataResponse from Repository`() = runTest {
        // Настраиваем поведение mock объектов при вызове метода getSearchInside
        whenever(searchInsideRepository.getSearchInside(testText)).thenReturn(testDataResponse)

        // Вызываем метод репозитория с передачей testDispatcher в качестве аргумента и проверяем результат с ожидаемым ответом
        Assertions.assertEquals(
            testDataResponse,
            searchInsideUseCase.getSearchInside(testText)
        )
    }
}