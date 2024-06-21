package com.example.mynewsapplication.repository

import com.example.mynewsapplication.database.NewsDao
import com.example.mynewsapplication.model.Article
import com.example.mynewsapplication.model.NewsResponse
import com.example.mynewsapplication.network.NewsApiService
import com.example.mynewsapplication.utils.Constant.CATEGORY_BUSINESS
import com.example.mynewsapplication.utils.Constant.COUNTRY_US
import com.example.mynewsapplication.utils.Constant.NEWS_API_KEY
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.doNothing
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class NewsRepositoryTest {

    // Mock dependencies
    @Mock
    lateinit var newsDao: NewsDao

    @Mock
    lateinit var newsApi: NewsApiService

    // The repository instance to be tested
    lateinit var newsRepository: NewsRepository

    // Test Coroutine Dispatcher
    private val testDispatcher = TestCoroutineDispatcher()

    @Before
    fun setUp() {
        // Initialize Mockito mocks
        MockitoAnnotations.openMocks(this)

        // Create instance of repository with mocked dependencies
        newsRepository = NewsRepository(newsDao, newsApi)
    }

    @After
    fun tearDown() {
        // Clean up any resources if needed
        testDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun `test getNews success`() = runTest {
        // Given
        val mockArticles = listOf(
            Article(
                author = "Author",
                title = "Title",
                description = "Description",
                url = "url",
                urlToImage = "urlToImage",
                publishedAt = "publishedAt",
                content = "content"
            )
            // Add more articles as needed
        )

        // Mock response from News API
        val mockNewsResponse = NewsResponse(
            status = "ok",
            totalResults = mockArticles.size,
            articles = mockArticles
        )

        // Mock behavior of newsApi.getTopHeadlines
        `when`(newsApi.getTopHeadlines("us", "business", NEWS_API_KEY)).thenReturn(mockNewsResponse)

        // Mock behavior of newsDao methods
        `when`(newsDao.getAllArticles()).thenReturn(mockArticles)
        doNothing().`when`(newsDao).insertAll(mockArticles)

        // When
        val flow = newsRepository.getNews()

        // Collect and verify the result
        val result = flow.first()

        // Then
        assertEquals(mockArticles, result)

        // Verify interactions
        verify(newsApi).getTopHeadlines(COUNTRY_US, CATEGORY_BUSINESS, NEWS_API_KEY)
        verify(newsDao).insertAll(mockArticles)
        verify(newsDao).getAllArticles()
    }

}
