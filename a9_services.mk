PRODUCT_PACKAGES += \
    a9_eink_server \
    A9AccessibilityService

PRODUCT_PROPERTY_OVERRIDES += \
    persist.accessibility.enabled_service=com.lmqr.ha9_comp_service/.A9AccessibilityService

BOARD_VENDOR_SEPOLICY_DIRS += vendor/a9_services/sepolicy/public
