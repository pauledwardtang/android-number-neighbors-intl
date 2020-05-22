package io.phatcat.numberneighborsinternational.persistence.di

import dagger.Binds
import dagger.Module
import io.phatcat.numberneighborsinternational.countries.data.LocalDataSource
import io.phatcat.numberneighborsinternational.persistence.datasource.CountriesLocalDataSource

@Module
internal abstract class DataSourceModule {
  @Binds
  abstract fun bindsCountriesLocalDataSource(
    countriesLocalDataSource: CountriesLocalDataSource
  ): LocalDataSource

}
