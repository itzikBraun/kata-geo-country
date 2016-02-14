package de.czyrux.countrykata.ui.util;

import java.util.List;

import android.support.annotation.NonNull;

import de.czyrux.countrykata.core.domain.country.Country;

public final class CountriesUtil {
    public static final int COUNTRY_NOT_FOUND = -1;

    private CountriesUtil() { }

    /**
     * @return  the position of the country in a list by country code, If the country does not exist
     *          {@link #COUNTRY_NOT_FOUND} will be returned.
     */
    public static int getCountryPosition(@NonNull final String countryCode, final List<Country> countries) {
        for (int i = 0; i < countries.size(); i++) {
            if (countries.get(i).getAlpha2Code().equals(countryCode)) {
                return i;
            }
        }

        return COUNTRY_NOT_FOUND;
    }
}
