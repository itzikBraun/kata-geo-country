package de.czyrux.countrykata.ui.util;

import android.os.Build;

final class VersionUtils {
    private VersionUtils() { }

    static boolean isLollipopAndAbove() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP;
    }
}
