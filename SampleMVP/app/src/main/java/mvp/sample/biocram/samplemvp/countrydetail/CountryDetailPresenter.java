package mvp.sample.biocram.samplemvp.countrydetail;

import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;

import com.google.common.base.Preconditions;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import mvp.sample.biocram.samplemvp.data.api.API;
import mvp.sample.biocram.samplemvp.data.model.Country;

/**
 * Created by biocram on 2017-04-11.
 */

public class CountryDetailPresenter implements CountryDetailContract.Presenter {

    private final static String TAG = CountryDetailPresenter.class.getSimpleName();

    private final CountryDetailContract.View mView;
    private final String mCountryId;

    private Disposable mDisposable;

    CountryDetailPresenter(@NonNull CountryDetailContract.View view, @NonNull String countryId) {
        Preconditions.checkNotNull(view, "countryDetailView can't be null!");
        Preconditions.checkArgument(!TextUtils.isEmpty(countryId), "CountryId can't be null or empty!");
        mView = view;
        mView.setPresenter(this);
        mCountryId = countryId;
    }

    @Override
    public void subscribe() {
        loadCountry();
    }

    @Override
    public void unsubscribe() {
        if (mDisposable != null && !mDisposable.isDisposed())
            mDisposable.dispose();
    }

    @Override
    public void loadCountry() {
        mView.setLoadingIndicator(true);

        // clear previous subscription
        if (mDisposable != null && !mDisposable.isDisposed())
            mDisposable.dispose();

        mDisposable = API.getService().getCountryDetail(mCountryId)
                .observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io())
                .subscribeWith(new DisposableObserver<Country>() {
                    @Override
                    public void onNext(Country response) {
                        // The view may not be able to handle UI updates anymore
                        if (!mView.isActive()) {
                            Log.d(TAG, "View is not active, don't send data to it");
                            return;
                        }

                        processCountry(response);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "Error retrieving list of countries", e);
                        mView.setLoadingIndicator(false);
                        mView.setLoadingCountryDetailError();
                    }

                    @Override
                    public void onComplete() {
                        mView.setLoadingIndicator(false);
                    }
                });
    }

    private void processCountry(Country country) {
        if (country == null) {
            // Show a message indicating there are no details for country.
            mView.showNoDetailForCountry();
        } else {
            // Show the list of countries
            mView.showCountry(country);
        }
    }
}
