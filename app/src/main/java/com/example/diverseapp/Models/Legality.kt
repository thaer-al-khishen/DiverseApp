package com.example.diverseapp.Models


import com.google.gson.annotations.SerializedName
import java.io.Serializable

//Not included in the project
data class Legality(
    @SerializedName("format")
    val format: String,
    @SerializedName("legality")
    val legality: String
): Serializable