package com.sd.lib.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class FTextLineLayout extends FrameLayout {
    public FTextLineLayout(@NonNull Context context) {
        super(context);
    }

    public FTextLineLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public FTextLineLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private TextView mTextView;

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        final TextView textView = mTextView;
        if (textView == null) return;

        final int textViewHeight = textView.getMeasuredHeight();
        if (textViewHeight <= 0) return;

        final int maxLines = textView.getMaxLines();
        if (maxLines <= 0) return;

        final int totalLine = textView.getLayout().getLineCount();
        if (totalLine <= 0) return;

        final int deltaLine = maxLines - totalLine;
        if (deltaLine <= 0) return;

        final int totalPadding = Math.abs(textView.getLayout().getTopPadding()) + Math.abs(textView.getLayout().getBottomPadding());
        final float lineSpacing = textView.getLineSpacingExtra() * textView.getLineSpacingMultiplier();
        final float totalLineSpacing = (totalLine - 1) * lineSpacing;

        final float totalLineHeight = textViewHeight - totalPadding - totalLineSpacing;
        final float lineHeight = totalLineHeight / totalLine;

        final float deltaHeight = deltaLine * (lineHeight + lineSpacing);
        final int targetHeight = (int) (textViewHeight + deltaHeight + 0.5);

        final int heightSpec = MeasureSpec.makeMeasureSpec(targetHeight, MeasureSpec.EXACTLY);
        super.onMeasure(widthMeasureSpec, heightSpec);
    }

    @Override
    public void onViewAdded(View child) {
        super.onViewAdded(child);
        if (mTextView == null && (child instanceof TextView)) {
            mTextView = (TextView) child;
        }
    }

    @Override
    public void onViewRemoved(View child) {
        super.onViewRemoved(child);
        if (mTextView == child) {
            mTextView = null;
        }
    }
}
