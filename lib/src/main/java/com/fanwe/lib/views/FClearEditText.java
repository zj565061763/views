package com.fanwe.lib.views;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;

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

    private int mClearDrawableWidth;
    private int mClearDrawableMarginRight;

    private void init()
    {
        addTagView(this);
        setClearDrawable(getResources().getDrawable(R.drawable.lib_views_selector_edit_clear));

        final float scale = getResources().getDisplayMetrics().density;
        setClearDrawableWidth((int) (scale * 16));
        setClearDrawableMarginRight((int) (scale * 5));
    }

    public void setClearDrawable(Drawable clearDrawable)
    {
        mClearDrawable = clearDrawable;
        updateDrawableLayoutIfNeed();
        updateTagViewState(this);
    }

    public void setClearDrawableWidth(int clearDrawableWidth)
    {
        mClearDrawableWidth = clearDrawableWidth;
        updateDrawableLayoutIfNeed();
    }

    public void setClearDrawableMarginRight(int clearDrawableMarginRight)
    {
        mClearDrawableMarginRight = clearDrawableMarginRight;
        updateDrawableLayoutIfNeed();
    }

    private void updateDrawableLayoutIfNeed()
    {
        if (mClearDrawable == null)
        {
            return;
        }

        int width = mClearDrawable.getIntrinsicWidth();
        int height = mClearDrawable.getIntrinsicHeight();
        if (mClearDrawableWidth > 0)
        {
            height = (int) (mClearDrawableWidth * height / (float) width);
            width = mClearDrawableWidth;
        }

        int left = 0 - mClearDrawableMarginRight;
        int top = 0;
        int right = left + width;
        int bottom = top + height;

        mClearDrawable.setBounds(left, top, right, bottom);
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
}
