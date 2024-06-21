package com.example.mynewsapplication.di.module

import android.app.Application
import com.example.mynewsapplication.database.NewsDatabase
import com.example.mynewsapplication.utils.Constant.DB_NAME
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotNull
import kotlinx.coroutines.test.TestCoroutineDispatcher
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class AppModuleTest {

    // Mock Application context
    @Mock
    lateinit var mockApplication: Application

    // Test coroutine dispatcher
    private val testDispatcher = TestCoroutineDispatcher()

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

    @After
    fun tearDown() {
        testDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun `test provideNewsDatabase`() {
        // Given
        val module = AppModule

        // When
        val newsDatabase = module.provideNewsDatabase(mockApplication)

        // Then
        assertNotNull(newsDatabase)
        assertEquals(DB_NAME, newsDatabase.openHelper.databaseName)
    }

    @Test
    fun `test provideNewsDao`() {
        // Given
        val module = AppModule
        val mockNewsDatabase = Mockito.mock(NewsDatabase::class.java)

        // When
        val newsDao = module.provideNewsDao(mockNewsDatabase)

        // Then
        assertEquals(mockNewsDatabase.newsDao(), newsDao)
    }

    @Test
    fun `test provideNewsApi`() {
        // Given
        val module = AppModule

        // When
        val newsApiService = module.provideNewsApi()

        // Then
        assertNotNull(newsApiService)
    }
}
