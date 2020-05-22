package io.phatcat.numberneighborsinternational.persistence.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.phatcat.numberneighborsinternational.persistence.entity.CountryEntity

@Dao
interface CountryDao {

  @Query("SELECT * from Country")
  fun getAll(): List<CountryEntity>

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  fun insert(countries: List<CountryEntity>)

}
