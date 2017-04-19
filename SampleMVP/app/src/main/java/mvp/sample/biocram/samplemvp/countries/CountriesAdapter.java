package mvp.sample.biocram.samplemvp.countries;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.google.common.base.Preconditions;
import com.google.common.collect.Collections2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import mvp.sample.biocram.samplemvp.data.model.Country;

/**
 * Created by biocram on 2017-04-19.
 */

class CountriesAdapter extends RecyclerView.Adapter<CountriesAdapter.CountryViewHolder> {

    private List<Country> mCountries = new ArrayList<>();

    static class CountryViewHolder extends RecyclerView.ViewHolder {

        private CountryListItemView countryView;

        CountryViewHolder(CountryListItemView itemView) {
            super(itemView);
            countryView = itemView;
        }
    }

    public void setCountries(@NonNull List<Country> countries) {
        Preconditions.checkNotNull(countries, "Countries can't be null");
        mCountries.clear();
        mCountries.addAll(countries);
        notifyDataSetChanged();
    }

    @Override
    public CountryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final CountryListItemView view = new CountryListItemView(parent.getContext());
        return new CountryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CountryViewHolder holder, int position) {
        final Country country = mCountries.get(position);
        holder.countryView.setCountry(country);
    }

    @Override
    public int getItemCount() {
        return mCountries.size();
    }


}
