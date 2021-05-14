package com.sd.views

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sd.views.databinding.ActivityTextHeightLayoutBinding

class TextHeightLayoutActivity : AppCompatActivity() {
    val TAG = TextHeightLayoutActivity::class.java.simpleName

    private lateinit var _binding: ActivityTextHeightLayoutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityTextHeightLayoutBinding.inflate(layoutInflater)
        setContentView(_binding.root)
    }
}