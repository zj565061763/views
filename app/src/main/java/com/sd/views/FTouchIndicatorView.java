package com.sd.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class FTouchIndicatorView extends View {
    public static final int HORIZONTAL = LinearLayout.HORIZONTAL;
    public static final int VERTICAL = LinearLayout.VERTICAL;

    /** 排列方向 */
    private int mOrientation = VERTICAL;
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
    private int mItemSize = 0;

    private Callback mCallback;

    public FTouchIndicatorView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mPaint.setTextSize(dp2px(13, context));
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
        invalidate();
    }

    /**
     * 设置方向
     */
    public void setOrientation(int orientation) {
        if (mOrientation != orientation) {
            mOrientation = orientation;
            invalidate();
        }
    }

    /**
     * 设置字体大小
     */
    public void setTextSize(int textSize) {
        if (mTextSize != textSize) {
            mTextSize = textSize;
            mPaint.setTextSize(textSize);
            invalidate();
        }
    }

    /**
     * 设置正常字体颜色
     */
    public void setTextColorNormal(int textColorNormal) {
        if (mTextColorNormal != textColorNormal) {
            mTextColorNormal = textColorNormal;
            invalidate();
        }
    }

    /**
     * 设置选中字体颜色
     */
    public void setTextColorSelected(int textColorSelected) {
        if (mTextColorSelected != textColorSelected) {
            mTextColorSelected = textColorSelected;
            invalidate();
        }
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

        int index = -1;
        int itemSize = 0;

        if (mOrientation == VERTICAL) {
            final int startBounds = getPaddingTop();
            final int endBounds = getMeasuredHeight() - getPaddingBottom();
            final int totalSize = endBounds - startBounds;
            itemSize = totalSize / array.length;

            if (event != null) {
                final int intValue = (int) event.getY();
                if (intValue > startBounds && intValue < endBounds
                        && itemSize > 0) {
                    final int fixValue = intValue - startBounds;
                    index = fixValue / itemSize;
                }
            }
        } else {
            final int startBounds = getPaddingLeft();
            final int endBounds = getMeasuredWidth() - getPaddingRight();
            final int totalSize = endBounds - startBounds;
            itemSize = totalSize / array.length;

            if (event != null) {
                final int intValue = (int) event.getX();
                if (intValue > startBounds && intValue < endBounds
                        && itemSize > 0) {
                    final int fixValue = intValue - startBounds;
                    index = fixValue / itemSize;
                }
            }
        }

        if (index >= array.length) {
            index = array.length - 1;
        }

        mItemSize = itemSize;
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
        if (text == null) {
            return super.onTouchEvent(event);
        }

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (mCallback != null) {
                    mCallback.onTouchDown(text);
                    return true;
                }
                break;
            case MotionEvent.ACTION_MOVE:
                if (mCallback != null) {
                    mCallback.onTouchMove(text);
                    return true;
                }
                break;
            case MotionEvent.ACTION_UP:
                if (mCallback != null) {
                    mCallback.onTouchUp(text);
                    return true;
                }
                break;
            default:
                break;
        }
        return super.onTouchEvent(event);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        final int itemCount = mTextArray.length;
        if (itemCount > 0) {
            int width = 0;
            int height = 0;
            final int itemSize = (int) mPaint.getTextSize();
            if (mOrientation == VERTICAL) {
                width = itemSize + getPaddingLeft() + getPaddingRight();
                height = itemSize * itemCount + getPaddingTop() + getPaddingBottom();
            } else {
                width = itemSize * itemCount + getPaddingLeft() + getPaddingRight();
                height = itemSize + getPaddingTop() + getPaddingBottom();
            }
            setMeasuredDimension(getDefaultSizeInternal(width, widthMeasureSpec),
                    getDefaultSizeInternal(height, heightMeasureSpec));
        } else {
            final int width = getPaddingLeft() + getPaddingRight();
            final int height = getPaddingTop() + getPaddingBottom();
            setMeasuredDimension(getDefaultSizeInternal(width, widthMeasureSpec),
                    getDefaultSizeInternal(height, heightMeasureSpec));
        }
        calculateIndex(null);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        final String[] array = mTextArray;
        if (array == null || array.length <= 0) {
            return;
        }

        final int itemSize = mItemSize;
        if (itemSize <= 0) {
            return;
        }

        canvas.save();
        if (mOrientation == VERTICAL) {
            drawVertical(canvas, array, mCurrentIndex, itemSize);
        } else {
            drawHorizontal(canvas, array, mCurrentIndex, itemSize);
        }
        canvas.restore();
    }

    private void drawVertical(Canvas canvas, String[] array, int currentIndex, int itemSize) {
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

    private void drawHorizontal(Canvas canvas, String[] array, int currentIndex, int itemSize) {
        int left = getPaddingLeft();
        for (int i = 0; i < array.length; i++) {
            final String text = array[i];
            if (i == currentIndex) {
                mPaint.setColor(mTextColorSelected);
            } else {
                mPaint.setColor(mTextColorNormal);
            }
            left += itemSize * i;
            canvas.drawText(text, left, getPaddingTop(), mPaint);
        }
    }

    private static int getDefaultSizeInternal(int size, int measureSpec) {
        int result = size;
        int specSize = MeasureSpec.getSize(measureSpec);
        int specMode = MeasureSpec.getMode(measureSpec);
        switch (specMode) {
            case MeasureSpec.UNSPECIFIED:
                result = size;
                break;
            case MeasureSpec.AT_MOST:
                result = Math.min(size, specSize);
                break;
            case MeasureSpec.EXACTLY:
                result = specSize;
                break;
        }
        return result;
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
