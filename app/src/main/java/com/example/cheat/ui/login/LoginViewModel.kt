package com.example.cheat.ui.login

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.cheat.di.Injection
import com.example.cheat.repository.CheatRepository
import com.example.cheat.utils.Event

class LoginViewModel(private val cheatRepository: CheatRepository) : ViewModel() {
    val isLoading: LiveData<Boolean> = cheatRepository.isLoading
    val toastSuccess: LiveData<Event<String>> = cheatRepository.toastSuccess
    val toastFailed: LiveData<Event<String>> = cheatRepository.toastFailed

    fun loginUserAccount(username: String, password: String) = cheatRepository.loginUserAccount(username, password)
}

class LoginViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            return LoginViewModel(Injection.provideRepository(context)) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class: ${modelClass.name}")
    }
}