package com.sd.lib.views;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatImageView;

public class FAutoHeightImageView extends AppCompatImageView
{
    public FAutoHeightImageView(Context context)
    {
        super(context);
        init();
    }

    public FAutoHeightImageView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        init();
    }

    public FAutoHeightImageView(Context context, AttributeSet attrs, int defStyle)
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
            final int measuredWidth = getMeasuredWidth();
            final int height = getScaleHeight(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), measuredWidth);
            setMeasuredDimension(measuredWidth, height);
        }
    }

    private static int getScaleHeight(int scaleWidth, int scaleHeight, int width)
    {
        if (scaleWidth == 0)
            return 0;
        return scaleHeight * width / scaleWidth;
    }
}
