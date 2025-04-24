package com.devsuperior.movieflix.controllers;

import com.devsuperior.movieflix.dto.MovieDetailsDTO;
import com.devsuperior.movieflix.services.MovieService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value ="/movies")
public class MovieController {

    @Autowired
    private MovieService service;

    @GetMapping
    public ResponseEntity<Page<MovieDetailsDTO>> findAllPaged(
            @RequestParam(value = "genreId", defaultValue = "0") Long genreId,
            Pageable pageable){
        Page<MovieDetailsDTO> list = service.findAllPaged(genreId, pageable);
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<MovieDetailsDTO> findById(@PathVariable Long id){
        MovieDetailsDTO dto = service.findById(id);
        return ResponseEntity.ok().body(dto);

    }
}
