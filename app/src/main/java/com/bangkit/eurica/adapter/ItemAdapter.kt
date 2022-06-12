package com.bangkit.eurica.adapter

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bangkit.eurica.R
import com.bangkit.eurica.model.ItemData
import com.bangkit.eurica.ui.scan.DetailItemActivity


class ItemAdapter(
    private val context: Context,
    private val dataset: List<ItemData>,
    private val moneyName: String?,
) : RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {
    class ItemViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        val imageView: ImageView = view.findViewById(R.id.item_image)
        val tvName: TextView = view.findViewById(R.id.item_title)
        val tvPrice: TextView = view.findViewById(R.id.item_price)
        val tvMaxQty: TextView = view.findViewById(R.id.item_max_qty)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item, parent, false)

        return ItemViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = dataset[position]
        val itemPrice = item.price
            .replace("Rp.", "")
            .replace(".", "")
            .replace(",-", "")
            .toInt()
        val moneyAmount = moneyName
            ?.replace("Rp. ", "")
            ?.replace(".00", "")
            ?.replace(",", "")
            ?.toInt()

        val resourceImg = when (item.name) {
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
        holder.imageView.setImageResource(resourceImg)
        holder.tvName.text = item.name
        holder.tvPrice.text = "Rp. $itemPrice.-"
        if (moneyAmount != null) {
            holder.tvMaxQty.text = "${(moneyAmount / itemPrice)}"
        }

        holder.imageView.setOnClickListener{
            val context = holder.imageView.context
            val intent = Intent(context, DetailItemActivity::class.java)

            Log.d("TEST BIND HOLDER", "$moneyAmount")
            intent.putExtra(DetailItemActivity.EXTRA_ITEM_NAME, item.name)
            intent.putExtra(DetailItemActivity.EXTRA_ITEM_PRICE, item.price)
            if (moneyAmount != null) {
                intent.putExtra(DetailItemActivity.EXTRA_MONEY_NAME, 3333)
                intent.putExtra(DetailItemActivity.EXTRA_ITEM_QTY, "${moneyAmount / itemPrice}")
            }
            intent.putExtra(DetailItemActivity.EXTRA_MONEY_AMOUNT, moneyName)
            intent.putExtra(DetailItemActivity.EXTRA_ID, "${item.id}")
            intent.putExtra(DetailItemActivity.EXTRA_DESCRIPTION, item.description)
            context.startActivity(intent)
        }
    }

    override fun getItemCount() = dataset.size
}