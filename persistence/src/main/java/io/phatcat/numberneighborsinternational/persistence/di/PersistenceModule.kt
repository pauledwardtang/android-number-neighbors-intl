package io.phatcat.numberneighborsinternational.persistence.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import io.phatcat.numberneighborsinternational.persistence.CountriesDatabase
import javax.inject.Singleton

@Module(includes = [DataSourceModule::class])
object PersistenceModule {

  @Provides
  @Singleton
  fun database(context: Context): CountriesDatabase =
    Room.databaseBuilder(context, CountriesDatabase::class.java, "Database").build()

  @Provides
  fun dao(countriesDatabase: CountriesDatabase) = countriesDatabase.countryDao
}
