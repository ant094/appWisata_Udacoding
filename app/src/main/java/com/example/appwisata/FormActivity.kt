package com.example.appwisata

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.appwisata.databinding.ActivityFormBinding
import com.example.appwisata.databinding.ActivityMainBinding

class FormActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFormBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFormBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}