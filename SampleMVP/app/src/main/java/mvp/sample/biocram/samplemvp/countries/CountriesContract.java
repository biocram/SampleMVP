/*
 * Copyright 2016, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package mvp.sample.biocram.samplemvp.countries;

import android.support.annotation.NonNull;

import java.util.List;

import mvp.sample.biocram.samplemvp.BasePresenter;
import mvp.sample.biocram.samplemvp.BaseView;
import mvp.sample.biocram.samplemvp.data.model.Country;

/**
 * This specifies the contract between the view and the presenter.
 */
public interface CountriesContract {

    interface View extends BaseView<Presenter> {

        void setLoadingIndicator(boolean active);

        void showCountries(List<Country> countries);

        void showCountryDetailsUi(String countryId);

        void showLoadingCountriesError();

        void showNoCountries();

        boolean isActive();
    }

    interface Presenter extends BasePresenter {

        void loadCountries(boolean forceUpdate);

        void openCountryDetails(@NonNull Country requestedCountry);
    }
}
