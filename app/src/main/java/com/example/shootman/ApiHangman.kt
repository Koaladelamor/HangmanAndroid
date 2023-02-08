package com.example.shootman

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiHangman {

    @GET("/new?lang=cat")
    fun getNewWord(): Call<HangmanModel>

    @POST("/guess")
    fun guessLetter(@Body body: Map<String, String?>): Call<HangmanModel>
}