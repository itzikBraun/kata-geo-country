package de.czyrux.countrykata.core.domain.country;

import java.util.List;

import de.czyrux.countrykata.storage.UserPreference;
import de.czyrux.countrykata.util.Preconditions;

public class CountryService {

    private final CountryRepository countryRepository;
    private final UserPreference userPreference;

    public CountryService(final CountryRepository countryRepository, final UserPreference userPreference) {
        Preconditions.checkNotNull(countryRepository);
        Preconditions.checkNotNull(userPreference);
        this.countryRepository = countryRepository;
        this.userPreference = userPreference;
    }

    public List<Country> getAllCountries() {
        return countryRepository.getAllCountries();
    }
}
