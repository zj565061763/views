package com.sd.lib.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.FrameLayout;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

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
    private final Map<Callback, String> mCallbackHolder = new ConcurrentHashMap<>();

    /**
     * 添加回调
     *
     * @param callback
     */
    public void addCallback(Callback callback)
    {
        if (callback == null)
            return;

        mCallbackHolder.put(callback, "");
    }

    /**
     * 移除回调
     *
     * @param callback
     */
    public void removeCallback(Callback callback)
    {
        if (callback == null)
            return;

        mCallbackHolder.remove(callback);
    }

    /**
     * 设置内容View
     *
     * @param view
     */
    public final void setContentView(View view)
    {
        final View oldView = mContentView;
        if (oldView != view)
        {
            mContentView = view;

            if (oldView != null)
                removeView(oldView);

            if (view != null)
            {
                if (view.getParent() != FObservableLayout.this)
                    removeViewFromParent(view);

                addView(view);
            }

            for (Callback item : mCallbackHolder.keySet())
            {
                item.onContentChanged(oldView, view);
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
        {
            // child被直接调用移除，这里也需要通知回调对象
            setContentView(null);
        }
    }

    private static void removeViewFromParent(final View view)
    {
        if (view == null)
            return;

        final ViewParent parent = view.getParent();
        if (parent == null)
            return;

        try
        {
            final ViewGroup viewGroup = (ViewGroup) parent;
            viewGroup.removeView(view);
        } catch (Exception e)
        {
        }
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
