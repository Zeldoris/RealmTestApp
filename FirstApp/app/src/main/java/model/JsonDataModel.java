package model;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmList;

/**
 * Created by DELL on 12/03/2018.
 */

public class JsonDataModel {
    @SerializedName("status")
    private int status;
    @SerializedName("message")
    private String message;
    @SerializedName("data")
    private RealmList<Movie> movies;

    public JsonDataModel() {
    }

    public JsonDataModel(int status, String message, RealmList<Movie> movies) {
        this.status = status;
        this.message = message;
        this.movies = movies;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public RealmList<Movie> getMovies() {
        return movies;
    }

    public void setMovies(RealmList<Movie> movies) {
        this.movies = movies;
    }
}
