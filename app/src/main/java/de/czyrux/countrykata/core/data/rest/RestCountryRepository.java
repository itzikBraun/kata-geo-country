package de.czyrux.countrykata.core.data.rest;

import java.util.List;

import de.czyrux.countrykata.core.domain.country.Country;
import de.czyrux.countrykata.core.domain.country.CountryRepository;
import de.czyrux.countrykata.util.Preconditions;

class RestCountryRepository implements CountryRepository {

    private final CountryApi restApi;

    protected RestCountryRepository(final CountryApi countryApi) {
        Preconditions.checkNotNull(countryApi);
        this.restApi = countryApi;
    }

    @Override
    public List<Country> getAllCountries() {
        return restApi.getAllCountries();
    }

    @Override
    public Country getCountryByCode(final String countryCode) {

        Country country = null;
        List<Country> countryList = getCountriesByCode(new String[] {countryCode});
        if (countryList != null && countryList.size() >= 1) {
            country = countryList.get(0);
        }

        return country;
    }

    @Override
    public List<Country> getCountriesByCode(final String[] countryCodes) {

        StringBuilder codesQuery = new StringBuilder();
        for (String code : countryCodes) {
            if (codesQuery.length() != 0) {
                codesQuery.append(';');
            }

            codesQuery.append(code);
        }

        return restApi.getCountriesByCodes(codesQuery.toString());
    }

    @Override
    public List<Country> getCountriesByRegion(final String region) {
        return restApi.getCountriesByRegion(region);
    }

    @Override
    public List<Country> getCountriesByLanguage(final String language) {
        return restApi.getCountriesByLanguage(language);
    }

}
