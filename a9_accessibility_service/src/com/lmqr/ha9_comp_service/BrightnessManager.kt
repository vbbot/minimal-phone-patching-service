package com.lmqr.ha9_comp_service

import android.content.SharedPreferences
import android.widget.SeekBar
import com.lmqr.ha9_comp_service.command_runners.CommandRunner

/**
 * Manages brightness settings and their persistence
 */
class BrightnessManager(
    private val sharedPreferences: SharedPreferences,
    private val commandRunner: CommandRunner
) {
    companion object {
        private const val PREF_COLD_BRIGHTNESS = "cold_brightness_value"
        private const val PREF_WARM_BRIGHTNESS = "warm_brightness_value"
        private const val PREF_KEYBOARD_BRIGHTNESS = "keyboard_brightness_value"
    }

    var coldBrightness: Int
        get() = sharedPreferences.getInt(PREF_COLD_BRIGHTNESS, 64)
        set(value) {
            sharedPreferences.edit().putInt(PREF_COLD_BRIGHTNESS, value).apply()
        }

    var warmBrightness: Int
        get() = sharedPreferences.getInt(PREF_WARM_BRIGHTNESS, 64)
        set(value) {
            sharedPreferences.edit().putInt(PREF_WARM_BRIGHTNESS, value).apply()
        }
    var keyboardBrightness: Int
        get() = sharedPreferences.getInt(PREF_KEYBOARD_BRIGHTNESS, 64)
        set(value) {
            sharedPreferences.edit().putInt(PREF_KEYBOARD_BRIGHTNESS, value).apply()
        }
    /**
     * Apply current brightness settings to the device
     */
    fun applyBrightness() {
        commandRunner.runCommands(arrayOf("br_co${coldBrightness}", "br_wm${warmBrightness}", "br_kb${keyboardBrightness}"))
    }

    /**
     * Update both brightness values and apply them
     */
    fun setBrightness(coldValue: Int, warmValue: Int, keyboardValue: Int) {
        coldBrightness = coldValue
        warmBrightness = warmValue
        keyboardBrightness = keyboardValue
        applyBrightness()
    }

    /**
     * Turn off both backlights
     */
    fun turnOffBrightness() {
        commandRunner.runCommands(arrayOf("br_co0", "br_wm0", "br_kb0"))
    }

    /**
     * Configure seekbars with current brightness values and listeners
     */
    fun setupSeekBars(lightSeekbar: SeekBar, lightWarmSeekbar: SeekBar, lightKeyboardSeekbar: SeekBar) {
        lightSeekbar.min = 0
        lightSeekbar.max = 80
        lightSeekbar.progress = coldBrightness
        lightSeekbar.setOnSeekBarChangeListener(
            object : SeekBar.OnSeekBarChangeListener {
                override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                    if (fromUser) {
                        commandRunner.runCommands(arrayOf("br_co$progress"))
                    }
                }
                
                override fun onStartTrackingTouch(seekBar: SeekBar?) {}
                
                override fun onStopTrackingTouch(seekBar: SeekBar?) {
                    coldBrightness = seekBar?.progress ?: 0
                }
            }
        )

        lightWarmSeekbar.min = 0
        lightWarmSeekbar.max = 254
        lightWarmSeekbar.progress = warmBrightness
        lightWarmSeekbar.setOnSeekBarChangeListener(
            object : SeekBar.OnSeekBarChangeListener {
                override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                    if (fromUser) {
                        commandRunner.runCommands(arrayOf("br_wm$progress"))
                    }
                }
                
                override fun onStartTrackingTouch(seekBar: SeekBar?) {}
                
                override fun onStopTrackingTouch(seekBar: SeekBar?) {
                    warmBrightness = seekBar?.progress ?: 0
                }
            }
        )
        lightKeyboardSeekbar.min = 0
        lightKeyboardSeekbar.max = 254
        lightKeyboardSeekbar.progress = warmBrightness
        lightKeyboardSeekbar.setOnSeekBarChangeListener(
            object : SeekBar.OnSeekBarChangeListener {
                override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                    if (fromUser) {
                        commandRunner.runCommands(arrayOf("br_kb$progress"))
                    }
                }
                
                override fun onStartTrackingTouch(seekBar: SeekBar?) {}
                
                override fun onStopTrackingTouch(seekBar: SeekBar?) {
                    keyboardBrightness = seekBar?.progress ?: 0
                }
            }
        )

    }
}