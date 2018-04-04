package com.fanwe.lib.views;

import android.content.Context;
import android.graphics.Rect;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhengjun on 2018/4/2.
 */
public class FTagEditText extends EditText implements TextWatcher
{
    public FTagEditText(Context context)
    {
        super(context);
        init();
    }

    public FTagEditText(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        init();
    }

    public FTagEditText(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
        init();
    }

    private List<TagView> mTagViewHolder;

    private void init()
    {
        addTextChangedListener(this);
    }

    private List<TagView> getTagViewHolder()
    {
        if (mTagViewHolder == null)
        {
            mTagViewHolder = new ArrayList<>();
        }
        return mTagViewHolder;
    }

    public void addTagView(TagView tagView)
    {
        if (tagView == null || getTagViewHolder().contains(tagView))
        {
            return;
        }
        getTagViewHolder().add(tagView);
        tagView.updateTagViewState(this);
    }

    public void removeTagView(TagView tagView)
    {
        getTagViewHolder().remove(tagView);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after)
    {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count)
    {
        updateTagViewStateIfNeed();
    }

    @Override
    public void afterTextChanged(Editable s)
    {

    }

    @Override
    protected void onFocusChanged(boolean focused, int direction, Rect previouslyFocusedRect)
    {
        super.onFocusChanged(focused, direction, previouslyFocusedRect);
        updateTagViewStateIfNeed();
    }

    @Override
    public void setEnabled(boolean enabled)
    {
        super.setEnabled(enabled);
        updateTagViewStateIfNeed();
    }

    @Override
    protected void onVisibilityChanged(View changedView, int visibility)
    {
        super.onVisibilityChanged(changedView, visibility);
        if (changedView == this)
        {
            updateTagViewStateIfNeed();
        }
    }

    protected final void updateTagViewStateIfNeed()
    {
        if (getTagViewHolder().isEmpty())
        {
            return;
        }

        for (TagView item : getTagViewHolder())
        {
            item.updateTagViewState(this);
        }
    }

    public interface TagView
    {
        void updateTagViewState(EditText editText);
    }
}
