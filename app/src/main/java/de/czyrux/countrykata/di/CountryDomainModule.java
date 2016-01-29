package de.czyrux.countrykata.di;

import dagger.Module;
import dagger.Provides;

import de.czyrux.countrykata.core.domain.country.CountryRepository;
import de.czyrux.countrykata.core.domain.country.CountryService;
import de.czyrux.countrykata.core.domain.country.action.AllCountriesAction;
import de.czyrux.countrykata.storage.UserPreference;

@Module(includes = RepositoryModule.class)
public class CountryDomainModule {

    @AppScope
    @Provides
    public CountryService provideCountryService(final CountryRepository countryRepository,
            final UserPreference userPreference) {
        return new CountryService(countryRepository, userPreference);
    }

    @AppScope
    @Provides
    public AllCountriesAction provideAllCountriesAction(final CountryService countryService) {
        return new AllCountriesAction(countryService);
    }
}
