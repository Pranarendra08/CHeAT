package com.example.cheat.data.remote.response

import com.google.gson.annotations.SerializedName

data class PostChatbotResponse(

	@field:SerializedName("predictions")
	val predictions: ArrayList<PredictionsItem>
)

data class PredictionsItem(

	@field:SerializedName("image")
	val image: String,

	@field:SerializedName("servings")
	val servings: Int,

	@field:SerializedName("instruction")
	val instruction: List<String>,

	@field:SerializedName("serving_size")
	val servingSize: String,

	@field:SerializedName("recipe_name")
	val recipeName: String,

	@field:SerializedName("ingredients")
	val ingredients: List<String>,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("calories")
	val calories: String
)
