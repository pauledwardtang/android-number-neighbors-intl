package io.phatcat.numberneighborsinternational.countries

import io.phatcat.numberneighborsinternational.countries.data.LocalDataSource
import io.phatcat.numberneighborsinternational.countries.data.RemoteDataSource
import io.phatcat.numberneighborsinternational.entity.Country
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertArrayEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.never
import org.mockito.Mockito.verify
import org.mockito.Mockito.verifyNoInteractions
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoTestRule

class CountriesRepositoryTest {
  @get:Rule val rule: MockitoTestRule = MockitoJUnit.testRule(this)

  @Mock lateinit var localDataSource: LocalDataSource
  @Mock lateinit var remoteDataSource: RemoteDataSource

  @InjectMocks
  private lateinit var countriesRepository: CountriesRepository

  private lateinit var mockResults: List<Country>

  @Before
  fun setUp() {
    mockResults = 0.until(10).map { Country("$it", "$it", "$it") }
  }

  @Test
  fun `GIVEN has local results WHEN retrieving THEN gets local results`() = runBlocking {
    // Given
    givenLocalDataSourceHasCountries()

    // When
    val countries = countriesRepository.getCountries()

    // Then
    verifyNoInteractions(remoteDataSource)
    assertArrayEquals(mockResults.toTypedArray(), countries.toTypedArray())
  }

  @Test
  fun `GIVEN no local results WHEN retrieving THEN gets remote results`() = runBlocking {
    // Given
    givenLocalDataSourceDoesNotHaveCountries()
    givenRemoteSourceGetsCountries()

    // When
    val countries = countriesRepository.getCountries()

    // Then
    assertArrayEquals(mockResults.toTypedArray(), countries.toTypedArray())
  }

  @Test
  fun `GIVEN has local results WHEN storing same results THEN doesn't store`() = runBlocking {
    // Given
    givenLocalDataSourceHasCountries()

    // When
    countriesRepository.storeCountries(mockResults)

    // Then
    verify(localDataSource, never()).storeCountries(mockResults)
    verifyNoInteractions(remoteDataSource)
  }

  @Test
  fun `GIVEN has local results WHEN storing different results THEN stores`() = runBlocking {
    // Given
    givenLocalDataSourceHasCountries()

    val subList = mockResults.subList(1, mockResults.size - 1)

    // When
    countriesRepository.storeCountries(subList)

    // Then
    verify(localDataSource).storeCountries(subList)
    verifyNoInteractions(remoteDataSource)
  }

  @Test
  fun `GIVEN no local results WHEN storing results THEN stores`() = runBlocking {
    // Given
    givenLocalDataSourceDoesNotHaveCountries()

    // When
    countriesRepository.storeCountries(mockResults)

    // Then
    verify(localDataSource).storeCountries(mockResults)
    verifyNoInteractions(remoteDataSource)
  }

  private suspend fun givenLocalDataSourceHasCountries() {
    `when`(localDataSource.hasCountries()).then { true }
    `when`(localDataSource.getCountries()).then { mockResults }
  }

  private suspend fun givenLocalDataSourceDoesNotHaveCountries() {
    `when`(localDataSource.hasCountries()).then { false }
    `when`(localDataSource.getCountries()).then { emptyList<Country>() }
  }

  private suspend fun givenRemoteSourceGetsCountries() {
    `when`(remoteDataSource.fetchCountries()).then { mockResults }
  }
}
