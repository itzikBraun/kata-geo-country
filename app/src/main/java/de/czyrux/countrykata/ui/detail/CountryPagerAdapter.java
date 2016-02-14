package de.czyrux.countrykata.ui.detail;

import java.util.List;

import android.support.annotation.NonNull;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import de.czyrux.countrykata.core.domain.country.Country;

public class CountryPagerAdapter extends FragmentStatePagerAdapter {

    public static final int COUNTRY_NOT_FOUND = -1;

    private List<Country> countries;

    public CountryPagerAdapter(final FragmentManager fm, final List<Country> countries) {
        super(fm);
        this.countries = countries;
    }

    @Override
    public Fragment getItem(final int position) {
        return CountryDetailFragment.newInstance(countries.get(position).getAlpha2Code());
    }

    @Override
    public int getCount() {
        return countries.size();
    }

    /**
     * @return  the position of the country in a list by country code, If the country does not exist
     *          {@link #COUNTRY_NOT_FOUND} will be returned.
     */
    public int getCountryPosition(@NonNull final String countryCode) {
        for (int i = 0; i < countries.size(); i++) {
            if (countries.get(i).getAlpha2Code().equals(countryCode)) {
                return i;
            }
        }

        return COUNTRY_NOT_FOUND;
    }
}
