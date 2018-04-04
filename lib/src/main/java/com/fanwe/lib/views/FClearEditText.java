package com.fanwe.lib.views;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;

import com.fanwe.lib.views.utils.DrawableConfig;

/**
 * Created by zhengjun on 2018/4/4.
 */

public class FClearEditText extends FTagEditText implements FTagEditText.TagView
{
    public FClearEditText(Context context)
    {
        super(context);
        init();
    }

    public FClearEditText(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        init();
    }

    public FClearEditText(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
        init();
    }

    private Drawable mClearDrawable;
    private DrawableConfig mClearDrawableConfig;

    private void init()
    {
        addTagView(this);
        setClearDrawable(getResources().getDrawable(R.drawable.lib_views_selector_edit_clear));

        final float scale = getResources().getDisplayMetrics().density;
        setClearDrawableWidth((int) (scale * 16));
        setClearDrawableMarginRight((int) (scale * 5));
    }

    private DrawableConfig getClearDrawableConfig()
    {
        if (mClearDrawableConfig == null)
        {
            mClearDrawableConfig = new DrawableConfig();
        }
        return mClearDrawableConfig;
    }

    /**
     * 设置清除图片
     *
     * @param clearDrawable
     */
    public void setClearDrawable(Drawable clearDrawable)
    {
        mClearDrawable = clearDrawable;
        getClearDrawableConfig().setBoundsDrawableRight(clearDrawable);
        updateTagViewState(this);
    }

    /**
     * 设置清除图片宽度
     *
     * @param clearDrawableWidth
     */
    public void setClearDrawableWidth(int clearDrawableWidth)
    {
        getClearDrawableConfig().setWidth(clearDrawableWidth);
        getClearDrawableConfig().setBoundsDrawableRight(mClearDrawable);
    }

    /**
     * 设置清除图片右边间距
     *
     * @param clearDrawableMarginRight
     */
    public void setClearDrawableMarginRight(int clearDrawableMarginRight)
    {
        getClearDrawableConfig().setMarginRight(clearDrawableMarginRight);
        getClearDrawableConfig().setBoundsDrawableRight(mClearDrawable);
    }

    @Override
    public void updateTagViewState(EditText editText)
    {
        Drawable drawable = null;

        if (getVisibility() == View.VISIBLE
                && isFocused()
                && isEnabled()
                && getText().length() > 0)
        {
            drawable = mClearDrawable;
        }

        setCompoundDrawables(getCompoundDrawables()[0], getCompoundDrawables()[1], drawable, getCompoundDrawables()[3]);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        if (event.getAction() == MotionEvent.ACTION_UP)
        {
            if (mClearDrawable != null)
            {
                boolean touch = event.getX() > (getWidth() - getTotalPaddingRight())
                        && (event.getX() < ((getWidth() - getPaddingRight())));

                if (touch)
                {
                    setText("");
                }
            }
        }

        return super.onTouchEvent(event);
    }
}
