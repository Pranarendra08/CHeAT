package com.example.cheat.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cheat.databinding.ItemListIngredientBinding

class IngredientAdapter(private val ingredientList: List<String>) : RecyclerView.Adapter<IngredientAdapter.ViewHolder>() {
    inner class ViewHolder(private val binding: ItemListIngredientBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(ingredient: String) {
            binding.listIngredient.text = ingredient
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): IngredientAdapter.ViewHolder {
        val view = ItemListIngredientBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: IngredientAdapter.ViewHolder, position: Int) {
        val ingredient = ingredientList[position]
        holder.bind(ingredient)
    }

    override fun getItemCount(): Int = ingredientList.size
}