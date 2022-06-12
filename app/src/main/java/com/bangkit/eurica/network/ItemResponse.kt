package com.bangkit.eurica.network

import com.bangkit.eurica.model.ItemData
import com.google.gson.annotations.SerializedName

data class ItemResponse(
    @field:SerializedName("data")
    val data: List<ItemData>? = null
)
