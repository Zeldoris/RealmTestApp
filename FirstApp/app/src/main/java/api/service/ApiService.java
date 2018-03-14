package api.service;

import io.realm.RealmList;
import model.JsonDataModel;
import model.Movie;
import retrofit2.Call;
import retrofit2.http.POST;

/**
 * Created by DELL on 11/03/2018.
 */

public interface ApiService {
    @POST("index.php")
    Call<JsonDataModel> getMovies();
}
