package io.phatcat.numberneighborsinternational.data.di

import dagger.Binds
import dagger.Module
import io.phatcat.numberneighborsinternational.application.port.output.GetCountriesPort
import io.phatcat.numberneighborsinternational.application.port.output.GetPhoneNumberVerificationPort
import io.phatcat.numberneighborsinternational.application.port.output.StoreCountriesPort
import io.phatcat.numberneighborsinternational.countries.CountriesRepository
import io.phatcat.numberneighborsinternational.phoneverification.PhoneVerificationRepository

@Module
abstract class DataModule {

  @Binds
  internal abstract fun storeCountriesPort(countriesRepository: CountriesRepository): StoreCountriesPort

  @Binds
  internal abstract fun getCountryPort(countriesRepository: CountriesRepository): GetCountriesPort

  @Binds
  internal abstract fun getPhoneNumberVerificationPort(phoneVerificationRepository: PhoneVerificationRepository): GetPhoneNumberVerificationPort

}
