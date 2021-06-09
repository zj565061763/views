package com.sd.views

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.sd.views.databinding.ActivityMaxSizeLayoutBinding

class MaxSizeLayoutActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var _binding: ActivityMaxSizeLayoutBinding
    private val _content = "123456789"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMaxSizeLayoutBinding.inflate(layoutInflater)
        setContentView(_binding.root)

        // 设置最大宽度
        _binding.maxSizeLayout.setMaxWidth(400)
        // 设置最大高度
        _binding.maxSizeLayout.setMaxHeight(400)
    }

    override fun onClick(v: View?) {
        when (v) {
            _binding.btnSmall -> _binding.tvContent.text = _content
            _binding.btnBig -> {
                val text = _binding.tvContent.text.toString()
                _binding.tvContent.text = text + _content
            }
        }
    }
}