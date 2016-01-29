package de.czyrux.countrykata.storage;

import java.util.Set;

import android.content.Context;
import android.content.SharedPreferences;

public class Storage implements PersistableStorage {

    private final Context context;
    private final SharedPreferences sharedPreferences;

    public Storage(final Context context, final SharedPreferences sharedPreferences) {
        this.context = context;
        this.sharedPreferences = sharedPreferences;
    }

    @Override
    public void putString(final String key, final String value) {
        sharedPreferences.edit().putString(key, value).apply();
    }

    @Override
    public void putStringSet(final String key, final Set<String> values) {
        sharedPreferences.edit().putStringSet(key, values).apply();
    }

    @Override
    public void putInt(final String key, final int value) {
        sharedPreferences.edit().putInt(key, value).apply();
    }

    @Override
    public void putLong(final String key, final long value) {
        sharedPreferences.edit().putLong(key, value).apply();
    }

    @Override
    public void putFloat(final String key, final float value) {
        sharedPreferences.edit().putFloat(key, value).apply();
    }

    @Override
    public void putBoolean(final String key, final boolean value) {
        sharedPreferences.edit().putBoolean(key, value).apply();
    }

    @Override
    public String getString(final String key, final String defValue) {
        return sharedPreferences.getString(key, defValue);
    }

    @Override
    public Set<String> getStringSet(final String key, final Set<String> defValues) {
        return sharedPreferences.getStringSet(key, defValues);
    }

    @Override
    public int getInt(final String key, final int defValue) {
        return sharedPreferences.getInt(key, defValue);
    }

    @Override
    public long getLong(final String key, final long defValue) {
        return sharedPreferences.getLong(key, defValue);
    }

    @Override
    public float getFloat(final String key, final float defValue) {
        return sharedPreferences.getFloat(key, defValue);
    }

    @Override
    public boolean getBoolean(final String key, final boolean defValue) {
        return sharedPreferences.getBoolean(key, defValue);
    }
}
