package io.phatcat.numberneighborsinternational.application.port

import io.phatcat.numberneighborsinternational.application.port.output.GetCountriesPort
import io.phatcat.numberneighborsinternational.application.port.output.StoreCountriesPort
import io.phatcat.numberneighborsinternational.entity.Country
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Rule
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoTestRule

class CountryServiceTest {
  @get:Rule val rule: MockitoTestRule = MockitoJUnit.testRule(this)

  @Mock lateinit var getCountriesPort: GetCountriesPort
  @Mock lateinit var storeCountriesPort: StoreCountriesPort

  @InjectMocks private lateinit var countryService: CountryService

  @Test(expected = IllegalArgumentException::class)
  fun `GIVEN no countries retrieved WHEN getting prefixes THEN throws`() = runBlocking {
    // Given
    givenNoCountriesRetrieved()

    // When
    countryService.getCountryCodePrefixes()

    // Then
    // Exception will have been thrown, otherwise this will automatically fail
    Unit
  }

  @Test(expected = IllegalArgumentException::class)
  fun `GIVEN countries size too small WHEN getting countries THEN throws`() = runBlocking {
    // Given
    givenCountriesRetrieved(1)

    // When
    countryService.getCountryCodePrefixes()

    // Then
    // Exception will have been thrown, otherwise this will automatically fail
    Unit
  }

  @Test(expected = IllegalArgumentException::class)
  fun `GIVEN countries are duplicated WHEN randomizing countries THEN throws`() = runBlocking {
    // Given
    givenCountriesWithDuplicates()

    // When
    countryService.getCountryCodePrefixes()

    // Then
    // Exception will have been thrown, otherwise this will automatically fail
    Unit
  }

  @Test
  fun `GIVEN storing fails WHEN storing countries THEN returns data`() = runBlocking {
    // Given
    givenCountriesRetrieved()
    givenStoringCountriesFails()

    // When
    val countries = countryService.getCountryCodePrefixes()

    // Then
    assertThat(countries.size, `is`(RANDOM_COUNTRIES_COUNT))
  }

  @Test
  fun `GIVEN storing succeeds WHEN storing countries THEN returns data`() = runBlocking {
    // Given
    givenCountriesRetrieved()

    // When
    val countries = countryService.getCountryCodePrefixes()

    // Then
    assertThat(countries.size, `is`(RANDOM_COUNTRIES_COUNT))
  }

  private fun givenNoCountriesRetrieved() = runBlocking {
    `when`(getCountriesPort.getCountries()).then { emptyList<Country>() }
  }

  private fun givenCountriesRetrieved(numCountries: Int = 10) = runBlocking {
    `when`(getCountriesPort.getCountries()).then {
      1.rangeTo(numCountries).map {
        Country(
          name = "$it",
          countryCode = "$it",
          dialingCode = "$it"
        )
      }
    }
  }

  private fun givenCountriesWithDuplicates(numCountries: Int = 10) = runBlocking {
    `when`(getCountriesPort.getCountries()).then {
      1.rangeTo(numCountries).map {
        Country(
          name = "USA",
          countryCode = "US",
          dialingCode = "+1"
        )
      }
    }
  }

  private fun givenStoringCountriesFails() = runBlocking {
    `when`(storeCountriesPort.storeCountries(any())).then { throw Exception("Storing failed on purpose") }
  }

  @Suppress("RemoveExplicitTypeArguments")
  private fun <T> any(): T = Mockito.any<T>()
}
