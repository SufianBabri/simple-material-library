package com.simple.material.utils;

import android.graphics.Color;
import android.util.Log;

/**
 * Utility class for converting values between different units
 */
public class ValueUtils {

	private static final int RADIX_HEX = 16;

	private ValueUtils(){}

	/**
	 * Remvoes "#" from hex number
	 * @param colorHex
	 * @return
	 */
	private static String getOnlyHexNumber(String colorHex) {
		if(colorHex.startsWith("#")) {
			return colorHex.substring(1);
		}
		else {
			return colorHex;
		}
	}

	private static int[] getRGB(String colorString) {
		int color = Color.parseColor(colorString);
		int r = (color >> 16) & 0xFF;
		int g = (color >> 8) & 0xFF;
		int b = (color >> 0) & 0xFF;
		return new int[]{r, g, b};
	}

	public static boolean isButtonColorDark(String colorHex) {
		// TODO see if ColorUtils.calculateLuminance() is better (ColorUtils from support lib)
		int[] rgb = ValueUtils.getRGB(colorHex);
		double luminance = 0.2126 * rgb[0] + 0.7152 * rgb[1] + 0.0722 * rgb[2];
        Log.d("luminance", "bg luminance = " + luminance);
		return luminance < 140;
	}

	/**
	 * Changes percentage of color's rgb values
	 * @param color
	 * @param percentage
	 * @return
	 */
	public static int changeColorPercentageTo(int color, int percentage) {
		return Color.rgb(Color.red(color) * percentage / 100,
				Color.green(color) * percentage / 100, Color.blue(color) * percentage / 100);
	}

}
