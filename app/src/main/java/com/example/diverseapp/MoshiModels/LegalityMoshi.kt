package com.example.diverseapp.MoshiModels


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.io.Serializable

//Not included in the project
//@JsonClass(generateAdapter = true)
data class LegalityMoshi(
    @field:Json(name = "format")
    val format: String,
    @field:Json(name = "legality")
    val legality: String
): Serializable