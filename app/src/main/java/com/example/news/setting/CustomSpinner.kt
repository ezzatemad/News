package com.example.news.setting

import android.content.Context
import android.graphics.drawable.Drawable
import android.graphics.drawable.LayerDrawable
import android.util.AttributeSet
import android.view.View
import android.widget.AdapterView
import android.widget.ListPopupWindow
import androidx.appcompat.widget.AppCompatSpinner
import com.example.news.R

class CustomSpinner(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : AppCompatSpinner(context, attrs, defStyleAttr), AdapterView.OnItemSelectedListener {

    private val arrowDown: Drawable? = context.getDrawable(R.drawable.ic_arrow_drop_down)
    private val arrowUp: Drawable? = context.getDrawable(R.drawable.ic_arrow_drop_up)
    private var isDropdownOpen = false
    private var lastSelectedPosition = INVALID_POSITION
    private var isFirstItemSelected = true
    private var popupWindow: ListPopupWindow? = null

    companion object {
        private const val INVALID_POSITION = -1
    }

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, androidx.appcompat.R.attr.spinnerStyle)

    init {
        setArrowDrawable()
        onItemSelectedListener = this
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        resetState()
    }

    override fun performClick(): Boolean {
        if (!isDropdownOpen) {
            // Dropdown is closed, open it
            super.performClick()
            isDropdownOpen = true
        } else {
            // Dropdown is open, close it
            dismissDropdown()
        }
        return true
    }

    private fun dismissDropdown() {
        popupWindow?.dismiss()
        isDropdownOpen = false
        setArrowDrawable() // Update arrow immediately after dismissing dropdown
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        lastSelectedPosition = position
        isFirstItemSelected = false // Update flag to indicate an item has been selected
        isDropdownOpen = false // Close the dropdown
        setArrowDrawable()
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        if (isFirstItemSelected) {
            // If no item was selected yet, reset the state
            resetState()
        } else {
            // Otherwise, just close the dropdown without changing the arrow
            isDropdownOpen = false
            setArrowDrawable()
        }
    }

    private fun resetState() {
        isDropdownOpen = false
        isFirstItemSelected = true
        lastSelectedPosition = INVALID_POSITION
        setArrowDrawable()
    }

    private fun setArrowDrawable() {
        val layers = context.getDrawable(R.drawable.spinner_shape) as LayerDrawable
        val drawable = if (isDropdownOpen && adapter != null && adapter.count > 0) arrowUp else arrowDown
        layers.setDrawableByLayerId(R.id.spinner_arrow, drawable)
        this.background = layers
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        popupWindow = ListPopupWindow(context)
        popupWindow?.anchorView = this
        popupWindow?.setOnDismissListener {
            dismissDropdown()
        }
    }
}
