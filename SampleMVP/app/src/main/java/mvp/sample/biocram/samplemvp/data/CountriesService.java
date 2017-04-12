package mvp.sample.biocram.samplemvp.data;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Service interface for our {@link API}
 * <p>
 */
interface CountriesService {

    // https://restcountries.eu/rest/v2/{service}?fields={field};{field};{field}
    // Get the list of all countries, only retrieve some selected fields
    @GET("rest/v2/all?fields={name};{nativeName};{alpha2Code};{capital};{flag}")
    Call<List<Country>> getAllCountries(@Path("name") String name,
                                        @Path("nativeName") String nativeName,
                                        @Path("alpha2Code") String alpha2Code,
                                        @Path("capital") String capital,
                                        @Query("flag") String flag);

    // https://restcountries.eu/rest/v2/alpha/{code}
    // Get only a specific country by alpha code (2 digits) with all the fields
    @GET("rest/v2/alpha/{alpha2Code}")
    Call<Country> getCountryDetail(@Path("alpha2Code") String alpha2Code);

}
