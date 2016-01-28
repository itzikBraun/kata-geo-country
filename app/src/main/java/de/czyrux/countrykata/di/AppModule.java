package de.czyrux.countrykata.di;

import dagger.Module;
import dagger.Provides;

import de.czyrux.countrykata.core.data.image.PicassoImageLoader;
import de.czyrux.countrykata.core.domain.image.ImageLoader;

@Module
public class AppModule {

    @AppScope
    @Provides
    public ImageLoader provideImageLoader() {
        return new PicassoImageLoader();
    }
}
