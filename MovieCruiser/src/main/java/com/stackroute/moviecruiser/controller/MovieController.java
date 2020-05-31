package com.stackroute.moviecruiser.controller;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.moviecruiser.domain.Movie;
import com.stackroute.moviecruiser.exception.MovieAlreadyExistsException;
import com.stackroute.moviecruiser.exception.MovieNotFoundException;
import com.stackroute.moviecruiser.service.MovieService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;



@CrossOrigin
@RestController
@RequestMapping(path = "/api/v1/movieservice")
public class MovieController {

	//testing purpose
	@Autowired
	private MovieService movieService;


	public MovieController( final MovieService movieService) {
		super();
		this.movieService = movieService;
	}
	
	@PostMapping("/movie")
	public ResponseEntity<?> SaveNewMovie(@RequestBody final Movie movie, HttpServletRequest request, HttpServletResponse response)
	{
		System.out.println("saving movie");
		final String authHeader = request.getHeader("authorization") ;
		final String token = authHeader.substring(7);
		String userId = Jwts.parser().setSigningKey("secretkey").parseClaimsJws(token).getBody().getSubject();
		System.out.println("userId:"+ userId);
		
		try {
			movie.setUserId(userId);
			movieService.saveMovie(movie);
		
		}catch(MovieAlreadyExistsException e)
		{
			return new ResponseEntity<String>("{ \"message\":\"" + e.getMessage() + "\"}", HttpStatus.CONFLICT);
			
					
		}
		return new ResponseEntity<Movie>(movie, HttpStatus.CREATED);
		
	}
	
	@PutMapping(path = "/movie/{id}")
	public ResponseEntity<?> updateMovie(@PathVariable("id") final Integer id, @RequestBody final Movie movie)
	{

			
		try {
			final Movie fetchedMovie = movieService.updateMovie(movie);
			return new ResponseEntity<Movie>(fetchedMovie, HttpStatus.OK);
		}catch(MovieNotFoundException e)
		{
			return new ResponseEntity<String>("{ \"message\":\"" + e.getMessage() + "\"}", HttpStatus.CONFLICT);
			
					
		}
	
		
	}
	
	@DeleteMapping(path = "/movie/{id}")
	public ResponseEntity<?> deleteMovie(@PathVariable("id") final Integer id)
	{
	
		try {
		 movieService.deleteMovieById(id);
		}catch(MovieNotFoundException e)
		{
			return new ResponseEntity<String>("{ \"message\":\"" + e.getMessage() + "\"}", HttpStatus.NOT_FOUND);
			
					
		}
		return new ResponseEntity<String>("movie Delete successfully", HttpStatus.OK);
	
		
	}

	@GetMapping(path = "/movie/{id}")
	public ResponseEntity<?> fetchMovieById(@PathVariable("id") final Integer id)
	{
		
		Movie thisMovie = null;
		try {
		 thisMovie = movieService.getMovieById(id);
		 
		}catch(MovieNotFoundException e)
		{
			return new ResponseEntity<String>("{ \"message\":\"" + e.getMessage() + "\"}", HttpStatus.NOT_FOUND);
			
					
		}
		return new ResponseEntity<Movie>(thisMovie, HttpStatus.OK);
	
		
	}

	
	@GetMapping("/movies")
	public @ResponseBody ResponseEntity<List<Movie>> fetchMyMovies(final HttpServletRequest req, final HttpServletResponse res)
	{
		System.out.println("saving movie");
		final String authHeader = req.getHeader("authorization");
		final String token = authHeader.substring(7);
		String userId = Jwts.parser().setSigningKey("secretkey").parseClaimsJws(token).getBody().getSubject();
		System.out.println("userId:"+ userId);
		
		return new ResponseEntity<List<Movie>>(movieService.getMyMovies(userId),HttpStatus.OK);
	}
	
	
	


}
