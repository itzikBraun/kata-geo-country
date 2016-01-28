package de.czyrux.countrykata.di;

import android.app.Activity;

import dagger.Module;
import dagger.Provides;

import de.czyrux.countrykata.ui.ToastProvider;

@Module
public class ActivityModule {
    private Activity activity;

    public ActivityModule(final Activity activity) {
        this.activity = activity;
    }

    @ActivityScope
    @Provides
    public ToastProvider provideToastProvider() {
        return new ToastProvider(activity);
    }
}
