package com.sd.lib.views

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView

class FTextLineLayout : FrameLayout {
    private var _textView: TextView? = null

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val textView = _textView ?: return

        val totalLine = textView.layout.lineCount
        if (totalLine <= 0) return

        val maxLines = textView.maxLines
        if (maxLines <= 0) return

        val totalHeight = textView.measuredHeight.toFloat()
        val lineHeight = (totalHeight.div(totalLine) + 0.5F).toInt()

        // 目标高度
        val targetHeight = lineHeight * maxLines
        if (targetHeight > 0) {
            val heightSpec = MeasureSpec.makeMeasureSpec(targetHeight, MeasureSpec.EXACTLY)
            super.onMeasure(widthMeasureSpec, heightSpec)
        }
    }

    override fun onViewAdded(child: View) {
        super.onViewAdded(child)
        if (_textView == null && child is TextView) {
            _textView = child
        }
    }

    override fun onViewRemoved(child: View) {
        super.onViewRemoved(child)
        if (_textView === child) {
            _textView = null
        }
    }
}