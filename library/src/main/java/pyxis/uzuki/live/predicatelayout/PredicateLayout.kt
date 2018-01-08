package pyxis.uzuki.live.predicatelayout

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.RectShape
import android.os.Build
import android.util.AttributeSet
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.google.android.flexbox.*
import pyxis.uzuki.live.predicatelayout.impl.OnItemClickListener
import pyxis.uzuki.live.predicatelayout.impl.PredicateTextTransformer
import pyxis.uzuki.live.predicatelayout.preset.PredefindTextTransformer
import java.util.*


class PredicateLayout constructor(context: Context, attrs: AttributeSet? = null) : FlexboxLayout(context, attrs), View.OnClickListener {
    private val mItems = ArrayList<String>()
    private var mHorizontalSpacing = 1
    private var mVerticalSpacing = 1
    private var mTextSize = 0
    private var mTextColor = android.R.color.white
    private var mBackgroundDrawable: Drawable? = null
    private val mSelectedList = ArrayList<String>()
    private var mGravity = 0
    private var mClickListener: OnItemClickListener? = null
    private var mTextTransformer: PredicateTextTransformer = PredefindTextTransformer(context)

    init {
        init(attrs)
    }

    /**
     * add mItems into PredicateLayout
     *
     * @param items String...
     */
    fun addItem(vararg items: String) {
        this.mItems.addAll(Arrays.asList(*items))
    }

    /**
     * set mItems into PredicateLayout
     *
     * @param items list of item
     */
    fun setItems(items: List<String>) {
        this.mItems.addAll(items)
    }

    /**
     * remove item in PredicateLayout
     *
     * @param item String
     */
    fun remove(item: String) {
        this.mItems.remove(item)
    }

    /**
     * Clear all layout
     */
    fun clear() {
        this.mItems.clear()
    }

    /**
     * notify when dataset is changed
     */
    fun notifyDataSetChanged() {
        removeAllViewsInLayout()
        for (item in mItems) {
            addView(getItemTextView(item))
        }
    }

    /**
     * callback when click item
     */
    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.mClickListener = listener
    }

    /**
     * get selected list
     */
    fun getSelectedList() = mSelectedList

    /**
     * set [PredicateTextTransformer] object
     *
     * PredicateTextTransformer is a tool to apply specific TextView style in PredicateLayout.
     * see [PredefindTextTransformer] for example.
     */
    fun setTextTransformer(transformer: PredicateTextTransformer) {
        this.mTextTransformer = transformer
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

        setShowDivider(FlexboxLayout.SHOW_DIVIDER_MIDDLE)
        dividerDrawableVertical = getDividerShape(mVerticalSpacing)
        dividerDrawableHorizontal = getDividerShape(mHorizontalSpacing)
        alignContent = AlignContent.FLEX_START
        flexDirection = FlexDirection.ROW
        alignItems = AlignItems.FLEX_START
        flexWrap = FlexWrap.WRAP
    }

    private fun getDividerShape(px: Int): ShapeDrawable {
        return ShapeDrawable(RectShape()).apply {
            intrinsicWidth = px
            intrinsicHeight = px
            paint.color = Color.TRANSPARENT
        }
    }

    private fun getItemTextView(text: String): TextView {
        val params = ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        val gravity = getGravityValue()
        val textView = mTextTransformer.generateNewText(text, mBackgroundDrawable, mTextSize, gravity, getColor())
        textView.text = String.format(" %s ", text)
        textView.tag = text
        textView.setOnClickListener(this)
        textView.layoutParams = params
        return textView
    }

    private fun getColor() = if (Build.VERSION.SDK_INT >= 23) context.resources.getColor(mTextColor, null) else context.resources.getColor(mTextColor)

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

        if (mClickListener != null) {
            mClickListener?.onClick(text)
        }

        if (mSelectedList.contains(text)) {
            mSelectedList.remove(text)
        } else {
            mSelectedList.add(text)
        }
    }
}