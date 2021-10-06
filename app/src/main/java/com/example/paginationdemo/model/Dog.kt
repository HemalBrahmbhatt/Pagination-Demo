package com.example.paginationdemo.model


import com.google.gson.annotations.SerializedName

data class Dog(

    @SerializedName("id")
    val id: String,

    @SerializedName("url")
    val url: String
)