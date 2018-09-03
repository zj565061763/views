package com.sd.lib.views;

import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * 用某个字符串替换占位符
 */
public abstract class FPlaceholderTextView extends AppCompatTextView
{
    public FPlaceholderTextView(Context context)
    {
        super(context);
    }

    public FPlaceholderTextView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
    }

    public FPlaceholderTextView(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
    }

    public static final String PLACEHOLDER = "$";

    @Override
    public void setText(CharSequence text, TextView.BufferType type)
    {
        final String replaceString = getReplaceString();
        if (text != null && replaceString != null)
        {
            final String content = text.toString().replace(PLACEHOLDER, replaceString);
            text = content;
        }
        super.setText(text, type);
    }

    protected abstract String getReplaceString();
}
