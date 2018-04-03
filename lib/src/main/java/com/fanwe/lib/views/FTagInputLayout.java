package com.fanwe.lib.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.EditText;
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

    private EditText mEditText;
    private View mTagView;

    private void init()
    {
    }

    public EditText getEditText()
    {
        return mEditText;
    }

    public void setEditText(EditText editText)
    {
        if (mEditText != editText)
        {
            if (mEditText != null)
            {
                removeView(mEditText);
            }

            mEditText = editText;

            if (editText != null)
            {
                removeViewFromParent(editText);
                addView(editText);
                orderViewsIfNeed();
            }
        }
    }

    public void setTagView(View tagView)
    {
        if (mTagView != tagView)
        {
            if (mTagView != null)
            {
                removeView(mTagView);
            }

            mTagView = tagView;

            if (tagView != null)
            {
                removeViewFromParent(tagView);
                addView(tagView);
                orderViewsIfNeed();
            }
        }
    }

    public View getTagView()
    {
        return mTagView;
    }

    private void orderViewsIfNeed()
    {
        bringChildToFront(mTagView);

        if (mEditText != null && mEditText instanceof FTagEditText)
        {
            FTagEditText tagEditText = (FTagEditText) mEditText;
            tagEditText.setTagView(mTagView);
        }
    }

    @Override
    protected void onFinishInflate()
    {
        super.onFinishInflate();

        final int count = getChildCount();
        if (count > 0)
        {
            for (int i = 0; i < count; i++)
            {
                final View child = getChildAt(i);
                if (child instanceof EditText)
                {
                    setEditText((EditText) child);
                } else
                {
                    setTagView(child);
                }
            }
        }
    }

    private static void removeViewFromParent(View view)
    {
        if (view == null)
        {
            return;
        }

        final ViewParent parent = view.getParent();
        if (parent != null && parent instanceof ViewGroup)
        {
            final ViewGroup viewGroup = (ViewGroup) parent;
            viewGroup.removeView(view);
        }
    }
}
