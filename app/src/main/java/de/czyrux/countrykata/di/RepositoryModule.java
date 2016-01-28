package de.czyrux.countrykata.di;

import dagger.Module;
import dagger.Provides;

import de.czyrux.countrykata.core.data.rest.RestCountryRepositoryFactory;
import de.czyrux.countrykata.core.domain.country.CountryRepository;

@Module
public class RepositoryModule {

    @AppScope
    @Provides
    public CountryRepository provideCountryRepository() {
        return RestCountryRepositoryFactory.create();
    }
}
