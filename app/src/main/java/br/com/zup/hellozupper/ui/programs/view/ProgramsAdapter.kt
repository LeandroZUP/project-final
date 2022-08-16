package br.com.zup.hellozupper.ui.programs.view

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.zup.hellozupper.data.model.Programs
import br.com.zup.hellozupper.databinding.ProgramsItemBinding

class ProgramsAdapter(
    private var programsList: MutableList<Programs>,
    private val clickPrograms: (programs: Programs) -> Unit
) : RecyclerView.Adapter<ProgramsAdapter.ViewHolder>() {

    class ViewHolder(val binding: ProgramsItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun showInformations(programs: Programs) {
            binding.tvItemPrograms.text = programs.title
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ProgramsItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val programs = programsList[position]
        holder.showInformations(programs)
        holder.binding.cvItemPrograms.setOnClickListener {
            clickPrograms(programs)
        }
    }

    override fun getItemCount() = programsList.size

    fun updateProgramsList(newList: MutableList<Programs>) {
        programsList = newList
        notifyDataSetChanged()
    }
}