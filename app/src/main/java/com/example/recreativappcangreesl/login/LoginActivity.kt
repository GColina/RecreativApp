package com.example.recreativappcangreesl.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.recreativappcangreesl.databinding.ActivityLoginBinding
import com.example.recreativappcangreesl.home.HomeActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = Firebase.auth

        initListeners()
    }

    private fun initListeners() {
        binding.loginButton.setOnClickListener {
            if (binding.emailET.text.isEmpty() || binding.passET.text.isEmpty()) {
                Toast.makeText(
                    this,
                    "Por favor rellena todos los datos de inicio de sesión!",
                    Toast.LENGTH_SHORT,
                ).show()
            } else {
                loading(true)
                doLogin(binding.emailET.text.toString(), binding.passET.text.toString())
            }
        }
    }

    private fun doLogin(
        email: String,
        pass: String,
    ) {
        auth.signInWithEmailAndPassword(email, pass)
            .addOnCompleteListener(this) { task ->
                loading(false)
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    val homeIntent = Intent(this, HomeActivity::class.java)
                    startActivity(homeIntent)
                    finish()
                } else {
                    Toast.makeText(
                        this,
                        "El inicio de sesión ha fallado. Vuelva a intentarlo!",
                        Toast.LENGTH_LONG,
                    ).show()
                }
            }
    }

    private fun loading(visible: Boolean) {
        if (visible) {
            binding.loaderBg.visibility = View.VISIBLE
            binding.loader.visibility = View.VISIBLE
        } else {
            binding.loaderBg.visibility = View.GONE
            binding.loader.visibility = View.GONE
        }
    }
}
