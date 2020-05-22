package io.phatcat.numberneighborsinternational.serialization.di

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import io.phatcat.numberneighborsinternational.entity.Country
import io.phatcat.numberneighborsinternational.serialization.adapter.CountriesAdapter

@Module
object SerializationModule {

  @Provides
  @JvmStatic
  fun providesMoshi(): Moshi {
    val listType = Types.newParameterizedType(List::class.java, Country::class.java)
    return Moshi.Builder()
      .add(KotlinJsonAdapterFactory())
      .add(
        listType,
        CountriesAdapter()
      )
      .build()
  }

}
