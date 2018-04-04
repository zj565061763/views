package com.fanwe.lib.views;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;

/**
 * Created by zhengjun on 2018/4/4.
 */
public class FClearEditText extends FDrawableEditText implements FTagEditText.TagView
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

    private Drawable mDrawableClear;

    private void init()
    {
        addTagView(this);
        setDrawableRight(getResources().getDrawable(R.drawable.lib_views_selector_edit_clear));

        final float scale = getResources().getDisplayMetrics().density;
        getDrawableRightConfig().setWidth((int) (16 * scale));
    }

    /**
     * 设置清空内容的图片
     *
     * @param drawable
     */
    public void setDrawableClear(Drawable drawable)
    {
        mDrawableClear = drawable;
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
            drawable = mDrawableClear;
        }

        setDrawableRight(drawable);
    }

    @Override
    protected void onClickDrawableRight()
    {
        super.onClickDrawableRight();
        setText("");
    }
}
