package com.sd.lib.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.FrameLayout;

public class FConsumeTouchLayout extends FrameLayout
{
    public FConsumeTouchLayout(Context context)
    {
        super(context);
    }

    public FConsumeTouchLayout(Context context, AttributeSet attrs)
    {
        super(context, attrs);
    }

    public FConsumeTouchLayout(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
    }

    private boolean mConsumeTouchEvent = true;

    /**
     * 设置是否消费掉触摸事件，默认true
     *
     * @param consume true-事件不会透过view继续往下传递
     */
    public void setConsumeTouchEvent(boolean consume)
    {
        mConsumeTouchEvent = consume;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        if (mConsumeTouchEvent)
        {
            super.onTouchEvent(event);
            return true;
        }
        return super.onTouchEvent(event);
    }
}
