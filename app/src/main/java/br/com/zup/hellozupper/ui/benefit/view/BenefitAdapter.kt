package br.com.zup.hellozupper.ui.benefit.view

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.zup.hellozupper.data.model.benefit.Benefit
import br.com.zup.hellozupper.databinding.BenefitItemBinding

class BenefitAdapter (
    private var benefitsList: MutableList<Benefit>,
    private val clickBenefit: (benefit: Benefit) -> Unit
) : RecyclerView.Adapter<BenefitAdapter.ViewHolder>() {

    //TODO TextView Benefit Item
    class ViewHolder(val binding: BenefitItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun showBenefitsInfo(benefit: Benefit) {
            binding.tvItemBenefit.text = Benefit.title
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = BenefitItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
            )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val benefits = benefitsList[position]
        holder.showBenefitsInfo(benefits)
        holder.binding.cvItemBenefit.setOnClickListener {
            clickBenefit(benefits)
        }
    }

    override fun getItemCount() = benefitsList.size

    @SuppressLint("NotifyDataSetChanged")
    fun updateBenefitsList(newList: MutableList<Benefit>) {
        benefitsList = newList
        notifyDataSetChanged()
    }
}