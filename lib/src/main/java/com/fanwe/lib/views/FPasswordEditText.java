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

    private static final int TYPE_TEXT_PASSWORD = 129;

    /**
     * 是否显示密码
     */
    private boolean mIsPasswordVisible = false;

    private Drawable mDrawablePasswordVisible;
    private Drawable mDrawablePasswordInvisible;

    private void init()
    {
        addTagView(this);

        setDrawablePasswordVisible(getResources().getDrawable(R.drawable.ic_eye_show_content_on));
        setDrawablePasswordInvisible(getResources().getDrawable(R.drawable.ic_eye_show_content_off));

        final float scale = getResources().getDisplayMetrics().density;
        getDrawableConfigRight().setWidth((int) (20 * scale));

        updateInputType();
    }

    /**
     * 设置密码可见状态的图片
     *
     * @param drawable
     */
    public void setDrawablePasswordVisible(Drawable drawable)
    {
        mDrawablePasswordVisible = drawable;
        updateTagViewState(this);
    }

    /**
     * 设置密码不可见状态的图片
     *
     * @param drawable
     */
    public void setDrawablePasswordInvisible(Drawable drawable)
    {
        mDrawablePasswordInvisible = drawable;
        updateTagViewState(this);
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

        mIsPasswordVisible = !mIsPasswordVisible;

        updateTagViewState(this);
        updateInputType();
    }

    private void updateInputType()
    {
        final int selection = getSelectionEnd();
        if (mIsPasswordVisible)
        {
            setInputType(InputType.TYPE_CLASS_TEXT);
        } else
        {
            setInputType(TYPE_TEXT_PASSWORD);
        }
        setSelection(selection);
    }
}
