package com.example.cheat.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.cheat.R
import com.example.cheat.adapter.IngredientAdapter
import com.example.cheat.adapter.InstructionAdapter
import com.example.cheat.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private val detailViewModel: DetailViewModel by viewModels { DetailViewModelFactory(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val id = intent.getStringExtra(EXTRA_ID)

        if (id != null) {
            detailViewModel.getRecipeDetail(id)
        }

        detailViewModel.detailRecipe.observe(this) {
            binding.apply {
                val imgLinkSplit = it.image.split("/")
                val idLink = imgLinkSplit[5].toString()
                Glide.with(this@DetailActivity)
                    .load(BASE_IMAGE_URL + idLink)
                    .centerCrop()
                    .into(binding.ivDetailFotoMakanan)
                tvChatNamaMakanan.text = it.recipeName
                tvDetailServing.text = "Servings: ${it.servings}"
                tvDetailServingSize.text = "Serving size: ${it.servingSize}"
                tvDetailCalories.text = "Calories: ${it.calories}"

                val ingredientList = it.ingredients
                val adapterRvIngredient = IngredientAdapter(ingredientList)
                rvDetailIngredient.layoutManager = LinearLayoutManager(this@DetailActivity)
                rvDetailIngredient.adapter = adapterRvIngredient

                val instructionList = it.instruction
                val adapterRvInstruction = InstructionAdapter(instructionList)
                rvDetailInstruction.layoutManager = LinearLayoutManager(this@DetailActivity)
                rvDetailInstruction.adapter = adapterRvInstruction
            }
        }

        detailViewModel.isLoading.observe(this) {
            showLoading(it)
        }

        binding.btnAddTracker.setOnClickListener {
            Toast.makeText(this, getString(R.string.tracker_text), Toast.LENGTH_SHORT).show()
        }
    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.detailProgressBar.visibility = View.VISIBLE
        } else {
            binding.detailProgressBar.visibility = View.GONE
        }
    }

    companion object {
        private const val EXTRA_ID = "EXTRA_ID"
        private const val BASE_IMAGE_URL = "https://docs.google.com/uc?id="
    }
}