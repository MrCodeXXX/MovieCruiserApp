package com.stackroute.moviecruiser.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackroute.moviecruiser.domain.Movie;
import com.stackroute.moviecruiser.exception.MovieAlreadyExistsException;
import com.stackroute.moviecruiser.exception.MovieNotFoundException;
import com.stackroute.moviecruiser.repository.MovieRepository;

@Service
public class MovieServiceImpl  implements MovieService{

	
	@Autowired
		private final transient MovieRepository movieRepo;
		
		
	
	
	
	
	public MovieServiceImpl(final MovieRepository movieRepo) {
			super();
			this.movieRepo = movieRepo;
		}

	@Override
	public boolean saveMovie(final Movie movie) throws MovieAlreadyExistsException {
		
		final Optional<Movie> object = movieRepo.findById(movie.getId());
		if(object.isPresent()) 
		{
			throw new MovieAlreadyExistsException("Could not save Movie, Movie already Exists");
		}
		movieRepo.save(movie);
		return true;
	}

	@Override
	public Movie updateMovie( final Movie updateMovie) throws MovieNotFoundException {
	
		
		final Movie movie = movieRepo.findById(updateMovie.getId()).orElse(null);
		if(movie == null) 
		{
			throw new MovieNotFoundException("Could not update Movie, Movie Not Found");
		}
		movie.setComments(updateMovie.getComments());
		movieRepo.save(movie);
		return movie;
	}

	@Override
	public boolean deleteMovieById(int id) throws MovieNotFoundException {
	
		final Movie movie = movieRepo.findById(id).orElse(null);
		if(movie == null) 
		{
			throw new MovieNotFoundException("Could not delete Movie, Movie Not Found");
		}
		movieRepo.delete(movie);
		return true;
	}

	@Override
	public Movie getMovieById(int id) throws MovieNotFoundException {
		

		final Movie movie = movieRepo.findById(id).orElse(null);
		if(movie == null) 
		{
			throw new MovieNotFoundException("Movie Not Found");
		}

		return movie;
	}

	@Override
	public List<Movie> getMyMovies(String userId) {
		
		return movieRepo.findByUserId(userId);
	
	}
	

}
