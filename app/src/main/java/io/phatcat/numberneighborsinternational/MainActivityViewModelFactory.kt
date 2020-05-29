package io.phatcat.numberneighborsinternational

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject

// This is ok for now, but won't scale with a large amount of ViewModels
class MainActivityViewModelFactory @Inject constructor(
  private val viewModel: MainActivityViewModel
) : ViewModelProvider.Factory {

  override fun <T : ViewModel?> create(modelClass: Class<T>): T {
    require(modelClass.isAssignableFrom(MainActivityViewModel::class.java))
    @Suppress("UNCHECKED_CAST")
    return viewModel as T
  }
}
