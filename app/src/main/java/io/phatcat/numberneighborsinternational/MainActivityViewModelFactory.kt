package io.phatcat.numberneighborsinternational

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class MainActivityViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
  override fun <T : ViewModel?> create(modelClass: Class<T>): T {
    require(modelClass.isAssignableFrom(MainActivityViewModel::class.java))

    val application = (context.applicationContext as AppApplication)

    @Suppress("UNCHECKED_CAST")
    return MainActivityViewModel(
      application.getCountriesUseCase,
      application.getPhoneNumberResultsUseCase
    ) as T
  }
}
