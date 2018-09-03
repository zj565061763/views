package com.sd.lib.views;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

/**
 * 一次性全部展示的RecyclerView，需要在android.support.v4.widget.NestedScrollView里面使用
 */
public class FFullRecyclerView extends FRecyclerView
{
    public FFullRecyclerView(Context context)
    {
        super(context);
        init();
    }

    public FFullRecyclerView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        init();
    }

    public FFullRecyclerView(Context context, AttributeSet attrs, int defStyle)
    {
        super(context, attrs, defStyle);
        init();
    }

    private boolean mIsFocusable;

    private void init()
    {
        setNestedScrollingEnabled(false);
    }

    @Override
    public void setAdapter(RecyclerView.Adapter adapter)
    {
        mIsFocusable = isFocusable();
        setFocusable(false);
        super.setAdapter(adapter);
        setFocusable(mIsFocusable);
    }
}
