package de.czyrux.countrykata.di;

import android.content.Context;
import android.content.SharedPreferences;

import dagger.Module;
import dagger.Provides;

import de.czyrux.countrykata.storage.Storage;
import de.czyrux.countrykata.storage.TrackingStorage;
import de.czyrux.countrykata.storage.UserPreference;

@Module
public class StorageModule {

    private static final String APP_DEFAULT_PREFS = "AppDefault";

    @AppScope
    @Provides
    public SharedPreferences provideSharedPreferences(final Context context) {
        return context.getSharedPreferences(APP_DEFAULT_PREFS, Context.MODE_PRIVATE);
    }

    @AppScope
    @Provides
    public Storage provideStorage(final Context context, final SharedPreferences sharedPreferences) {
        return new Storage(context, sharedPreferences);
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
