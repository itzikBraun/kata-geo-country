package de.czyrux.countrykata.ui;

import android.content.Context;

import android.widget.Toast;

public class ToastProvider {

    private Context context;

    public ToastProvider(final Context context) {
        this.context = context;
    }

    public Toast get(final String text, final Object... param) {
        return Toast.makeText(context, String.format(text, param), Toast.LENGTH_SHORT);
    }
}
