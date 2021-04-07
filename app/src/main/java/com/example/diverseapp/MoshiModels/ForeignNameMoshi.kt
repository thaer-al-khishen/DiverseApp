package com.example.diverseapp.MoshiModels


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.io.Serializable

//Not included in the project
//@JsonClass(generateAdapter = true)
data class ForeignNameMoshi(
    @field:Json(name = "flavor")
    val flavor: Any,
    @field:Json(name = "imageUrl")
    val imageUrl: String,
    @field:Json(name = "language")
    val language: String,
    @field:Json(name = "multiverseid")
    val multiverseid: Int,
    @field:Json(name = "name")
    val name: String,
    @field:Json(name = "text")
    val text: String
): Serializable