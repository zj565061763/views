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

    private DrawableConfig mDrawableConfigLeft;
    private DrawableConfig mDrawableConfigRight;

    private void init()
    {

    }

    /**
     * 设置左边图片
     *
     * @param drawableLeft
     */
    public final void setDrawableLeft(Drawable drawableLeft)
    {
        getDrawableConfigLeft().setBounds(drawableLeft);
        setCompoundDrawables(drawableLeft, getCompoundDrawables()[1], getCompoundDrawables()[2], getCompoundDrawables()[3]);
    }

    /**
     * 设置右边图片
     *
     * @param drawableRight
     */
    public final void setDrawableRight(Drawable drawableRight)
    {
        getDrawableConfigRight().setBounds(drawableRight);
        setCompoundDrawables(getCompoundDrawables()[0], getCompoundDrawables()[1], drawableRight, getCompoundDrawables()[3]);
    }

    public final Drawable getDrawableLeft()
    {
        return getCompoundDrawables()[0];
    }

    public final Drawable getDrawableRight()
    {
        return getCompoundDrawables()[2];
    }

    public final DrawableConfig getDrawableConfigLeft()
    {
        if (mDrawableConfigLeft == null)
        {
            mDrawableConfigLeft = new DrawableConfig();
        }
        return mDrawableConfigLeft;
    }

    public final DrawableConfig getDrawableConfigRight()
    {
        if (mDrawableConfigRight == null)
        {
            mDrawableConfigRight = new DrawableConfig();
        }
        return mDrawableConfigRight;
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        getDrawableConfigLeft().setBounds(getDrawableLeft());
        getDrawableConfigRight().setBounds(getDrawableRight());
        super.onDraw(canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        if (event.getAction() == MotionEvent.ACTION_UP)
        {
            final float x = event.getX();
            final Drawable drawableLeft = getDrawableLeft();
            final Drawable drawableRight = getDrawableRight();

            if (drawableLeft != null)
            {
                final int left = getPaddingLeft();
                final int right = getTotalPaddingLeft();

                if (x > left && x < right)
                {
                    onClickDrawableLeft();
                }
            }
            if (drawableRight != null)
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
