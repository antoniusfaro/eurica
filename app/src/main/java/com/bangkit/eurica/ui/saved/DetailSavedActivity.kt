package com.bangkit.eurica.ui.saved

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.bangkit.eurica.R
import com.bangkit.eurica.databinding.ActivityDetailSavedBinding
import com.bangkit.eurica.ui.scan.DetailItemActivity

class DetailSavedActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailSavedBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        binding = ActivityDetailSavedBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val itemPrice = intent.getStringExtra(DetailItemActivity.EXTRA_ITEM_PRICE)
            ?.replace("Rp.", "")
            ?.replace(".", "")
            ?.replace(",-", "")
            ?.toInt()
        val moneyAmount = intent.getStringExtra(DetailItemActivity.EXTRA_MONEY_AMOUNT)
        val money = moneyAmount
            ?.replace("Rp. ", "")
            ?.replace(".00", "")
            ?.replace(",", "")
            ?.toInt()

        val itemQty = intent.getStringExtra(DetailItemActivity.EXTRA_ITEM_QTY)?.toInt()

        val resourceImg = when (intent.getStringExtra(DetailItemActivity.EXTRA_ITEM_NAME)) {
            "Chicken Kebab" -> R.drawable.eurica_chicken_kebab
            "Green Goodes Salad" -> R.drawable.eurica_green_goddess_salad
            "Mix Crunchy Salad" -> R.drawable.eurica_cruncy_salad
            "Beff Patty Steak" -> R.drawable.eurica_beef_patty
            "Vietnamese Springroll" -> R.drawable.eurica_viatnemese_springroll
            "Dinilaku Ramen Katsu" -> R.drawable.eurica_dinilaku_ramen_katsu
            "Dinilaku Kids Fried Rice" -> R.drawable.eurica_kids_ricebowl
            "Chicken Wrap" -> R.drawable.eurica_chicken_wrap
            "Tako Itamegohan" -> R.drawable.eurica_tako_ikamegohan
            "Dori Morzagila" -> R.drawable.eurica_dory_mozarella
            "Ricebowl Pindang" -> R.drawable.eurica_pindang_ricebowl
            "Ricebowl Cumi" -> R.drawable.eurica_cumi_ricebowl
            "Ricebowl Chicken Katsu" -> R.drawable.eurica_ricebowl_chicken_katsu
            "Ricebowl Beef Teriyaki" -> R.drawable.bali_ricebowl_teriyaki
            "Ricebowl Baby Gurita Balado" -> R.drawable.ricebowl_octopus
            else -> R.drawable.dice_1
        }

        val id = intent.getStringExtra(EXTRA_ID)
        val itemName = intent.getStringExtra(EXTRA_ITEM_NAME)
        val price = intent.getStringExtra(EXTRA_ITEM_PRICE)
        val qty = intent.getStringExtra(EXTRA_ITEM_QTY)
        val desc = intent.getStringExtra(EXTRA_DESCRIPTION)
        val moneyVal = intent.getStringExtra(EXTRA_MONEY_AMOUNT)

        binding.detailSavedItemImage.setImageResource(resourceImg)
        binding.detailSavedItemName.text = itemName
        binding.detailSavedItemPrice.text = price
        binding.detailSavedItemQty.text =
            "Max: ${intent.getStringExtra(DetailItemActivity.EXTRA_ITEM_QTY)} item(s)"
        if (itemPrice != null) {
            binding.detailSavedItemTotal.text = "Rp. ${itemPrice * itemQty!!},-"
        }

        Log.d(
            "MMONEYAMOUNT",
            "${intent.getStringExtra(DetailItemActivity.EXTRA_MONEY_AMOUNT)}"
        )
        binding.detailSavedItemDescription.text = desc
        if (itemPrice != null) {
            if (moneyAmount != null) {
                if (money != null) {
                    binding.detailSavedItemChangeDue.text =
                        "Change due: Rp ${money.toInt() - (itemPrice * itemQty!!)},-"
                }
            }
        }
        var _isFabSaveEnabled = false
    }

    companion object {
        private const val TAG = "DetailSavedActivity"
        const val EXTRA_ITEM_NAME = "extra_item_name"
        const val EXTRA_MONEY_NAME = "extra_money_name"
        const val EXTRA_ITEM_QTY = "extra_money_name"
        const val EXTRA_ITEM_PRICE = "extra_item_price"
        const val EXTRA_DESCRIPTION =  "extra_description"
        const val EXTRA_MONEY_AMOUNT = "extra_money_amount"
        const val EXTRA_ID = "extra_id"
    }
}