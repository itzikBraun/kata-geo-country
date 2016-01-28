package de.czyrux.countrykata.di;

import dagger.Subcomponent;

import de.czyrux.countrykata.core.domain.country.action.AllCountriesAction;
import de.czyrux.countrykata.ui.CountryListActivity;
import de.czyrux.countrykata.ui.FeatureObject;

@FeatureScope
@Subcomponent(modules = FeatureModule.class)
public interface FeatureComponent {

    void inject(CountryListActivity activity);

    FeatureObject featureObject();

    AllCountriesAction allCountriesAction();
}
