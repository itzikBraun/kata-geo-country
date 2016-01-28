package de.czyrux.countrykata.di;

import dagger.Component;

@AppScope
@Component(modules = {AppModule.class, ServiceModule.class})
public interface AppComponent {

    ActivityComponent plus(ActivityModule activityModule);
}
