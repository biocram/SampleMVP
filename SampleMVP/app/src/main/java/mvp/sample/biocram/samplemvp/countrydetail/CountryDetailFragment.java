package mvp.sample.biocram.samplemvp.countrydetail;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.GenericRequestBuilder;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.common.base.Preconditions;

import butterknife.BindView;
import butterknife.ButterKnife;
import mvp.sample.biocram.samplemvp.R;
import mvp.sample.biocram.samplemvp.data.model.Country;
import mvp.sample.biocram.samplemvp.utils.svgutil.SvgRequestBuilder;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by biocram on 2017-04-11.
 */

public class CountryDetailFragment extends Fragment implements CountryDetailContract.View {

    private static final String TAG = CountryDetailFragment.class.getSimpleName();

    private static final String SAVE_STATE_COUNTRY = "save_state_country";

    private CountryDetailContract.Presenter mPresenter;

    @BindView(R.id.progressbar)
    ProgressBar mProgressBar;
    @BindView(R.id.container_content)
    ViewGroup mContainerContent;
    @BindView(R.id.imageview_flag)
    ImageView mImageViewFlag;
    @BindView(R.id.textview_name)
    TextView mTextViewName;
    @BindView(R.id.textview_alt_spellings)
    TextView mTextViewAltSpellings;
    @BindView(R.id.textview_calling_codes)
    TextView mTextViewCallingCodes;
    @BindView(R.id.textview_toplevel_domain)
    TextView mTextViewTopLevelDomain;

    private GenericRequestBuilder mSvgReqBuilder;

    private Country mCountry;


    public static CountryDetailFragment newInstance() {
        return new CountryDetailFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSvgReqBuilder = SvgRequestBuilder.getSVGRequestBuilder(getContext());

        if (savedInstanceState != null) {
            if (savedInstanceState.containsKey(SAVE_STATE_COUNTRY))
                mCountry = (Country) savedInstanceState.getSerializable(SAVE_STATE_COUNTRY);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (mCountry != null)
            outState.putSerializable(SAVE_STATE_COUNTRY, mCountry);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View root = inflater.inflate(R.layout.country_detail_fragment_layout, container, false);
        ButterKnife.bind(this, root);
        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mCountry == null) mPresenter.subscribe();
        else {
            setLoadingIndicator(false);
            showCountry(mCountry);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.unsubscribe();
    }

    @Override
    public void setPresenter(CountryDetailContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter, "countryDetailPresenter can't be null");
    }

    @Override
    public void setLoadingIndicator(boolean active) {
        mProgressBar.setVisibility(active ? View.VISIBLE : View.GONE);
        mContainerContent.setVisibility(mProgressBar.getVisibility() == View.VISIBLE ? View.GONE : View.VISIBLE);
    }

    @Override
    public void showCountry(Country country) {
        Log.d(TAG, "Received country detail data");

        mCountry = Preconditions.checkNotNull(country, "Country can't be null!");

        mTextViewName.setText(country.name);
        mTextViewTopLevelDomain.setText(String.format(getContext().getString(R.string.top_level_domain), country.topLevelDomain));
        mTextViewAltSpellings.setText(String.format(getContext().getString(R.string.alternative_spellings), country.altSpellings));
        mTextViewCallingCodes.setText(String.format(getContext().getString(R.string.calling_codes), country.callingCodes));
        mSvgReqBuilder
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                // SVG cannot be serialized so it's not worth to cache it
                .load(Uri.parse(country.flag))
                .into(mImageViewFlag);

    }

    @Override
    public boolean isActive() {
        return isAdded();
    }

    @Override
    public void showNoDetailForCountry() {
        Toast.makeText(getContext(), "Sorry, there are no details for the selected country.", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setLoadingCountryDetailError() {
        Toast.makeText(getContext(), "Sorry, something went wrong...", Toast.LENGTH_SHORT).show();
    }

}
