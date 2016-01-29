package de.czyrux.countrykata.ui;

import android.app.Activity;

import android.widget.Toast;

public class ToastProvider {

    private Activity activity;

    public ToastProvider(final Activity activity) {
        this.activity = activity;
    }

    public Toast get(final String text, final Object... param) {
        return Toast.makeText(activity, String.format(text, param), Toast.LENGTH_SHORT);
    }
}
