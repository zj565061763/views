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

    private void init()
    {
        addTagView(this);
        setClearDrawable(getResources().getDrawable(R.drawable.lib_views_selector_edit_clear));
    }

    public void setClearDrawable(Drawable clearDrawable)
    {
        mClearDrawable = clearDrawable;
        if (mClearDrawable != null)
        {
            mClearDrawable.setBounds(0, 0, mClearDrawable.getIntrinsicWidth(), mClearDrawable.getIntrinsicHeight());
        }
        updateTagViewState(this);
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
