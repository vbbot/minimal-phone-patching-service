package com.lmqr.ha9_comp_service

import android.view.View
import android.view.LayoutInflater
import android.widget.Button
import android.widget.Switch
import android.widget.LinearLayout
import android.widget.SeekBar
import android.widget.TextView
import android.content.Context
import android.widget.CompoundButton
import androidx.core.content.ContextCompat

/**
 * This class replaces the generated data binding class for floating_menu_layout.xml
 * and provides direct access to the views in the layout.
 */
class FloatingMenuViewAccessor(val root: View) {
    val button1: Button = root.findViewById(R.id.button1)
    val button2: Button = root.findViewById(R.id.button2)
    val button3: Button = root.findViewById(R.id.button3)
    val button4: Button = root.findViewById(R.id.button4)
    
    val autoRefresh: Switch = root.findViewById(R.id.auto_refresh)
    val antiShake: Switch = root.findViewById(R.id.anti_shake)
    
    val settingsIcon: View = root.findViewById(R.id.settings_icon)
    val lightSeekbar: SeekBar = root.findViewById(R.id.light_seekbar)
    val lightWarmSeekbar: SeekBar = root.findViewById(R.id.light_warm_seekbar)
    
    companion object {
        fun inflate(inflater: LayoutInflater): FloatingMenuViewAccessor {
            val view = inflater.inflate(R.layout.floating_menu_layout, null)
            return FloatingMenuViewAccessor(view)
        }
    }
}

fun FloatingMenuViewAccessor?.close() = this?.run {
    root.visibility = View.GONE
}

fun FloatingMenuViewAccessor?.updateButtons(mode: RefreshMode) = this?.run {
    listOf(button1, button2, button3, button4).forEach { 
        it.deselect()
    }
    
    when (mode) {
        RefreshMode.CLEAR -> button1.select()
        RefreshMode.BALANCED -> button2.select()
        RefreshMode.SMOOTH -> button3.select()
        RefreshMode.SPEED -> button4.select()
    }
}
