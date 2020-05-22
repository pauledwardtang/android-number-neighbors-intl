package io.phatcat.numberneighborsinternational.data.di

import dagger.Binds
import dagger.Module
import io.phatcat.numberneighborsinternational.application.port.output.GetCountriesPort
import io.phatcat.numberneighborsinternational.application.port.output.GetPhoneNumberVerificationPort
import io.phatcat.numberneighborsinternational.application.port.output.StoreCountriesPort
import io.phatcat.numberneighborsinternational.countries.CountriesRepository
import io.phatcat.numberneighborsinternational.phoneverification.PhoneVerificationRepository

@Module
interface DataModule {

  @Binds
  fun storeCountriesPort(countriesRepository: CountriesRepository): StoreCountriesPort

  @Binds
  fun getCountryPort(countriesRepository: CountriesRepository): GetCountriesPort

  @Binds
  fun getPhoneNumberVerificationPort(phoneVerificationRepository: PhoneVerificationRepository): GetPhoneNumberVerificationPort

}
