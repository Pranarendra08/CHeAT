package com.example.cheat.utils

class Chatbot {
    var id: String? = null
    var recipeName: String? = null
    var calories: String? = null
    var image: String? = null

    constructor(
        id: String?,
        recipeName: String?,
        calories: String?,
        image: String?
    ) {
        this.id = id
        this.recipeName = recipeName
        this.calories = calories
        this.image = image
    }
}