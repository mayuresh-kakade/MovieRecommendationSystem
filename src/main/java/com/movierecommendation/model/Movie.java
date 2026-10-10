
package com.movierecommendation.model;

public class Movie {

    private int movieId;
    private String title;
    private String overview;
    private String genre;
    private int releaseYear;
    private String posterUrl;
    private int tmdbId;

    public Movie() {
    }

    public Movie(String title, String overview, String genre,
                 int releaseYear, String posterUrl, int tmdbId) {
        this.title = title;
        this.overview = overview;
        this.genre = genre;
        this.releaseYear = releaseYear;
        this.posterUrl = posterUrl;
        this.tmdbId = tmdbId;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public String getPosterUrl() {
        return posterUrl;
    }

    public void setPosterUrl(String posterUrl) {
        this.posterUrl = posterUrl;
    }

    public int getTmdbId() {
        return tmdbId;
    }

    public void setTmdbId(int tmdbId) {
        this.tmdbId = tmdbId;
    }
}
