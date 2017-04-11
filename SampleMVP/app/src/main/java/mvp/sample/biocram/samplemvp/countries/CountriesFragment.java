package mvp.sample.biocram.samplemvp.countries;

import android.support.v4.app.Fragment;

import java.util.List;

import mvp.sample.biocram.samplemvp.data.Country;

/**
 * Created by biocram on 2017-04-11.
 */

public class CountriesFragment extends Fragment implements CountriesContract.View {

    @Override
    public void setPresenter(CountriesContract.Presenter presenter) {

    }

    @Override
    public void setLoadingIndicator(boolean active) {

    }

    @Override
    public void showCountries(List<Country> countries) {

    }

    @Override
    public void showCountryDetailsUi(String countryId) {

    }

    @Override
    public void showLoadingCountriesError() {

    }

    @Override
    public void showNoCountries() {

    }
}
