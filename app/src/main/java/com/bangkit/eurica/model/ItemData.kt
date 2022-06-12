package com.bangkit.eurica.model

import com.bangkit.eurica.R
import com.google.gson.annotations.SerializedName

data class ItemData (
    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("price")
    val price: String,

    @field:SerializedName("description")
    val description: String,

    @field:SerializedName("createdAt")
    val createdAt: String,

    @field:SerializedName("updatedAt")
    val updatedAt: String,
)