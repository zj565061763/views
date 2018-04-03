package com.fanwe.lib.views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by zhengjun on 2018/4/3.
 */
public class FClearInputLayout extends FTagInputLayout
{
    public FClearInputLayout(Context context)
    {
        super(context);
        init();
    }

    public FClearInputLayout(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        init();
    }

    public FClearInputLayout(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init()
    {
        setEditText(new FClearEditText(getContext()));

        ImageView imageView = new ImageView(getContext());
        imageView.setImageResource(R.drawable.lib_views_selector_edit_clear);
        setTagView(imageView);
    }
}
