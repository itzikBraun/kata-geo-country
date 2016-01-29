package de.czyrux.countrykata.di;

import dagger.Component;

@AppScope
@Component(modules = {AppModule.class, CountryDomainModule.class, StorageModule.class})
public interface AppComponent {

    ActivityComponent plus(ActivityModule activityModule);
}
