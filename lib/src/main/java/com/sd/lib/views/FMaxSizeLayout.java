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
    public FMaxSizeLayout(@NonNull Context context) {
        super(context);
    }

    public FMaxSizeLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public FMaxSizeLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /** 最大宽度 */
    private int mMaxWidth = 0;
    /** 最大高度 */
    private int mMaxHeight = 0;

    /**
     * 设置最大宽度，大于0才有效
     */
    public void setMaxWidth(int maxWidth) {
        if (mMaxWidth != maxWidth) {
            mMaxWidth = maxWidth;
            requestLayout();
        }
    }

    /**
     * 设置最大高度，大于0才有效
     */
    public void setMaxHeight(int maxHeight) {
        if (mMaxHeight != maxHeight) {
            mMaxHeight = maxHeight;
            requestLayout();
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(getSpec(widthMeasureSpec, mMaxWidth),
                getSpec(heightMeasureSpec, mMaxHeight));
    }

    private static int getSpec(int measureSpec, int maxSize) {
        if (maxSize <= 0) {
            return measureSpec;
        }

        boolean makeMeasureSpec = false;
        final int mode = MeasureSpec.getMode(measureSpec);
        switch (mode) {
            case MeasureSpec.AT_MOST:
                final int size = MeasureSpec.getSize(measureSpec);
                makeMeasureSpec = size > maxSize;
                break;
            case MeasureSpec.UNSPECIFIED:
                makeMeasureSpec = true;
                break;
        }

        return makeMeasureSpec ?
                MeasureSpec.makeMeasureSpec(maxSize, MeasureSpec.AT_MOST) :
                measureSpec;
    }
}