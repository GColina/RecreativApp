package com.example.recreativappcangreesl.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.recreativappcangreesl.R
import com.example.recreativappcangreesl.databinding.ActivityDetailBinding
import com.example.recreativappcangreesl.databinding.ActivityMainBinding

private lateinit var binding: ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}
