package com.simple.utils;

import android.graphics.Color;

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

	public static boolean isButtonColourDark(String colorHex) {
		int[] rgb = ValueUtils.getRGB(colorHex);
		double luminance = 0.2126 * rgb[0] + 0.7152 * rgb[1] + 0.0722 * rgb[2];
		return luminance < 128;
	}

	/**
	 * Changes percentage of colour's rgb values
	 * @param colour
	 * @param percentage
	 * @return
	 */
	public static int changeColourPercentageTo(int colour, int percentage) {
		return Color.rgb(Color.red(colour) * percentage / 100,
				Color.green(colour) * percentage / 100, Color.blue(colour) * percentage / 100);
	}
}
