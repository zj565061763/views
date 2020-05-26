package com.sd.lib.views;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatImageView;

public class FAutoWidthImageView extends AppCompatImageView
{
    public FAutoWidthImageView(Context context)
    {
        super(context);
        init();
    }

    public FAutoWidthImageView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        init();
    }

    public FAutoWidthImageView(Context context, AttributeSet attrs, int defStyle)
    {
        super(context, attrs, defStyle);
        init();
    }

    private void init()
    {
        setScaleType(ScaleType.FIT_XY);
    }

    @Override
    public void setScaleType(ScaleType scaleType)
    {
        super.setScaleType(ScaleType.FIT_XY);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        final Drawable drawable = getDrawable();
        if (drawable != null)
        {
            final int measuredHeight = getMeasuredHeight();
            final int width = getScaleWidth(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), measuredHeight);
            setMeasuredDimension(width, measuredHeight);
        }
    }

    private static int getScaleWidth(int scaleWidth, int scaleHeight, int height)
    {
        if (scaleHeight == 0)
            return 0;
        return scaleWidth * height / scaleHeight;
    }
}
