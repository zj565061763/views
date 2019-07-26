package com.sd.lib.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 当内容view变化会通知回调
 */
public class FObservableLayout extends FrameLayout
{
    public FObservableLayout(Context context)
    {
        super(context);
    }

    public FObservableLayout(Context context, AttributeSet attrs)
    {
        super(context, attrs);
    }

    public FObservableLayout(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
    }

    private View mContentView;
    private final List<Callback> mListCallback = new CopyOnWriteArrayList<>();

    /**
     * 添加回调
     *
     * @param callback
     */
    public void addCallback(Callback callback)
    {
        if (callback == null || mListCallback.contains(callback))
            return;

        mListCallback.add(callback);
    }

    /**
     * 移除回调
     *
     * @param callback
     */
    public void removeCallback(Callback callback)
    {
        mListCallback.remove(callback);
    }

    /**
     * 把当前容器的内容替换为child
     *
     * @param child
     */
    public final void setContentView(View child)
    {
        final View old = mContentView;
        if (old != child)
        {
            mContentView = child;

            if (old != null)
                removeView(old);

            if (child != null && child.getParent() != this)
                addView(child);

            for (Callback item : mListCallback)
            {
                item.onContentChanged(old, child);
            }
        }
    }

    /**
     * 获得内容view
     *
     * @return
     */
    public final View getContentView()
    {
        return mContentView;
    }

    @Override
    public void onViewAdded(View child)
    {
        super.onViewAdded(child);
        if (child != mContentView)
            throw new RuntimeException("Illegal child:" + child);
    }

    @Override
    public void onViewRemoved(View child)
    {
        super.onViewRemoved(child);
        if (child == mContentView)
            setContentView(null);
    }

    public interface Callback
    {
        /**
         * 内容View变化
         *
         * @param oldView
         * @param newView
         */
        void onContentChanged(View oldView, View newView);
    }
}
