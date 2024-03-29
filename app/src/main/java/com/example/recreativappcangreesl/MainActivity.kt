package com.example.recreativappcangreesl

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.recreativappcangreesl.databinding.ActivityMainBinding
import com.example.recreativappcangreesl.home.HomeActivity
import com.example.recreativappcangreesl.login.LoginActivity
import com.example.recreativappcangreesl.rvCategories.TypesCategoriesMachines.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    public override fun onStart() {
        super.onStart()
        auth = Firebase.auth
        val currentUser = auth.currentUser
        Log.d("GABRIEL", "===> CURRENT USER: ${currentUser?.uid}")
        if (currentUser != null) {
            val homeIntent = Intent(this, HomeActivity::class.java)
            startActivity(homeIntent)
        } else {
            val loginIntent = Intent(this, LoginActivity::class.java)
            startActivity(loginIntent)
        }
        finish()
    }
}
