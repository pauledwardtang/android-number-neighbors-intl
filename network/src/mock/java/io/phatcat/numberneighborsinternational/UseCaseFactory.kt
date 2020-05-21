package io.phatcat.numberneighborsinternational

import io.phatcat.numberneighborsinternational.network.AbstractUseCaseFactory
import io.phatcat.numberneighborsinternational.usecase.GetCountriesUseCase
import io.phatcat.numberneighborsinternational.usecase.GetPhoneNumberResultsUseCase
import io.phatcat.numberneighborsinternational.usecase.MockGetCountriesUseCase
import io.phatcat.numberneighborsinternational.usecase.MockGetPhoneNumberResultsUseCase

class UseCaseFactory : AbstractUseCaseFactory() {
  override val getCountriesUseCase: GetCountriesUseCase by lazy {
    MockGetCountriesUseCase(moshi)
  }

  override val getPhoneNumberResultsUseCase: GetPhoneNumberResultsUseCase by lazy {
    MockGetPhoneNumberResultsUseCase()
  }
}
