
package com.movierecommendation.servlet;

import com.movierecommendation.dao.MovieDAO;
import com.movierecommendation.model.Movie;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/movies")
public class MovieListServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private MovieDAO movieDAO;

    @Override
    public void init() throws ServletException {
        movieDAO = new MovieDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        String search = request.getParameter("search");
        String genre = request.getParameter("genre");

        try {
            List<Movie> movies =
                    movieDAO.searchMovies(search, genre);

            PrintWriter out = response.getWriter();

            out.println("<!DOCTYPE html>");
            out.println("<html lang='en'>");
            out.println("<head>");
            out.println("<meta charset='UTF-8'>");
            out.println("<meta name='viewport' content='width=device-width, initial-scale=1.0'>");
            out.println("<title>CineMatch - Movies</title>");

            out.println("<style>");
            out.println("body{font-family:Arial,sans-serif;background:#10121b;color:#f5f5f5;margin:0;padding:30px;}");
            out.println(".container{max-width:1100px;margin:auto;}");
            out.println("h1{color:#a78bfa;}");
            out.println("a{color:#c4b5fd;text-decoration:none;}");
            out.println(".search-form{display:flex;gap:10px;flex-wrap:wrap;margin:25px 0;}");
            out.println("input,select,button{padding:12px;border-radius:7px;border:1px solid #444;background:#202332;color:white;}");
            out.println("button{background:#7c3aed;border:none;cursor:pointer;}");
            out.println(".grid{display:grid;grid-template-columns:repeat(auto-fit,minmax(240px,1fr));gap:20px;}");
            out.println(".card{background:#1c2030;padding:20px;border-radius:12px;overflow-wrap:anywhere;}");
            out.println(".card img{width:100%;max-height:320px;object-fit:cover;border-radius:8px;}");
            out.println(".muted{color:#b6bac9;}");
            out.println("</style>");

            out.println("</head><body>");
            out.println("<div class='container'>");

            out.println("<a href='index.html'>&larr; Back to CineMatch Home</a>");
            out.println("<h1>Explore Movies</h1>");
            out.println("<p class='muted'>Search your movie collection or filter by genre.</p>");

            out.println("<form class='search-form' method='get' action='movies'>");

            out.println("<input type='text' name='search' placeholder='Search movie title...' value='"
                    + escapeHtml(search == null ? "" : search) + "'>");

            out.println("<select name='genre'>");
            out.println("<option value=''>All Genres</option>");

            String[] genres = {
                "Action", "Science Fiction", "Comedy",
                "Drama", "Horror", "Romance", "Adventure",
                "Animation", "Thriller", "Fantasy"
            };

            for (String item : genres) {
                out.println("<option value='" + escapeHtml(item) + "' "
                        + (item.equalsIgnoreCase(genre == null ? "" : genre)
                        ? "selected" : "")
                        + ">" + escapeHtml(item) + "</option>");
            }

            out.println("</select>");
            out.println("<button type='submit'>Search</button>");
            out.println("<a href='movies'>Show All</a>");
            out.println("</form>");

            out.println("<h2>Results (" + movies.size() + ")</h2>");

            if (movies.isEmpty()) {
                out.println("<p>No movies found. Try another title or genre.</p>");
            } else {
                out.println("<div class='grid'>");

                for (Movie movie : movies) {
                    out.println("<div class='card'>");

                    String posterUrl = movie.getPosterUrl();

                    if (posterUrl != null && !posterUrl.trim().isEmpty()) {
                        out.println("<img src='" + escapeHtml(posterUrl)
                                + "' alt='Movie poster' loading='lazy'>");
                    }

                    out.println("<h3>" + escapeHtml(movie.getTitle()) + "</h3>");

                    out.println("<p class='muted'>Year: "
                            + movie.getReleaseYear() + "</p>");

                    out.println("<p><strong>Genre:</strong> "
                            + escapeHtml(movie.getGenre()) + "</p>");

                    out.println("<p>"
                            + escapeHtml(movie.getOverview()) + "</p>");

                    out.println("</div>");
                }

                out.println("</div>");
            }

            out.println("</div></body></html>");

        } catch (SQLException e) {
            throw new ServletException("Unable to load movies.", e);
        }
    }

    private String escapeHtml(String value) {
        if (value == null) {
            return "";
        }

        return value
                .replace("&", "&amp;")
                .replace("<", "&lt;")
                .replace(">", "&gt;")
                .replace("\"", "&quot;")
                .replace("'", "&#39;");
    }
}
