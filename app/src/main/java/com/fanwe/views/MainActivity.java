package com.fanwe.views;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.fanwe.lib.views.FTagInputLayout;

public class MainActivity extends AppCompatActivity
{
    public static final String TAG = MainActivity.class.getSimpleName();

    private FTagInputLayout view_input;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        view_input = findViewById(R.id.view_input);
    }
}
