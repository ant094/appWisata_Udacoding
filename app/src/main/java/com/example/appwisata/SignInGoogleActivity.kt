package com.example.appwisata

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.appwisata.databinding.ActivityMainBinding
import com.example.appwisata.databinding.ActivitySignInGoogleBinding

class SignInGoogleActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignInGoogleBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInGoogleBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}