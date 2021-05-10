package com.sd.lib.views;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * 限制最大宽高的布局
 */
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
        if (maxWidth != null && maxWidth <= 0) {
            maxWidth = null;
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
        if (maxHeight != null && maxHeight <= 0) {
            maxHeight = null;
        }

        if (mMaxHeight != maxHeight) {
            mMaxHeight = maxHeight;
            requestLayout();
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (mMaxWidth != null || mMaxHeight != null) {
            super.onMeasure(getSpec(widthMeasureSpec, mMaxWidth),
                    getSpec(heightMeasureSpec, mMaxHeight));
            Log.i(FMaxSizeLayout.class.getSimpleName(), getMeasuredWidth() + "," + getMeasuredHeight());
        } else {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }
    }

    private static int getSpec(int measureSpec, Integer maxSize) {
        if (maxSize == null) {
            return measureSpec;
        }

        int resultSpec = measureSpec;

        final int size = MeasureSpec.getSize(measureSpec);
        final int mode = MeasureSpec.getMode(measureSpec);
        if (mode == MeasureSpec.EXACTLY || mode == MeasureSpec.AT_MOST) {
            if (size > maxSize) {
                resultSpec = MeasureSpec.makeMeasureSpec(maxSize, mode);
            }
        } else if (mode == MeasureSpec.UNSPECIFIED) {
            resultSpec = MeasureSpec.makeMeasureSpec(maxSize, MeasureSpec.AT_MOST);
        }
        return resultSpec;
    }
}
