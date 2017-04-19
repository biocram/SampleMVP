package mvp.sample.biocram.samplemvp.countries;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.common.base.Preconditions;

import java.util.List;

import mvp.sample.biocram.samplemvp.R;
import mvp.sample.biocram.samplemvp.data.model.Country;

/**
 * Created by biocram on 2017-04-11.
 */

public class CountriesFragment extends Fragment implements CountriesContract.View {

    private static final String TAG = CountriesFragment.class.getSimpleName();

    private CountriesContract.Presenter mPresenter;
    private ICountries mActivity;

    public static CountriesFragment newInstance() {
        return new CountriesFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.countries_fragment_layout, container, false);
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Preconditions.checkArgument(getActivity() instanceof ICountries, "Activity must implement ICountries interface");
        mActivity = (ICountries) getActivity();
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.subscribe();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.unsubscribe();
    }

    @Override
    public void setPresenter(CountriesContract.Presenter presenter) {
        mPresenter = Preconditions.checkNotNull(presenter, "countriesPresenter can't be null");
    }

    @Override
    public void setLoadingIndicator(boolean active) {

    }

    @Override
    public void showCountries(List<Country> countries) {
        Log.d(TAG,"received countries from presenter");
    }

    @Override
    public void showCountryDetailsUi(String countryId) {
        mActivity.showCountryDetail(countryId);
    }

    @Override
    public void showLoadingCountriesError() {

    }

    @Override
    public void showNoCountries() {

    }

    @Override
    public boolean isActive() {
        return isAdded();
    }
}
