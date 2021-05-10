package com.sd.views;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.sd.views.databinding.ActivityMaxSizeLayoutBinding;

public class MaxSizeLayoutActivity extends AppCompatActivity {
    public static final String TAG = MaxSizeLayoutActivity.class.getSimpleName();

    private ActivityMaxSizeLayoutBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityMaxSizeLayoutBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());

        // 设置最大宽度
        mBinding.maxSizeLayout.setMaxWidth(300);
        // 设置最大高度
        mBinding.maxSizeLayout.setMaxHeight(300);

        mBinding.btnSmall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBinding.tvContent.setText("123456789");
            }
        });
        mBinding.btnBig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String content = mBinding.tvContent.getText().toString() + mBinding.tvContent.getText().toString();
                mBinding.tvContent.setText(content);
            }
        });
    }
}
