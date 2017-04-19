package mvp.sample.biocram.samplemvp.countrydetail;

import android.support.v4.app.Fragment;

import mvp.sample.biocram.samplemvp.data.model.Country;

/**
 * Created by biocram on 2017-04-11.
 */

public class CountryDetailFragment extends Fragment implements CountryDetailContract.View {
    @Override
    public void setPresenter(CountryDetailContract.Presenter presenter) {

    }

    @Override
    public void setLoadingIndicator(boolean active) {

    }

    @Override
    public void showCountry(Country country) {

    }

    @Override
    public void showLoadingCountryError() {

    }

    @Override
    public void showNoCountry() {

    }
}
