package de.czyrux.countrykata.ui.list;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import android.support.v4.view.ViewCompat;

import android.support.v7.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageView;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;

import de.czyrux.countrykata.R;
import de.czyrux.countrykata.core.domain.country.Country;
import de.czyrux.countrykata.core.domain.country.CountryImageBuilder;
import de.czyrux.countrykata.core.domain.image.ImageLoader;

public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.CountryViewHolder> {

    private final ImageLoader imageLoader;
    private final List<Country> countries;
    private final CountryListListener listListener;

    public CountryAdapter(final ImageLoader imageLoader, final CountryListListener listListener) {
        this.imageLoader = imageLoader;
        this.listListener = listListener;
        this.countries = new ArrayList<>(20);
    }

    public void setCountries(final List<Country> countries) {
        this.countries.clear();
        this.countries.addAll(countries);
        this.notifyDataSetChanged();
    }

    @Override
    public CountryViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        return new CountryViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_country_item,
                    parent, false), listListener);
    }

    @Override
    public void onBindViewHolder(final CountryViewHolder holder, final int position) {
        Country country = countries.get(position);
        holder.bind(country, imageLoader);
    }

    @Override
    public int getItemCount() {
        return countries.size();
    }

    public static class CountryViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.country_item_image)
        ImageView image;
        @Bind(R.id.country_item_name)
        TextView name;
        @Bind(R.id.country_item_population)
        TextView population;
        @Bind(R.id.country_item_region)
        TextView region;

        private final CountryListListener listListener;

        CountryViewHolder(final View view, final CountryListListener listListener) {
            super(view);
            this.listListener = listListener;
            ButterKnife.bind(this, view);

        }

        public void bind(final Country country, final ImageLoader imageLoader) {
            String imageUrl = CountryImageBuilder.obtainImageUrl(country);
            imageLoader.load(imageUrl, image);
            ViewCompat.setTransitionName(image, country.getAlpha2Code());
            name.setText(country.getName());
            population.setText(String.format(Locale.GERMAN, "%,d", country.getPopulation()));
            region.setText(country.getSubregion());
            itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(final View v) {
                        listListener.onCountryClicked(country, getAdapterPosition(), image);
                    }
                });
        }
    }
}
