package mvp.sample.biocram.samplemvp.countries;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.bumptech.glide.GenericRequestBuilder;
import com.google.common.base.Preconditions;

import java.util.ArrayList;
import java.util.List;

import mvp.sample.biocram.samplemvp.data.model.Country;
import mvp.sample.biocram.samplemvp.utils.svgutil.SvgRequestBuilder;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by biocram on 2017-04-19.
 */

class CountriesAdapter extends RecyclerView.Adapter<CountriesAdapter.CountryViewHolder> {

    interface OnCountryClickListener {
        void onCountryClick(Country country);
    }

    private OnCountryClickListener mListener;
    private List<Country> mCountries = new ArrayList<>();

    private final GenericRequestBuilder mSvgRequestBuilder;

    CountriesAdapter(@NonNull Context context, @NonNull OnCountryClickListener listener) {
        mListener = Preconditions.checkNotNull(listener, "OnCountryClickListener can't be null!");
        mSvgRequestBuilder = SvgRequestBuilder.getSVGRequestBuilder(context);
    }

    static class CountryViewHolder extends RecyclerView.ViewHolder {

        private CountryListItemView countryView;

        CountryViewHolder(CountryListItemView itemView) {
            super(itemView);
            countryView = itemView;
        }
    }

    public void setCountries(@NonNull List<Country> countries) {
        checkNotNull(countries, "Countries can't be null");
        mCountries.clear();
        mCountries.addAll(countries);
        notifyDataSetChanged();
    }

    @Override
    public CountryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final CountryListItemView view = new CountryListItemView(parent.getContext());
        view.setSvgRequestBuilder(mSvgRequestBuilder);
        return new CountryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CountryViewHolder holder, int position) {
        final Country country = mCountries.get(position);
        holder.countryView.setCountry(country);
        holder.countryView.setOnClickListener(v -> mListener.onCountryClick(country));
    }

    @Override
    public int getItemCount() {
        return mCountries.size();
    }


}
