package com.sd.views

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.sd.views.databinding.ActivityTouchIndicatorBinding

class TouchIndicatorActivity : AppCompatActivity() {
    private val TAG = TouchIndicatorActivity::class.java.simpleName
    private lateinit var _binding: ActivityTouchIndicatorBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityTouchIndicatorBinding.inflate(layoutInflater)
        setContentView(_binding.root)

        _binding.viewTouchIndicator.setCallback(object : FTouchIndicatorView.Callback() {
            override fun onIndexChanged(index: Int, text: String?) {
                Log.i(TAG, "onIndexChanged ${index} -> ${text}")
                if (text != null) {
                    _binding.viewTouchIndicator.setBackgroundColor(Color.parseColor("#EEEEEE"))
                    _binding.tvText.apply {
                        this.text = text
                        this.visibility = View.VISIBLE
                    }
                } else {
                    _binding.viewTouchIndicator.setBackgroundColor(Color.TRANSPARENT)
                    _binding.tvText.apply {
                        this.text = ""
                        this.visibility = View.GONE
                    }
                }
            }

            override fun onTouchDown() {
                Log.i(TAG, "onTouchDown")
            }

            override fun onTouchMove() {
                Log.i(TAG, "onTouchMove")
            }

            override fun onTouchUp() {
                Log.i(TAG, "onTouchUp")
            }
        })
    }
}