package io.phatcat.numberneighborsinternational.results

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import io.phatcat.numberneighborsinternational.R
import io.phatcat.numberneighborsinternational.databinding.FragmentResultsDialogBinding

private const val ARG_RESULTS = "results"

class ResultsDialogFragment : DialogFragment() {

  private var _binding: FragmentResultsDialogBinding? = null
  private val binding get() = requireNotNull(_binding)

  companion object {
    fun create(results: List<PhoneResultModel>): ResultsDialogFragment {
      return ResultsDialogFragment().apply {
        arguments = bundleOf(ARG_RESULTS to results)
      }
    }
  }

  override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
    val bundle = savedInstanceState ?: requireArguments()
    val results = bundle.get(ARG_RESULTS) as List<PhoneResultModel>
    val (titleResId, messageResId) = getDialogResourceIds(results)

    _binding = FragmentResultsDialogBinding.inflate(layoutInflater, null, false)
    binding.listView.adapter = ResultsListAdapter(results)

    return AlertDialog.Builder(requireActivity(), R.style.AppTheme_AlertDialog)
      .setTitle(titleResId)
      .setMessage(messageResId)
      .setView(binding.root)
      .setPositiveButton(R.string.result_dialog_positive_button) { _, _ -> }
      .create()
  }

  override fun onDestroy() {
    super.onDestroy()
    _binding?.unbind()
    _binding = null
  }

  private fun getDialogResourceIds(results: List<PhoneResultModel>): Pair<Int, Int> {
    return when {
      results.isEmpty() -> Pair(R.string.error_result_title, R.string.error_message)
      results.any { it.success } -> Pair(R.string.success_result_title, R.string.success_message)
      else -> Pair(R.string.failed_result_title, R.string.failed_message)
    }
  }

}
