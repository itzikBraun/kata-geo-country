package de.czyrux.countrykata.ui.util;

import android.app.Activity;

import android.support.v4.util.Pair;

import android.view.View;
import android.view.Window;

public final class SharedElementCompat {

    public static final String TOOLBAR_TRANSITION_NAME = "toolbar_transition_name";

    private SharedElementCompat() { }

    /**
     * Must be called before {@link android.app.Activity#setContentView(int)}.
     */
    public static void enableActivityTransition(final Window window) {
        if (VersionUtils.isLollipopAndAbove()) {
            window.requestFeature(Window.FEATURE_ACTIVITY_TRANSITIONS);

            View toolbar = ViewUtils.getToolbar(window.getDecorView());

            if (toolbar != null) {
                toolbar.setTransitionName(TOOLBAR_TRANSITION_NAME);
            }
        }
    }

    public static void postpone(final Activity activity) {
        if (VersionUtils.isLollipopAndAbove()) {
            activity.postponeEnterTransition();
        }
    }

    public static void startPostponed(final Activity activity) {
        if (VersionUtils.isLollipopAndAbove()) {
            activity.startPostponedEnterTransition();
        }
    }

    public static Pair<View, String>[] concatSystemUiElements(final View decoreView,
            final Pair<View, String>... pairs) {
        if (VersionUtils.isLollipopAndAbove()) {
            View statusBar = decoreView.findViewById(android.R.id.statusBarBackground);
            View navBar = decoreView.findViewById(android.R.id.navigationBarBackground);
            View toolbar = ViewUtils.getToolbar(decoreView);

            @SuppressWarnings("unchecked")
            Pair<View, String>[] finalPairs = new Pair[pairs.length + 2];

            for (int i = 0; i < pairs.length; i++) {
                finalPairs[i] = pairs[i];
            }

            finalPairs[pairs.length] = Pair.create(statusBar, Window.STATUS_BAR_BACKGROUND_TRANSITION_NAME);
            finalPairs[pairs.length + 1] = Pair.create(navBar, Window.NAVIGATION_BAR_BACKGROUND_TRANSITION_NAME);
            if (toolbar != null) {
                finalPairs[pairs.length + 2] = Pair.create(toolbar, TOOLBAR_TRANSITION_NAME);
            }

            return finalPairs;
        }

        return pairs;
    }

}
