package io.phatcat.numberneighborsinternational

import android.app.Application
import io.phatcat.numberneighborsinternational.usecase.GetCountriesUseCase
import io.phatcat.numberneighborsinternational.usecase.GetPhoneNumberResultsUseCase

class AppApplication : Application() {

  lateinit var getCountriesUseCase: GetCountriesUseCase
  lateinit var getPhoneNumberResultsUseCase: GetPhoneNumberResultsUseCase

  override fun onCreate() {
    super.onCreate()
    val factory = UseCaseFactory()
    getCountriesUseCase = factory.getCountriesUseCase
    getPhoneNumberResultsUseCase = factory.getPhoneNumberResultsUseCase
  }

}
