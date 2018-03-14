package model;

import android.net.Uri;

import io.realm.RealmObject;

/**
 * Created by DELL on 11/03/2018.
 */

public class Movie extends RealmObject {
    private int id;
    private String nom;
    private String realisateur;
    private String urlImage;
    private String dateSortie;
    private String dateEnregistrement;

    public Movie(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getRealisateur() {
        return realisateur;
    }

    public void setRealisateur(String realisateur) {
        this.realisateur = realisateur;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    public String getDateSortie() {
        return dateSortie;
    }

    public void setDateSortie(String dateSortie) {
        this.dateSortie = dateSortie;
    }

    public String getDateEnregistrement() {
        return dateEnregistrement;
    }

    public void setDateEnregistrement(String dateEnregistrement) {
        this.dateEnregistrement = dateEnregistrement;
    }

    public void setMovie(Movie movie) {
        setId(movie.getId());
        setNom(movie.getNom());
        setRealisateur(movie.getRealisateur());
        setUrlImage(movie.getUrlImage());
        setDateSortie(movie.getDateSortie());
        setDateEnregistrement(movie.getDateEnregistrement());
    }
}
