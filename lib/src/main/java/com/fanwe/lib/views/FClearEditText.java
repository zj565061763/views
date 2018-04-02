package com.fanwe.lib.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;

/**
 * Created by zhengjun on 2018/4/2.
 */
public class FClearEditText extends FrameLayout
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

    private EditText mEditText;
    private ImageView mImageViewRight;

    private void init()
    {
        FrameLayout.LayoutParams paramsEditText = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);

        addView(getEditText(), paramsEditText);

        FrameLayout.LayoutParams paramsImageViewRight = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        paramsImageViewRight.gravity = Gravity.RIGHT | Gravity.CENTER_VERTICAL;

        addView(getImageViewRight(), paramsImageViewRight);
    }

    public EditText getEditText()
    {
        if (mEditText == null)
        {
            mEditText = new EditText(getContext());
        }
        return mEditText;
    }

    public ImageView getImageViewRight()
    {
        if (mImageViewRight == null)
        {
            mImageViewRight = new ImageView(getContext());
        }
        return mImageViewRight;
    }
}
