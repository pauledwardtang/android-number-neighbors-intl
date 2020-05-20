package io.phatcat.numberneighborsinternational

import io.phatcat.numberneighborsinternational.domain.usecase.GetCountriesUseCase
import io.phatcat.numberneighborsinternational.domain.usecase.GetPhoneNumberResultsUseCase
import io.phatcat.numberneighborsinternational.network.service.CountriesService
import io.phatcat.numberneighborsinternational.network.service.PhoneNumberService
import io.phatcat.numberneighborsinternational.usecase.GetCountriesUseCaseImpl
import io.phatcat.numberneighborsinternational.usecase.GetPhoneNumberResultsUseCaseImpl

class AppApplication : BaseApplication() {
  override val getCountriesUseCase: GetCountriesUseCase by lazy {
    GetCountriesUseCaseImpl(retrofit.create(CountriesService::class.java))
  }

  override val getPhoneNumberResultsUseCase: GetPhoneNumberResultsUseCase by lazy {
    GetPhoneNumberResultsUseCaseImpl(retrofit.create(PhoneNumberService::class.java))
  }
}
