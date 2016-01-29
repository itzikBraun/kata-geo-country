package de.czyrux.countrykata.di;

import dagger.Subcomponent;

import de.czyrux.countrykata.core.domain.image.ImageLoader;
import de.czyrux.countrykata.storage.UserPreference;
import de.czyrux.countrykata.ui.CountryListPresenter;
import de.czyrux.countrykata.ui.ToastProvider;

@ActivityScope
@Subcomponent(modules = {ActivityModule.class})
public interface ActivityComponent {

    ToastProvider toastProvider();

    UserPreference userPreference();

    ImageLoader imageLoader();

    CountryListPresenter countryListPresenter();
}
