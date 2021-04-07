package com.example.diverseapp.MoshiModels


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.io.Serializable
import java.util.ArrayList

//Not included in the project
//@JsonClass(generateAdapter = true)
data class CardMoshi(
    @field:Json(name = "artist")
    val artist: String,
    @field:Json(name = "cmc")
    val cmc: Double,
    @field:Json(name = "colorIdentity")
    val colorIdentity: List<String>,
    @field:Json(name = "colors")
    val colors: List<String>,
    @field:Json(name = "flavor")
    val flavor: String,
    @field:Json(name = "foreignNames")
    val foreignNameMoshis: List<ForeignNameMoshi>,
    @field:Json(name = "id")
    val id: String,
    @field:Json(name = "imageUrl")
    val imageUrl: String,
    @field:Json(name = "layout")
    val layout: String,
    @field:Json(name = "legalities")
    val legalityMoshis: List<LegalityMoshi>,
    @field:Json(name = "manaCost")
    val manaCost: String,
    @field:Json(name = "multiverseid")
    val multiverseid: Int,
    @field:Json(name = "name")
    val name: String,
    @field:Json(name = "number")
    val number: String,
    @field:Json(name = "originalText")
    val originalText: String,
    @field:Json(name = "originalType")
    val originalType: String,
    @field:Json(name = "power")
    val power: String,
    @field:Json(name = "printings")
    val printings: List<String>,
    @field:Json(name = "rarity")
    val rarity: String,
    @field:Json(name = "rulings")
    val rulings: List<RulingMoshi> = ArrayList(),
    @field:Json(name = "set")
    val `set`: String,
    @field:Json(name = "setName")
    val setName: String,
    @field:Json(name = "subtypes")
    val subtypes: List<String>,
    @field:Json(name = "supertypes")
    val supertypes: List<String>,
    @field:Json(name = "text")
    val text: String,
    @field:Json(name = "toughness")
    val toughness: String,
    @field:Json(name = "type")
    val type: String,
    @field:Json(name = "types")
    val types: List<String>,
    @field:Json(name = "variations")
    val variations: List<String>
): Serializable