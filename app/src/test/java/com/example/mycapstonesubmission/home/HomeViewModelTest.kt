package com.example.mycapstonesubmission.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.mycapstonesubmission.core.data.UiState
import com.example.mycapstonesubmission.core.data.source.local.entity.MoviesEntity
import com.example.mycapstonesubmission.core.domain.model.Movies
import com.example.mycapstonesubmission.core.domain.usecase.MoviesUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.argumentCaptor
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@OptIn(ExperimentalCoroutinesApi::class)
class HomeViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val testDispatcher = StandardTestDispatcher()
    private val moviesUseCase: MoviesUseCase = mock()
    private lateinit var homeViewModel: HomeViewModel

    private val dummyMovie = Movies(
        movieId = 1,
        title = "Inception",
        overview = "A thief who steals corporate secrets...",
        originalLanguage = "en",
        originalTitle = "Inception",
        video = false,
        posterPath = "/inception.jpg",
        backdropPath = "/backdrop.jpg",
        releaseDate = "2010-07-16",
        popularity = 100.0,
        voteAverage = 8.5,
        adult = false,
        voteCount = 12345,
        isFavorite = true
    )

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        val dummyFlow = flowOf(UiState.Success(listOf(dummyMovie)))
        whenever(moviesUseCase.getAllMovies()).thenReturn(dummyFlow)
        homeViewModel = HomeViewModel(moviesUseCase)
    }
    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `movies LiveData should emit UiState Success with dummy data`() = runTest {
        val observer = mock<Observer<UiState<List<Movies>>>>()
        homeViewModel.movies.observeForever(observer)
        advanceUntilIdle()
        val captor = argumentCaptor<UiState<List<Movies>>>()
        verify(observer).onChanged(captor.capture())
        val actualValue = captor.firstValue
        assertTrue(actualValue is UiState.Success)
        assertEquals(listOf(dummyMovie), (actualValue as UiState.Success).data)
        homeViewModel.movies.removeObserver(observer)
    }

}
