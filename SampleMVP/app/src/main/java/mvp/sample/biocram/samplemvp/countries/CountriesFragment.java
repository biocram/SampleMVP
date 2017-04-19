package mvp.sample.biocram.samplemvp.countries;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.common.base.Preconditions;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import mvp.sample.biocram.samplemvp.R;
import mvp.sample.biocram.samplemvp.data.model.Country;

/**
 * Created by biocram on 2017-04-11.
 */

public class CountriesFragment extends Fragment implements CountriesContract.View {

    private static final String TAG = CountriesFragment.class.getSimpleName();

    private CountriesContract.Presenter mPresenter;
    private ICountries mActivity;

    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;

    private CountriesAdapter mAdapter;

    public static CountriesFragment newInstance() {
        return new CountriesFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View root = inflater.inflate(R.layout.countries_fragment_layout, container, false);
        ButterKnife.bind(this, root);
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Preconditions.checkArgument(getActivity() instanceof ICountries, "Activity must implement ICountries interface");
        mActivity = (ICountries) getActivity();

        initRecyclerView();
    }

    private void initRecyclerView() {
        mAdapter = new CountriesAdapter();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(mAdapter);
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
        mAdapter.setCountries(countries);
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
