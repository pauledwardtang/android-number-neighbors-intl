package io.phatcat.numberneighborsinternational.network.di

import dagger.Binds
import dagger.Module
import io.phatcat.numberneighborsinternational.datasource.CountriesRemoteDataSource
import io.phatcat.numberneighborsinternational.datasource.PhoneVerificationResultDataSource

typealias CountriesRemoteSource = io.phatcat.numberneighborsinternational.countries.data.RemoteDataSource
typealias PhoneVerificationRemoteSource = io.phatcat.numberneighborsinternational.phoneverification.data.RemoteDataSource

@Module
abstract class DataSourceModule {
  @Binds
  internal abstract fun countriesRemoteDataSource(
    countriesRemoteDataSource: CountriesRemoteDataSource
  ): CountriesRemoteSource

  @Binds
  internal abstract fun phoneVerificationRemoteDataSource(
    phoneVerificationResultDataSource: PhoneVerificationResultDataSource
  ): PhoneVerificationRemoteSource

}
