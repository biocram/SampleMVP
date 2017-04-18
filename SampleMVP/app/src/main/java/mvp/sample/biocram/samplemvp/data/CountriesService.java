package mvp.sample.biocram.samplemvp.data;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Service interface for our {@link API}
 * <p>
 */
public interface CountriesService {

    // https://restcountries.eu/rest/v2/all?fields=name;nativeName;alpha2Code;capital;flag
    // Get the list of all countries, only retrieve some selected fields
    @GET("rest/v2/all?fields=name;nativeName;alpha2Code;capital;flag")
    Observable<List<Country>> getAllCountries();

    // https://restcountries.eu/rest/v2/alpha/{code}
    // Get only a specific country by alpha code (2 digits) with all the fields
    @GET("rest/v2/alpha/{alpha2Code}")
    Observable<Country> getCountryDetail(@Path("alpha2Code") String alpha2Code);

}
