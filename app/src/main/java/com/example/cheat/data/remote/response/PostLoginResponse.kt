package com.example.cheat.data.remote.response

import com.google.gson.annotations.SerializedName

data class PostLoginResponse(

	@field:SerializedName("message")
	val message: String
)
