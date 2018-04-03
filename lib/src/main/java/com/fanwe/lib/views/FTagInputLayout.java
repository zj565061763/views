package com.fanwe.lib.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

/**
 * Created by zhengjun on 2018/4/2.
 */
public class FTagInputLayout extends FrameLayout
{
    public FTagInputLayout(Context context)
    {
        super(context);
        init();
    }

    public FTagInputLayout(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        init();
    }

    public FTagInputLayout(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
        init();
    }

    private FTagEditText mEditText;

    private void init()
    {
        mEditText = new FTagEditText(getContext());
        addView(mEditText);
    }

    public FTagEditText getEditText()
    {
        return mEditText;
    }

    @Override
    public void onViewAdded(View child)
    {
        super.onViewAdded(child);

        if (child != getEditText())
        {
            if (child instanceof FTagEditText.TagView)
            {
                getEditText().addTagView((FTagEditText.TagView) child);
            }
        }
    }

    @Override
    public void onViewRemoved(View child)
    {
        super.onViewRemoved(child);

        if (child == getEditText())
        {
            throw new IllegalArgumentException("FTagEditText can not be remove from FTagInputLayout");
        } else
        {
            if (child instanceof FTagEditText.TagView)
            {
                getEditText().removeTagView((FTagEditText.TagView) child);
            }
        }
    }
}
