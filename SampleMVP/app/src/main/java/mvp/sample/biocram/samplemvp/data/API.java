package mvp.sample.biocram.samplemvp.data;

import mvp.sample.biocram.samplemvp.BuildConfig;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static okhttp3.logging.HttpLoggingInterceptor.Level;

/**
 * Application API implementation
 * <p>
 * Created by biocram on 2017-04-12.
 */

public final class API {

    private static final CountriesService service;

    static {

        // initialize logging
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(BuildConfig.DEBUG ? Level.BODY : Level.NONE);

        // initialize OkHttp
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(logging)
                .build();

        // create the rest adapter
        Retrofit restAdapter = new Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        service = restAdapter.create(CountriesService.class);
    }

    private API() {
    }

    public static CountriesService getService() {
        return service;
    }

}
