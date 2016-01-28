package de.czyrux.countrykata.ui;

import de.czyrux.countrykata.core.domain.image.ImageLoader;
import de.czyrux.countrykata.util.Preconditions;

public class FeatureObject {

    ToastProvider toastProvider;
    ImageLoader imageLoader;

    public FeatureObject(final ToastProvider toastProvider, final ImageLoader imageLoader) {
        Preconditions.checkNotNull(toastProvider);
        Preconditions.checkNotNull(imageLoader);

        this.toastProvider = toastProvider;
        this.imageLoader = imageLoader;
    }
}
