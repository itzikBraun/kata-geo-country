package de.czyrux.countrykata.storage;

public class TrackingStorage {

    public static final String COUNTRY_LIST_LOAD = "country_list_load";
    private final Storage storage;

    public TrackingStorage(final Storage storage) {
        this.storage = storage;
    }

    public void countryListLoaded() {
        int current = getCountryListLoadedCount();

        current++;

        storage.putInt(COUNTRY_LIST_LOAD, current);
    }

    public int getCountryListLoadedCount() {
        return storage.getInt(COUNTRY_LIST_LOAD, 0);
    }
}
