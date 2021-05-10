package com.sd.lib.views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class FMaxSizeLayout extends FrameLayout {
    private Integer mMaxHeight = null;

    public FMaxSizeLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
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

        if (mMaxHeight != null) {
            final int height = getMeasuredHeight();
            if (height > mMaxHeight) {
                final int heightSpec = MeasureSpec.makeMeasureSpec(mMaxHeight, MeasureSpec.AT_MOST);
                super.onMeasure(widthMeasureSpec, heightSpec);
            }
        }
    }
}
