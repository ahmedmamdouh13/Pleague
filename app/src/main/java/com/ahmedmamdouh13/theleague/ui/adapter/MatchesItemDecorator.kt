package com.ahmedmamdouh13.theleague.ui.adapter

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class MatchesItemDecorator(space: Int) : RecyclerView.ItemDecoration() {

    val x = space

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        outRect.bottom = x
    }
}