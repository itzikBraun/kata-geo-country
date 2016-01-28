package de.czyrux.countrykata.core.domain;

public interface BaseAction<R, C extends CallArgs> {
    R execute(C args);
}
