package com.example.shootman

import android.content.Context
import android.widget.TextView
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class HangmanModelView {

    var hangmanObj: HangmanModel? = null

    val outside = Retrofit.Builder()
        .baseUrl("http://hangman.enti.cat:5002/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    // Funcion para obtener una nueva palabra en un thread aparte porque el onCreate no puede pararse
    fun getNewWord(hangmanTextOutput: TextView) {
        val request = outside.create(ApiHangman::class.java)
        request.getNewWord().enqueue(object : Callback<HangmanModel>{
            override fun onResponse(call: Call<HangmanModel>, response: Response<HangmanModel>) {
                hangmanObj = response.body() ?: return
                hangmanTextOutput.setText(hangmanObj?.hangman?.replace("_", "_ ") ?: "")
            }

            override fun onFailure(call: Call<HangmanModel>, t: Throwable) {
                hangmanTextOutput.setText("Error: $t")
            }

        })
    }

    fun guessLeter(letter: Char, hangmanTextOutput: TextView, context: Context) {
        val request = outside.create(ApiHangman::class.java)
        request.guessLetter(
            mapOf(
                "token" to hangmanObj?.token,
                "letter" to letter.toString()
            )
        ).enqueue(object : Callback<HangmanModel>{
            override fun onResponse(call: Call<HangmanModel>, response: Response<HangmanModel>) {
                hangmanObj = response.body() ?: return

                if (hangmanObj?.correct == true)
                    hangmanTextOutput.setText(hangmanObj?.hangman?.replace("_", "_ ") ?: "")
                else
                    Toast.makeText(context, "Letter $letter is not in the word :(", Toast.LENGTH_SHORT).show()
            }

            override fun onFailure(call: Call<HangmanModel>, t: Throwable) {
                hangmanTextOutput.setText("Error: $t")
            }

        })
    }

    fun isGameWon(): Boolean {
        return false
    }
}