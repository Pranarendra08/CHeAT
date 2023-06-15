package com.example.cheat.ui.cheatbot

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.cheat.data.remote.response.PostChatbotResponse
import com.example.cheat.data.remote.response.PredictionsItem
import com.example.cheat.di.Injection
import com.example.cheat.repository.CheatRepository
import com.example.cheat.utils.Event

class CheatbotViewModel(private val cheatRepository: CheatRepository) : ViewModel() {
    val isLoading: LiveData<Boolean> = cheatRepository.isLoading
    val toastSuccess: LiveData<Event<String>> = cheatRepository.toastSuccess
    val toastFailed: LiveData<Event<String>> = cheatRepository.toastFailed
    val chatbot : LiveData<ArrayList<PredictionsItem>> = cheatRepository.chatbot

    fun chatbot(message: String) = cheatRepository.chatbot(message)
}

class chatbotViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CheatbotViewModel::class.java)) {
            return CheatbotViewModel(Injection.provideRepository(context)) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class: ${modelClass.name}")
    }
}