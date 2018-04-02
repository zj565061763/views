package com.fanwe.lib.views;

import android.content.Context;
import android.graphics.Rect;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;

/**
 * Created by zhengjun on 2018/4/2.
 */
public class FClearEditText extends EditText implements TextWatcher
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

    private View mClearView;

    private void init()
    {
        addTextChangedListener(this);
        changeVisibilityIfNeed();
    }

    public void setClearView(View clearView)
    {
        mClearView = clearView;
        changeVisibilityIfNeed();
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
    protected void onFocusChanged(boolean focused, int direction, Rect previouslyFocusedRect)
    {
        super.onFocusChanged(focused, direction, previouslyFocusedRect);
        changeVisibilityIfNeed();
    }

    @Override
    public void setEnabled(boolean enabled)
    {
        super.setEnabled(enabled);
        changeVisibilityIfNeed();
    }

    private void changeVisibilityIfNeed()
    {
        if (mClearView == null)
        {
            return;
        }

        if (isFocused() && isEnabled() && getText().length() > 0)
        {
            mClearView.setVisibility(View.VISIBLE);
        } else
        {
            mClearView.setVisibility(View.GONE);
        }
    }
}
