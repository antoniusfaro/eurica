package com.bangkit.eurica.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bangkit.eurica.databinding.ActivityDetailItemBinding

class DetailItemActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailItemBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        binding = ActivityDetailItemBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}