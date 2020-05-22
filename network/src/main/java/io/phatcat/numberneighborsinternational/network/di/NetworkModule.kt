package io.phatcat.numberneighborsinternational.network.di

import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import io.phatcat.numberneighborsinternational.entity.Country
import io.phatcat.numberneighborsinternational.network.adapter.CountriesAdapter
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

// Note that HTTPS is available only for paid subscriptions
private const val BASE_URL = "http://apilayer.net/api/"

@Module(includes = [DataSourceModule::class])
object NetworkModule {

  @Provides
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

  @Provides
  fun providesRetrofit(moshi: Moshi): Retrofit {
    return Retrofit.Builder()
      .baseUrl(BASE_URL)
      .addConverterFactory(MoshiConverterFactory.create(moshi))
      .build()
  }

}
