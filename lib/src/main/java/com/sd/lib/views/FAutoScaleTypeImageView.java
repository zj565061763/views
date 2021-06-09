package com.sd.lib.views;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;

/**
 * 根据图片宽高比例和控件宽高比例的差值，自动改变{@link ScaleType}
 * <li>差值 > 设置的值，默认{@link ScaleType#FIT_CENTER}
 * <li>差值 <= 设置的值，默认{@link ScaleType#CENTER_CROP}
 */
public class FAutoScaleTypeImageView extends AppCompatImageView {
    public FAutoScaleTypeImageView(@NonNull Context context) {
        super(context);
    }

    public FAutoScaleTypeImageView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public FAutoScaleTypeImageView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private float mWHScaleDelta = 0.2f;
    private ScaleTypeHandler mScaleTypeHandler;

    /**
     * 设置图片的宽高比例和控件的宽高比例的差值
     */
    public void setWHScaleDelta(float delta) {
        if (mWHScaleDelta != delta) {
            mWHScaleDelta = delta;
            checkScaleType(getDrawable());
        }
    }

    /**
     * 设置{@link ScaleTypeHandler}
     */
    public void setScaleTypeHandler(ScaleTypeHandler handler) {
        if (mScaleTypeHandler != handler) {
            mScaleTypeHandler = handler;
            checkScaleType(getDrawable());
        }
    }

    private ScaleTypeHandler getScaleTypeHandler() {
        if (mScaleTypeHandler == null) {
            mScaleTypeHandler = new ScaleTypeHandler() {
                @Override
                public ScaleType onStretch() {
                    return ScaleType.CENTER_CROP;
                }

                @Override
                public ScaleType onFit() {
                    return ScaleType.FIT_CENTER;
                }
            };
        }
        return mScaleTypeHandler;
    }

    @Override
    public void setImageDrawable(@Nullable Drawable drawable) {
        checkScaleType(drawable);
        super.setImageDrawable(drawable);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        if (changed) {
            checkScaleType(getDrawable());
        }
    }

    private boolean checkScaleType(Drawable drawable) {
        if (drawable == null) return false;

        final int drawableWidth = drawable.getIntrinsicWidth();
        final int drawableHeight = drawable.getIntrinsicHeight();
        if (drawableWidth <= 0 || drawableHeight <= 0) return false;

        final int width = getWidth();
        if (width <= 0) return false;

        final int height = getHeight();
        if (height <= 0) return false;

        final float viewScale = (float) width / height;
        final float drawableScale = (float) drawableWidth / drawableHeight;
        final float deltaScale = Math.abs(viewScale - drawableScale);

        final ScaleType scaleType = deltaScale > mWHScaleDelta ?
                getScaleTypeHandler().onFit() :
                getScaleTypeHandler().onStretch();

        if (getScaleType() == scaleType) {
            return false;
        }

        setScaleType(scaleType);
        return true;
    }

    public interface ScaleTypeHandler {
        /**
         * 宽高比例的差值小于等于设置的值，此时拉伸展示
         */
        ScaleType onStretch();

        /**
         * 宽高比例的差值大于设置的值，此时按比例展示
         */
        ScaleType onFit();
    }
}
