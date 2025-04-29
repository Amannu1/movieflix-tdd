package com.devsuperior.movieflix.services;

import com.devsuperior.movieflix.dto.ReviewDTO;
import com.devsuperior.movieflix.entities.Movie;
import com.devsuperior.movieflix.entities.Review;
import com.devsuperior.movieflix.entities.User;
import com.devsuperior.movieflix.repositories.MovieRepository;
import com.devsuperior.movieflix.repositories.ReviewRepository;
import com.devsuperior.movieflix.repositories.UserRepository;
import com.devsuperior.movieflix.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository repository;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private AuthService authService;

    @Transactional(readOnly = true)
    public ReviewDTO insert(ReviewDTO dto){
        Review entity = new Review();
        entity.setText(dto.getText());
        Movie movie = movieRepository.findById(dto.getMovieId())
                .orElseThrow(() -> new ResourceNotFoundException("Movie not found"));
        entity.setMovie(movie);

        User user = authService.authenticated();
        entity.setUser(user);

        entity = repository.save(entity);

        ReviewDTO result = new ReviewDTO();
        result.setId(entity.getId());
        result.setText(entity.getText());
        result.setMovieId(entity.getMovie().getId());
        result.setUserId(user.getId());
        result.setUserName(user.getName());
        result.setUserEmail(user.getEmail());

        return result;
    }
}
