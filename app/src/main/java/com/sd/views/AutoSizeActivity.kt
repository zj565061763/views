package com.sd.views

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.sd.views.databinding.ActivityAutoSizeBinding

class AutoSizeActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var _binding: ActivityAutoSizeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityAutoSizeBinding.inflate(layoutInflater)
        setContentView(_binding.root)
    }

    override fun onClick(v: View?) {
        when (v) {
            _binding.btnBig -> {
                _binding.tvContent.apply {
                    this.text = this.text.toString() + "hello"
                }
            }
            _binding.btnReset -> {
                _binding.tvContent.apply {
                    this.text = "hello"
                }
            }
        }
    }
}