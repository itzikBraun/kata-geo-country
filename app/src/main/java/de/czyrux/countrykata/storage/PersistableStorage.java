package de.czyrux.countrykata.storage;

import java.util.Set;

public interface PersistableStorage {
    void putString(String key, String value);

    void putStringSet(String key, Set<String> values);

    void putInt(String key, int value);

    void putLong(String key, long value);

    void putFloat(String key, float value);

    void putBoolean(String key, boolean value);

    String getString(String key, String defValue);

    Set<String> getStringSet(String key, Set<String> defValues);

    int getInt(String key, int defValue);

    long getLong(String key, long defValue);

    float getFloat(String key, float defValue);

    boolean getBoolean(String key, boolean defValue);
}
