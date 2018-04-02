package com.fanwe.lib.views;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by zhengjun on 2018/4/2.
 */
public class FClearEditText extends FIconRightEditText implements
        TextWatcher,
        View.OnClickListener,
        View.OnFocusChangeListener
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

    private boolean mHasFocused;

    private void init()
    {
        getEditText().addTextChangedListener(this);
        getEditText().setOnFocusChangeListener(this);
        getImageViewRight().setImageResource(R.drawable.lib_views_selector_edit_clear);
        getImageViewRight().setOnClickListener(this);

        changeVisibilityIfNeed();
    }

    private void changeVisibilityIfNeed()
    {
        if (mHasFocused && isEnabled() && getEditText().getText().length() > 0)
        {
            getImageViewRight().setVisibility(View.VISIBLE);
        } else
        {
            getImageViewRight().setVisibility(View.GONE);
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after)
    {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count)
    {
        changeVisibilityIfNeed();
    }

    @Override
    public void afterTextChanged(Editable s)
    {

    }

    @Override
    public void onClick(View v)
    {
        if (v == getImageViewRight())
        {
            getEditText().setText("");
        }
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus)
    {
        mHasFocused = hasFocus;
        changeVisibilityIfNeed();
    }
}
