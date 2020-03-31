package com.parassidhu.coronavirusapp.ui.custom;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.parassidhu.coronavirusapp.CoronaApp;

@SuppressLint("AppCompatCustomView")
public class CustomTextView extends TextView {
    public CustomTextView(Context context) {
        super(context);
        if (!isInEditMode()) {
            SetTypeFace();
        }
    }

    public CustomTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        if (!isInEditMode()) {
            SetTypeFace();
        }
    }

    public CustomTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        if (!isInEditMode()) {
            SetTypeFace();
        }
    }

    public CustomTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        if (!isInEditMode()) {
            SetTypeFace();
        }
    }

    private void SetTypeFace() {
        CoronaApp applicationContext = (CoronaApp) getContext().getApplicationContext();
        setTypeface(applicationContext.iranSansFont);
    }
}
