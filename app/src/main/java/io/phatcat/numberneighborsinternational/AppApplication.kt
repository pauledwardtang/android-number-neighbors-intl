package io.phatcat.numberneighborsinternational

import android.app.Application
import io.phatcat.numberneighborsinternational.application.port.input.GetCountriesUseCase
import io.phatcat.numberneighborsinternational.application.port.input.GetPhoneNumberResultsUseCase
import io.phatcat.numberneighborsinternational.network.di.DaggerApplicationComponent

class AppApplication : Application() {

  lateinit var getCountriesUseCase: GetCountriesUseCase
  lateinit var getPhoneNumberResultsUseCase: GetPhoneNumberResultsUseCase

  override fun onCreate() {
    super.onCreate()

    val component = DaggerApplicationComponent.factory().create(this)
    getCountriesUseCase = component.getCountriesUseCase
    getPhoneNumberResultsUseCase = component.getPhoneNumberResultsUseCase
  }

}
