package de.czyrux.countrykata.ui.detail;

import android.app.Activity;

import android.content.Context;
import android.content.Intent;

import android.os.Bundle;

import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;

import android.support.v7.app.AppCompatActivity;

import android.view.View;

import de.czyrux.countrykata.R;
import de.czyrux.countrykata.core.domain.country.Country;

public class CountryDetailActivity extends AppCompatActivity implements CountryDetailListener {

    public static void launch(final Context context, final String countryCode) {
        Intent intent = new Intent(context, CountryDetailActivity.class);
        intent.putExtra(CountryDetailFragment.ARG_CODE, countryCode);
        context.startActivity(intent);
    }

    public static void launchWithAnimation(final Activity activity, final String countryCode, final View view) {
        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(activity, view, countryCode);

        Intent intent = new Intent(activity, CountryDetailActivity.class);
        intent.putExtra(CountryDetailFragment.ARG_CODE, countryCode);

        ActivityCompat.startActivity(activity, intent, options.toBundle());
    }

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_country_activity);

        if (savedInstanceState == null) {
            String countryCode = getIntent().getStringExtra(CountryDetailFragment.ARG_CODE);
            if (countryCode == null) {
                finish();
                return;
            }

            getSupportFragmentManager().beginTransaction()
                                       .replace(R.id.container, CountryDetailFragment.newInstance(countryCode))
                                       .commit();
        }
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
