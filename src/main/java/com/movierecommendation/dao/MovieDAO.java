
package com.movierecommendation.dao;

import com.movierecommendation.model.Movie;
import com.movierecommendation.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MovieDAO {

    public boolean addMovie(Movie movie) throws SQLException {
        String sql = """
            INSERT INTO movies
            (title, overview, genre, release_year, poster_url, tmdb_id)
            VALUES (?, ?, ?, ?, ?, ?)
            """;

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setString(1, movie.getTitle());
            statement.setString(2, movie.getOverview());
            statement.setString(3, movie.getGenre());
            statement.setInt(4, movie.getReleaseYear());
            statement.setString(5, movie.getPosterUrl());

            if (movie.getTmdbId() > 0) {
                statement.setInt(6, movie.getTmdbId());
            } else {
                statement.setNull(6, java.sql.Types.INTEGER);
            }

            return statement.executeUpdate() > 0;
        }
    }

    public List<Movie> getAllMovies() throws SQLException {
        List<Movie> movies = new ArrayList<>();

        String sql = """
            SELECT movie_id, title, overview, genre,
                   release_year, poster_url, tmdb_id
            FROM movies
            ORDER BY title
            """;

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Movie movie = new Movie();

                movie.setMovieId(resultSet.getInt("movie_id"));
                movie.setTitle(resultSet.getString("title"));
                movie.setOverview(resultSet.getString("overview"));
                movie.setGenre(resultSet.getString("genre"));
                movie.setReleaseYear(resultSet.getInt("release_year"));
                movie.setPosterUrl(resultSet.getString("poster_url"));
                movie.setTmdbId(resultSet.getInt("tmdb_id"));

                movies.add(movie);
            }
        }

        return movies;
    }
}
