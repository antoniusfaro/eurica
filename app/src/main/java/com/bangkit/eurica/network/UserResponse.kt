package com.bangkit.eurica.network

import com.bangkit.eurica.model.UserData
import com.google.gson.annotations.SerializedName

data class UserResponse(
    @field:SerializedName("data")
    val data: List<UserData>
)
