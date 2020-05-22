package io.phatcat.numberneighborsinternational.network.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import io.phatcat.numberneighborsinternational.datasource.CountriesRemoteDataSource
import io.phatcat.numberneighborsinternational.datasource.PhoneVerificationResultDataSource
import io.phatcat.numberneighborsinternational.network.service.CountriesService
import io.phatcat.numberneighborsinternational.network.service.PhoneNumberService
import retrofit2.Retrofit

typealias CountriesRemoteSource = io.phatcat.numberneighborsinternational.countries.data.RemoteDataSource
typealias PhoneVerificationRemoteSource = io.phatcat.numberneighborsinternational.phoneverification.data.RemoteDataSource

@Module
abstract class DataSourceModule {

  companion object {
    @Provides
    @JvmStatic
    internal fun providesCountriesService(retrofit: Retrofit): CountriesService {
      return retrofit.create(CountriesService::class.java)
    }

    @Provides
    @JvmStatic
    internal fun providesPhoneNumberService(retrofit: Retrofit): PhoneNumberService {
      return retrofit.create(PhoneNumberService::class.java)
    }
  }

  @Binds
  internal abstract fun countriesRemoteDataSource(
    countriesRemoteDataSource: CountriesRemoteDataSource
  ): CountriesRemoteSource

  @Binds
  internal abstract fun phoneVerificationRemoteDataSource(
    phoneVerificationResultDataSource: PhoneVerificationResultDataSource
  ): PhoneVerificationRemoteSource

}
