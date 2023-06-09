package my.lovely.booksearcher2.data.repository

import my.lovely.booksearcher2.data.api.DataService
import my.lovely.booksearcher2.domain.models.api.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import retrofit2.Response
import retrofit2.Retrofit

class SearchInsideRepositoryImplTest {

    // Инициализируем mock объекты для имитации dataService и retrofit
    private val dataService = mock<DataService>()
    private val retrofit = mock<Retrofit>()

    // Создаем экземпляр класса репозитория с передачей mock dataService
    private val searchInsideRepository = SearchInsideRepositoryImpl(dataService)
    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `getSearchInside from repository returns DataResponse`() = runTest {

        // Подготавливаем тестовые данные для проверки
        val testText = "test"
        val testDataResponse = DataResponse(hits = Hits(
            hits = listOf(Hit(edition = Edition(listOf(Author("test","test")), borrowUrl = "test", coverUrl = "test", key = "test", title = "test", url = "test", workKey = "test"), highlight = Highlight(listOf("test")))),total = 1))
        // Настраиваем поведение mock объектов при вызове метода getSearchInside
        whenever(retrofit.create(DataService::class.java)).thenReturn(dataService)
        whenever(dataService.getInsideSearch(testText)).thenReturn(Response.success(testDataResponse))

        // Вызываем метод репозитория с передачей testDispatcher в качестве аргумента и проверяем результат с ожидаемым ответом
        assertEquals(
            testDataResponse,
            searchInsideRepository.getSearchInside(testText)?.body()
        )
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `getSearchInside from repository returns Error because of Internet`() = runTest {

        val testText = "test"
        val testDataResponse = null
        // Настраиваем поведение mock объектов при вызове метода getSearchInside
        whenever(retrofit.create(DataService::class.java)).thenReturn(dataService)
        whenever(dataService.getInsideSearch(testText)).thenReturn(Response.success(testDataResponse))

        // Вызываем метод репозитория с передачей testDispatcher в качестве аргумента и проверяем результат с ожидаемым ответом
        assertEquals(
            testDataResponse,
            searchInsideRepository.getSearchInside(testText)?.body()
        )
    }



}