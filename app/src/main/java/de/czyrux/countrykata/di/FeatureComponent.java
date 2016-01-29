package de.czyrux.countrykata.di;

import dagger.Component;

import de.czyrux.countrykata.storage.UserPreference;
import de.czyrux.countrykata.ui.CountryListActivity;

@FeatureScope
@Component(modules = FeatureModule.class, dependencies = ActivityComponent.class)
public interface FeatureComponent {

    void inject(CountryListActivity activity);

    UserPreference userPreferance();
}
