package com.sd.lib.views

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import kotlin.math.absoluteValue

class FTextLineLayout : FrameLayout {
    private var _textView: TextView? = null

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val textView = _textView ?: return

        val maxLines = textView.maxLines
        if (maxLines <= 0) return

        val totalLine = textView.layout.lineCount
        if (totalLine <= 0) return

        val deltaLine = maxLines - totalLine
        if (deltaLine <= 0) return

        val viewHeight = textView.measuredHeight
        val totalPadding = textView.layout.topPadding.absoluteValue + textView.layout.bottomPadding.absoluteValue
        val lineSpacing = textView.lineSpacingExtra * textView.lineSpacingMultiplier

        val totalLineHeight = viewHeight - totalPadding - ((totalLine - 1) * lineSpacing)
        val lineHeight = totalLineHeight / totalLine

        val deltaHeight = deltaLine * (lineHeight + lineSpacing)
        val targetHeight = viewHeight + deltaHeight

        if (targetHeight > 0) {
            val finalHeight = (targetHeight + 0.5).toInt()
            val heightSpec = MeasureSpec.makeMeasureSpec(finalHeight, MeasureSpec.EXACTLY)
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