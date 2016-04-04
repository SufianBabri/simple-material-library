package com.simple.material.utils;

import android.content.Context;

/**
 * Device related utility methods go here
 */
public class DeviceUtils {

    public static float getDisplayDensity(Context context) {
        return context.getResources().getDisplayMetrics().density;
    }
}
