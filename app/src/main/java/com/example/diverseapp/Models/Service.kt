package com.example.diverseapp.Models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

//Model class for service, doesn't have to be serializable yet as not network calls are involved
data class Service (
    @SerializedName("service_id")
    var service_id: Int,
    @SerializedName("service_name")
    var service_name: String,
    @SerializedName("isSelected")
    var isSelected: Boolean,
    @SerializedName("imageResource")
    var imageResource: Int,
): Serializable


