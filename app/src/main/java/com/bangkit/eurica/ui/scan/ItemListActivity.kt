package com.bangkit.eurica.ui.scan

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.bangkit.eurica.R
import com.bangkit.eurica.adapter.ItemAdapter
import com.bangkit.eurica.data.ItemDataSource
import com.bangkit.eurica.databinding.ActivityItemListBinding
import com.bangkit.eurica.model.ItemData
import com.bangkit.eurica.network.ApiConfig
import com.bangkit.eurica.network.ItemResponse
import com.bangkit.eurica.network.MoneyResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ItemListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityItemListBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        binding = ActivityItemListBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        binding.imageView2.setOnClickListener{
//            val intent = Intent(this, DetailItemActivity::class.java)
//            startActivity(intent)
//        }
        val moneyName = intent.getStringExtra(EXTRA_MONEY_NAME)
        var dataset: List<ItemData> = listOf()

        val client = ApiConfig.getItemApiService().getItemList()
        client.enqueue(object : Callback<ItemResponse> {
            override fun onResponse(
                call: Call<ItemResponse>,
                response: Response<ItemResponse>
            ) {
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        Log.d(TAG, "INI DATA ITEM: ${responseBody.data}")
                        dataset = responseBody.data!!
                        val recyclerView = findViewById<RecyclerView>(R.id.rv_list)
                        recyclerView.adapter = ItemAdapter(applicationContext, dataset, moneyName)
                        Log.d("DATASET", "$dataset")
                    }
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }
            override fun onFailure(call: Call<ItemResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message}")
            }
        })
    }

    companion object {
        private const val TAG = "ItemListActivity"
        const val EXTRA_MONEY_NAME = "Rp 0"
    }
}