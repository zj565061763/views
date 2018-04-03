package com.fanwe.lib.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
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
                if (editText.getLayoutParams() == null)
                {
                    FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.MATCH_PARENT);

                    editText.setLayoutParams(params);
                }
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
                if (tagView.getLayoutParams() == null)
                {
                    FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT);
                    params.gravity = Gravity.RIGHT | Gravity.CENTER_VERTICAL;
                    params.rightMargin = (int) (getResources().getDisplayMetrics().density * 10);

                    tagView.setLayoutParams(params);
                }
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
