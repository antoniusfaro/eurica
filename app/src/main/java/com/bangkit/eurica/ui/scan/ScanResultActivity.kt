package com.bangkit.eurica.ui.scan

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.bangkit.eurica.R
import com.bangkit.eurica.databinding.ActivityScanResultBinding
import com.bangkit.eurica.model.MoneyData
import com.bangkit.eurica.network.ApiConfig
import com.bangkit.eurica.network.MoneyResponse
import com.bangkit.eurica.network.UserResponse
import com.bangkit.eurica.ui.MainActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ScanResultActivity : AppCompatActivity() {
    private lateinit var binding: ActivityScanResultBinding
    private lateinit var moneyName: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        binding = ActivityScanResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getMoneyList()

        binding.btnLogin2.setOnClickListener{
            val intent = Intent(this, ScanActivity::class.java)
            startActivity(intent)
        }

        binding.btnLogin3.setOnClickListener{
            val intent = Intent(this, ItemListActivity::class.java)
            intent.putExtra(ItemListActivity.EXTRA_MONEY_NAME, moneyName)
            startActivity(intent)
        }
    }

    private fun getMoneyList() {
        val searchIndex = intent.getIntExtra(EXTRA_ID, -1)
        var moneyResult: MoneyData

        val client = ApiConfig.getMoneyApiService().getMoneyList()
        client.enqueue(object : Callback<MoneyResponse> {
            override fun onResponse(
                call: Call<MoneyResponse>,
                response: Response<MoneyResponse>
            ) {
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        Log.d(TAG, "INI DATA MONEY: ${responseBody.data}")
                        for (money in responseBody.data) {
                            if (money.id == searchIndex) {
                                moneyResult = money
                                binding.moneyName.text = money.name.toString()

                                val resourceImg = when (money.id) {
                                    1 -> R.drawable.rupiah_1
                                    2 -> R.drawable.rupiah_2
                                    3 -> R.drawable.rupiah_3
                                    4 -> R.drawable.rupiah_4
                                    5 -> R.drawable.rupiah_5
                                    6 -> R.drawable.rupiah_6
                                    8 -> R.drawable.rupiah_7
                                    else -> R.drawable.dice_1
                                }
                                binding.moneyImage.setImageResource(resourceImg)
                                binding.moneyPronounciation.text = money.pronunciation.toString()
                                binding.moneyDescription.text = money.description.toString()
                                moneyName = money.name.toString()
                            }
                        }
                    }
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }
            override fun onFailure(call: Call<MoneyResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message}")
            }
        })
    }
    companion object {
        private const val TAG = "ScanResultActivity"
        const val EXTRA_ID = "extra_id"
    }
}