package com.sd.lib.views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * 限制最大宽高的布局
 */
public class FMaxSizeLayout extends FrameLayout {
    /** 最大宽度 */
    private Integer mMaxWidth = null;
    /** 最大高度 */
    private Integer mMaxHeight = null;
    /** 是否检查{@link MeasureSpec#EXACTLY} */
    private boolean mCheckMeasureExactly = false;

    public FMaxSizeLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * 设置最大宽度
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

    /**
     * 设置是否检查{@link MeasureSpec#EXACTLY}，默认不检查
     *
     * @param checkMeasureExactly
     */
    public void setCheckMeasureExactly(boolean checkMeasureExactly) {
        if (mCheckMeasureExactly != checkMeasureExactly) {
            mCheckMeasureExactly = checkMeasureExactly;
            requestLayout();
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (mMaxWidth != null || mMaxHeight != null) {
            super.onMeasure(getSpec(widthMeasureSpec, mMaxWidth),
                    getSpec(heightMeasureSpec, mMaxHeight));
        } else {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }
    }

    private int getSpec(int measureSpec, Integer maxSize) {
        if (maxSize == null) {
            return measureSpec;
        }

        int resultSpec = measureSpec;

        final int size = MeasureSpec.getSize(measureSpec);
        final int mode = MeasureSpec.getMode(measureSpec);

        final boolean checkExactly = mCheckMeasureExactly && mode == MeasureSpec.EXACTLY;
        if (mode == MeasureSpec.AT_MOST || checkExactly) {
            if (size > maxSize) {
                resultSpec = MeasureSpec.makeMeasureSpec(maxSize, mode);
            }
        } else if (mode == MeasureSpec.UNSPECIFIED) {
            resultSpec = MeasureSpec.makeMeasureSpec(maxSize, MeasureSpec.AT_MOST);
        }
        return resultSpec;
    }
}
