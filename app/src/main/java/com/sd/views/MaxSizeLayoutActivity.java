package com.sd.views;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.sd.views.databinding.ActivityMaxSizeLayoutBinding;

public class MaxSizeLayoutActivity extends AppCompatActivity {
    public static final String TAG = MaxSizeLayoutActivity.class.getSimpleName();

    private ActivityMaxSizeLayoutBinding _binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        _binding = ActivityMaxSizeLayoutBinding.inflate(getLayoutInflater());
        setContentView(_binding.getRoot());

        // 设置最大宽度
        _binding.maxSizeLayout.setMaxWidth(300);
        // 设置最大高度
        _binding.maxSizeLayout.setMaxHeight(300);

        _binding.btnSmall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _binding.tvContent.setText("123456789");
            }
        });
        _binding.btnBig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String content = _binding.tvContent.getText().toString() + _binding.tvContent.getText().toString();
                _binding.tvContent.setText(content);
            }
        });
    }
}
