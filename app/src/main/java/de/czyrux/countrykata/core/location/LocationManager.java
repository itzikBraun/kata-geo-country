package de.czyrux.countrykata.core.location;

import android.content.Context;

import de.czyrux.countrykata.core.premission.RequestLocationPermission;

public class LocationManager {

    private final Context context;
    private final RequestLocationPermission requestLocationPremission;

    public LocationManager(final Context context, final RequestLocationPermission requestLocationPermission) {
        this.context = context;
        this.requestLocationPremission = requestLocationPermission;
    }
}
