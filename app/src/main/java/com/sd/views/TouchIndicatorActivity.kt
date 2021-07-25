package com.sd.views

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.sd.views.databinding.ActivityTouchIndicatorBinding

class TouchIndicatorActivity : AppCompatActivity() {
    private lateinit var _binding: ActivityTouchIndicatorBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityTouchIndicatorBinding.inflate(layoutInflater)
        setContentView(_binding.root)

        _binding.viewTouchIndicator.setCallback(object : FTouchIndicatorView.Callback {
            override fun onTouchDown(text: String) {
                _binding.viewTouchIndicator.setBackgroundColor(Color.parseColor("#EEEEEE"))
                _binding.tvText.apply {
                    this.text = text
                    this.visibility = View.VISIBLE
                }
            }

            override fun onTouchMove(text: String) {
                _binding.tvText.apply {
                    this.text = text
                    this.visibility = View.VISIBLE
                }
            }

            override fun onTouchUp(text: String) {
                _binding.viewTouchIndicator.setBackgroundColor(Color.TRANSPARENT)
                _binding.tvText.apply {
                    this.text = ""
                    this.visibility = View.GONE
                }
            }
        })
    }
}