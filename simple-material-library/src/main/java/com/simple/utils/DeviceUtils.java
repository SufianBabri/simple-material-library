package com.simple.utils;

import android.content.Context;

/**
 * Device related utility methods go here
 */
public class DeviceUtils {

    public static float getDisplayDensity(Context context) {
        return context.getResources().getDisplayMetrics().density;
    }
}
