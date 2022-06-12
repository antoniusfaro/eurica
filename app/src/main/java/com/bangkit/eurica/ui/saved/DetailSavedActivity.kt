package com.bangkit.eurica.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bangkit.eurica.databinding.ActivityDetailSavedBinding

class DetailSavedActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailSavedBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        binding = ActivityDetailSavedBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}