package de.czyrux.countrykata.ui.list;

import android.widget.ImageView;

import de.czyrux.countrykata.core.domain.country.Country;

public interface CountryListListener {
    void onCountryClicked(Country country, int position, ImageView view);
}
