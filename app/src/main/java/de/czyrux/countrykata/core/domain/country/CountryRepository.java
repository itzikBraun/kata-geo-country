package de.czyrux.countrykata.core.domain.country;

import java.util.List;

public interface CountryRepository {
    List<Country> getAllCountries();

    Country getCountryByCode(String countryCode);

    List<Country> getCountriesByCode(String[] countryCodes);

    List<Country> getCountriesByRegion(String region);

    List<Country> getCountriesByLanguage(String language);
}
