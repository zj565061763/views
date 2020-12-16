package com.sd.lib.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.TypedValue;

import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.widget.TextViewCompat;

public class FAutoSizeTextView extends AppCompatTextView
{
    public FAutoSizeTextView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        initAttributeSet(context, attrs);
    }

    public FAutoSizeTextView(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
        initAttributeSet(context, attrs);
    }

    private void initAttributeSet(Context context, AttributeSet attrs)
    {
        if (context == null || attrs == null)
            return;

        boolean applyAutoSize = true;
        float autoSizeMinTextSizeInPx = dp2px(1, context);
        float autoSizeMaxTextSizeInPx = dp2px(500, context);
        float autoSizeStepGranularityInPx = dp2px(1, context);

        final TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.AppCompatTextView);
        try
        {
            if (a.hasValue(R.styleable.AppCompatTextView_autoSizeTextType))
            {
                final int type = a.getInt(R.styleable.AppCompatTextView_autoSizeTextType, TextViewCompat.AUTO_SIZE_TEXT_TYPE_NONE);
                if (type == TextViewCompat.AUTO_SIZE_TEXT_TYPE_NONE)
                    applyAutoSize = false;
            }

            if (applyAutoSize)
            {
                if (a.hasValue(R.styleable.AppCompatTextView_autoSizeMinTextSize))
                    autoSizeMinTextSizeInPx = a.getDimension(R.styleable.AppCompatTextView_autoSizeMinTextSize, autoSizeMinTextSizeInPx);

                if (a.hasValue(R.styleable.AppCompatTextView_autoSizeMaxTextSize))
                    autoSizeMaxTextSizeInPx = a.getDimension(R.styleable.AppCompatTextView_autoSizeMaxTextSize, autoSizeMaxTextSizeInPx);

                if (a.hasValue(R.styleable.AppCompatTextView_autoSizeStepGranularity))
                    autoSizeStepGranularityInPx = a.getDimension(R.styleable.AppCompatTextView_autoSizeStepGranularity, autoSizeStepGranularityInPx);
            }
        } finally
        {
            if (a != null)
                a.recycle();
        }

        if (applyAutoSize)
        {
            if (autoSizeMinTextSizeInPx > 0 && autoSizeMaxTextSizeInPx > 0 && autoSizeStepGranularityInPx > 0)
            {
                TextViewCompat.setAutoSizeTextTypeWithDefaults(this, TextViewCompat.AUTO_SIZE_TEXT_TYPE_UNIFORM);
                TextViewCompat.setAutoSizeTextTypeUniformWithConfiguration(this,
                        (int) autoSizeMinTextSizeInPx,
                        (int) autoSizeMaxTextSizeInPx,
                        (int) autoSizeStepGranularityInPx,
                        TypedValue.COMPLEX_UNIT_PX);
            }
        }
    }

    private static int dp2px(int dp, Context context)
    {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }
}
