package com.fanwe.lib.views;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.InputType;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;

/**
 * Created by zhengjun on 2018/4/4.
 */
public class FPasswordEditText extends FDrawableEditText implements FTagEditText.TagView
{
    public FPasswordEditText(Context context)
    {
        super(context);
        init();
    }

    public FPasswordEditText(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        init();
    }

    public FPasswordEditText(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
        init();
    }

    /**
     * 是否显示密码
     */
    private boolean mIsPasswordVisible = false;
    private int mInputTypeOriginal;

    private Drawable mDrawablePasswordVisible;
    private Drawable mDrawablePasswordInvisible;

    private void init()
    {
        addTagView(this);

        setDrawablePasswordVisible(getResources().getDrawable(R.drawable.ic_eye_show_content_on));
        setDrawablePasswordInvisible(getResources().getDrawable(R.drawable.ic_eye_show_content_off));

        final float scale = getResources().getDisplayMetrics().density;
        getDrawableConfigRight().setWidth((int) (16 * scale));
    }

    public void setDrawablePasswordVisible(Drawable drawable)
    {
        mDrawablePasswordVisible = drawable;
        updateTagViewState(this);
    }

    public void setDrawablePasswordInvisible(Drawable drawable)
    {
        mDrawablePasswordInvisible = drawable;
        updateTagViewState(this);
    }

    @Override
    public void setInputType(int type)
    {
        super.setInputType(type);
        mInputTypeOriginal = type;
    }

    @Override
    public void updateTagViewState(EditText editText)
    {
        Drawable drawable = null;

        if (getVisibility() == View.VISIBLE
                && isFocused()
                && isEnabled()
                && getText().length() > 0)
        {
            if (mIsPasswordVisible)
            {
                drawable = mDrawablePasswordVisible;
            } else
            {
                drawable = mDrawablePasswordInvisible;
            }
        }

        setDrawableRight(drawable);
    }

    @Override
    protected void onClickDrawableRight()
    {
        super.onClickDrawableRight();

        if (mIsPasswordVisible)
        {
            mIsPasswordVisible = false;
            super.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
        } else
        {
            mIsPasswordVisible = true;
            super.setInputType(mInputTypeOriginal);
        }
        setSelection(getText().toString().length());
        updateTagViewState(this);
    }
}
