package br.com.zup.hellozupper.ui.pillars.view

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.zup.hellozupper.data.model.Pillar
import br.com.zup.hellozupper.databinding.PillarsItemBinding

class PillarsAdapter(
    private var pillarList: MutableList<Pillar>
) : RecyclerView.Adapter<PillarsAdapter.ViewHolder>() {

    class ViewHolder(val binding: PillarsItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun showPillarsInfo(pillar: Pillar) {
            binding.tvItemPillars.text = pillar.title
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            PillarsItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val pillars = pillarList[position]
        holder.showPillarsInfo(pillars)
    }

    override fun getItemCount() = pillarList.size

    @SuppressLint("NotifyDataSetChanged")
    fun updatePillarsList(newList: MutableList<Pillar>) {

        pillarList = newList
        notifyDataSetChanged()

    }
}