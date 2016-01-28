package de.czyrux.countrykata.di;

import dagger.Module;
import dagger.Provides;

import de.czyrux.countrykata.core.domain.country.CountryService;
import de.czyrux.countrykata.core.domain.country.action.AllCountriesAction;

@Module
public class CountryActionsModule {

    @FeatureScope
    @Provides
    public AllCountriesAction provideAllCountriesAction(final CountryService countryService) {
        return new AllCountriesAction(countryService);
    }
}
