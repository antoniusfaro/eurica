package com.bangkit.eurica.network

import com.google.gson.annotations.SerializedName

class RegisterResponse (
    @field:SerializedName("massage")
    val message: String,

    @field:SerializedName("affectedRows")
    val affectedRows: String
)