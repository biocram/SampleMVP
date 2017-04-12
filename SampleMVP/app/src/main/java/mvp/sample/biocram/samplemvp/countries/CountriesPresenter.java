package mvp.sample.biocram.samplemvp.countries;

import android.support.annotation.NonNull;

import com.google.common.base.Preconditions;

import java.util.ArrayList;
import java.util.List;

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
        loadCountries(true);
    }

    @Override
    public void loadCountries(boolean forceUpdate) {

        mView.setLoadingIndicator(true);

        if (forceUpdate) {
            // refresh countriesToShow
        }

        // fake data
        List<Country> countriesToShow = new ArrayList<>();

        // The view may not be able to handle UI updates anymore
        if (!mView.isActive()) {
            return;
        }
        mView.setLoadingIndicator(false);

        processCountries(countriesToShow);

    }

    private void processCountries(List<Country> countries) {
        if (countries.isEmpty()) {
            // Show a message indicating there are no countries.
            mView.showNoCountries();
        } else {
            // Show the list of countries
            mView.showCountries(countries);
        }
    }

    @Override
    public void openCountryDetails(@NonNull Country requestedCountry) {
        Preconditions.checkNotNull(requestedCountry, "countryDetail can't be null!");
        mView.showCountryDetailsUi(requestedCountry.getId());
    }
}
