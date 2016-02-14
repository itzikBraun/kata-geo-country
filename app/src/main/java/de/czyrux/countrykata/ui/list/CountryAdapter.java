package de.czyrux.countrykata.ui.list;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import android.support.v4.util.Pair;
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

    private Map<String, ImageView> images = new HashMap<>();

    public CountryAdapter(final ImageLoader imageLoader, final CountryListListener listListener) {
        this.imageLoader = imageLoader;
        this.listListener = listListener;
        this.countries = new ArrayList<>(20);
    }

    public void setCountries(final List<Country> countries) {
        if (this.countries.equals(countries)) {
            return;
        }

        this.countries.clear();
        this.countries.addAll(countries);
        this.notifyDataSetChanged();
    }

    @Override
    public CountryViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        return new CountryViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_country_item,
                    parent, false), listListener, images);
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

    public Pair<? extends View, String>[] getPairs() {
        Set<Map.Entry<String, ImageView>> entries = images.entrySet();

        Pair<? extends View, String>[] pairs = new Pair[entries.size()];
        int count = 0;
        for (Map.Entry<String, ImageView> entry : entries) {
            pairs[count] = (Pair.create(entry.getValue(), entry.getKey()));
            count++;
        }

        return pairs;
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
        private final Map<String, ImageView> images;

        CountryViewHolder(final View view, final CountryListListener listListener,
                final Map<String, ImageView> images) {
            super(view);
            this.listListener = listListener;
            ButterKnife.bind(this, view);

            this.images = images;
        }

        public void bind(final Country country, final ImageLoader imageLoader) {
            String imageUrl = CountryImageBuilder.obtainImageUrl(country);
            imageLoader.load(imageUrl, image);

            String countryCode = country.getAlpha2Code();
            ViewCompat.setTransitionName(image, countryCode);

            removeTheOldImageBinding();

            images.put(countryCode, image);

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

        private void removeTheOldImageBinding() {
            if (images.containsValue(image)) {
                Set<Map.Entry<String, ImageView>> entries = images.entrySet();
                for (Map.Entry<String, ImageView> entry : entries) {
                    if (entry.getValue() == image) {
                        images.remove(entry.getKey());
                        break;
                    }
                }
            }
        }
    }
}
