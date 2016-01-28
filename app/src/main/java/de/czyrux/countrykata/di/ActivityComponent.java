package de.czyrux.countrykata.di;

import dagger.Subcomponent;

@ActivityScope
@Subcomponent(modules = {ActivityModule.class})
public interface ActivityComponent {
    FeatureComponent plus(FeatureModule featureModule);
}
