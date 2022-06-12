package com.bangkit.eurica.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bangkit.eurica.databinding.ActivityItemListBinding

class ItemListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityItemListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        binding = ActivityItemListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.imageView2.setOnClickListener{
            val intent = Intent(this, DetailItemActivity::class.java)
            startActivity(intent)
        }
    }
}