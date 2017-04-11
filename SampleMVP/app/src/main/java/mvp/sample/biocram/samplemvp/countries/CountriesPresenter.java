package mvp.sample.biocram.samplemvp.countries;

import android.support.annotation.NonNull;

import com.google.common.base.Preconditions;

import mvp.sample.biocram.samplemvp.data.Country;

/**
 * Created by biocram on 2017-04-11.
 */

public class CountriesPresenter implements CountriesContract.Presenter {

    private final CountriesContract.View mView;

    public CountriesPresenter(@NonNull CountriesContract.View view) {
        mView = Preconditions.checkNotNull(view, "countriesView can't be null!");

        mView.setPresenter(this);
    }

    @Override
    public void start() {

    }

    @Override
    public void loadCountries(boolean forceUpdate) {

    }

    @Override
    public void openCountryDetails(@NonNull Country requestedCountry) {

    }
}
