package de.czyrux.countrykata.ui.util;

import android.annotation.TargetApi;

import android.os.Build;

import android.view.View;
import android.view.ViewTreeObserver;

import de.czyrux.countrykata.R;

public class ViewUtils {

    public static void removeOnGlobalLayoutListener(final View v,
            final ViewTreeObserver.OnGlobalLayoutListener onGlobalLayoutListener) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            v.getViewTreeObserver().removeOnGlobalLayoutListener(onGlobalLayoutListener);
        } else {
            v.getViewTreeObserver().removeGlobalOnLayoutListener(onGlobalLayoutListener);
        }
    }

    public static View getToolbar(final View decoreView) {
        return decoreView.findViewById(R.id.toolbar);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    static View getStatusBar(final View decoreView) {
        return decoreView.findViewById(android.R.id.statusBarBackground);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    static View getNavigationBar(final View decoreView) {
        return decoreView.findViewById(android.R.id.navigationBarBackground);
    }
}
