package de.czyrux.countrykata.core.domain.country;

import java.util.List;

import de.czyrux.countrykata.util.Preconditions;

public class CountryService {

    private final CountryRepository countryRepository;

    public CountryService(final CountryRepository countryRepository) {
        Preconditions.checkNotNull(countryRepository);

        this.countryRepository = countryRepository;
    }

    public List<Country> getAllCountries() {
        return countryRepository.getAllCountries();
    }
}
