package com.simple.material;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.TextView;

import com.simple.utils.DeviceUtils;

/**
 * Material Flat button
 */
public class FlatButton extends TextView {
    public FlatButton(Context context) {
        super(context);
        init();
    }

    public FlatButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public FlatButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public FlatButton(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        setThe(R.attr.colorControlHighlight);
        final int density = (int) DeviceUtils.getDisplayDensity(getContext());
        setMinWidth(88 * density);
        setMinHeight(36 * density);
        setTextSize(14);
        setTypeface(getTypeface(), Typeface.BOLD);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
            setAllCaps(true);
        }
    }
}
