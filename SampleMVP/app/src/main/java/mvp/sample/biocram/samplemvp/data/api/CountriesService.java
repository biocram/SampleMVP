package mvp.sample.biocram.samplemvp.data.api;

import java.util.List;

import io.reactivex.Observable;
import mvp.sample.biocram.samplemvp.data.model.Country;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Service interface for our {@link API}
 * <p>
 */
public interface CountriesService {

    // https://restcountries.eu/rest/v2/all?fields=name;nativeName;alpha2Code;capital;flag
    // Get the list of all countries, only retrieve some selected fields for each country
    @GET("rest/v2/all?fields=name;nativeName;alpha2Code;capital;flag")
    Observable<List<Country>> getAllCountries();

    // https://restcountries.eu/rest/v2/alpha/{code}
    // Get only a specific country by alpha code (2 digits) with all the fields
    @GET("rest/v2/alpha/{alpha2Code}")
    Observable<Country> getCountryDetail(@Path("alpha2Code") String alpha2Code);

}
