package com.example.diverseapp.Network.Network.Services

import com.example.diverseapp.Models.Card
import com.example.diverseapp.Models.Cards
import com.example.diverseapp.Network.Network.Builder.UrlBuilder
import com.google.gson.JsonArray
import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

//Not included in the project
interface CardsAPIService {
    @GET(UrlBuilder.GET_ALL_CARDS)
    fun getAllCards(): Single<Cards>

    @GET(UrlBuilder.GET_CARD_BY_ID)
    fun getCardById(
        @Query("id") id: Int
    ): Single<Cards>
}