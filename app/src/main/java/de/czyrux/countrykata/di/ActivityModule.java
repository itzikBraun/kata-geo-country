package de.czyrux.countrykata.di;

import android.app.Activity;

import dagger.Module;
import dagger.Provides;

import de.czyrux.countrykata.core.domain.country.action.AllCountriesAction;
import de.czyrux.countrykata.storage.TrackingStorage;
import de.czyrux.countrykata.storage.UserPreference;
import de.czyrux.countrykata.ui.CountryListPresenter;
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

    @ActivityScope
    @Provides
    public CountryListPresenter provideCountryListPresenter(final AllCountriesAction action,
            final TrackingStorage trackingStorage, final UserPreference userPreference) {
        return new CountryListPresenter(action, trackingStorage, userPreference);
    }
}
