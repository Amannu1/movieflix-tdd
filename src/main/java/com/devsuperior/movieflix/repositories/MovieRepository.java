package com.devsuperior.movieflix.repositories;

import com.devsuperior.movieflix.entities.Genre;
import com.devsuperior.movieflix.entities.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface MovieRepository extends JpaRepository<Movie, Long> {

    Optional<Movie> findById(Long id);

    @Query("SELECT obj FROM Movie obj WHERE (:genre IS NULL OR obj.genre = :genre) AND (:title IS NULL OR obj.title LIKE %:title%) ORDER BY obj.title")
    Page<Movie> find(String title, Genre genre, Pageable pageable);

    @Query("SELECT obj FROM Movie obj JOIN FETCH obj.genre WHERE obj IN :movies")
    List<Movie> findMoviesWithGenres(List<Movie> movies);

    List<Movie> findAll();

    @Query("SELECT obj FROM Movie obj JOIN FETCH obj.reviews WHERE obj.id = :movieId")
    Movie findMovieReviews(Long movieId);

}