package com.example.diverseapp.Models


import com.example.diverseapp.Network.Network.GsonHelper
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.annotations.SerializedName
import java.io.Serializable

//Not included in the project
data class Card(
    @SerializedName("artist")
    val artist: String,
    @SerializedName("cmc")
    val cmc: Double,
    @SerializedName("colorIdentity")
    val colorIdentity: List<String>,
    @SerializedName("colors")
    val colors: List<String>,
    @SerializedName("flavor")
    val flavor: String,
    @SerializedName("foreignNames")
    val foreignNames: List<ForeignName>,
    @SerializedName("id")
    val id: String,
    @SerializedName("imageUrl")
    val imageUrl: String,
    @SerializedName("layout")
    val layout: String,
    @SerializedName("legalities")
    val legalities: List<Legality>,
    @SerializedName("manaCost")
    val manaCost: String,
    @SerializedName("multiverseid")
    val multiverseid: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("number")
    val number: String,
    @SerializedName("originalText")
    val originalText: String,
    @SerializedName("originalType")
    val originalType: String,
    @SerializedName("power")
    val power: String,
    @SerializedName("printings")
    val printings: List<String>,
    @SerializedName("rarity")
    val rarity: String,
    @SerializedName("rulings")

    val rulings: List<Ruling>,
    @SerializedName("set")
    val `set`: String,
    @SerializedName("setName")
    val setName: String,
    @SerializedName("subtypes")
    val subtypes: List<String>,
    @SerializedName("supertypes")
    val supertypes: List<String>,
    @SerializedName("text")
    val text: String,
    @SerializedName("toughness")
    val toughness: String,
    @SerializedName("type")
    val type: String,
    @SerializedName("types")
    val types: List<String>,
    @SerializedName("variations")
    val variations: List<String>
): Serializable