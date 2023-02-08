package com.example.shootman

import com.google.gson.annotations.SerializedName

data class HangmanModel(
    val token: String,
    val language: String,
    val maxTries: Int,
    //si queremos cambiar el nombre: @SerializedName("hangman")
    val hangman: String,
    val incorrectGuesses: Int,
    val correct: Boolean? = null //guess endpoint
) {

}
