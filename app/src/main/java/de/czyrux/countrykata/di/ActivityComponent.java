package de.czyrux.countrykata.di;

import dagger.Subcomponent;

import de.czyrux.countrykata.ui.ToastProvider;

@ActivityScope
@Subcomponent(modules = {ActivityModule.class})
public interface ActivityComponent {
    FeatureComponent plus(FeatureModule featureModule);

    ToastProvider toastProvider();
}
