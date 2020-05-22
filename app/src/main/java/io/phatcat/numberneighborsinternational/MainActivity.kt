package io.phatcat.numberneighborsinternational

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import com.google.android.material.snackbar.Snackbar
import io.phatcat.numberneighborsinternational.databinding.ActivityMainBinding
import io.phatcat.numberneighborsinternational.results.PhoneResultModel
import io.phatcat.numberneighborsinternational.results.ResultsDialogFragment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

  private var _binding: ActivityMainBinding? = null
  private val binding get() = requireNotNull(_binding)

  private val viewModel by viewModels<MainActivityViewModel> {
    MainActivityViewModelFactory(application)
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    _binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
    binding.searchButton.setOnClickListener {
      onSearchClicked(binding.phoneNumber.text.toString())
    }
  }

  override fun onDestroy() {
    super.onDestroy()
    _binding?.unbind()
    _binding = null
  }

  private fun onSearchClicked(phoneNumber: String) {
    onLoading(true)
    lifecycleScope.launch(Dispatchers.Main) {

      try {
        val results = withContext(Dispatchers.IO) { viewModel.searchClicked(phoneNumber) }
        showResults(results)

      } catch (e: Exception) {
        e.printStackTrace()
        showSearchErrorMessage(getString(R.string.generic_verification_error))

      } finally {
        onLoading(false)
      }
    }
  }

  private fun showSearchErrorMessage(message: String) {
    Snackbar.make(binding.root, message, Snackbar.LENGTH_LONG).show()
  }

  private fun showResults(results: List<PhoneResultModel>) {
    // Alternatively, the dialog or a Fragment can handle loading states and communicate exclusively
    // with the VM while this class simply validates the phone number or just fetches countries
    ResultsDialogFragment.create(results).show(supportFragmentManager, null)
  }

  private fun onLoading(isLoading: Boolean) {
    binding.searchButton.isEnabled = !isLoading
    binding.progressBar.isVisible = isLoading
  }

}
