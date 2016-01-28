package de.czyrux.countrykata.di;

import dagger.Module;
import dagger.Provides;

import de.czyrux.countrykata.core.domain.image.ImageLoader;
import de.czyrux.countrykata.ui.FeatureObject;
import de.czyrux.countrykata.ui.ToastProvider;

@Module(includes = CountryActionsModule.class)
public class FeatureModule {

    @FeatureScope
    @Provides
    public FeatureObject provideFeatureObject(final ToastProvider toastProvider, final ImageLoader imageLoader) {
        return new FeatureObject(toastProvider, imageLoader);
    }
}
