package de.czyrux.countrykata.ui.detail;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;

import android.content.Context;
import android.content.Intent;

import android.os.Bundle;

import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;

import android.support.v7.app.AppCompatActivity;

import android.view.View;

import android.widget.ImageView;

import butterknife.Bind;
import butterknife.ButterKnife;

import de.czyrux.countrykata.R;
import de.czyrux.countrykata.core.domain.country.Country;
import de.czyrux.countrykata.core.domain.country.CountryImageBuilder;
import de.czyrux.countrykata.core.inject.Injector;

public class CountryDetailActivity extends AppCompatActivity implements CountryDetailListener {

    public static final String COUNTRIES = "countries";

    private List<Country> countries;

    @Bind(R.id.view_pager)
    ViewPager viewPager;

    @Bind(R.id.country_detail_flag_anim_stub)
    ImageView imageView;

    public static void launch(final Context context, final String countryCode) {
        Intent intent = new Intent(context, CountryDetailActivity.class);
        intent.putExtra(CountryDetailFragment.ARG_CODE, countryCode);
        context.startActivity(intent);
    }

    public static void launchWithAnimation(final Activity activity, final String countryCode, final View view,
            final ArrayList<Country> countries) {
        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(activity, view, countryCode);

        Intent intent = new Intent(activity, CountryDetailActivity.class);
        intent.putExtra(CountryDetailFragment.ARG_CODE, countryCode);
        intent.putParcelableArrayListExtra(CountryDetailActivity.COUNTRIES, countries);
        ActivityCompat.startActivity(activity, intent, options.toBundle());
    }

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_country_activity);

        ButterKnife.bind(this);

        countries = getIntent().getParcelableArrayListExtra(COUNTRIES);

        String countryCode;
        if (savedInstanceState == null) {
            countryCode = getIntent().getStringExtra(CountryDetailFragment.ARG_CODE);
        } else {
            countryCode = savedInstanceState.getString(CountryDetailFragment.ARG_CODE);
        }

        if (countryCode == null) {
            finish();
            return;
        }

        CountryPagerAdapter adapter = new CountryPagerAdapter(getSupportFragmentManager(), countries);
        int position = adapter.getCountryPosition(countryCode);

        if (position == CountryPagerAdapter.COUNTRY_NOT_FOUND) {
            finish();
            return;
        }

        ViewCompat.setTransitionName(imageView, countryCode);
        Injector.imageLoader().load(CountryImageBuilder.obtainImageUrl(countries.get(position)), imageView);

        // Hiding the animation stub
        hideAnimationStub();

        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(position);
    }

    @Override
    protected void onSaveInstanceState(final Bundle outState) {
        super.onSaveInstanceState(outState);

        Country country = countries.get(viewPager.getCurrentItem());
        outState.putString(CountryDetailFragment.ARG_CODE, country.getAlpha2Code());
    }

    private void hideAnimationStub() {
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(final int position, final float positionOffset,
                        final int positionOffsetPixels) {
                    imageView.setVisibility(View.GONE);
                }

                @Override
                public void onPageSelected(final int position) { }

                @Override
                public void onPageScrollStateChanged(final int state) { }
            });
    }

    @Override
    public void onExploreCountryRegion(final Country country) {
        // TODO
    }

    @Override
    public void onExploreCountryNeighbours(final Country country) {
        // TODO
    }
}
