
CREATE DATABASE IF NOT EXISTS movie_recommendation;

USE movie_recommendation;

CREATE TABLE IF NOT EXISTS movies (
    movie_id INT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(200) NOT NULL,
    overview TEXT,
    genre VARCHAR(200),
    release_year INT,
    poster_url VARCHAR(500),
    tmdb_id INT UNIQUE
);

CREATE TABLE IF NOT EXISTS users (
    user_id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(100) NOT NULL UNIQUE,
    email VARCHAR(150) NOT NULL UNIQUE,
    password_hash VARCHAR(255) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS ratings (
    rating_id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT NOT NULL,
    movie_id INT NOT NULL,
    rating DECIMAL(2,1) NOT NULL,
    rated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_rating_user
        FOREIGN KEY (user_id) REFERENCES users(user_id),
    CONSTRAINT fk_rating_movie
        FOREIGN KEY (movie_id) REFERENCES movies(movie_id),
    CONSTRAINT chk_rating_value
        CHECK (rating >= 0.5 AND rating <= 5.0),
    CONSTRAINT uq_user_movie
        UNIQUE (user_id, movie_id)
);
