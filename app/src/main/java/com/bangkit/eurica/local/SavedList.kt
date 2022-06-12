package com.bangkit.eurica.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "saved_list")
data class SavedList(
    @PrimaryKey
    val id: Int,
    val name: String,
    val item_price: String,
    val max_qty: String,
    val money_amount: String,
    val imageResource: Int,
    val description: String,
): Serializable