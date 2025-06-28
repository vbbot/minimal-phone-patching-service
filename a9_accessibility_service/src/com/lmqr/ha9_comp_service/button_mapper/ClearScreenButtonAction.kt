package com.lmqr.ha9_comp_service.button_mapper

import android.content.Context
import com.lmqr.ha9_comp_service.command_runners.CommandRunner
import com.lmqr.ha9_comp_service.command_runners.Commands
import com.lmqr.ha9_comp_service.RefreshModeManager
import android.view.WindowManager
import android.view.Gravity
import android.graphics.PixelFormat
import android.os.Handler
import android.os.Looper
import android.view.View
import android.graphics.Color
import android.content.SharedPreferences
import android.graphics.ColorMatrixColorFilter
import android.graphics.Paint
import android.graphics.Canvas
import android.graphics.PorterDuff
import android.graphics.PorterDuff.Mode

class ClearScreenButtonAction(private val commandRunner: CommandRunner, private val refreshModeManager: RefreshModeManager) : ButtonAction {
    override fun execute(context: Context) {
        val originalMode = refreshModeManager.currentMode
        commandRunner.runCommands(arrayOf(Commands.SPEED_CLEAR))
        simulateDisplayRefresh(context)
        refreshModeManager.changeMode(originalMode)
        refreshModeManager.applyMode()
    }
    private fun simulateDisplayRefresh(context: Context) {
        val windowManager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager

        val blackOverlayView = View(context).apply {
            setBackgroundColor(Color.BLACK)
        }

        val whiteOverlayView = View(context).apply {
            setBackgroundColor(Color.WHITE)
        }

        val layoutParams = WindowManager.LayoutParams().apply {
            type = WindowManager.LayoutParams.TYPE_ACCESSIBILITY_OVERLAY
            format = PixelFormat.OPAQUE
            flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE or
                    WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE or
                    WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN
            width = WindowManager.LayoutParams.MATCH_PARENT
            height = WindowManager.LayoutParams.MATCH_PARENT
            gravity = Gravity.TOP or Gravity.START
        }

        windowManager.addView(blackOverlayView, layoutParams)

        Handler(Looper.getMainLooper()).postDelayed({
            windowManager.removeView(blackOverlayView)
            windowManager.addView(whiteOverlayView, layoutParams)

            Handler(Looper.getMainLooper()).postDelayed({
                windowManager.removeView(whiteOverlayView)
            }, 100)
        }, 100)
    }
}