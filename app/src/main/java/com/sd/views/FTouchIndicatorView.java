package com.sd.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class FTouchIndicatorView extends View {
    /** 字体大小 */
    private int mTextSize = 0;
    /** 正常字体颜色 */
    private int mTextColorNormal = Color.BLACK;
    /** 正常字体颜色 */
    private int mTextColorSelected = Color.RED;
    /** 文字数组 */
    private String[] mTextArray = new String[]{
            "A", "B", "C", "D", "E", "F", "G",
            "H", "I", "J", "K", "L", "M", "N",
            "O", "P", "Q", "R", "S", "T", "U",
            "V", "W", "X", "Y", "Z"
    };

    private final Paint mPaint = new Paint();
    private int mCurrentIndex = -1;

    private Callback mCallback;

    public FTouchIndicatorView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mTextSize = dp2px(13, context);
        mPaint.setTextSize(mTextSize);
        mPaint.setAntiAlias(true);
    }

    /**
     * 设置回调对象
     */
    public void setCallback(Callback callback) {
        mCallback = callback;
    }

    /**
     * 设置文字数组
     */
    public void setTextArray(String[] textArray) {
        mTextArray = textArray;
        requestLayout();
    }

    /**
     * 设置字体大小
     */
    public void setTextSize(int textSize) {
        if (mTextSize != textSize) {
            mTextSize = textSize;
            mPaint.setTextSize(textSize);
            requestLayout();
        }
    }

    /**
     * 设置正常字体颜色
     */
    public void setTextColorNormal(int textColorNormal) {
        if (mTextColorNormal != textColorNormal) {
            mTextColorNormal = textColorNormal;
            requestLayout();
        }
    }

    /**
     * 设置选中字体颜色
     */
    public void setTextColorSelected(int textColorSelected) {
        if (mTextColorSelected != textColorSelected) {
            mTextColorSelected = textColorSelected;
            requestLayout();
        }
    }

    private int getItemSize() {
        return (int) mPaint.getTextSize() + 20;
    }

    /**
     * 计算位置
     */
    private void calculateIndex(MotionEvent event) {
        final String[] array = mTextArray;
        if (array == null || array.length <= 0) {
            setCurrentIndex(-1);
            return;
        }

        final int itemSize = getItemSize();
        int index = -1;

        final int startBounds = getPaddingTop();
        final int endBounds = getMeasuredHeight() - getPaddingBottom();
        final int intValue = (int) event.getY();
        if (intValue > startBounds && intValue < endBounds
                && itemSize > 0) {
            final int fixValue = intValue - startBounds;
            index = fixValue / itemSize;
        }

        if (index >= array.length) {
            index = array.length - 1;
        }
        setCurrentIndex(index);
    }

    private void setCurrentIndex(int currentIndex) {
        mCurrentIndex = currentIndex;
    }

    private String getTouchText(MotionEvent event) {
        calculateIndex(event);
        final int index = mCurrentIndex;
        if (index >= 0 && index < mTextArray.length) {
            return mTextArray[index];
        }
        return null;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        final String text = getTouchText(event);
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                invalidate();
                if (mCallback != null) {
                    mCallback.onTouchDown(text);
                }
                break;
            case MotionEvent.ACTION_MOVE:
                invalidate();
                if (mCallback != null) {
                    mCallback.onTouchMove(text);
                }
                break;
            case MotionEvent.ACTION_UP:
                setCurrentIndex(-1);
                invalidate();
                if (mCallback != null) {
                    mCallback.onTouchUp(text);
                }
                break;
            default:
                break;
        }
        return true;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        final int itemCount = mTextArray.length;
        if (itemCount > 0) {
            final int itemSize = getItemSize();
            int width = itemSize + getPaddingLeft() + getPaddingRight();
            int height = itemSize * itemCount + getPaddingTop() + getPaddingBottom();
            setMeasuredDimension(width, height);
        } else {
            final int width = getPaddingLeft() + getPaddingRight();
            final int height = getPaddingTop() + getPaddingBottom();
            setMeasuredDimension(width, height);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        final String[] array = mTextArray;
        if (array == null || array.length <= 0) {
            return;
        }

        final int itemSize = getItemSize();
        if (itemSize <= 0) {
            return;
        }

        canvas.save();
        drawItem(canvas, array, mCurrentIndex, itemSize);
        canvas.restore();
    }

    private void drawItem(Canvas canvas, String[] array, int currentIndex, int itemSize) {
        int top = getPaddingTop();
        for (int i = 0; i < array.length; i++) {
            final String text = array[i];
            if (i == currentIndex) {
                mPaint.setColor(mTextColorSelected);
            } else {
                mPaint.setColor(mTextColorNormal);
            }
            canvas.drawText(text, getPaddingLeft(), top, mPaint);
            top += itemSize;
        }
    }

    private static int dp2px(float dp, Context context) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }

    public interface Callback {
        void onTouchDown(String text);

        void onTouchMove(String text);

        void onTouchUp(String text);
    }
}
