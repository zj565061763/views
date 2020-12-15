package com.sd.lib.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

public class FSimpleLayout extends FrameLayout
{
    public FSimpleLayout(Context context, AttributeSet attrs)
    {
        super(context, attrs);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom)
    {
        final int count = getChildCount();
        for (int i = 0; i < count; i++)
        {
            final View child = getChildAt(i);
            if (child == null || child.getVisibility() == GONE)
                continue;

            final int l = child.getLeft();
            final int t = child.getTop();
            final int r = l + child.getMeasuredWidth();
            final int b = t + child.getMeasuredHeight();

            child.layout(l, t, r, b);
        }
    }
}
