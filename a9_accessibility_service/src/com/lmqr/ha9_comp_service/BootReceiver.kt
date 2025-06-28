package com.lmqr.ha9_comp_service

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.SystemProperties
import android.provider.Settings

class BootReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        // Get the intended service from system property
        val propertyValue = SystemProperties.get(
            "persist.accessibility.enabled_service", "")
            
        // Check if service is already enabled
        val serviceName = context.packageName + "/" + 
            A9AccessibilityService::class.java.canonicalName
        var enabledServices = Settings.Secure.getString(
            context.contentResolver,
            Settings.Secure.ENABLED_ACCESSIBILITY_SERVICES)
            
        if (enabledServices == null) enabledServices = ""
        
        // Enable if needed and matching our property
        if (!enabledServices.contains(serviceName) && 
            (propertyValue == serviceName || propertyValue.isEmpty())) {
            
            if (!enabledServices.isEmpty()) {
                enabledServices += ":"
            }
            enabledServices += serviceName
            
            Settings.Secure.putString(
                context.contentResolver,
                Settings.Secure.ENABLED_ACCESSIBILITY_SERVICES,
                enabledServices)
                
            Settings.Secure.putInt(
                context.contentResolver,
                Settings.Secure.ACCESSIBILITY_ENABLED, 1)
        }
    }
}