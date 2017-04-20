package mvp.sample.biocram.samplemvp.countries;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import mvp.sample.biocram.samplemvp.R;
import mvp.sample.biocram.samplemvp.countrydetail.CountryDetailActivity;
import mvp.sample.biocram.samplemvp.utils.ActivityUtils;

/**
 * Created by biocram on 2017-04-11.
 */

public class CountriesActivity extends AppCompatActivity implements ICountries {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.countries_layout);

        CountriesContract.View view = setUpView();
        setUpPresenter(view);
    }

    private CountriesContract.View setUpView() {
        CountriesFragment countriesFragment =
                (CountriesFragment) getSupportFragmentManager().findFragmentById(R.id.contentFrame);
        if (countriesFragment == null) {
            // Create the fragment
            countriesFragment = CountriesFragment.newInstance();
            ActivityUtils.addFragmentToActivity(
                    getSupportFragmentManager(), countriesFragment, R.id.contentFrame);
        }

        return countriesFragment;
    }

    private void setUpPresenter(CountriesContract.View view) {
        new CountriesPresenter(view);
    }

    @Override
    public void showCountryDetail(String countryID) {
        Intent intent = CountryDetailActivity.createIntent(this, countryID);
        startActivity(intent);
    }
}
