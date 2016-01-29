package de.czyrux.countrykata.ui;

import java.util.List;

import javax.inject.Inject;

import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.util.Log;

import android.view.View;

import android.widget.ProgressBar;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;

import de.czyrux.countrykata.CountryApp;
import de.czyrux.countrykata.R;
import de.czyrux.countrykata.core.domain.country.Country;
import de.czyrux.countrykata.core.domain.image.ImageLoader;
import de.czyrux.countrykata.di.ActivityComponent;
import de.czyrux.countrykata.di.ActivityModule;
import de.czyrux.countrykata.di.AppComponent;
import de.czyrux.countrykata.di.DaggerFeatureComponent;
import de.czyrux.countrykata.di.FeatureComponent;
import de.czyrux.countrykata.di.FeatureModule;

public class CountryListActivity extends AppCompatActivity implements CountryListView {

    @Inject
    CountryListPresenter presenter;

    @Inject
    ImageLoader imageLoader;

    @Inject
    ToastProvider toastProvider;

    @Bind(R.id.country_list)
    RecyclerView countryListView;

    @Bind(R.id.country_list_progressbar)
    ProgressBar progressBar;

    @Bind(R.id.country_list_empty)
    TextView emptyTextView;

// @Inject
// CountryRepository countryRepository;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_country_list);
        ButterKnife.bind(this);

        AppComponent appComponent = ((CountryApp) getApplication()).getComponent();

        ActivityComponent activityComponent = appComponent.plus(new ActivityModule(this));

        FeatureComponent component =
            DaggerFeatureComponent.builder()                            //
                                  .activityComponent(activityComponent) //
                                  .featureModule(new FeatureModule())   //
                                  .build();

        component.inject(this);

// Preconditions.checkNotNull(countryRepository);

        setupViews();
    }

    private void setupViews() {
        countryListView.setLayoutManager(new LinearLayoutManager(this));
        countryListView.setAdapter(new CountryAdapter(imageLoader));

    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.attachView(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        presenter.detach();
    }

    @Override
    public void showError(final String message, final Object... param) {
        String errorMessage = String.format(message, param);
        toastProvider.get(errorMessage).show();
        Log.d("Tag", errorMessage);
    }

    @Override
    public void showCountries(final List<Country> countries) {
        ((CountryAdapter) countryListView.getAdapter()).addCountries(countries);
    }

    @Override
    public void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showEmptyState(final String message) {
        emptyTextView.setVisibility(View.VISIBLE);
        emptyTextView.setText(message);
    }
}
