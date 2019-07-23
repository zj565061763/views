package com.sd.lib.views;

import android.content.Context;
import android.os.CountDownTimer;
import android.util.AttributeSet;
import android.view.View;

import androidx.appcompat.widget.AppCompatButton;

public class FCountDownButton extends AppCompatButton
{
    public FCountDownButton(Context context)
    {
        this(context, null);
    }

    public FCountDownButton(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        mTextDefault = getText().toString();

        super.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (mCallback != null)
                    mCallback.onClick(FCountDownButton.this);
            }
        });
    }

    private CountDownTimer mTimer;
    private final String mTextDefault;
    private Callback mCallback;

    public void setCallback(Callback callback)
    {
        mCallback = callback;
    }

    @Override
    public void setOnClickListener(OnClickListener l)
    {
    }

    @Override
    public void setEnabled(boolean enabled)
    {
        super.setEnabled(enabled);
        if (mCallback != null)
            mCallback.onEnableChanged(enabled);
    }

    public void startTimer(long millisInFuture)
    {
        if (millisInFuture <= 0)
            return;

        if (mTimer == null)
        {
            mTimer = new CountDownTimer(millisInFuture, 1000)
            {
                @Override
                public void onTick(long millisUntilFinished)
                {
                    if (mCallback != null)
                        mCallback.onTick(millisUntilFinished, FCountDownButton.this);
                }

                @Override
                public void onFinish()
                {
                    stopTimer();
                    if (mCallback != null)
                        mCallback.onFinish(mTextDefault, FCountDownButton.this);
                }
            };
            mTimer.start();
            setEnabled(false);
        }
    }

    public void stopTimer()
    {
        if (mTimer != null)
        {
            mTimer.cancel();
            mTimer = null;
            setEnabled(true);
        }
    }

    @Override
    protected void onDetachedFromWindow()
    {
        super.onDetachedFromWindow();
        stopTimer();
    }

    public static abstract class Callback
    {
        public void onTick(long millisUntilFinished, FCountDownButton button)
        {
            button.setText((millisUntilFinished / 1000) + "s");
        }

        public void onFinish(String textDefault, FCountDownButton button)
        {
            button.setText(textDefault);
        }

        public void onEnableChanged(boolean enabled)
        {
        }

        public abstract void onClick(FCountDownButton button);
    }
}
