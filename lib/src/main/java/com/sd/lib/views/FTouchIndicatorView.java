package com.sd.lib.views;

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
    public static final String[] AZ_ARRAY = new String[]{
            "A", "B", "C", "D", "E", "F", "G",
            "H", "I", "J", "K", "L", "M", "N",
            "O", "P", "Q", "R", "S", "T", "U",
            "V", "W", "X", "Y", "Z"
    };

    /** 字体大小（sp） */
    private int mTextSize = 13;
    /** 正常字体颜色 */
    private int mTextColorNormal = Color.BLACK;
    /** 正常字体颜色 */
    private int mTextColorSelected = Color.RED;
    /** Item之间的间距 */
    private int mItemMargin = 0;
    /** 文字数组 */
    private String[] mTextArray = AZ_ARRAY;

    /** 当前触摸的位置 */
    private int mCurrentIndex = -1;

    private IndexChangeCallback mCallback;

    public FTouchIndicatorView(@NonNull Context context) {
        this(context, null);
    }

    public FTouchIndicatorView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setOrientation(LinearLayout.VERTICAL);
        setGravity(Gravity.CENTER);
        setItemMargin(dp2px(4, context));
    }

    /**
     * 设置回调对象
     */
    public void setIndexChangeCallback(@Nullable IndexChangeCallback callback) {
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
            for (int i = 0; i < array.length; i++) {
                final TextView textView = createTextView(i);
                textView.setText(array[i]);
                addView(textView);
            }
        }
    }

    private TextView createTextView(int index) {
        final TextView textView = new TextView(getContext());
        textView.setGravity(Gravity.CENTER);
        textView.setPadding(0, getItemMargin(), 0, getItemMargin());
        textView.setTextSize(mTextSize);
        if (index == mCurrentIndex) {
            textView.setTextColor(mTextColorSelected);
        } else {
            textView.setTextColor(mTextColorNormal);
        }
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

    @Nullable
    private TextView getTextViewAt(int index) {
        final View child = getChildAt(index);
        return child instanceof TextView ? (TextView) child : null;
    }

    @Override
    public void setOrientation(int orientation) {
        super.setOrientation(VERTICAL);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return mTextArray.length > 0;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
                setCurrentIndex(calculateIndex(event));
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                setCurrentIndex(-1);
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
            final TextView child = getTextViewAt(i);
            if (child == null) continue;
            if (child.getVisibility() != VISIBLE) continue;

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

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (getChildCount() <= 0) {
            createView();
        }
    }

    private static int dp2px(float dp, Context context) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }

    public interface IndexChangeCallback {
        /**
         * 触摸位置变化回调
         *
         * @param index 触摸的位置
         * @param text  触摸的文本
         */
        void onIndexChanged(int index, @Nullable String text);
    }
}
