package de.czyrux.countrykata.util;

import rx.Observable;

import rx.android.schedulers.AndroidSchedulers;

import rx.schedulers.Schedulers;

public class RxUtils {

    public static <T> Observable.Transformer<T, T> applySchedulers() {
        return new Observable.Transformer<T, T>() {
            @Override
            public Observable<T> call(final Observable<T> tObservable) {
                return tObservable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
            }
        };
    }
}
