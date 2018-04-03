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
public class FIconRightInputLayout extends FrameLayout
{
    public FIconRightInputLayout(Context context)
    {
        super(context);
        init();
    }

    public FIconRightInputLayout(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        init();
    }

    public FIconRightInputLayout(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
        init();
    }

    private EditText mEditText;
    private ImageView mImageViewRight;

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
                addEditText(editText);
            }
        }
    }

    private void addEditText(EditText editText)
    {
        ViewGroup.LayoutParams params = editText.getLayoutParams();
        if (params == null)
        {
            editText.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT));
        }

        removeView(getImageViewRight());
        addView(editText);
        addView(getImageViewRight());

        if (editText instanceof FTagEditText)
        {
            FTagEditText tagEditText = (FTagEditText) editText;
            if (tagEditText.getTagView() == null)
            {
                tagEditText.setTagView(getImageViewRight());
            }
        }
    }

    public ImageView getImageViewRight()
    {
        if (mImageViewRight == null)
        {
            mImageViewRight = new ImageView(getContext());

            final FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            params.gravity = Gravity.RIGHT | Gravity.CENTER_VERTICAL;

            final float scale = getResources().getDisplayMetrics().density;
            params.rightMargin = (int) (scale * 10);

            mImageViewRight.setLayoutParams(params);
        }
        return mImageViewRight;
    }
}
