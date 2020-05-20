package io.phatcat.numberneighborsinternational

import io.phatcat.numberneighborsinternational.domain.entity.Country
import io.phatcat.numberneighborsinternational.domain.entity.PhoneResultModel
import io.phatcat.numberneighborsinternational.domain.usecase.GetCountryCodePrefixesUseCase
import io.phatcat.numberneighborsinternational.domain.usecase.GetPhoneNumberResultsUseCase
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.`is`
import org.junit.Assert.assertThat
import org.junit.Rule
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoTestRule

class MainActivityViewModelTest {
  @get:Rule val rule: MockitoTestRule = MockitoJUnit.testRule(this)

  @Mock lateinit var getCountriesUseCase: GetCountryCodePrefixesUseCase
  @Mock lateinit var getVerificationUseCase: GetPhoneNumberResultsUseCase

  @InjectMocks lateinit var viewModel: MainActivityViewModel

  @Test
  fun searchesForExactNumberOfNeighbors() = runBlocking {
    // Given
    val numResults = 5
    givenCountryResults(numResults)
    givenPhoneResults(numResults)

    // When
    val results = viewModel.searchClicked("1234567")

    // Then
    assertThat(results.size, `is`(numResults))
  }

  private suspend fun givenCountryResults(numResults: Int) {
    `when`(getCountriesUseCase.getCountryCodePrefixes()).then {
      val results = mutableListOf<Country>()
      for (i in 0 until numResults) {
        results.add(
          Country(
            countryCode = "$i",
            countryName = "$i",
            dialingCode = "+$i"
          )
        )
      }
      results
    }
  }

  private suspend fun givenPhoneResults(numResults: Int) {
    `when`(getVerificationUseCase.getPhoneNumbers(any(), any())).then {
      val results = mutableListOf<PhoneResultModel>()
      for (i in 0 until numResults) {
        results.add(
          PhoneResultModel(
            success = true,
            countryName = "$i",
            dialingCode = "+$i"
          )
        )
      }
      results
    }
  }

  // Workaround for null default values
  private fun <T> any(): T = Mockito.any<T>()

}