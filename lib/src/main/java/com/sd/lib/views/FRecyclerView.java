package com.sd.lib.views;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sd.lib.views.utils.FDividerItemDecoration;

public class FRecyclerView extends RecyclerView
{
    public FRecyclerView(Context context)
    {
        super(context);
        init();
    }

    public FRecyclerView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        init();
    }

    public FRecyclerView(Context context, AttributeSet attrs, int defStyle)
    {
        super(context, attrs, defStyle);
        init();
    }

    private void init()
    {
        setLinearLayoutManager(RecyclerView.VERTICAL);
    }

    //----------Linear----------

    /**
     * 设置线性布局管理器
     *
     * @param orientation {@link RecyclerView#VERTICAL} 或者 {@link RecyclerView#HORIZONTAL}
     */
    public final void setLinearLayoutManager(int orientation)
    {
        if (orientation == RecyclerView.VERTICAL || orientation == RecyclerView.HORIZONTAL)
        {
            LinearLayoutManager manager = null;
            if (getLayoutManager() instanceof LinearLayoutManager)
                manager = (LinearLayoutManager) getLayoutManager();
            else
                manager = new LinearLayoutManager(getContext());

            manager.setOrientation(orientation);
            setLayoutManager(manager);
        } else
        {
            throw new IllegalArgumentException("orientation must be RecyclerView.VERTICAL or RecyclerView.HORIZONTAL");
        }
    }

    /**
     * 返回线性布局管理器
     *
     * @return
     */
    public final LinearLayoutManager getLinearLayoutManager()
    {
        final LayoutManager manager = getLayoutManager();
        if (manager instanceof LinearLayoutManager)
            return (LinearLayoutManager) manager;
        return null;
    }

    //----------Grid----------

    /**
     * 设置网格布局管理器
     *
     * @param orientation {@link RecyclerView#VERTICAL} 或者 {@link RecyclerView#HORIZONTAL}
     * @param spanCount   单行或者单列的网格数量
     */
    public final void setGridLayoutManager(int orientation, int spanCount)
    {
        if (orientation == RecyclerView.VERTICAL || orientation == RecyclerView.HORIZONTAL)
        {
            GridLayoutManager manager = null;
            if (getLayoutManager() instanceof GridLayoutManager)
                manager = (GridLayoutManager) getLayoutManager();
            else
                manager = new GridLayoutManager(getContext(), spanCount);

            manager.setOrientation(orientation);
            setLayoutManager(manager);
        } else
        {
            throw new IllegalArgumentException("orientation must be RecyclerView.VERTICAL or RecyclerView.HORIZONTAL");
        }
    }

    /**
     * 返回网格布局管理器
     *
     * @return
     */
    public final GridLayoutManager getGridLayoutManager()
    {
        final LayoutManager manager = getLayoutManager();
        if (manager instanceof GridLayoutManager)
            return (GridLayoutManager) manager;
        return null;
    }

    //----------scroll----------

    /**
     * 获得item的数量
     *
     * @return
     */
    public final int getItemCount()
    {
        final Adapter adapter = getAdapter();
        return adapter == null ? 0 : adapter.getItemCount();
    }

    /**
     * 滚动到布局开始的位置
     */
    public final void scrollToStart()
    {
        scrollToPosition(0);
    }

    /**
     * 滚动到布局结束的位置
     */
    public final void scrollToEnd()
    {
        final int count = getItemCount();
        if (count > 0)
            scrollToPosition(count - 1);
    }

    //----------divider start----------

    /**
     * 添加横分割线
     *
     * @param drawable
     */
    public final void addDividerHorizontal(Drawable drawable)
    {
        addDividerHorizontal(drawable, 0);
    }

    /**
     * 添加横分割线
     *
     * @param drawable
     * @param padding  分割线左右padding
     */
    public final void addDividerHorizontal(Drawable drawable, int padding)
    {
        FDividerItemDecoration divider = new FDividerItemDecoration(FDividerItemDecoration.HORIZONTAL);
        divider.setDrawable(drawable);
        divider.setPadding(padding);
        addItemDecoration(divider);
    }

    /**
     * 添加竖分割线
     *
     * @param drawable
     */
    public final void addDividerVertical(Drawable drawable)
    {
        addDividerVertical(drawable, 0);
    }

    /**
     * 添加竖分割线
     *
     * @param drawable
     * @param padding  分割线上下padding
     */
    public final void addDividerVertical(Drawable drawable, int padding)
    {
        FDividerItemDecoration divider = new FDividerItemDecoration(FDividerItemDecoration.VERTICAL);
        divider.setDrawable(drawable);
        divider.setPadding(padding);
        addItemDecoration(divider);
    }

    //----------divider end----------
}
