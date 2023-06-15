package com.example.cheat.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cheat.databinding.ItemListInstructionBinding

class InstructionAdapter(private val instructionList: List<String>) : RecyclerView.Adapter<InstructionAdapter.ViewHolder>() {
    inner class ViewHolder(private val binding: ItemListInstructionBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(instruction: String) {
            binding.listInstruction.text = instruction
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): InstructionAdapter.ViewHolder {
        val view = ItemListInstructionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: InstructionAdapter.ViewHolder, position: Int) {
        val instruction = instructionList[position]
        holder.bind("${(position + 1)}. $instruction")
    }

    override fun getItemCount(): Int = instructionList.size

}