package io.phatcat.numberneighborsinternational.di

import dagger.Binds
import dagger.Module
import io.phatcat.numberneighborsinternational.application.port.CountryService
import io.phatcat.numberneighborsinternational.application.port.PhoneVerificationService
import io.phatcat.numberneighborsinternational.application.port.input.GetCountriesUseCase
import io.phatcat.numberneighborsinternational.application.port.input.GetPhoneNumberResultsUseCase

@Module
abstract class DomainModule {
  @Binds
  internal abstract fun getCountriesUseCase(countryService: CountryService): GetCountriesUseCase

  @Binds
  internal abstract fun getPhoneNumberResultsUseCase(phoneVerificationService: PhoneVerificationService): GetPhoneNumberResultsUseCase
}
