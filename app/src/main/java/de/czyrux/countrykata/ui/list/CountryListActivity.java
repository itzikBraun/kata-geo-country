package de.czyrux.countrykata.ui.list;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import android.content.Intent;

import android.os.Bundle;

import android.support.v4.app.SharedElementCallback;
import android.support.v4.util.Pair;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import android.view.View;

import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;

import de.czyrux.countrykata.R;
import de.czyrux.countrykata.core.domain.country.Country;
import de.czyrux.countrykata.core.domain.country.CountryService;
import de.czyrux.countrykata.core.domain.image.ImageLoader;
import de.czyrux.countrykata.core.inject.Injector;
import de.czyrux.countrykata.ui.detail.CountryDetailActivity;
import de.czyrux.countrykata.ui.util.CountriesUtil;
import de.czyrux.countrykata.ui.util.SharedElementCompat;

public class CountryListActivity extends AppCompatActivity implements CountryListListener, CountryListView {

    public static final String CURRENT_COUNTRY = "current_country";
    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Bind(R.id.country_list)
    RecyclerView countryListView;

    @Bind(R.id.country_list_progressbar)
    ProgressBar progressBar;

    @Bind(R.id.country_list_empty)
    TextView emptyTextView;

    private ImageLoader imageLoader;
    private CountryListPresenter presenter;
    private ArrayList<Country> countries;
    private String openedCountryCode;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        SharedElementCompat.enableActivityTransition(getWindow());
        super.onCreate(savedInstanceState);

        setContentView(R.layout.list_country_activity);
        ButterKnife.bind(this);

        SharedElementCompat.setupSystemUiTransition(getWindow());
        setSupportActionBar(toolbar);

        imageLoader = Injector.imageLoader();
        setupViews();

        CountryService countryService = Injector.countryService();
        presenter = new CountryListPresenter(countryService);
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.onViewAttached(this);
    }

    @Override
    protected void onStop() {
        presenter.onViewDetached();
        super.onStop();
    }

    @Override
    protected void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onActivityReenter(final int resultCode, final Intent data) {
        super.onActivityReenter(resultCode, data);

        final String transitionName = data.getStringExtra(CURRENT_COUNTRY);
        int pos = CountriesUtil.getCountryPosition(transitionName, countries);

        removeElementsFromEnterAnimation(transitionName, pos);

        countryListView.scrollToPosition(pos);
    }

    private void removeElementsFromEnterAnimation(final String transitionName, final int position) {
        countryListView.post(new Runnable() {
                @Override
                public void run() {
                    setEnterSharedElementCallback(new SharedElementCallback() {
                            @Override
                            public void onMapSharedElements(final List<String> names,
                                    final Map<String, View> sharedElements) {
                                super.onMapSharedElements(names, sharedElements);

                                // Removing unused elements
                                for (Country country : countries) {
                                    String code = country.getAlpha2Code();
                                    if (code.equals(transitionName)) {
                                        continue;
                                    }

                                    sharedElements.remove(code);
                                }

                                // Failed attempt to animate to a view that was not present before.
// View v = countryListView.findViewHolderForAdapterPosition(position).itemView
// .findViewById(R.id.country_item_image);
// ViewCompat.setTransitionName(v, transitionName);
// sharedElements.put(transitionName, v);
// names.add(transitionName);
                            }
                        });
                }
            });
    }

    private void setupViews() {
        countryListView.setLayoutManager(new LinearLayoutManager(this));
        countryListView.setAdapter(new CountryAdapter(imageLoader, this));
    }

    @Override
    public void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showCountryList(final List<Country> countryList) {
        countries = new ArrayList<>(countryList);
        ((CountryAdapter) countryListView.getAdapter()).setCountries(countryList);
    }

    @Override
    public void showEmptyText() {
        emptyTextView.setVisibility(View.VISIBLE);
    }

    @Override
    public void showError() {
        Toast.makeText(CountryListActivity.this, "Ups!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCountryClicked(final Country country, final int position, final ImageView imageView) {
        openedCountryCode = country.getAlpha2Code();

        CountryDetailActivity.launchWithAnimation(this, openedCountryCode,
            (Pair<View, String>[]) ((CountryAdapter) countryListView.getAdapter()).getPairs(), countries, 1);
    }
}
