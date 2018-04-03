package com.fanwe.lib.views.tagview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.fanwe.lib.views.FTagEditText;

/**
 * Created by zhengjun on 2018/4/3.
 */
public class SimpleClearTagView extends ImageView implements FTagEditText.TagView
{
    public SimpleClearTagView(Context context)
    {
        super(context);
        init();
    }

    public SimpleClearTagView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        init();
    }

    public SimpleClearTagView(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
        init();
    }

    private EditText mEditText;
    private OnClickListener mOnClickListener;

    private void init()
    {
        super.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (mEditText != null)
                {
                    mEditText.setText("");
                }

                if (mOnClickListener != null)
                {
                    mOnClickListener.onClick(v);
                }
            }
        });
    }

    @Override
    public void setOnClickListener(OnClickListener l)
    {
        mOnClickListener = l;
    }

    @Override
    public void updateTagViewState(EditText editText)
    {
        mEditText = editText;

        if (editText.getVisibility() == View.VISIBLE
                && editText.isFocused()
                && editText.isEnabled()
                && editText.getText().length() > 0)
        {
            setVisibility(View.VISIBLE);
        } else
        {
            setVisibility(View.GONE);
        }
    }
}
