package de.czyrux.countrykata.ui.util;

import static de.czyrux.countrykata.ui.util.VersionUtils.isLollipopAndAbove;

import android.annotation.TargetApi;

import android.app.Activity;

import android.os.Build;

import android.support.v4.util.Pair;

import android.view.View;
import android.view.ViewTreeObserver;
import android.view.Window;

public final class SharedElementCompat {

    public static final String TOOLBAR_TRANSITION_NAME = "toolbar_transition_name";
    private static View decorView;

    private SharedElementCompat() { }

    /**
     * Must be called before {@link android.app.Activity#setContentView(int)}.
     */
    public static void enableActivityTransition(final Window window) {
        if (isLollipopAndAbove()) {
            window.requestFeature(Window.FEATURE_ACTIVITY_TRANSITIONS);
        }
    }

    public static void setupSystemUiTransition(final Window window) {
        if (isLollipopAndAbove()) {
            final View decoreView = window.getDecorView();

            View toolbar = ViewUtils.getToolbar(decoreView);
            if (toolbar != null) {
                toolbar.setTransitionName(TOOLBAR_TRANSITION_NAME);
            }

            decoreView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
                    public void onGlobalLayout() {
                        View statusBar = ViewUtils.getStatusBar(decoreView);
                        View navBar = ViewUtils.getNavigationBar(decoreView);
                        statusBar.setTransitionName(Window.STATUS_BAR_BACKGROUND_TRANSITION_NAME);

                        navBar.setTransitionName(Window.NAVIGATION_BAR_BACKGROUND_TRANSITION_NAME);
                        decoreView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    }
                });
        }
    }

    public static void postpone(final Activity activity) {
        if (isLollipopAndAbove()) {
            activity.postponeEnterTransition();
        }
    }

    public static void startPostponed(final Activity activity) {
        if (isLollipopAndAbove()) {
            activity.startPostponedEnterTransition();
        }
    }

    public static void waitForDecoreViewLayout(final Activity activity) {
        if (isLollipopAndAbove()) {
            postpone(activity);

            decorView = activity.getWindow().getDecorView();
            decorView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        startPostponed(activity);
                        ViewUtils.removeOnGlobalLayoutListener(decorView, this);
                    }
                });
        }
    }

    public static Pair<View, String>[] concatSystemUiElements(final View decoreView,
            final Pair<View, String>... pairs) {
        if (isLollipopAndAbove()) {
            View statusBar = ViewUtils.getStatusBar(decoreView);
            View navBar = ViewUtils.getNavigationBar(decoreView);
            View toolbar = ViewUtils.getToolbar(decoreView);

            boolean hasToolbar = toolbar != null;
            int systemUiComponents = (hasToolbar ? 3 : 2);

            @SuppressWarnings("unchecked")
            Pair<View, String>[] finalPairs = new Pair[pairs.length + systemUiComponents];
            System.arraycopy(pairs, 0, finalPairs, systemUiComponents, pairs.length);

            finalPairs[0] = Pair.create(statusBar, Window.STATUS_BAR_BACKGROUND_TRANSITION_NAME);
            finalPairs[1] = Pair.create(navBar, Window.NAVIGATION_BAR_BACKGROUND_TRANSITION_NAME);
            if (hasToolbar) {
                finalPairs[2] = Pair.create(toolbar, TOOLBAR_TRANSITION_NAME);
            }

            return finalPairs;
        }

        return pairs;
    }

}
