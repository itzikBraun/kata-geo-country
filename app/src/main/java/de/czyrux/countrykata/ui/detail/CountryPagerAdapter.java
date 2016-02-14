package de.czyrux.countrykata.ui.detail;

import java.util.List;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import de.czyrux.countrykata.core.domain.country.Country;

public class CountryPagerAdapter extends FragmentStatePagerAdapter {

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

}
