package io.phatcat.numberneighborsinternational.results

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import io.phatcat.numberneighborsinternational.R
import io.phatcat.numberneighborsinternational.databinding.ResultItemBinding
import io.phatcat.numberneighborsinternational.results.ResultsListAdapter.NeighborsViewHolder

internal class ResultsListAdapter(
  private val results: List<PhoneResultModel>
) : RecyclerView.Adapter<NeighborsViewHolder>() {

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NeighborsViewHolder {
    val view = LayoutInflater.from(parent.context).inflate(R.layout.result_item, parent, false)
    val binding = ResultItemBinding.bind(view)
    return NeighborsViewHolder(binding)
  }

  override fun getItemCount() = results.size

  override fun onBindViewHolder(holder: NeighborsViewHolder, position: Int) {
    holder.bind(results[position])
  }

  class NeighborsViewHolder(private val binding: ResultItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(result: PhoneResultModel) {
      val imageRes = if (result.success)
        R.drawable.ic_check_circle_black_24dp else R.drawable.ic_cancel_black_24dp

      binding.successImage.setImageResource(imageRes)
      binding.countryName.text = result.countryName
      binding.countryCodePrefix.text = result.dialingCode
    }
  }

}
