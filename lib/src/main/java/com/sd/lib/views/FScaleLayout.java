package com.sd.lib.views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;

/**
 * 按比例显示的布局
 */
public class FScaleLayout extends FrameLayout
{
    private ScaleSide mScaleSide = ScaleSide.height;
    private float mWHScale;

    public FScaleLayout(Context context, AttributeSet attrs)
    {
        super(context, attrs);
    }

    /**
     * 设置要缩放的边
     *
     * @param scaleSide
     */
    public void setScaleSide(ScaleSide scaleSide)
    {
        if (scaleSide == null)
            return;

        if (mScaleSide != scaleSide)
        {
            mScaleSide = scaleSide;
            requestLayout();
        }
    }

    /**
     * 设置宽高比例
     *
     * @param width
     * @param height
     */
    public void setWHScale(float width, float height)
    {
        if (height == 0)
        {
            setWHScale(0);
            return;
        }

        final float whScale = width / height;
        setWHScale(whScale);
    }

    /**
     * 设置宽高比例
     *
     * @param whScale
     */
    public void setWHScale(float whScale)
    {
        if (mWHScale != whScale)
        {
            mWHScale = whScale;
            requestLayout();
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (mWHScale > 0)
        {
            if (mScaleSide == ScaleSide.height)
            {
                final int measuredWidth = getMeasuredWidth();
                if (measuredWidth > 0)
                {
                    final int scaleHeight = (int) (measuredWidth / mWHScale);
                    final int scaleHeightMeasureSpec = MeasureSpec.makeMeasureSpec(scaleHeight, MeasureSpec.EXACTLY);
                    super.onMeasure(widthMeasureSpec, scaleHeightMeasureSpec);
                }
            } else if (mScaleSide == ScaleSide.width)
            {
                final int measuredHeight = getMeasuredHeight();
                if (measuredHeight > 0)
                {
                    final int scaleWidth = (int) (measuredHeight * mWHScale);
                    final int scaleWidthMeasureSpec = MeasureSpec.makeMeasureSpec(scaleWidth, MeasureSpec.EXACTLY);
                    super.onMeasure(scaleWidthMeasureSpec, heightMeasureSpec);
                }
            }
        }
    }

    public enum ScaleSide
    {
        width,
        height
    }
}
