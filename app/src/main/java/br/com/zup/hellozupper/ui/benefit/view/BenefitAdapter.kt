package br.com.zup.hellozupper.ui.benefit.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.zup.hellozupper.databinding.BenefitItemBinding

class BenefitAdapter (
    private var benefitList: MutableList<Benefit>,
    private val itemBenefit: (category: Benefit) -> Unit
) : RecyclerView.Adapter<BenefitAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = BenefitItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount() = benefitList.size

    class ViewHolder(private val binding: BenefitItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun addBenefitItem(category: Benefit) {
            binding.tvBenefitItem.text = category.content
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val benefitItem = benefitList[position]
    }
}

//TODO Create data class Benefit

data class Benefit(
    val content: String
)