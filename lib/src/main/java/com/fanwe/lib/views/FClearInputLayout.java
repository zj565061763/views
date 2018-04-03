package com.fanwe.lib.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.FrameLayout;
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

        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        params.gravity = Gravity.RIGHT | Gravity.CENTER_VERTICAL;
        params.rightMargin = (int) (getResources().getDisplayMetrics().density * 10);

        ImageView imageView = new ImageView(getContext());
        imageView.setLayoutParams(params);
        imageView.setImageResource(R.drawable.lib_views_selector_edit_clear);

        setTagView(imageView);
    }
}
