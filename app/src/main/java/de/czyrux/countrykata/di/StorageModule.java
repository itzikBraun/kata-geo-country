package de.czyrux.countrykata.di;

import android.content.Context;

import dagger.Module;
import dagger.Provides;

import de.czyrux.countrykata.storage.Storage;
import de.czyrux.countrykata.storage.TrackingStorage;
import de.czyrux.countrykata.storage.UserPreference;

@Module
public class StorageModule {

    @AppScope
    @Provides
    public Storage provideStorage(final Context context) {
        return new Storage(context);
    }

    @AppScope
    @Provides
    public TrackingStorage provideTrackingStorage(final Storage storage) {
        return new TrackingStorage(storage);
    }

    @AppScope
    @Provides
    public UserPreference provideUserPreferance(final Storage storage) {
        return new UserPreference(storage);
    }

}
