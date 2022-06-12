package com.bangkit.eurica.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bangkit.eurica.databinding.ActivityScanResultBinding

class ScanResultActivity : AppCompatActivity() {
    private lateinit var binding: ActivityScanResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        binding = ActivityScanResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLogin2.setOnClickListener{
            val intent = Intent(this, ScanActivity::class.java)
            startActivity(intent)
        }

        binding.btnLogin3.setOnClickListener{
            val intent = Intent(this, ItemListActivity::class.java)
            startActivity(intent)
        }
    }
}