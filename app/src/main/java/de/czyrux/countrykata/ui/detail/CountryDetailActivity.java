package de.czyrux.countrykata.ui.detail;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;

import android.content.Intent;

import android.os.Bundle;

import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.view.View;
import android.view.ViewTreeObserver;

import android.widget.ImageView;

import butterknife.Bind;
import butterknife.ButterKnife;

import de.czyrux.countrykata.R;
import de.czyrux.countrykata.core.domain.country.Country;
import de.czyrux.countrykata.core.domain.country.CountryImageBuilder;
import de.czyrux.countrykata.core.inject.Injector;
import de.czyrux.countrykata.ui.list.CountryListActivity;
import de.czyrux.countrykata.ui.util.CountriesUtil;
import de.czyrux.countrykata.ui.util.SharedElementCompat;
import de.czyrux.countrykata.ui.util.ViewUtils;

public class CountryDetailActivity extends AppCompatActivity implements CountryDetailListener {

    public static final String COUNTRIES = "countries";

    private List<Country> countries;

    @Bind(R.id.view_pager)
    ViewPager viewPager;

    @Bind(R.id.country_detail_flag_anim_stub)
    ImageView imageView;

    private final ViewTreeObserver.OnGlobalLayoutListener globalLayoutListener =
        new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                imageView.setVisibility(View.GONE);
            }
        };

    public static void launchWithAnimation(final Activity activity, final String countryCode,
            final Pair<View, String>[] elements, final ArrayList<Country> countries, final int requestCode) {
        Pair<View, String>[] pairs = SharedElementCompat.concatSystemUiElements(activity.getWindow().getDecorView(),
                elements);
        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(activity, pairs);

        Intent intent = new Intent(activity, CountryDetailActivity.class);

        intent.putExtra(CountryDetailFragment.ARG_CODE, countryCode);
        intent.putParcelableArrayListExtra(CountryDetailActivity.COUNTRIES, countries);
        ActivityCompat.startActivityForResult(activity, intent, requestCode, options.toBundle());
    }

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        SharedElementCompat.enableActivityTransition(getWindow());
        super.onCreate(savedInstanceState);

        setContentView(R.layout.detail_country_activity);

        Toolbar toolbar = ButterKnife.findById(this, R.id.toolbar);
        setSupportActionBar(toolbar);

        ButterKnife.bind(this);

        hideAnimationStub();

        loadCountriesFromIntent();

        String countryCode = loadCountryCodeFromBundleOrIntent(savedInstanceState);

        if (countryCode == null) {
            finish();
            return;
        }

        int position = CountriesUtil.getCountryPosition(countryCode, countries);

        if (position == CountriesUtil.COUNTRY_NOT_FOUND) {
            finish();
            return;
        }

        setupEnterAnimation(countryCode, position);

        setupViewPager(position);
    }

    private void setupEnterAnimation(final String countryCode, final int position) {
        ViewCompat.setTransitionName(imageView, countryCode);
        loadImageIntoStub(position);
    }

    private void loadCountriesFromIntent() {
        countries = getIntent().getParcelableArrayListExtra(COUNTRIES);
    }

    private void setupViewPager(final int position) {
        viewPager.setAdapter(new CountryPagerAdapter(getSupportFragmentManager(), countries));
        viewPager.setCurrentItem(position);
    }

    private String loadCountryCodeFromBundleOrIntent(final Bundle savedInstanceState) {
        String countryCode;
        if (savedInstanceState == null) {
            countryCode = getIntent().getStringExtra(CountryDetailFragment.ARG_CODE);
        } else {
            countryCode = savedInstanceState.getString(CountryDetailFragment.ARG_CODE);
        }

        return countryCode;
    }

    private void loadImageIntoStub(final int position) {
        Injector.imageLoader().load(CountryImageBuilder.obtainImageUrl(countries.get(position)), imageView);
    }

    @Override
    protected void onSaveInstanceState(final Bundle outState) {
        super.onSaveInstanceState(outState);

        Country country = getCurrentCountry();
        outState.putString(CountryDetailFragment.ARG_CODE, country.getAlpha2Code());
    }

    @Override
    protected void onStop() {
        super.onStop();
        ViewUtils.removeOnGlobalLayoutListener(viewPager, globalLayoutListener);
    }

    @Override
    public void finishAfterTransition() {
        final String transitionName = getCurrentCountryCode();
        Intent data = new Intent();
        data.putExtra(CountryListActivity.CURRENT_COUNTRY, transitionName);
        setResult(RESULT_OK, data);

        ViewCompat.setTransitionName(imageView, transitionName);
        imageView.setVisibility(View.VISIBLE);
        loadImageIntoStub(viewPager.getCurrentItem());
        super.finishAfterTransition();
    }

    private Country getCurrentCountry() {
        return countries.get(viewPager.getCurrentItem());
    }

    private String getCurrentCountryCode() {
        return getCurrentCountry().getAlpha2Code();
    }

    private void hideAnimationStub() {
        viewPager.getViewTreeObserver().addOnGlobalLayoutListener(globalLayoutListener);
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
