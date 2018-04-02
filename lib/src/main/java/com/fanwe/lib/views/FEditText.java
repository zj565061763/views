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
public class FEditText extends FrameLayout
{
    public FEditText(Context context)
    {
        super(context);
        init();
    }

    public FEditText(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        init();
    }

    public FEditText(Context context, AttributeSet attrs, int defStyleAttr)
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

            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT);
            params.gravity = Gravity.RIGHT | Gravity.CENTER_VERTICAL;

            addView(mImageViewRight, params);
        }
        return mImageViewRight;
    }
}
