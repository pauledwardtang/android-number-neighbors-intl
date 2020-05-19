package io.phatcat.numberneighborsinternational.network.adapter

import com.squareup.moshi.JsonDataException
import com.squareup.moshi.JsonReader
import io.phatcat.numberneighborsinternational.domain.entity.Country

/**
 * Parses country data from numverify country response. Manual parsing is necessary because the JSON
 * structure doesn't contain an array of objects, but instead a variable series of named objects.
 */
class CountriesAdapter : ReadOnlyJsonAdapter<List<Country>>() {

  private val options: JsonReader.Options = JsonReader.Options.of("country_name", "dialling_code")

  override fun fromJson(reader: JsonReader): List<Country> {
    if (!reader.hasNext()) {
      // Not yet handling error JSON from the API (missing/invalid access key etc)
      throw JsonDataException("Invalid data format")
    }

    lateinit var countryCode: String
    lateinit var countryName: String
    lateinit var dialingCode: String

    val countries = mutableListOf<Country>()
    reader.beginObject()
    while (reader.hasNext()) {
      countryCode = reader.nextName()
      reader.beginObject()

      while (reader.hasNext()) {
        when (reader.selectName(options)) {
          0 -> countryName = reader.nextString()
          1 -> dialingCode = reader.nextString()
        }
      }

      reader.endObject()
      countries.add(Country(countryCode, countryName, dialingCode))
    }
    reader.endObject()

    return countries
  }
}