package com.bangkit.eurica.model

import com.google.gson.annotations.SerializedName

data class UserData(
    @field:SerializedName("id")
    val id: Int? = null,

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("password")
    val password: String? = null,

    @field:SerializedName("confirm_password")
    val confirm_password: String? = null,
)
