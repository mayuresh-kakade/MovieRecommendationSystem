
package com.movierecommendation;

import com.movierecommendation.dao.MovieDAO;
import com.movierecommendation.model.Movie;

import java.sql.SQLException;
import java.util.List;

public class MovieDAOTest {

    public static void main(String[] args) {
        MovieDAO movieDAO = new MovieDAO();

        try {
            Movie movie = new Movie(
                "Interstellar",
                "A team travels through a wormhole in space.",
                "Sci-Fi",
                2014,
                "",
                157336
            );

            boolean added = movieDAO.addMovie(movie);
            System.out.println("Movie added: " + added);

            List<Movie> movies = movieDAO.getAllMovies();

            for (Movie item : movies) {
                System.out.println(
                    item.getTitle() + " (" + item.getReleaseYear() + ")"
                );
            }

        } catch (SQLException e) {
            System.out.println("Database operation failed:");
            e.printStackTrace();
        }
    }
}
