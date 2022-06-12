package com.bangkit.eurica.model

import com.google.gson.annotations.SerializedName

data class MoneyData(
    @field:SerializedName("id")
    val id: Int? = null,

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("pronunciation")
    val pronunciation: String? = null,

    @field:SerializedName("description")
    val description: String? = null,
)
