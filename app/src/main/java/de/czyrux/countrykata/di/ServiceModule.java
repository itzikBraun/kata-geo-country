package de.czyrux.countrykata.di;

import dagger.Module;
import dagger.Provides;

import de.czyrux.countrykata.core.domain.country.CountryRepository;
import de.czyrux.countrykata.core.domain.country.CountryService;

@Module(includes = RepositoryModule.class)
public class ServiceModule {

    @AppScope
    @Provides
    public CountryService provideCountryService(final CountryRepository countryRepository) {
        return new CountryService(countryRepository);
    }
}
