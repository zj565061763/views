package com.sd.lib.views;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;

/**
 * 圆形CardView
 */
public class FCircleCardView extends CardView
{
    public FCircleCardView(Context context)
    {
        super(context);
    }

    public FCircleCardView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
    }

    public FCircleCardView(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        final int measuredWidth = getMeasuredWidth();
        final int measuredHeight = getMeasuredHeight();

        final int maxSize = Math.max(measuredWidth, measuredHeight);
        final int maxSpec = MeasureSpec.makeMeasureSpec(maxSize, MeasureSpec.EXACTLY);

        super.onMeasure(maxSpec, maxSpec);

        setRadius(maxSize / 2);
    }
}
