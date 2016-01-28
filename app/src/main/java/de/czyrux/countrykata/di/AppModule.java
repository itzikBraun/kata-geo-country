package de.czyrux.countrykata.di;

import android.content.Context;

import dagger.Module;
import dagger.Provides;

import de.czyrux.countrykata.CountryApp;
import de.czyrux.countrykata.core.data.image.PicassoImageLoader;
import de.czyrux.countrykata.core.domain.image.ImageLoader;

@Module
public class AppModule {

    private CountryApp countryApp;

    public AppModule(final CountryApp countryApp) {
        this.countryApp = countryApp;
    }

    @AppScope
    @Provides
    public Context provideContext() {
        return countryApp;
    }

    @AppScope
    @Provides
    public ImageLoader provideImageLoader() {
        return new PicassoImageLoader();
    }
}
