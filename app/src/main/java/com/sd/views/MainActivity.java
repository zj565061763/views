package com.sd.views;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.sd.views.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity
{
    public static final String TAG = MainActivity.class.getSimpleName();

    private ActivityMainBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        mBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());

        mBinding.btnHeader.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                final String text = mBinding.tvHeader.getText().toString();
                mBinding.tvHeader.setText(text + text);
            }
        });

        mBinding.btnFooter.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                final String text = mBinding.tvFooter.getText().toString();
                mBinding.tvFooter.setText(text + text);
            }
        });

        mBinding.btnReset.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                mBinding.tvHeader.setText("header");
                mBinding.tvFooter.setText("footer");
            }
        });
    }
}
