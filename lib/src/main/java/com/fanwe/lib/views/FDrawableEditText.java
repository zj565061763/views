package com.fanwe.lib.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.fanwe.lib.views.utils.DrawableConfig;

/**
 * Created by zhengjun on 2018/4/4.
 */

public class FDrawableEditText extends FTagEditText
{
    public FDrawableEditText(Context context)
    {
        super(context);
        init();
    }

    public FDrawableEditText(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        init();
    }

    public FDrawableEditText(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
        init();
    }

    private Drawable mDrawableLeft;
    private Drawable mDrawableRight;

    private DrawableConfig mDrawableLeftConfig;
    private DrawableConfig mDrawableRightConfig;

    private void init()
    {

    }

    public final void setDrawableLeft(Drawable drawableLeft)
    {
        mDrawableLeft = drawableLeft;
        setCompoundDrawables(drawableLeft, getCompoundDrawables()[1], getCompoundDrawables()[2], getCompoundDrawables()[3]);
    }

    public final void setDrawableRight(Drawable drawableRight)
    {
        mDrawableRight = drawableRight;
        setCompoundDrawables(getCompoundDrawables()[0], getCompoundDrawables()[1], drawableRight, getCompoundDrawables()[3]);
    }

    public final Drawable getDrawableLeft()
    {
        return mDrawableLeft;
    }

    public final Drawable getDrawableRight()
    {
        return mDrawableRight;
    }

    public final DrawableConfig getDrawableLeftConfig()
    {
        if (mDrawableLeftConfig == null)
        {
            mDrawableLeftConfig = new DrawableConfig();
        }
        return mDrawableLeftConfig;
    }

    public final DrawableConfig getDrawableRightConfig()
    {
        if (mDrawableRightConfig == null)
        {
            mDrawableRightConfig = new DrawableConfig();
        }
        return mDrawableRightConfig;
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        getDrawableLeftConfig().setBounds(mDrawableLeft);
        getDrawableRightConfig().setBounds(mDrawableRight);
        super.onDraw(canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        if (event.getAction() == MotionEvent.ACTION_UP)
        {
            final float x = event.getX();

            if (getCompoundDrawables()[0] != null)
            {
                final int left = getPaddingLeft();
                final int right = getTotalPaddingLeft();

                if (x > left && x < right)
                {
                    onClickDrawableLeft();
                }
            }
            if (getCompoundDrawables()[2] != null)
            {
                final int left = getWidth() - getTotalPaddingRight();
                final int right = getWidth() - getPaddingRight();

                if (x > left && x < right)
                {
                    onClickDrawableRight();
                }
            }
        }

        return super.onTouchEvent(event);
    }

    protected void onClickDrawableLeft()
    {
    }

    protected void onClickDrawableRight()
    {
    }
}
