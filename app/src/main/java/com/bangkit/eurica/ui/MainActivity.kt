package com.bangkit.eurica

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bangkit.eurica.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.imageButton.setOnClickListener() {
            val intent = Intent(this, ScanActivity::class.java)
            startActivity(intent)
        }

        binding.imageButton2.setOnClickListener() {
            val intent = Intent(this, SavedListActivity::class.java)
            startActivity(intent)
        }
    }
}