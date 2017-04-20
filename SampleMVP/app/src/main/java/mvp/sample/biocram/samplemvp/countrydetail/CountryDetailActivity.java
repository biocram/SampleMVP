package mvp.sample.biocram.samplemvp.countrydetail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;

import com.google.common.base.Preconditions;

import mvp.sample.biocram.samplemvp.R;
import mvp.sample.biocram.samplemvp.utils.ActivityUtils;

/**
 * Created by biocram on 2017-04-11.
 */

public class CountryDetailActivity extends AppCompatActivity {

    private static final String INTENT_COUNTRY_ID = "intent_country_id";

    public static Intent createIntent(Context context, String countryID) {
        final Intent intent = new Intent(context, CountryDetailActivity.class);
        intent.putExtra(INTENT_COUNTRY_ID, countryID);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.country_detail_layout);

        final String countryId = getIntent().getStringExtra(INTENT_COUNTRY_ID);
        Preconditions.checkArgument(TextUtils.isEmpty(countryId), "CountryId can't be null!");

        CountryDetailContract.View view = setUpView();
        setUpPresenter(view, countryId);
    }

    private CountryDetailContract.View setUpView() {
        CountryDetailFragment countryDetailFragment =
                (CountryDetailFragment) getSupportFragmentManager().findFragmentById(R.id.contentFrame);
        if (countryDetailFragment == null) {
            // Create the fragment
            countryDetailFragment = CountryDetailFragment.newInstance();
            ActivityUtils.addFragmentToActivity(
                    getSupportFragmentManager(), countryDetailFragment, R.id.contentFrame);
        }

        return countryDetailFragment;
    }

    private void setUpPresenter(CountryDetailContract.View view, String countryId) {
        new CountryDetailPresenter(view, countryId);
    }
}
