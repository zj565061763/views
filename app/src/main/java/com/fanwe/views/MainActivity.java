package com.fanwe.views;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.fanwe.lib.views.FClearInputLayout;

public class MainActivity extends AppCompatActivity
{
    public static final String TAG = MainActivity.class.getSimpleName();

    private FClearInputLayout mClearInputLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mClearInputLayout = findViewById(R.id.view_clear);
    }
}
