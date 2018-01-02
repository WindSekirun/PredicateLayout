package pyxis.uzuki.live.predicatelayout

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import pyxis.uzuki.live.predicatelayout.impl.OnItemClickListener
import pyxis.uzuki.live.predicatelayout.impl.PredicateTextTransformer
import pyxis.uzuki.live.predicatelayout.preset.PredefindTextTransformer
import java.util.*


class PredicateLayout constructor(context: Context, attrs: AttributeSet? = null) : ViewGroup(context, attrs), View.OnClickListener {
    private var mLineHeight: Int = 0
    private val items = ArrayList<String>()
    private var mHorizontalSpacing = 1
    private var mVerticalSpacing = 1
    private var mTextSize = 0
    private var mTextColor = android.R.color.white
    private var mBackgroundDrawable: Drawable? = null
    private val selectedList = ArrayList<String>()
    private var mGravity = 0
    private var clickListener: OnItemClickListener? = null
    private var textTransformer: PredicateTextTransformer = PredefindTextTransformer(context)

    init {
        init(attrs)
    }

    /**
     * add items into PredicateLayout
     *
     * @param items String...
     */
    fun addItem(vararg items: String) {
        this.items.addAll(Arrays.asList(*items))
    }

    /**
     * remove item in PredicateLayout
     *
     * @param item String
     */
    fun remove(item: String) {
        this.items.remove(item)
    }

    /**
     * Clear all layout
     */
    fun clear() {
        this.items.clear()
    }

    /**
     * notify when dataset is changed
     */
    fun notifyDataSetChanged() {
        removeAllViews()
        for (item in items) {
            addView(getItemTextView(item))
        }
    }

    /**
     * callback when click item
     */
    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.clickListener = listener
    }

    /**
     * get selected list
     */
    fun getSelectedList() = selectedList

    /**
     * set [PredicateTextTransformer] object
     *
     * PredicateTextTransformer is a tool to apply specific TextView style in PredicateLayout.
     * see [PredefindTextTransformer] for example.
     */
    fun setTextTransformer(transformer: PredicateTextTransformer) {
        this.textTransformer = transformer
    }

    private fun getDimensionSize(resId: Int) = context.resources.getDimensionPixelSize(resId)

    private fun init(attrs: AttributeSet?) {
        if (attrs != null) {
            val array = context.obtainStyledAttributes(attrs, R.styleable.PredicateLayout)
            mHorizontalSpacing = array.getDimensionPixelSize(R.styleable.PredicateLayout_horizontalSpacing,
                    getDimensionSize(R.dimen.predicate_default_spacing))
            mVerticalSpacing = array.getDimensionPixelSize(R.styleable.PredicateLayout_verticalSpacing,
                    getDimensionSize(R.dimen.predicate_default_spacing))
            mTextSize = array.getDimensionPixelSize(R.styleable.PredicateLayout_textSize,
                    getDimensionSize(R.dimen.predicate_default_size))
            mBackgroundDrawable = array.getDrawable(R.styleable.PredicateLayout_backgroundResources)
            mTextColor = array.getResourceId(R.styleable.PredicateLayout_textColor, android.R.color.white)
            mGravity = array.getInt(R.styleable.PredicateLayout_gravity, 0)
            array.recycle()
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val width = View.MeasureSpec.getSize(widthMeasureSpec) - paddingLeft - paddingRight
        var height = View.MeasureSpec.getSize(heightMeasureSpec) - paddingTop - paddingBottom
        val count = childCount
        var lineHeight = 0

        var xPos = paddingLeft
        var yPos = paddingTop

        for (i in 0 until count) {
            val child = getChildAt(i)
            if (child.visibility != View.GONE) {
                child.measure(View.MeasureSpec.makeMeasureSpec(width, View.MeasureSpec.AT_MOST), View.MeasureSpec.makeMeasureSpec(height, View.MeasureSpec.UNSPECIFIED))

                val childW = child.measuredWidth
                lineHeight = Math.max(lineHeight, child.measuredHeight + mVerticalSpacing)
                if (xPos + childW > width) {
                    xPos = paddingLeft
                    yPos += lineHeight
                }

                xPos += childW + mHorizontalSpacing
            }
        }

        this.mLineHeight = lineHeight

        if (View.MeasureSpec.getMode(heightMeasureSpec) == View.MeasureSpec.UNSPECIFIED) {
            height = yPos + lineHeight
        } else if (View.MeasureSpec.getMode(heightMeasureSpec) == View.MeasureSpec.AT_MOST && yPos + lineHeight < height) {
            height = yPos + lineHeight
        }

        setMeasuredDimension(width, height)
    }

    override fun generateDefaultLayoutParams(): ViewGroup.LayoutParams {
        return ViewGroup.LayoutParams(1, 1)
    }

    override fun checkLayoutParams(p: ViewGroup.LayoutParams?): Boolean {
        return p != null
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        val count = childCount
        val width = r - l
        var xPos = paddingLeft
        var yPos = paddingTop

        for (i in 0 until count) {
            val child = getChildAt(i)
            if (child.visibility != View.GONE) {
                val childW = child.measuredWidth
                val childH = child.measuredHeight

                if (xPos + childW > width) {
                    xPos = paddingLeft
                    yPos += mLineHeight
                }

                child.layout(xPos, yPos, xPos + childW, yPos + childH)
                xPos += childW + mHorizontalSpacing
            }
        }
    }

    private fun getItemTextView(text: String): TextView {
        val params = ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        val textView = textTransformer.generateNewText(text, mBackgroundDrawable, mTextSize, getGravityValue(), mTextColor)
        textView.text = String.format(" %s ", text)
        textView.tag = text
        textView.setOnClickListener(this)
        textView.layoutParams = params
        return textView
    }

    private fun getGravityValue(): Int {
        return when (mGravity) {
            0 -> Gravity.CENTER
            1 -> Gravity.START or Gravity.CENTER_VERTICAL
            2 -> Gravity.END or Gravity.CENTER_VERTICAL
            else -> Gravity.CENTER
        }
    }

    override fun onClick(v: View?) {
        val text = v?.tag.toString()

        if (clickListener != null) {
            clickListener?.onClick(text)
        }

        if (selectedList.contains(text)) {
            selectedList.remove(text)
        } else {
            selectedList.add(text)
        }
    }
}