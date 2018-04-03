package com.fanwe.lib.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

/**
 * 支持清除文字内容的输入框
 */
public class FClearEditText extends FTagEditText
{
    public FClearEditText(Context context)
    {
        super(context);
    }

    public FClearEditText(Context context, AttributeSet attrs)
    {
        super(context, attrs);
    }

    public FClearEditText(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void setTagView(View tagView)
    {
        super.setTagView(tagView);
        if (tagView != null)
        {
            tagView.setOnClickListener(new OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    setText("");
                }
            });
        }
    }

    @Override
    protected boolean showTagView()
    {
        return getText().length() > 0;
    }
}
