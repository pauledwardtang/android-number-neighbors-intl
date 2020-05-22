package io.phatcat.numberneighborsinternational.countries

import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import io.phatcat.numberneighborsinternational.datasource.CountriesRemoteDataSource
import io.phatcat.numberneighborsinternational.entity.Country
import io.phatcat.numberneighborsinternational.network.adapter.CountriesAdapter
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers
import org.junit.Assert
import org.junit.Test

class CountriesRemoteDataSourceTest {

  @Test
  fun parsesCountriesFromMoshi() = runBlocking {
    val listType = Types.newParameterizedType(List::class.java, Country::class.java)

    val moshi = Moshi.Builder()
      .add(KotlinJsonAdapterFactory())
      .add(
        listType,
        CountriesAdapter()
      )
      .build()

    val countries = CountriesRemoteDataSource(
      moshi
    ).fetchCountries()
    Assert.assertThat(countries.size, CoreMatchers.`is`(232))

  }

}
