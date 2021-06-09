package com.sd.views

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sd.views.databinding.ActivityTextLineLayoutBinding

class TextLineLayoutActivity : AppCompatActivity() {
    private lateinit var _binding: ActivityTextLineLayoutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityTextLineLayoutBinding.inflate(layoutInflater)
        setContentView(_binding.root)
    }
}