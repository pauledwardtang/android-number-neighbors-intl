package io.phatcat.numberneighborsinternational

import io.phatcat.numberneighborsinternational.usecase.GetCountriesUseCase
import io.phatcat.numberneighborsinternational.usecase.GetPhoneNumberResultsUseCase
import io.phatcat.numberneighborsinternational.usecase.MockGetCountriesUseCase
import io.phatcat.numberneighborsinternational.usecase.MockGetPhoneNumberResultsUseCase

class AppApplication : BaseApplication() {
  override val getCountriesUseCase: GetCountriesUseCase by lazy {
    MockGetCountriesUseCase(moshi)
  }

  override val getPhoneNumberResultsUseCase: GetPhoneNumberResultsUseCase by lazy {
    MockGetPhoneNumberResultsUseCase()
  }
}
