package io.phatcat.numberneighborsinternational.usecase

import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import io.phatcat.numberneighborsinternational.domain.entity.Country
import io.phatcat.numberneighborsinternational.network.adapter.CountriesAdapter
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertTrue
import org.junit.Test

class GetCountryCodePrefixesUseCaseTest {

  @Test
  fun parsesCountriesFromMoshi() = runBlocking {
    val listType = Types.newParameterizedType(List::class.java, Country::class.java)

    val moshi = Moshi.Builder()
      .add(KotlinJsonAdapterFactory())
      .add(listType, CountriesAdapter())
      .build()

    val countries = GetCountryCodePrefixesUseCaseImpl(moshi).getCountryCodePrefixes()
    assertTrue(countries.size == 232)
  }

}
