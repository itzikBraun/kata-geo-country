package de.czyrux.countrykata.util;

import rx.Observable;

import rx.Observable.Transformer;

import rx.android.schedulers.AndroidSchedulers;

import rx.schedulers.Schedulers;

public class RxUtils {

    @SuppressWarnings("unchecked")
    private static final Transformer schedulersTransformer = new Transformer<Observable, Observable>() {
        @Override
        public Observable call(final Observable o) {
            return o.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
        }
    };

    @SuppressWarnings("unchecked")
    public static <T> Transformer<T, T> applySchedulers() {
        return (Transformer<T, T>) schedulersTransformer;
    }
}
