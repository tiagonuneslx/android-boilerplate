package com.example.androidboilerplate

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.androidboilerplate.databinding.MainActivityBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MainActivityBinding.inflate(layoutInflater)
    }
}