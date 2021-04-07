package com.example.diverseapp.MoshiModels


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.io.Serializable

//Not included in the project
//@JsonClass(generateAdapter = true)
data class CardsMoshi(
    @field:Json(name = "cards")
    val cards: List<CardMoshi>
): Serializable