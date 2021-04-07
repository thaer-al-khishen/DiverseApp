package com.example.diverseapp.MoshiModels


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.io.Serializable

//Not included in the project
//@JsonClass(generateAdapter = true)
data class RulingMoshi(
    @field:Json(name = "date")
    val date: String,
    @field:Json(name = "text")
    val text: String
): Serializable