package com.example.diverseapp.Network.Network

import com.google.gson.Gson
import com.google.gson.JsonArray
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

//Not included in the project
object GsonHelper {
    fun isNull(element: JsonElement?): Boolean {
        return element == null || element.isJsonNull
    }

    inline fun <reified T : Any> from(array: JsonArray): ArrayList<T> {
        val gson = Gson()
        val events = ArrayList<T>()
        for (element: JsonElement in array) {
            events.add(gson.fromJson(element, T::class.java))
        }
        return events
    }

    inline fun <reified T : Any> from(json: JsonObject): T {
        val gson = Gson()
        return gson.fromJson(json, T::class.java)
    }

}