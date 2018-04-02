package com.fanwe.views;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class MainActivity extends AppCompatActivity
{
    public static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        FEditText editText = findViewById(R.id.et_content);
        editText.setCallback(new FEditText.Callback()
        {
            @Override
            public void onFocusChanged(boolean focused)
            {
                Log.i(TAG, "onFocusChanged:" + focused);
            }

            @Override
            public void onEnableChanged(boolean enabled)
            {
                Log.i(TAG, "onEnableChanged:" + enabled);
            }
        });
    }
}
