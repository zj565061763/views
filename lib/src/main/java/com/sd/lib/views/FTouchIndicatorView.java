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
import androidx.appcompat.widget.AppCompatTextView;

import java.util.ArrayList;
import java.util.List;

public class FTouchIndicatorView extends LinearLayout {
    /** 当前触摸的位置 */
    private int mCurrentIndex = -1;
    /** Item之间的间距 */
    private int mItemMargin = 0;

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
     * 当前触摸的View
     */
    @Nullable
    public View getCurrentView() {
        final int index = mCurrentIndex;
        return getChildAt(index);
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

    /**
     * 设置TextView构建对象
     */
    public void setTextBuilder(@NonNull TextBuilder builder) {
        removeAllViews();
        final List<View> list = builder.build(getContext());

        final int margin = getItemMargin();
        for (View view : list) {
            view.setPadding(0, margin, 0, margin);
            addView(view);
        }
    }

    /**
     * 更新Item间距
     */
    private void updateItemMargin() {
        final int count = getChildCount();
        final int margin = getItemMargin();
        for (int i = 0; i < count; i++) {
            final View view = getChildAt(i);
            view.setPadding(0, margin, 0, margin);
        }
    }

    @Override
    public void setOrientation(int orientation) {
        super.setOrientation(VERTICAL);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return getChildCount() > 0;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
                final int index = calculateIndex(event);
                setCurrentIndex(index);
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
            final View child = getChildAt(i);
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
            final View oldView = getChildAt(oldIndex);
            if (oldView != null) {
                oldView.setSelected(false);
            }

            mCurrentIndex = currentIndex;

            final View currentView = getChildAt(currentIndex);
            if (currentView != null) {
                currentView.setSelected(true);
            }

            if (mCallback != null) {
                mCallback.onIndexChanged(currentIndex, getCurrentView());
            }
        }
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (getChildCount() <= 0) {
            setTextBuilder(new TextBuilder());
        }
    }

    private static int dp2px(float dp, Context context) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }

    public static class TextBuilder {
        /** 字体大小（sp） */
        int mTextSize = 13;
        /** 正常字体颜色 */
        int mTextColorNormal = Color.BLACK;
        /** 正常字体颜色 */
        int mTextColorSelected = Color.RED;
        /** 文字数组 */
        String[] mTextArray = new String[]{
                "A", "B", "C", "D", "E", "F", "G",
                "H", "I", "J", "K", "L", "M", "N",
                "O", "P", "Q", "R", "S", "T", "U",
                "V", "W", "X", "Y", "Z"
        };

        /**
         * 设置字体大小（sp）
         */
        public TextBuilder setTextSize(int textSize) {
            mTextSize = textSize;
            return this;
        }

        /**
         * 设置正常字体颜色
         */
        public TextBuilder setTextColorNormal(int textColorNormal) {
            mTextColorNormal = textColorNormal;
            return this;
        }

        /**
         * 设置选中字体颜色
         */
        public TextBuilder setTextColorSelected(int textColorSelected) {
            mTextColorSelected = textColorSelected;
            return this;
        }

        /**
         * 设置文字数组
         */
        public TextBuilder setTextArray(@Nullable String[] textArray) {
            mTextArray = textArray;
            return this;
        }

        @NonNull
        public List<View> build(Context context) {
            final String[] array = mTextArray;
            final int size = array != null ? array.length : 0;

            final List<View> list = new ArrayList<>(size);
            if (array != null) {
                for (int i = 0; i < size; i++) {
                    final TextView textView = createTextView(context);
                    textView.setText(array[i]);
                    list.add(textView);
                }
            }
            return list;
        }

        private TextView createTextView(Context context) {
            final TextView textView = new AppCompatTextView(context) {
                @Override
                public void setSelected(boolean selected) {
                    super.setSelected(selected);
                    if (selected) {
                        this.setTextColor(mTextColorSelected);
                    } else {
                        this.setTextColor(mTextColorNormal);
                    }
                }
            };
            textView.setGravity(Gravity.CENTER);
            textView.setTextSize(mTextSize);
            textView.setTextColor(mTextColorNormal);
            return textView;
        }
    }

    public interface IndexChangeCallback {
        /**
         * 触摸位置变化回调
         *
         * @param index 触摸的位置
         * @param view  触摸的View
         */
        void onIndexChanged(int index, @Nullable View view);
    }
}
