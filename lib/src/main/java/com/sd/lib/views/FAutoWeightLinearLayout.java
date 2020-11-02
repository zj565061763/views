package com.sd.lib.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public class FAutoWeightLinearLayout extends LinearLayout
{
    public FAutoWeightLinearLayout(Context context)
    {
        super(context);
    }

    public FAutoWeightLinearLayout(Context context, AttributeSet attrs)
    {
        super(context, attrs);
    }

    public FAutoWeightLinearLayout(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
    }

    private View mHeaderView;
    private View mFooterView;

    private SizeHandler mSizeHandler;

    private SizeHandler getSizeHandler()
    {
        if (mSizeHandler == null)
            mSizeHandler = getOrientation() == HORIZONTAL ? new HorizontalSizeHandler() : new VerticalSizeHandler();
        return mSizeHandler;
    }

    @Override
    public void setOrientation(int orientation)
    {
        if (getOrientation() != orientation)
            mSizeHandler = null;

        super.setOrientation(orientation);
    }

    @Override
    protected void onFinishInflate()
    {
        super.onFinishInflate();
        if (getChildCount() != 2)
            throw new RuntimeException("child count must be 2");

        mHeaderView = getChildAt(0);
        mFooterView = getChildAt(1);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {
        setWeight(0, mHeaderView);
        setWeight(0, mFooterView);

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        getSizeHandler().onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    private abstract class SizeHandler
    {
        private void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
        {
            final int totalSize = getSize(FAutoWeightLinearLayout.this) - getPadding(FAutoWeightLinearLayout.this);
            final int headerSize = getSize(mHeaderView) + getMargin(mHeaderView);
            final int footerSize = getSize(mFooterView) + getMargin(mFooterView);
            final int leftSize = totalSize - footerSize;
            if (headerSize >= leftSize)
            {
                if (setWeight(1, mHeaderView))
                    FAutoWeightLinearLayout.super.onMeasure(widthMeasureSpec, heightMeasureSpec);
            } else
            {
                if (setWeight(0, mHeaderView))
                    FAutoWeightLinearLayout.super.onMeasure(widthMeasureSpec, heightMeasureSpec);
            }
        }

        protected abstract int getSize(View view);

        protected abstract int getPadding(View view);

        protected abstract int getMargin(View view);
    }

    private final class HorizontalSizeHandler extends SizeHandler
    {
        @Override
        protected int getSize(View view)
        {
            return view.getMeasuredWidth();
        }

        @Override
        protected int getPadding(View view)
        {
            return view.getPaddingLeft() + view.getPaddingRight();
        }

        @Override
        protected int getMargin(View view)
        {
            final ViewGroup.LayoutParams params = view.getLayoutParams();
            if (!(params instanceof MarginLayoutParams))
                return 0;

            final MarginLayoutParams mp = (MarginLayoutParams) params;
            return mp.leftMargin + mp.rightMargin;
        }
    }

    private final class VerticalSizeHandler extends SizeHandler
    {
        @Override
        protected int getSize(View view)
        {
            return view.getMeasuredHeight();
        }

        @Override
        protected int getPadding(View view)
        {
            return view.getPaddingTop() + view.getPaddingBottom();
        }

        @Override
        protected int getMargin(View view)
        {
            final ViewGroup.LayoutParams params = view.getLayoutParams();
            if (!(params instanceof MarginLayoutParams))
                return 0;

            final MarginLayoutParams mp = (MarginLayoutParams) params;
            return mp.topMargin + mp.bottomMargin;
        }
    }

    private static boolean setWeight(int weight, View view)
    {
        final ViewGroup.LayoutParams params = view.getLayoutParams();
        if (!(params instanceof LinearLayout.LayoutParams))
            return false;

        final LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) params;
        if (lp.weight != weight)
        {
            lp.weight = weight;
            return true;
        }
        return false;
    }
}
