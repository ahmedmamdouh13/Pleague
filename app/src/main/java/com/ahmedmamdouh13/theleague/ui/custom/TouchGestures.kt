package com.ahmedmamdouh13.theleague.ui.custom

interface TouchGestures {
    fun click()
    fun swipe(percent: Float)
    fun scroll(
        percentTop: Float,
        percentBottom: Float,
        percentLeft: Float,
        percentRight: Float
    )
    fun expand(isExpanded: Boolean)
}
