package com.example.cheat.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.cheat.data.remote.response.PostLoginResponse
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

    fun loginUserAccount(username: String, password: String) {
        _isLoading.value = true
        userPreference.saveUsername(username)
        val client = apiService.loginUserAccount(username, password)
        client.enqueue(object : Callback<PostLoginResponse> {
            override fun onResponse(
                call: Call<PostLoginResponse>,
                response: Response<PostLoginResponse>
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        userPreference.saveCookie(response.headers()["set-cookie"].toString())
                        _toastSuccess.value = Event(response.body()?.message.toString())
                        Log.d("CHEAT_REPOSITORY", "Ini adalah header dari login response: ${userPreference.getCookie()}")
                        Log.d("CHEAT_REPOSITORY", "Ini adalah header dari login response: ${userPreference.getUsername()}")
                    }
                }
                else {
                    _toastFailed.value = Event(response.body()?.message.toString())
                }
            }

            override fun onFailure(call: Call<PostLoginResponse>, t: Throwable) {
                _toastFailed.value = Event(t.message.toString())
            }

        })
    }
}