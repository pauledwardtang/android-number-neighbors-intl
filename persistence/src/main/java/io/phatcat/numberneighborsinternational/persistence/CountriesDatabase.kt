package io.phatcat.numberneighborsinternational.persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import io.phatcat.numberneighborsinternational.persistence.dao.CountryDao
import io.phatcat.numberneighborsinternational.persistence.entity.CountryEntity

@Database(
  version = 1,
  entities = [CountryEntity::class]
)
abstract class CountriesDatabase : RoomDatabase() {

  abstract val countryDao: CountryDao

}
