package com.example.cheat.ui.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.cheat.repository.CheatRepository
import com.example.cheat.utils.Event

class RegisterViewModel(private val cheatRepository: CheatRepository) : ViewModel() {

    val isLoading: LiveData<Boolean> = cheatRepository.isLoading
    val toastSuccess: LiveData<Event<String>> = cheatRepository.toastSuccess
    val toastFailed: LiveData<Event<String>> = cheatRepository.toastFailed

    fun createUserAccount(username: String, password: String) = cheatRepository.createUserAccount(username, password)

}