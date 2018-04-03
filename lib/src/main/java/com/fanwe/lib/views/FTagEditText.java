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
public abstract class FTagEditText extends EditText implements TextWatcher
{
    public FTagEditText(Context context)
    {
        super(context);
        init();
    }

    public FTagEditText(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        init();
    }

    public FTagEditText(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
        init();
    }

    private View mTagView;

    private void init()
    {
        addTextChangedListener(this);
        changeVisibilityIfNeed();
    }

    public void setTagView(View tagView)
    {
        mTagView = tagView;
        changeVisibilityIfNeed();
    }

    public View getTagView()
    {
        return mTagView;
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

    @Override
    protected void onVisibilityChanged(View changedView, int visibility)
    {
        super.onVisibilityChanged(changedView, visibility);
        if (changedView == this)
        {
            changeVisibilityIfNeed();
        }
    }

    private void changeVisibilityIfNeed()
    {
        if (mTagView == null)
        {
            return;
        }

        if (getVisibility() == View.VISIBLE
                && isFocused()
                && isEnabled()
                && showTagView())
        {
            mTagView.setVisibility(View.VISIBLE);
        } else
        {
            mTagView.setVisibility(View.GONE);
        }
    }

    /**
     * true-显示tagview，false-隐藏tagview
     *
     * @return
     */
    protected abstract boolean showTagView();
}
