package com.sd.lib.views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class FMaxSizeLayout extends FrameLayout {

    private Integer mMaxWidth = null;
    private Integer mMaxHeight = null;

    public FMaxSizeLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * 设置最大高度
     *
     * @param maxWidth
     */
    public void setMaxWidth(Integer maxWidth) {
        if (maxWidth != null && maxWidth < 0) {
            maxWidth = 0;
        }

        if (mMaxWidth != maxWidth) {
            mMaxWidth = maxWidth;
            requestLayout();
        }
    }

    /**
     * 设置最大高度
     *
     * @param maxHeight
     */
    public void setMaxHeight(Integer maxHeight) {
        if (maxHeight != null && maxHeight < 0) {
            maxHeight = 0;
        }

        if (mMaxHeight != maxHeight) {
            mMaxHeight = maxHeight;
            requestLayout();
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        if (mMaxWidth != null || mMaxHeight != null) {
            int widthSpec = widthMeasureSpec;
            int heightSpec = heightMeasureSpec;

            if (mMaxWidth != null) {
                if (getMeasuredWidth() > mMaxWidth) {
                    widthSpec = MeasureSpec.makeMeasureSpec(mMaxWidth, MeasureSpec.AT_MOST);
                }
            }

            if (mMaxHeight != null) {
                if (getMeasuredHeight() > mMaxHeight) {
                    heightSpec = MeasureSpec.makeMeasureSpec(mMaxHeight, MeasureSpec.AT_MOST);
                }
            }

            super.onMeasure(widthSpec, heightSpec);
        }
    }
}
