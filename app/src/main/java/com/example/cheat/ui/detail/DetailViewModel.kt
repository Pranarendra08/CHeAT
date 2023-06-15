package com.example.cheat.ui.detail

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.cheat.data.remote.response.GetRecipeDetailResponse
import com.example.cheat.di.Injection
import com.example.cheat.repository.CheatRepository

class DetailViewModel(private val cheatRepository: CheatRepository) : ViewModel() {

    val isLoading: LiveData<Boolean> = cheatRepository.isLoading
    val detailRecipe : LiveData<GetRecipeDetailResponse> = cheatRepository.detailRecipe

    fun getRecipeDetail(id: String) = cheatRepository.getRecipeDetail(id)
}

class DetailViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
            return DetailViewModel(Injection.provideRepository(context)) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class: ${modelClass.name}")
    }
}