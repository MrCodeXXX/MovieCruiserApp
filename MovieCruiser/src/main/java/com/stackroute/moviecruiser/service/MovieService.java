package com.stackroute.moviecruiser.service;



import java.util.List;

import com.stackroute.moviecruiser.domain.Movie;
import com.stackroute.moviecruiser.exception.MovieAlreadyExistsException;
import com.stackroute.moviecruiser.exception.MovieNotFoundException;

public interface MovieService {

	
	/**
	 * @param movie
	 * @return
	 * @throws MovieAlreadyExistsException
	 */
	boolean saveMovie(Movie movie) throws MovieAlreadyExistsException;
	
	/**
	 * @param movie
	 * @return
	 * @throws MovieNotFoundException
	 */
	Movie updateMovie(Movie movie) throws MovieNotFoundException;
	
	/**
	 * @param id
	 * @return
	 * @throws MovieNotFoundException
	 */
	boolean deleteMovieById(int id) throws MovieNotFoundException;
	
	/**
	 * @param id
	 * @return
	 * @throws MovieNotFoundException
	 */
	Movie getMovieById(int id) throws MovieNotFoundException;
	
	/**
	 * @return List<Movie>
	 */
	List <Movie> getMyMovies(String userId);
}
