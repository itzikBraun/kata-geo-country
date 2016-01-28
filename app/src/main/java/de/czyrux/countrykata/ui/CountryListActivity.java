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
import de.czyrux.countrykata.core.domain.country.action.AllCountriesAction;
import de.czyrux.countrykata.core.domain.country.args.AllCountriesArgs;
import de.czyrux.countrykata.core.domain.image.ImageLoader;
import de.czyrux.countrykata.di.ActivityModule;
import de.czyrux.countrykata.di.AppComponent;
import de.czyrux.countrykata.di.FeatureModule;
import de.czyrux.countrykata.util.RxUtils;

import rx.Observer;

public class CountryListActivity extends AppCompatActivity {

    @Inject
    AllCountriesAction allCountriesAction;

    @Inject
    ImageLoader imageLoader;

    @Inject
    ToastProvider toastProvider;

    @Inject
    FeatureObject featureObject;

    @Bind(R.id.country_list)
    RecyclerView countryListView;

    @Bind(R.id.country_list_progressbar)
    ProgressBar progressBar;

    @Bind(R.id.country_list_empty)
    TextView emptyTextView;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        AppComponent appComponent = ((CountryApp) getApplication()).getComponent();

        appComponent.plus(new ActivityModule(this)).plus(new FeatureModule()).inject(this);

        setContentView(R.layout.activity_country_list);
        ButterKnife.bind(this);

        setupViews();
    }

    private void setupViews() {
        countryListView.setLayoutManager(new LinearLayoutManager(this));
        countryListView.setAdapter(new CountryAdapter(imageLoader));

        emptyTextView.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onResume() {
        super.onResume();
        allCountriesAction.execute(new AllCountriesArgs())                   //
                          .compose(RxUtils.<List<Country>>applySchedulers()) //
                          .subscribe(new Observer<List<Country>>() {
                                  @Override
                                  public void onCompleted() { }

                                  @Override
                                  public void onError(final Throwable e) {
                                      toastProvider.get("oops!").show();
                                      Log.d("Tag", Log.getStackTraceString(e));
                                  }

                                  @Override
                                  public void onNext(final List<Country> countries) {
                                      progressBar.setVisibility(View.GONE);
                                      ((CountryAdapter) countryListView.getAdapter()).addCountries(countries);
                                  }
                              });
    }
}
