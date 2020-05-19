package io.phatcat.numberneighborsinternational

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import io.phatcat.numberneighborsinternational.network.service.PhoneNumberService
import io.phatcat.numberneighborsinternational.usecase.GetCountryCodePrefixesUseCaseImpl
import io.phatcat.numberneighborsinternational.usecase.GetPhoneNumberResultsUseCaseImpl

class MainActivityViewModelFactory(val context: Context) : ViewModelProvider.Factory {
  override fun <T : ViewModel?> create(modelClass: Class<T>): T {
    require(modelClass.isAssignableFrom(MainActivityViewModel::class.java))

    val retrofit = (context.applicationContext as AppApplication).retrofit

    val getCountryCodePrefixesUseCase = GetCountryCodePrefixesUseCaseImpl()
    val getPhoneNumberResultsUseCase =
      GetPhoneNumberResultsUseCaseImpl(retrofit.create(PhoneNumberService::class.java))

    return MainActivityViewModel(
      getCountryCodePrefixesUseCase,
      getPhoneNumberResultsUseCase
    ) as T
  }
}
