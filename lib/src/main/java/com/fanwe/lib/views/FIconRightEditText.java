package com.fanwe.lib.views;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;

/**
 * Created by zhengjun on 2018/4/2.
 */
public abstract class FIconRightEditText extends FrameLayout
{
    public FIconRightEditText(Context context)
    {
        super(context);
        init();
    }

    public FIconRightEditText(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        init();
    }

    public FIconRightEditText(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
        init();
    }

    private EditText mEditText;
    private ImageView mImageViewRight;

    private void init()
    {
        addView(getEditText());
        addView(getImageViewRight());

        getImageViewRight().setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

            }
        });
    }

    public EditText getEditText()
    {
        if (mEditText == null)
        {
            final FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT);

            mEditText = new EditText(getContext())
            {
                @Override
                protected void onFocusChanged(boolean focused, int direction, Rect previouslyFocusedRect)
                {
                    super.onFocusChanged(focused, direction, previouslyFocusedRect);
                    onFocusChanged_editText(focused);
                }

                @Override
                public void setEnabled(boolean enabled)
                {
                    super.setEnabled(enabled);
                    onEnableChanged_editText(enabled);
                }
            };
            mEditText.setLayoutParams(params);
        }
        return mEditText;
    }

    public ImageView getImageViewRight()
    {
        if (mImageViewRight == null)
        {
            final FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            params.gravity = Gravity.RIGHT | Gravity.CENTER_VERTICAL;

            final float scale = getResources().getDisplayMetrics().density;
            params.rightMargin = (int) (scale * 10);

            mImageViewRight = new ImageView(getContext());
            mImageViewRight.setLayoutParams(params);
        }
        return mImageViewRight;
    }

    protected abstract void onFocusChanged_editText(boolean focused);

    protected abstract void onEnableChanged_editText(boolean focused);
}
