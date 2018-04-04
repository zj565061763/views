package com.fanwe.lib.views.utils;

import android.graphics.drawable.Drawable;

/**
 * Created by zhengjun on 2018/4/4.
 */
public class DrawableConfig
{
    private int width;

    public void setBounds(Drawable drawable)
    {
        if (drawable == null)
        {
            return;
        }

        final int[] size = scaleDrawableIfNeed(drawable);

        int left = 0;
        int top = 0;
        int right = left + size[0];
        int bottom = top + size[1];

        drawable.setBounds(left, top, right, bottom);
    }

    private int[] scaleDrawableIfNeed(Drawable drawable)
    {
        int drawableWidth = drawable.getIntrinsicWidth();
        int drawableHeight = drawable.getIntrinsicHeight();
        if (width > 0 && width != drawableWidth)
        {
            drawableHeight = getScaledHeight(width, drawable);
            drawableWidth = width;
        }
        return new int[]{drawableWidth, drawableHeight};
    }

    private static int getScaledHeight(int width, Drawable drawable)
    {
        return (int) (width * drawable.getIntrinsicHeight() / (float) drawable.getIntrinsicWidth());
    }

    public int getWidth()
    {
        return width;
    }

    public void setWidth(int width)
    {
        this.width = width;
    }
}
