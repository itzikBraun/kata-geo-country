package de.czyrux.countrykata.ui;

import java.util.List;

import android.support.annotation.Nullable;

import de.czyrux.countrykata.core.domain.country.Country;
import de.czyrux.countrykata.core.domain.country.action.AllCountriesAction;
import de.czyrux.countrykata.core.domain.country.args.AllCountriesArgs;
import de.czyrux.countrykata.storage.TrackingStorage;
import de.czyrux.countrykata.storage.UserPreference;
import de.czyrux.countrykata.util.RxUtils;

import rx.Observer;

public class CountryListPresenter {

    private CountryListView countryListView;
    private final AllCountriesAction allCountriesAction;
    private final TrackingStorage trackingStorage;
    private final UserPreference userPreference;

    public CountryListPresenter(final AllCountriesAction allCountriesAction, final TrackingStorage trackingStorage,
            final UserPreference userPreference) {
        this.allCountriesAction = allCountriesAction;
        this.trackingStorage = trackingStorage;
        this.userPreference = userPreference;
    }

    public void attachView(final CountryListView countryListView) {
        this.countryListView = countryListView;
        loadCountries();
    }

    public void detach() {
        this.countryListView = null;
    }

    private void loadCountries() {
        if (getView() != null) {
            getView().showLoading();
        }

        allCountriesAction.execute(new AllCountriesArgs())                   //
                          .compose(RxUtils.<List<Country>>applySchedulers()) //
                          .subscribe(new Observer<List<Country>>() {
                                  @Override
                                  public void onCompleted() { }

                                  @Override
                                  public void onError(final Throwable e) {
                                      if (getView() != null) {
                                          getView().hideLoading();
                                          getView().showError("Oops!");
                                          getView().showEmptyState("Cannot load countries");
                                      }
                                  }

                                  @Override
                                  public void onNext(final List<Country> countries) {
                                      trackingStorage.countryListLoaded();

                                      if (getView() != null) {
                                          getView().hideLoading();
                                          getView().showCountries(countries);
                                      }
                                  }
                              });
    }

    @Nullable
    private CountryListView getView() {
        return countryListView;
    }
}
