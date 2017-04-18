package mvp.sample.biocram.samplemvp.countries;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.common.base.Preconditions;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import mvp.sample.biocram.samplemvp.data.API;
import mvp.sample.biocram.samplemvp.data.Country;

/**
 * Created by biocram on 2017-04-11.
 */

class CountriesPresenter implements CountriesContract.Presenter {

    private final static String TAG = CountriesPresenter.class.getSimpleName();

    private final CountriesContract.View mView;

    private Disposable mDisposable;

    CountriesPresenter(@NonNull CountriesContract.View view) {
        mView = Preconditions.checkNotNull(view, "countriesView can't be null!");

        mView.setPresenter(this);
    }

    @Override
    public void subscribe() {
        loadCountries(true);
    }

    @Override
    public void loadCountries(boolean forceUpdate) {

        mView.setLoadingIndicator(true);

        if (forceUpdate) {
            // clear previous subscription
            if (mDisposable != null && !mDisposable.isDisposed())
                mDisposable.dispose();

            Disposable disposable = API.getService().getAllCountries()
                    .observeOn(Schedulers.io()).subscribeOn(AndroidSchedulers.mainThread())
                    .subscribeWith(new DisposableObserver<List<Country>>() {
                        @Override
                        public void onNext(List<Country> countries) {
                            // The view may not be able to handle UI updates anymore
                            if (!mView.isActive()) {
                                Log.d(TAG, "View is not active, don't send data to it");
                                return;
                            }

                            processCountries(countries);
                        }

                        @Override
                        public void onError(Throwable e) {
                            Log.e(TAG, "Error retrieving list of countries", e);
                            mView.setLoadingIndicator(false);
                        }

                        @Override
                        public void onComplete() {
                            mView.setLoadingIndicator(false);
                        }
                    });

            mDisposable = disposable;
        }
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

    @Override
    public void unsubscribe() {
        if (mDisposable != null && !mDisposable.isDisposed())
            mDisposable.dispose();
    }
}
