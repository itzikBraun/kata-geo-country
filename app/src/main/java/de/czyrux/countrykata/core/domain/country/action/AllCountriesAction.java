package de.czyrux.countrykata.core.domain.country.action;

import java.util.List;

import de.czyrux.countrykata.core.domain.BaseAction;
import de.czyrux.countrykata.core.domain.country.Country;
import de.czyrux.countrykata.core.domain.country.CountryService;
import de.czyrux.countrykata.core.domain.country.args.AllCountriesArgs;

import rx.Observable;

import rx.functions.Func0;

public class AllCountriesAction implements BaseAction<Observable<List<Country>>, AllCountriesArgs> {

    private final CountryService countryService;

    public AllCountriesAction(final CountryService countryService) {
        this.countryService = countryService;
    }

    @Override
    public Observable<List<Country>> execute(final AllCountriesArgs args) {
        return Observable.defer(new Func0<Observable<List<Country>>>() {
                    @Override
                    public Observable<List<Country>> call() {
                        return Observable.just(countryService.getAllCountries());
                    }
                });
    }
}
