package com.bangkit.eurica.data

import com.bangkit.eurica.R
import com.bangkit.eurica.model.Item

class ItemDataSource {
    fun loadAffirmation(): List<Item> {
        return listOf<Item>(
            Item("Aqua Botol", 5000, 10, R.drawable.dice_1),
            Item("Teh Botol", 7000, 7, R.drawable.dice_1),
            Item("Minyak Kayu Putih", 15000, 3, R.drawable.dice_1),
            Item("Aqua Botol", 5000, 10, R.drawable.dice_1),
            Item("Teh Botol", 7000, 7, R.drawable.dice_1),
            Item("Minyak Kayu Putih", 15000, 3, R.drawable.dice_1),
            Item("Aqua Botol", 5000, 10, R.drawable.dice_1),
            Item("Teh Botol", 7000, 7, R.drawable.dice_1),
            Item("Minyak Kayu Putih", 15000, 3, R.drawable.dice_1),
        )
    }
}
