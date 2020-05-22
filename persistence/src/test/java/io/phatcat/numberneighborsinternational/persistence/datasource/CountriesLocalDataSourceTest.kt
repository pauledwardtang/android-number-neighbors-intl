package io.phatcat.numberneighborsinternational.persistence.datasource

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import io.phatcat.numberneighborsinternational.entity.Country
import io.phatcat.numberneighborsinternational.persistence.CountriesDatabase
import io.phatcat.numberneighborsinternational.persistence.dao.CountryDao
import io.phatcat.numberneighborsinternational.persistence.entity.CountryEntity
import kotlinx.coroutines.runBlocking
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.Assert.assertArrayEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@Config(manifest = Config.NONE)
@RunWith(RobolectricTestRunner::class)
class CountriesLocalDataSourceTest {
  @get:Rule var instantTaskExecutorRule = InstantTaskExecutorRule()

  private lateinit var dao: CountryDao
  private lateinit var countriesLocalDataSource: CountriesLocalDataSource

  @Before
  fun setUp() {
    val context = InstrumentationRegistry.getInstrumentation().targetContext
    val db = Room.inMemoryDatabaseBuilder(context, CountriesDatabase::class.java)
      .allowMainThreadQueries()
      .build()

    dao = db.countryDao
    countriesLocalDataSource = CountriesLocalDataSource(dao)
  }

  @Test
  fun `GIVEN empty database WHEN getting countries THEN returns empty`() = runBlocking {
    // Given
    // Do Nothing

    // When
    val countries = countriesLocalDataSource.getCountries()

    // Then
    assertArrayEquals(emptyArray<Country>(), countries.toTypedArray())
  }

  @Test
  fun `GIVEN database populated WHEN getting countries THEN returns countries`() = runBlocking {
    // Given
    val quantity = 50
    givenDatabasePopulated(quantity)

    // When
    val countries = countriesLocalDataSource.getCountries()

    assertThat(quantity, `is`(countries.size))
  }

  @Test
  fun `GIVEN database empty WHEN storing countries THEN operation successful`() = runBlocking {
    // Given
    val quantity = 50
    val countries = givenRandomCountries(quantity)

    // When
    countriesLocalDataSource.storeCountries(countries)

    // Then
    assertThat(quantity, `is`(dao.getAll().size))
  }

  @Test
  fun `GIVEN database populated WHEN storing countries THEN does not duplicate`() = runBlocking {
    // Given
    val quantity = 50
    val countries = givenRandomCountries(quantity)

    // When
    countriesLocalDataSource.storeCountries(countries)
    countriesLocalDataSource.storeCountries(countries)

    // Then
    assertThat(quantity, `is`(dao.getAll().size))
  }

  @Test
  fun `GIVEN empty database WHEN verifying entries exist THEN false`() = runBlocking {
    // Given
    // Do nothing

    // When
    val hasCountries = countriesLocalDataSource.hasCountries()

    // Then
    assertFalse(hasCountries)
  }

  @Test
  fun `GIVEN database populated WHEN verifying entries exist THEN true`() = runBlocking {
    // Given
    givenDatabasePopulated()

    // When
    val hasCountries = countriesLocalDataSource.hasCountries()

    // Then
    assertTrue(hasCountries)
  }

  private fun givenDatabasePopulated(quantity: Int = 10) {
    val countries = 0.until(quantity).map { CountryEntity("$it", "$it", "$it") }
    dao.insert(countries)
  }

  private fun givenRandomCountries(quantity: Int): List<Country> {
    return 0.until(quantity).map { Country("$it", "$it", "$it") }
  }

}
