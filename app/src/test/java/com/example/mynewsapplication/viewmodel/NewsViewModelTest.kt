package com.example.mynewsapplication.viewmodel

import junit.framework.TestCase.assertEquals

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.mynewsapplication.model.Article
import com.example.mynewsapplication.repository.NewsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(MockitoJUnitRunner::class)
class NewsViewModelTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var newsRepository: NewsRepository

    private lateinit var newsViewModel: NewsViewModel

    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
    }

    @Test
    fun fetchNews_success() = runTest {
        // Given
        val articleList = listOf(
            Article(
                author = "Author",
                title = "Title",
                description = "Description",
                url = "url",
                urlToImage = "urlToImage",
                publishedAt = "publishedAt",
                content = "content"
            ),
            Article(
                author = "Author1",
                title = "Title1",
                description = "Description1",
                url = "url1",
                urlToImage = "urlToImage1",
                publishedAt = "publishedAt1",
                content = "content1"
            )
        )
        val flow = MutableStateFlow(articleList)
        Mockito.`when`(newsRepository.getNews()).thenReturn(flow)

        // When
        newsViewModel = NewsViewModel(newsRepository) // Instantiate ViewModel here

        // Collect the StateFlow in a coroutine
        var collectedData: List<Article>? = null
        val job = launch(testDispatcher) {
            newsViewModel.news.collect {
                collectedData = it
            }
        }

        // Move forward in time to allow the coroutine to collect the data
        testDispatcher.scheduler.advanceUntilIdle()

        // Then
        assertEquals(articleList, collectedData)
        job.cancel()
    }

}
