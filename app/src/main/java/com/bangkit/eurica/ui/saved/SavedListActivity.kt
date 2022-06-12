package com.bangkit.eurica.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bangkit.eurica.databinding.ActivitySavedListBinding

class SavedListActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySavedListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        binding = ActivitySavedListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.imageView2.setOnClickListener{
            val intent = Intent(this, DetailSavedActivity::class.java)
            startActivity(intent)
        }
    }
}