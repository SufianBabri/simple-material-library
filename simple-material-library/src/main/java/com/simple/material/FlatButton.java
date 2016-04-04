package com.simple.material;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.widget.TextView;

import com.simple.material.utils.ColorUtils;
import com.simple.material.utils.DeviceUtils;

/**
 * Material Flat button
 */
public class FlatButton extends TextView {
	private static final int DEFAULT_ENABLED_TEXT_COLOR = 0xFF262626;
	private final int mDensity;

    public FlatButton(Context context) {
        super(context);
		mDensity = (int) DeviceUtils.getDisplayDensity(context);
        init(null);
    }

    public FlatButton(Context context, AttributeSet attrs) {
        super(context, attrs);
		mDensity = (int) DeviceUtils.getDisplayDensity(context);
        init(attrs);
    }

    public FlatButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
		mDensity = (int) DeviceUtils.getDisplayDensity(context);
        init(attrs);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public FlatButton(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
		mDensity = (int) DeviceUtils.getDisplayDensity(context);
        init(attrs);
    }

    private void init(@Nullable AttributeSet attrs) {
		loadAttrs(getContext(), attrs);
		setClickable(true);
		addRippleEffect();
        final int density = (int) DeviceUtils.getDisplayDensity(getContext());
        setMinWidth(88 * density);
        setMinHeight(36 * density);
        setTextSize(14);
		setPadding(8 * mDensity, 4 * mDensity, 8 * mDensity, 4 * mDensity);
		setGravity(Gravity.CENTER);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
            setAllCaps(true);
        }
    }

	private void addRippleEffect() {
		TypedValue outValue = new TypedValue();
		getContext().getTheme().resolveAttribute(R.attr.selectableItemBackground, outValue, true);
		setBackgroundResource(outValue.resourceId);
	}

	private void loadAttrs(@NonNull final Context context, @Nullable AttributeSet attrs) {
		TypedArray a = context.getTheme().obtainStyledAttributes(
				attrs,
				R.styleable.FlatButton,
				0, 0);
		int enabledTextColor, disabledTextColor;
		if (a.hasValue(R.styleable.FlatButton_textColor)) {
			enabledTextColor = Color.parseColor(a.getString(R.styleable.FlatButton_textColor));
			disabledTextColor = ColorUtils.adjustAlpha(enabledTextColor, ColorUtils.DISABLED_COLOR_ALPHA);
		}
		else {
			enabledTextColor = ColorUtils.resolveColor(context, R.attr.colorAccent, DEFAULT_ENABLED_TEXT_COLOR);
			disabledTextColor = ColorUtils.adjustAlpha(enabledTextColor, ColorUtils.DISABLED_COLOR_ALPHA);
		}
		setTextColor(ColorUtils.getTextStateColor(enabledTextColor, disabledTextColor));
	}
}
