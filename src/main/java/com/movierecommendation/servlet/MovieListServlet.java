
package com.movierecommendation.servlet;

import com.movierecommendation.dao.MovieDAO;
import com.movierecommendation.model.Movie;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/movies")
public class MovieListServlet extends HttpServlet {

    private final MovieDAO movieDAO = new MovieDAO();

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");

        try {
            List<Movie> movies = movieDAO.getAllMovies();

            response.getWriter().println("""
                <!DOCTYPE html>
                <html lang="en">
                <head>
                    <meta charset="UTF-8">
                    <title>Movies</title>
                    <style>
                        body {
                            background: #10131c;
                            color: white;
                            font-family: Arial, sans-serif;
                            padding: 30px;
                        }
                        .movie {
                            background: #191f2e;
                            padding: 16px;
                            margin: 12px 0;
                            border-radius: 8px;
                        }
                        a { color: #ff4757; }
                    </style>
                </head>
                <body>
                    <h1>Available Movies</h1>
                """);

            if (movies.isEmpty()) {
                response.getWriter().println("<p>No movies found.</p>");
            }

            for (Movie movie : movies) {
                response.getWriter().println("<div class='movie'>");
                response.getWriter().println("<h2>"
                        + escapeHtml(movie.getTitle()) + "</h2>");
                response.getWriter().println("<p>Year: "
                        + movie.getReleaseYear() + "</p>");
                response.getWriter().println("<p>Genre: "
                        + escapeHtml(movie.getGenre()) + "</p>");
                response.getWriter().println("<p>"
                        + escapeHtml(movie.getOverview()) + "</p>");
                response.getWriter().println("</div>");
            }

            response.getWriter().println("""
                    <p><a href="index.html">Back to Home</a></p>
                </body>
                </html>
                """);

        } catch (SQLException e) {
            throw new ServletException("Unable to load movies.", e);
        }
    }

    private String escapeHtml(String value) {
        if (value == null) {
            return "";
        }

        return value.replace("&", "&amp;")
                    .replace("<", "&lt;")
                    .replace(">", "&gt;")
                    .replace("\"", "&quot;")
                    .replace("'", "&#x27;");
    }
}
