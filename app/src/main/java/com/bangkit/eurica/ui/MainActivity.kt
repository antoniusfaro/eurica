package com.bangkit.eurica.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bangkit.eurica.databinding.ActivityMainBinding
import com.bangkit.eurica.ui.saved.SavedListActivity
import com.bangkit.eurica.ui.scan.ScanActivity

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val username = intent.getStringExtra(EXTRA_NAME)
        binding.username.text = "Hi, $username"

        binding.imageButton.setOnClickListener() {
            val intent = Intent(this, ScanActivity::class.java)
            startActivity(intent)
        }

        binding.imageButton2.setOnClickListener() {
            val intent = Intent(this, SavedListActivity::class.java)
            startActivity(intent)
        }
    }

    companion object {
        const val EXTRA_NAME = "extra_name"
    }
}