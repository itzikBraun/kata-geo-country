package de.czyrux.countrykata.ui;

import java.util.List;

import de.czyrux.countrykata.core.domain.country.Country;

public interface CountryListView {

    void showError(String message, Object... param);

    void showCountries(List<Country> countries);

    void showLoading();

    void hideLoading();

    void showEmptyState(String message);
}
