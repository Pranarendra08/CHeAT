package com.example.cheat.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.cheat.data.remote.response.PostRegisterResponse
import com.example.cheat.data.remote.retrofit.ApiService
import com.example.cheat.pref.UserPreference
import com.example.cheat.utils.Event
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CheatRepository(private val userPreference: UserPreference, private val apiService: ApiService) {

    private val _toastSuccess = MutableLiveData<Event<String>>()
    val toastSuccess: LiveData<Event<String>> = _toastSuccess

    private val _toastFailed = MutableLiveData<Event<String>>()
    val toastFailed: LiveData<Event<String>> = _toastFailed

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun createUserAccount(username: String, password: String) {
        _isLoading.value = true
        val client = apiService.createUserAccount(username, password)
        client.enqueue(object : Callback<PostRegisterResponse> {
            override fun onResponse(
                call: Call<PostRegisterResponse>,
                response: Response<PostRegisterResponse>
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        _toastSuccess.value = Event(response.body()?.message.toString())
                    }
                }
                else {
                    _toastFailed.value = Event(response.body()?.message.toString())
                }
            }

            override fun onFailure(call: Call<PostRegisterResponse>, t: Throwable) {
                _isLoading.value = false
                _toastFailed.value = Event(t.message.toString())
            }

        })
    }
}