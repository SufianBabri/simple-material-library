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
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.simple.utils.DeviceUtils;
import com.simple.utils.ValueUtils;

/**
 * Material Raised button
 */
public class RaisedButton extends CardView {
    private static final int PERCENTAGE_SHADE_600 = 82;
	private static final int PERCENTAGE_SHADE_700 = 58;
	private static final int DARK_THEME_DISABLED_BUTTON_COLOUR = 0XFF404040;
	private static final int DARK_THEME_DISABLED_TEXT_COLOUR = 0XFF7A7A7A;
	private static final int LIGHT_THEME_DISABLED_BUTTON_COLOUR = 0XFFDFDFDF;
	private static final int LIGHT_THEME_DISABLED_TEXT_COLOUR = 0XFF9F9F9F;
    private static final String DEFAULT_BUTTON_COLOUR = "#FF2196f3";
    private TextView mTextView;
    private final int mDensity;
    private @Nullable OnClickListener mClickListener;

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

    /**
     * Creates and adds button to this {@link CardView}
     * @param context
     * @param attrs
     */
	private void init(@NonNull Context context, @Nullable AttributeSet attrs) {
        setMaxCardElevation(4 * mDensity);
        setCardElevation(2 * mDensity);
		mTextView = new TextView(context);
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            StateListAnimator stateListAnimator = AnimatorInflater.loadStateListAnimator(context,
//                    R.anim.lift_up);
//            mTextView.setStateListAnimator(stateListAnimator);
//        }
        mTextView.setTextSize(14);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
            mTextView.setAllCaps(true);
        }
//        mTextView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
//                ViewGroup.LayoutParams.WRAP_CONTENT));
		addView(mTextView);

        mTextView.setClickable(true);
        mTextView.setGravity(Gravity.CENTER);
        setButtonUiFromAttrs(context, attrs);
	}

    /**
     * Reads attrs and set the button's background and text colours.
     * @param context
     * @param attrs
     */
	private void setButtonUiFromAttrs(final Context context, AttributeSet attrs) {
		TypedArray a = context.getTheme().obtainStyledAttributes(
				attrs,
				R.styleable.RaisedButton,
				0, 0);

		try {
			setPreventCornerOverlap(false);
            if (a.getBoolean(R.styleable.RaisedButton_smallButton, false)) {
                int minWidth = a.getInt(R.styleable.RaisedButton_minWidth, 48);
                int minHeight = a.getInt(R.styleable.RaisedButton_minHeight, 28);
                mTextView.setMinWidth(minWidth * mDensity);
                mTextView.setMinHeight(minHeight * mDensity);
                mTextView.setPadding(8 * mDensity, 4 * mDensity, 8 * mDensity, 4 * mDensity);
            }
            else {
                mTextView.setMinWidth(88 * mDensity);
                mTextView.setMinHeight(36 * mDensity);
                mTextView.setPadding(16 * mDensity, 8 * mDensity, 16 * mDensity, 8 * mDensity);
            }
			String buttonColour;
			int enabledTextColour, disabledTextColour, buttonDisabledColour;
			if ((buttonColour = a.getString(R.styleable.RaisedButton_buttonColor)) == null) {
				buttonColour = DEFAULT_BUTTON_COLOUR;
			}

			if (ValueUtils.isButtonColourDark(buttonColour)) {
				enabledTextColour = Color.WHITE;
				disabledTextColour = DARK_THEME_DISABLED_TEXT_COLOUR;
				buttonDisabledColour = DARK_THEME_DISABLED_BUTTON_COLOUR;
			}
			else {
				enabledTextColour = Color.BLACK;
				disabledTextColour = LIGHT_THEME_DISABLED_TEXT_COLOUR;
				buttonDisabledColour = LIGHT_THEME_DISABLED_BUTTON_COLOUR;
			}
			mTextView.setTextColor(getTextStateColour(enabledTextColour, disabledTextColour));
			mTextView.setEnabled(a.getBoolean(R.styleable.RaisedButton_enabled, true));
			mTextView.setText(a.getString(R.styleable.RaisedButton_text));

			Drawable drawable = getBackgroundDrawable(buttonColour, buttonDisabledColour);
			if (Build.VERSION.SDK_INT < 16) {
				mTextView.setBackgroundDrawable(drawable);
			}
			else {
				mTextView.setBackground(drawable);
			}
            addClickEffects();
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
                switch(motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        touchValid = true;
                        setCardElevation(4 * mDensity);
                        setButtonPressed(true);
                        break;
                    case MotionEvent.ACTION_MOVE:
                        touchValid = false;
                        if(!textViewBounds.contains((int) motionEvent.getX(), (int) motionEvent.getY())) {
                            touchValid = false;
                        }
                        else {
                            break;
                        }
                    case MotionEvent.ACTION_UP:
                        if(mClickListener != null && touchValid) {
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
     * If {@param pressed} is true, the button shows up as pressed and {@code 4dp} shadow.
     * <br/>
     * Otherwise it shows as normal state and {@code 2dp} elevation/shadow.
     * @param pressed
     */
    private void setButtonPressed(boolean pressed) {
        mTextView.setPressed(pressed);
        if(pressed) {
            setCardElevation(4 * mDensity);
        }
        else {
            setCardElevation(2 * mDensity);
        }
    }

    /**
     * Generates Drawable to be used as button's background
     * @param normalColourHex
     * @param disabledColour
     * @return
     */
	private Drawable getBackgroundDrawable(String normalColourHex, int disabledColour) {
		int normalColour = Color.parseColor(normalColourHex);

		int pressedColour = getPressedColour(normalColour);
		int focusedColour = getFocusedColour(normalColour);

		if (Build.VERSION.SDK_INT < 21) {
			StateListDrawable drawable = new StateListDrawable();
			drawable.addState(new int[]{ android.R.attr.state_focused}, new ColorDrawable(focusedColour));
			drawable.addState(new int[]{ android.R.attr.state_pressed}, new ColorDrawable(pressedColour));
			drawable.addState(new int[]{-android.R.attr.state_enabled}, new ColorDrawable(disabledColour));
			drawable.addState(new int[]{}, new ColorDrawable(normalColour));
			return drawable;
		}
		else {
			int[][] states = new int[][]{
					new int[]{}
			};

			int[] colours = new int[]{
					pressedColour
			};
			ColorStateList colourStateList = new ColorStateList(states, colours);

			StateListDrawable drawable = new StateListDrawable();
			drawable.addState(new int[]{ android.R.attr.state_focused}, new ColorDrawable(focusedColour));
			drawable.addState(new int[]{-android.R.attr.state_pressed}, new ColorDrawable(normalColour));
			drawable.addState(new int[]{-android.R.attr.state_enabled}, new ColorDrawable(disabledColour));
			drawable.addState(new int[]{}, new ColorDrawable(normalColour));
			return new RippleDrawable(colourStateList, drawable, null);
		}
	}

    /**
     * Calculates the colour by adding darkness to the default button's colour
     * @param normalColour
     * @return
     */
	private int getPressedColour(int normalColour) {
		return ValueUtils.changeColourPercentageTo(normalColour, PERCENTAGE_SHADE_600);
	}

    /**
     * Calculates the colour by adding darkness to the default button's colour
     * @param normalColour
     * @return
     */
	private int getFocusedColour(int normalColour) {
		return ValueUtils.changeColourPercentageTo(normalColour, PERCENTAGE_SHADE_700);
	}

    /**
     * creates text's colours state list. This has two colours, one for enabled and the other for disabled button state.
     * @param enabledTextColour
     * @param disabledTextColour
     * @return
     */
	private ColorStateList getTextStateColour(int enabledTextColour, int disabledTextColour) {
		int[][] states = new int[][]{
				new int[]{android.R.attr.state_enabled},
				new int[]{},
		};

		int[] colours = new int[]{
				enabledTextColour,
				disabledTextColour
		};
		return new ColorStateList(states, colours);
	}

	@Override
	public void setOnClickListener(OnClickListener clickListener) {
		mClickListener = clickListener;
	}

	@Override
	public void setEnabled(boolean enabled) {
		mTextView.setEnabled(enabled);
	}
}
