package com.example.matulegulnaz.presentation.home.drawer

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

interface DrawerState {
    fun getRotateValue(): Float
    fun getScaleValue(): Float
    fun getOffsetValue(): Offset
    fun getCornerRadiusValue(): Dp

    object Expanded: DrawerState{
        override fun getRotateValue(): Float = -3f
        override fun getScaleValue(): Float = 0.7f
        override fun getOffsetValue(): Offset = Offset(220f, -60f)
        override fun getCornerRadiusValue(): Dp = 25.dp
    }

    object Collapsed: DrawerState{
        override fun getRotateValue(): Float = 0f
        override fun getScaleValue(): Float = 1f
        override fun getOffsetValue(): Offset = Offset(0f,0f)
        override fun getCornerRadiusValue(): Dp = 0.dp
    }
}

