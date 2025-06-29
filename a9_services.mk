PRODUCT_PACKAGES += \
    a9_eink_server \
    A9AccessibilityService

PRODUCT_COPY_FILES += \
    vendor/a9_services/mp_keyboard/aw9523b-key.idc:system/usr/idc/aw9523b-key.idc \
    vendor/a9_services/mp_keyboard/aw9523b-key.kl:system/usr/keylayout/aw9523b-key.kl \
    vendor/a9_services/mp_keyboard/aw9523b-key.kcm:system/usr/keychars/aw9523b-key.kcm

PRODUCT_PROPERTY_OVERRIDES += \
    persist.accessibility.enabled_service=com.lmqr.ha9_comp_service/.A9AccessibilityService

BOARD_VENDOR_SEPOLICY_DIRS += vendor/a9_services/sepolicy/public
