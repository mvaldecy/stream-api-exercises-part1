package com.example.exercises;

import java.util.Collection;
import java.util.List;

import com.example.domain.Director;
import com.example.domain.Movie;
import com.example.service.InMemoryMovieService;
import com.example.service.MovieService;

// Find the number of movies of each director

public class Exercise1 {
    private static final MovieService movieService =  InMemoryMovieService.getInstance();
    public record DirectorDto(String name, int id) {}
    public record MovieDto(String title, int id, List<Director> directors) {};
    public record DirectoMovieDto(String name, Long count) {};

    public static void main(String[] args) {
        Collection<Movie> movies = movieService.findAllMovies();
        Collection<Director> directors = movieService.findAllDirectors();
        List<Exercise1.MovieDto> movieDtoList = movies.stream().map((movie) -> new MovieDto(movie.getTitle(), movie.getId(), movie.getDirectors())).toList();
        List<Exercise1.DirectoMovieDto> response = directors.stream().map((i) -> new DirectoMovieDto(i.getName(), 
        movies.stream().filter((j) -> j.getDirectors().stream().anyMatch((p) -> p.getName() == i.getName())).count())).toList();

        System.out.print(response);


        
    }
    
}

/*
 * public class Director {
	private int id;
	private String name;
	private String imdb;

    public class Movie {
	private int id;
	private String title;
	private int year;
	private String imdb;
	private List<Genre> genres;
	private List<Director> directors;
 */
