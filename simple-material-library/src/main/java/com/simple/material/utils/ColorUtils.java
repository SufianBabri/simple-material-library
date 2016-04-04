package com.simple.material.utils;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.annotation.AttrRes;

/**
 * Methods related to colors.
 * <br/>
 * Created by Sufian Babri on 27/03/2016.
 */
public class ColorUtils {
	public static final float DISABLED_COLOR_ALPHA = 0.4f;

	/**
	 * creates text's colors state list. This has two colors, one for enabled and the other for disabled button state.
	 *
	 * @param enabledTextColor
	 * @param disabledTextColor
	 * @return
	 */
	public static ColorStateList getTextStateColor(int enabledTextColor, int disabledTextColor) {
		int[][] states = new int[][]{
				new int[]{android.R.attr.state_enabled},
				new int[]{},
		};

		int[] colors = new int[]{
				enabledTextColor,
				disabledTextColor
		};
		return new ColorStateList(states, colors);
	}

	/**
	 * Taken from <a href="https://github.com/afollestad/material-dialogs/blob/master/core/src/main/java/com/afollestad/materialdialogs/util/DialogUtils.java">Material Dialogs source</a>
	 * @param context
	 * @param attr
	 * @return
	 */
	public static int resolveColor(Context context, @AttrRes int attr) {
		return resolveColor(context, attr, 0);
	}

	/**
	 * Taken from <a href="https://github.com/afollestad/material-dialogs/blob/master/core/src/main/java/com/afollestad/materialdialogs/util/DialogUtils.java">Material Dialogs source</a>
	 * @param context
	 * @param attr
	 * @param fallback
	 * @return
	 */
	public static int resolveColor(Context context, @AttrRes int attr, int fallback) {
		TypedArray a = context.getTheme().obtainStyledAttributes(new int[]{attr});
		try {
			return a.getColor(0, fallback);
		} finally {
			a.recycle();
		}
	}

	/**
	 * Taken from <a href="https://github.com/afollestad/material-dialogs/blob/master/core/src/main/java/com/afollestad/materialdialogs/util/DialogUtils.java">Material Dialogs source</a>
	 * @param color
	 * @param factor
	 * @return
	 */
	public static int adjustAlpha(int color, float factor) {
		int alpha = Math.round(Color.alpha(color) * factor);
		int red = Color.red(color);
		int green = Color.green(color);
		int blue = Color.blue(color);
		return Color.argb(alpha, red, green, blue);
	}

	/**
	 * Taken from <a href="https://github.com/afollestad/material-dialogs/blob/master/core/src/main/java/com/afollestad/materialdialogs/util/DialogUtils.java">Material Dialogs source</a>
	 * @param color
	 * @return
	 */
	public static boolean isColorDark(int color) {
		double darkness = 1 - (0.299 * Color.red(color) + 0.587 * Color.green(color) + 0.114 * Color.blue(color)) / 255;
		return darkness >= 0.5;
	}

	/**
	 * Check this to decide if white should be used on this color
	 * @param backgroundColor
	 * @return {@code true} if the color is dark
	 */
	public static boolean isBackgroundColorDark(int backgroundColor) {
		double darkness = 1 - (0.299 * Color.red(backgroundColor) + 0.587 * Color.green(backgroundColor) + 0.114 * Color.blue(backgroundColor)) / 255;
		return darkness >= 0.4;
	}
}
