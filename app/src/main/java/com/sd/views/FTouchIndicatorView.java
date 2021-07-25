package com.sd.views;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class FTouchIndicatorView extends LinearLayout {
    /** 字体大小（sp） */
    private int mTextSize = 13;
    /** 正常字体颜色 */
    private int mTextColorNormal = Color.BLACK;
    /** 正常字体颜色 */
    private int mTextColorSelected = Color.RED;
    /** Item之间的间距 */
    private int mItemMargin = 0;
    /** 文字数组 */
    private String[] mTextArray = new String[]{
            "A", "B", "C", "D", "E", "F", "G",
            "H", "I", "J", "K", "L", "M", "N",
            "O", "P", "Q", "R", "S", "T", "U",
            "V", "W", "X", "Y", "Z"
    };

    /** 当前触摸的位置 */
    private int mCurrentIndex = -1;

    private Callback mCallback;

    public FTouchIndicatorView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setOrientation(LinearLayout.VERTICAL);
        setGravity(Gravity.CENTER);
        setItemMargin(dp2px(4, context));
        setTextArray(mTextArray);
    }

    /**
     * 设置回调对象
     */
    public void setCallback(@Nullable Callback callback) {
        mCallback = callback;
    }

    /**
     * 当前触摸的位置
     */
    public int getCurrentIndex() {
        return mCurrentIndex;
    }

    /**
     * 当前触摸的文本
     */
    @Nullable
    public String getCurrentText() {
        final int index = mCurrentIndex;
        if (index >= 0 && index < mTextArray.length) {
            return mTextArray[index];
        }
        return null;
    }

    /**
     * 设置文字数组
     */
    public void setTextArray(@Nullable String[] textArray) {
        mTextArray = textArray;
        createView();
    }

    /**
     * 设置字体大小（sp）
     */
    public void setTextSize(int textSize) {
        if (mTextSize != textSize) {
            mTextSize = textSize;
            updateTextSize();
        }
    }

    /**
     * 设置正常字体颜色
     */
    public void setTextColorNormal(int textColorNormal) {
        if (mTextColorNormal != textColorNormal) {
            mTextColorNormal = textColorNormal;
            updateTextColorNormal();
        }
    }

    /**
     * 设置选中字体颜色
     */
    public void setTextColorSelected(int textColorSelected) {
        if (mTextColorSelected != textColorSelected) {
            mTextColorSelected = textColorSelected;
            updateTextColorSelected();
        }
    }

    /**
     * 设置Item之间的间距
     */
    public void setItemMargin(int margin) {
        if (mItemMargin != margin) {
            mItemMargin = margin;
            updateItemMargin();
        }
    }

    private int getItemMargin() {
        return mItemMargin / 2;
    }

    private void createView() {
        removeAllViews();

        final String[] array = mTextArray;
        if (array != null) {
            for (String item : array) {
                final TextView textView = createTextView();
                textView.setText(item);
                addView(textView);
            }
        }
    }

    private TextView createTextView() {
        final TextView textView = new TextView(getContext());
        textView.setGravity(Gravity.CENTER);
        textView.setPadding(0, getItemMargin(), 0, getItemMargin());
        textView.setTextSize(mTextSize);
        textView.setTextColor(mTextColorNormal);
        return textView;
    }

    private void updateTextSize() {
        final int count = getChildCount();
        for (int i = 0; i < count; i++) {
            final TextView textView = getTextViewAt(i);
            if (textView != null) {
                textView.setTextSize(mTextSize);
            }
        }
    }

    private void updateTextColorNormal() {
        final int count = getChildCount();
        for (int i = 0; i < count; i++) {
            if (i != mCurrentIndex) {
                final TextView textView = getTextViewAt(i);
                if (textView != null) {
                    textView.setTextColor(mTextColorNormal);
                }
            }
        }
    }

    private void updateTextColorSelected() {
        final TextView textView = getTextViewAt(mCurrentIndex);
        if (textView != null) {
            textView.setTextColor(mTextColorSelected);
        }
    }

    private void updateItemMargin() {
        final int count = getChildCount();
        final int margin = getItemMargin();
        for (int i = 0; i < count; i++) {
            final TextView textView = getTextViewAt(i);
            if (textView != null) {
                textView.setPadding(0, margin, 0, margin);
            }
        }
    }

    private TextView getTextViewAt(int index) {
        final View child = getChildAt(index);
        return (TextView) child;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return mTextArray.length > 0;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                calculateIndex(event);
                if (mCallback != null) {
                    mCallback.onTouchDown();
                }
                break;
            case MotionEvent.ACTION_MOVE:
                calculateIndex(event);
                if (mCallback != null) {
                    mCallback.onTouchMove();
                }
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                setCurrentIndex(-1);
                if (mCallback != null) {
                    mCallback.onTouchUp();
                }
                break;
            default:
                break;
        }
        return true;
    }

    /**
     * 计算位置
     */
    private int calculateIndex(MotionEvent event) {
        final int count = getChildCount();
        if (count <= 0) {
            return -1;
        }

        final int intValue = (int) event.getY();
        int index = -1;
        for (int i = 0; i < count; i++) {
            final View child = getChildAt(i);
            if (intValue > child.getTop() && intValue <= child.getBottom()) {
                index = i;
                break;
            }
        }
        return index;
    }

    private void setCurrentIndex(int currentIndex) {
        final int oldIndex = mCurrentIndex;
        if (oldIndex != currentIndex) {
            final TextView oldTextView = getTextViewAt(oldIndex);
            if (oldTextView != null) {
                oldTextView.setTextColor(mTextColorNormal);
                oldTextView.setSelected(false);
            }

            mCurrentIndex = currentIndex;

            final TextView textView = getTextViewAt(currentIndex);
            if (textView != null) {
                textView.setTextColor(mTextColorSelected);
                textView.setSelected(true);
            }

            if (mCallback != null) {
                final String text = getCurrentText();
                mCallback.onIndexChanged(currentIndex, text);
            }
        }
    }

    private static int dp2px(float dp, Context context) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }

    public static abstract class Callback {

        public abstract void onIndexChanged(int index, String text);

        public void onTouchDown() {
        }

        public void onTouchMove() {
        }

        public void onTouchUp() {
        }
    }
}
