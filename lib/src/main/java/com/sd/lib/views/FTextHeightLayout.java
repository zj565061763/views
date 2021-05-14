package com.sd.lib.views;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class FTextHeightLayout extends FrameLayout
{
    private TextView mTextView;

    public FTextHeightLayout(@NonNull Context context, @Nullable AttributeSet attrs)
    {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (mTextView != null)
        {
            final int lineHeight = mTextView.getLayout().getHeight();
            final int measureHeight = mTextView.getMeasuredHeight();
            Log.i(FTextHeightLayout.class.getSimpleName(), "lineHeight:" + lineHeight + " measureHeight:" + measureHeight);
        }
    }

    @Override
    public void onViewAdded(View child)
    {
        super.onViewAdded(child);
        if (mTextView == null && child instanceof TextView)
        {
            mTextView = (TextView) child;
        }
    }

    @Override
    public void onViewRemoved(View child)
    {
        super.onViewRemoved(child);
        if (mTextView == child)
        {
            mTextView = null;
        }
    }
}
