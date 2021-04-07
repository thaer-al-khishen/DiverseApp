package com.example.diverseapp.Models


import com.google.gson.annotations.SerializedName
import com.squareup.moshi.Json
import java.io.Serializable

//Not included in the project
data class Ruling(
    @SerializedName("date")
    val date: String,
    @SerializedName("text")
    val text: String
): Serializable