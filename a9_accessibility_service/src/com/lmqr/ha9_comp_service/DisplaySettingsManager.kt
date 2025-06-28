package com.lmqr.ha9_comp_service

import android.content.SharedPreferences
import android.graphics.Color
import android.widget.CompoundButton
import android.widget.TextView
import com.lmqr.ha9_comp_service.command_runners.CommandRunner

/**
 * Manages display settings like auto refresh and anti-shake
 */
class DisplaySettingsManager(
    private val sharedPreferences: SharedPreferences,
    private val commandRunner: CommandRunner
) {
    companion object {
        private const val PREF_AUTO_REFRESH = "auto_refresh_enabled"
        private const val PREF_ANTI_SHAKE = "anti_shake_enabled"
    }

    var isAutoRefreshEnabled: Boolean
        get() = sharedPreferences.getBoolean(PREF_AUTO_REFRESH, false)
        set(value) {
            sharedPreferences.edit().putBoolean(PREF_AUTO_REFRESH, value).apply()
            commandRunner.runCommands(arrayOf(if (value) "au_br1" else "au_br0"))
        }

    var isAntiShakeEnabled: Boolean
        get() = sharedPreferences.getBoolean(PREF_ANTI_SHAKE, false)
        set(value) {
            sharedPreferences.edit().putBoolean(PREF_ANTI_SHAKE, value).apply()
            commandRunner.runCommands(arrayOf(if (value) "an_fl1" else "an_fl0"))
        }

    /**
     * Apply all display settings (for use on boot or service start)
     */
    fun applySettings() {
        commandRunner.runCommands(arrayOf(if (isAutoRefreshEnabled) "au_br1" else "au_br0"))
        
        commandRunner.runCommands(arrayOf(if (isAntiShakeEnabled) "an_fl1" else "an_fl0"))
    }

    /**
     * Setup UI switches with current settings values and attach listeners
     */
    fun setupSwitches(autoRefresh: CompoundButton, antiShake: CompoundButton) {
        autoRefresh.isChecked = isAutoRefreshEnabled
        antiShake.isChecked = isAntiShakeEnabled
        
        if (autoRefresh is TextView) autoRefresh.setTextColor(Color.BLACK)
        if (antiShake is TextView) antiShake.setTextColor(Color.BLACK)
        
        autoRefresh.setOnCheckedChangeListener { _, isChecked ->
            isAutoRefreshEnabled = isChecked
        }
        
        antiShake.setOnCheckedChangeListener { _, isChecked ->
            isAntiShakeEnabled = isChecked
        }
    }
}