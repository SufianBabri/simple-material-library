package com.simple.material;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.RippleDrawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Build;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.simple.material.utils.ColorUtils;
import com.simple.material.utils.DeviceUtils;
import com.simple.material.utils.ValueUtils;

/**
 * Material Raised button
 */
public class RaisedButton extends CardView {
	private static final int PERCENTAGE_SHADE_600 = 82;
	private static final int PERCENTAGE_SHADE_700 = 58;
	private static final int DARK_THEME_DISABLED_BUTTON_COLOR = 0XFF404040;
	private static final int DARK_THEME_DISABLED_TEXT_COLOR = 0XFF7E7E7E;
	private static final int LIGHT_THEME_DISABLED_BUTTON_COLOR = 0XFFDFDFDF;
	private static final int LIGHT_THEME_DISABLED_TEXT_COLOR = 0XFF9F9F9F;
	private static final int DEFAULT_BUTTON_COLOR = 0xFF2196F3;
	private static final int NORMAL_SHADOW = 2;
	private static final int PRESSED_SHADOW = 4;
	private final int mDensity;
	private TextView mTextView;
	private	@Nullable OnClickListener mClickListener;

	public RaisedButton(Context context) {
		super(context);
		mDensity = (int) DeviceUtils.getDisplayDensity(context);
		init(context, null);
	}

	public RaisedButton(Context context, AttributeSet attrs) {
		super(context, attrs);
		mDensity = (int) DeviceUtils.getDisplayDensity(context);
		init(context, attrs);
	}

	public RaisedButton(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		mDensity = (int) DeviceUtils.getDisplayDensity(context);
		init(context, attrs);
	}

	@Override
	protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
		super.onLayout(changed, left, top, right, bottom);
		addMargins();
	}

	/**
	 * Creates and adds button to this {@link CardView}
	 *
	 * @param context
	 * @param attrs
	 */
	private void init(@NonNull Context context, @Nullable AttributeSet attrs) {
		setMaxCardElevation(PRESSED_SHADOW * mDensity);
		setCardElevation(NORMAL_SHADOW * mDensity);
		addTextView();
		addClickEffects();

		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
			mTextView.setAllCaps(true);
		}
		loadAttrs(context, attrs);
	}

	/**
	 * Adds margins to the {@link RaisedButton} so that it doesn't overlap with
	 * other widgets on devices running Lollipop and above
	 */
	private void addMargins() {
		if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
			int pressedShadowDp = (int) getMaxCardElevation();
			if (getLayoutParams() instanceof LinearLayout.LayoutParams) {
				LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) getLayoutParams();
				layoutParams.setMargins(layoutParams.leftMargin + pressedShadowDp/2,
						layoutParams.topMargin + pressedShadowDp,
						layoutParams.rightMargin + pressedShadowDp/2,
						layoutParams.bottomMargin + pressedShadowDp);
			}
			else if (getLayoutParams() instanceof RelativeLayout.LayoutParams) {
				RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) getLayoutParams();
				layoutParams.setMargins(layoutParams.leftMargin + pressedShadowDp/2,
						layoutParams.topMargin + pressedShadowDp,
						layoutParams.rightMargin + pressedShadowDp/2,
						layoutParams.bottomMargin + pressedShadowDp);
			}
		}
		else {
			int pressedShadowDp = (int) getMaxCardElevation() / 2;
			if (getLayoutParams() instanceof LinearLayout.LayoutParams) {
				LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) getLayoutParams();
				layoutParams.setMargins(layoutParams.leftMargin,
						layoutParams.topMargin - pressedShadowDp,
						layoutParams.rightMargin,
						layoutParams.bottomMargin - pressedShadowDp);
			}
			else if (getLayoutParams() instanceof RelativeLayout.LayoutParams) {
				RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) getLayoutParams();
				layoutParams.setMargins(layoutParams.leftMargin,
						layoutParams.topMargin - pressedShadowDp,
						layoutParams.rightMargin,
						layoutParams.bottomMargin - pressedShadowDp);
			}
		}
	}

	/**
	 * Creates a TextView and sets properties and adds it, to complete the {@link RaisedButton}
	 */
	private void addTextView() {
		mTextView = new TextView(getContext());
		mTextView.setTextSize(14);
		mTextView.setClickable(true);
		mTextView.setGravity(Gravity.CENTER);
		mTextView.setCompoundDrawablePadding(10 * mDensity);
		addView(mTextView);
	}

	/**
	 * Reads attrs and set the button's background and text colors.
	 *
	 * @param context
	 * @param attrs
	 */
	private void loadAttrs(@NonNull final Context context, @Nullable AttributeSet attrs) {
		TypedArray a = context.getTheme().obtainStyledAttributes(
				attrs,
				R.styleable.RaisedButton,
				0, 0);

		try {
			setPreventCornerOverlap(false);
			if (a.getBoolean(R.styleable.RaisedButton_smallButton, false)) {
				int minWidth = a.getInt(R.styleable.RaisedButton_minWidth, 70);
				int minHeight = a.getInt(R.styleable.RaisedButton_minHeight, 36);
				mTextView.setMinWidth(minWidth * mDensity);
				mTextView.setMinHeight(minHeight * mDensity);
				mTextView.setPadding(8 * mDensity, 4 * mDensity, 8 * mDensity, 4 * mDensity);
			}
			else {
				mTextView.setMinWidth(88 * mDensity);
				mTextView.setMinHeight(36 * mDensity);
				mTextView.setPadding(16 * mDensity, 8 * mDensity, 16 * mDensity, 8 * mDensity);
			}
			if (a.hasValue(R.styleable.RaisedButton_iconStart)) {
				setStartIcon(a.getResourceId(R.styleable.RaisedButton_iconStart, 0));
			}
			else if (a.hasValue(R.styleable.RaisedButton_iconLeft)) {
				setLeftIcon(a.getResourceId(R.styleable.RaisedButton_iconLeft, 0));
			}
			int buttonColor;
			int enabledTextColor, disabledTextColor, buttonDisabledColor;
			if (a.hasValue(R.styleable.RaisedButton_buttonColor)) {
				buttonColor = a.getColor(R.styleable.RaisedButton_buttonColor, DEFAULT_BUTTON_COLOR);
			}
			else {
				buttonColor = ColorUtils.resolveColor(context, R.attr.colorAccent, DEFAULT_BUTTON_COLOR);
			}

			if (ColorUtils.isBackgroundColorDark(buttonColor)) {
				enabledTextColor = Color.WHITE;
				disabledTextColor = DARK_THEME_DISABLED_TEXT_COLOR;
				buttonDisabledColor = DARK_THEME_DISABLED_BUTTON_COLOR;
			}
			else {
				enabledTextColor = Color.BLACK;
				disabledTextColor = LIGHT_THEME_DISABLED_TEXT_COLOR;
				buttonDisabledColor = LIGHT_THEME_DISABLED_BUTTON_COLOR;
			}
			mTextView.setTextColor(ColorUtils.getTextStateColor(enabledTextColor, disabledTextColor));
			mTextView.setEnabled(a.getBoolean(R.styleable.RaisedButton_enabled, true));
			mTextView.setText(a.getString(R.styleable.RaisedButton_text));

			Drawable drawable = getBackgroundDrawable(buttonColor, buttonDisabledColor);
			if (Build.VERSION.SDK_INT < 16) {
				mTextView.setBackgroundDrawable(drawable);
			}
			else {
				mTextView.setBackground(drawable);
			}
		}
		finally {
			a.recycle();
		}

	}

	private void addClickEffects() {
		final Rect textViewBounds = new Rect();
		mTextView.getDrawingRect(textViewBounds);
		mTextView.setOnTouchListener(new OnTouchListener() {
			private boolean touchValid;

			@Override
			public boolean onTouch(View view, MotionEvent motionEvent) {
				final Rect textViewBounds = new Rect();
				mTextView.getHitRect(textViewBounds);
				switch (motionEvent.getAction()) {
					case MotionEvent.ACTION_DOWN:
						touchValid = true;
						setButtonPressed(true);
						break;
					case MotionEvent.ACTION_MOVE:
						if (!textViewBounds.contains((int) motionEvent.getX(), (int) motionEvent.getY())) {
							touchValid = false;
						}
						else {
							break;
						}
					case MotionEvent.ACTION_UP:
						if (mClickListener != null && touchValid) {
							mClickListener.onClick(RaisedButton.this);
						}
					default:
						setButtonPressed(false);
						break;
				}
				return true;
			}
		});
	}

	/**
	 * If {@param pressed} is true, the button shows up as pressed and {@value #PRESSED_SHADOW}dp shadow.
	 * <br/>
	 * Otherwise it shows as normal state and {@value #NORMAL_SHADOW}dp elevation/shadow.
	 *
	 * @param pressed
	 */
	private void setButtonPressed(boolean pressed) {
		mTextView.setPressed(pressed);
		if (pressed) {
			setCardElevation(PRESSED_SHADOW * mDensity);
		}
		else {
			setCardElevation(NORMAL_SHADOW * mDensity);
		}
	}

	/**
	 * Generates Drawable to be used as button's background
	 *
	 * @param normalColor
	 * @param disabledColor
	 * @return
	 */
	private Drawable getBackgroundDrawable(int normalColor, int disabledColor) {

		int pressedColor = getPressedColor(normalColor);
		int focusedColor = getFocusedColor(normalColor);

		if (Build.VERSION.SDK_INT < 21) {
			StateListDrawable drawable = new StateListDrawable();
			drawable.addState(new int[]{android.R.attr.state_focused}, new ColorDrawable(focusedColor));
			drawable.addState(new int[]{-android.R.attr.state_enabled}, new ColorDrawable(disabledColor));
			drawable.addState(new int[]{android.R.attr.state_pressed}, new ColorDrawable(pressedColor));
			drawable.addState(new int[]{}, new ColorDrawable(normalColor));
			return drawable;
		}
		else {
			int[][] states = new int[][]{
					new int[]{}
			};

			int[] colors = new int[]{
					pressedColor
			};
			ColorStateList colorStateList = new ColorStateList(states, colors);

			StateListDrawable drawable = new StateListDrawable();
			drawable.addState(new int[]{android.R.attr.state_focused}, new ColorDrawable(focusedColor));
			drawable.addState(new int[]{-android.R.attr.state_enabled}, new ColorDrawable(disabledColor));
			drawable.addState(new int[]{android.R.attr.state_enabled}, new ColorDrawable(normalColor));
			drawable.addState(new int[]{}, new ColorDrawable(normalColor));
			//ripple will handle the pressed color
			return new RippleDrawable(colorStateList, drawable, null);
		}
	}

	/**
	 * Calculates the color by adding darkness to the default button's color
	 *
	 * @param normalColor
	 * @return
	 */
	private int getPressedColor(int normalColor) {
		return ValueUtils.changeColorPercentageTo(normalColor, PERCENTAGE_SHADE_600);
	}

	/**
	 * Calculates the color by adding darkness to the default button's color
	 *
	 * @param normalColor
	 * @return
	 */
	private int getFocusedColor(int normalColor) {
		return ValueUtils.changeColorPercentageTo(normalColor, PERCENTAGE_SHADE_700);
	}

	@Override
	public void setOnClickListener(OnClickListener clickListener) {
		mClickListener = clickListener;
	}

	@Override
	public void setEnabled(boolean enabled) {
		mTextView.setEnabled(enabled);
	}

	public void setText(@StringRes int resId) {
		mTextView.setText(resId);
	}

	public void setText(String text) {
		mTextView.setText(text);
	}

	/**
	 * Puts icon at start for API {@value android.os.Build.VERSION_CODES#JELLY_BEAN_MR1}, otherwise
	 * calls {@link #setLeftIcon(int)}
	 *
	 * @param resId
	 */
	public void setStartIcon(@DrawableRes int resId) {
		if (Build.VERSION.SDK_INT < 17) {
			setLeftIcon(resId);
		}
		else {
			mTextView.setCompoundDrawablesRelativeWithIntrinsicBounds(resId, 0, 0, 0);
		}
	}


	/**
	 * Puts at icon at the left
	 *
	 * @param resId
	 */
	public void setLeftIcon(@DrawableRes int resId) {
		mTextView.setCompoundDrawablesWithIntrinsicBounds(resId, 0, 0, 0);
	}
}
