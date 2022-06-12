package com.bangkit.eurica.network

import com.bangkit.eurica.model.MoneyData
import com.google.gson.annotations.SerializedName

data class MoneyResponse(
    @field:SerializedName("data")
    val data: List<MoneyData>
)
