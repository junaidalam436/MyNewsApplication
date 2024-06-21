package com.example.mynewsapplication.database

import android.content.Context
import androidx.room.Room
import junit.framework.TestCase.assertNotNull
import kotlinx.coroutines.test.TestCoroutineDispatcher
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class NewsDatabaseTest {

    // Mock Application context
    @Mock
    lateinit var mockContext: Context

    // Test coroutine dispatcher
    private val testDispatcher = TestCoroutineDispatcher()

    // SUT (System Under Test)
    lateinit var newsDatabase: NewsDatabase

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        // Using an in-memory database for testing
        newsDatabase = Room.inMemoryDatabaseBuilder(mockContext, NewsDatabase::class.java)
            .allowMainThreadQueries()
            .build()
    }

    @After
    fun tearDown() {
        newsDatabase.close()
        testDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun `test newsDao`() {
        // Given - initialized in @Before

        // When
        val newsDao = newsDatabase.newsDao()

        // Then
        assertNotNull(newsDao)
    }
}
