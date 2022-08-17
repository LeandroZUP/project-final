package br.com.zup.hellozupper.ui.benefits.view

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.zup.hellozupper.data.model.Benefits
import br.com.zup.hellozupper.databinding.BenefitsItemBinding

class BenefitsAdapter(
    private var benefitsList: MutableList<Benefits>,
    private val clickBenefits: (benefits: Benefits) -> Unit
) : RecyclerView.Adapter<BenefitsAdapter.ViewHolder>() {

    class ViewHolder(val binding: BenefitsItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun showInformations(benefits: Benefits) {
            binding.tvItemButtonsBenefits.text = benefits.title
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            BenefitsItemBinding.inflate(LayoutInflater.from(parent.context),
                parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val benefits = benefitsList[position]
        holder.showInformations(benefits)
        holder.binding.cvItemBenefits.setOnClickListener {
            clickBenefits(benefits)
        }
    }
    override fun getItemCount() = benefitsList.size

    @SuppressLint("NotifyDataSetChanged")
    fun updateBenefitsList(newList: MutableList<Benefits>) {
        benefitsList = newList
        notifyDataSetChanged()
    }
}