package de.czyrux.countrykata.ui.util;

import android.os.Build;

import android.view.View;
import android.view.ViewTreeObserver;

public class ViewUtils {
    public static View getToolbar(final View decoreView) {
        return decoreView.findViewById(decoreView.getResources().getIdentifier("action_bar_container", "id",
                    "android"));
    }

    public static void removeOnGlobalLayoutListener(final View v,
            final ViewTreeObserver.OnGlobalLayoutListener onGlobalLayoutListener) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            v.getViewTreeObserver().removeOnGlobalLayoutListener(onGlobalLayoutListener);
        } else {
            v.getViewTreeObserver().removeGlobalOnLayoutListener(onGlobalLayoutListener);
        }
    }
}
