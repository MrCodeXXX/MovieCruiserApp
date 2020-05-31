package com.stackroute.moviecruiser.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringRunner;


import com.stackroute.moviecruiser.domain.Movie;
import com.stackroute.moviecruiser.exception.MovieAlreadyExistsException;
import com.stackroute.moviecruiser.exception.MovieNotFoundException;
import com.stackroute.moviecruiser.repository.MovieRepository;

import static org.mockito.Mockito.when;   // ...or...
import static org.mockito.Mockito.*;      // ...with the caveat noted below.


public class MovieServiceImplTest {


@Mock
private transient MovieRepository movieRepo;


private transient Movie movie;

@InjectMocks
private transient MovieServiceImpl movieServiceImpl;

transient Optional<Movie> options;

@Before
public void setUpMock() 
{
	MockitoAnnotations.initMocks(this);
	movie = new Movie(1,"1234","superman","good movie","wwww.abc.com","2015-03-23",45,112,"aneesh");
	options = Optional.of(movie);
}

@Test
public void testMockCreation() 
{
	assertNotNull(movie);
	assertNotNull("JPa repository creation fails, use imnject mocks over movieServiceImpl", movie);
	
	
}

@Test
public void testSaveMovieSuccess() throws MovieAlreadyExistsException
{
	
	when(movieRepo.save(movie)).thenReturn(movie);
	final boolean flag = movieServiceImpl.saveMovie(movie);
	assertTrue("Saving Movie failed, the call to movieServiceImpl failed, please check", flag);
	verify(movieRepo, times(1)).save(movie);
	verify(movieRepo, times(1)).findById(movie.getId());
	


}


@Test(expected = MovieAlreadyExistsException.class)
public void testSaveMovieFailure() throws MovieAlreadyExistsException
{
	when(movieRepo.findById(1)).thenReturn(options);
	
	when(movieRepo.save(movie)).thenReturn(movie);
	final boolean flag = movieServiceImpl.saveMovie(movie);


}


@Test
public void testUpdateMovie() throws MovieNotFoundException
{
	when(movieRepo.findById(1)).thenReturn(options);
	when(movieRepo.save(movie)).thenReturn(movie);
	movie.setComments("not so good movie");
	final Movie movie1 = movieServiceImpl.updateMovie(movie);
	assertEquals("updating movie failed", "not so good movie", movie1.getComments());
	verify(movieRepo, times(1)).save(movie);
	verify(movieRepo, times(1)).findById(movie.getId());
	


}


@Test
public void testDeleteMovieById() throws MovieNotFoundException
{
	when(movieRepo.findById(1)).thenReturn(options);
	doNothing().when(movieRepo).delete(movie);
	final boolean flag = movieServiceImpl.deleteMovieById(1);
	assertTrue("Deleting Movie failed",flag);
	verify(movieRepo, times(1)).delete(movie);
	verify(movieRepo, times(1)).findById(movie.getId());
	

}


@Test
public void getMovieById() throws MovieNotFoundException
{
	when(movieRepo.findById(1)).thenReturn(options);
	final Movie movie1 = movieServiceImpl.getMovieById(1);
	assertEquals("Getting Movie by This id failed", movie1,movie);
	verify(movieRepo, times(1)).findById(movie.getId());
	

}


@Test
public void testGetMyMovies() throws Exception
{
	List<Movie> movieList = new ArrayList<>();
	movieList.add(movie);
	
	when(movieRepo.findByUserId("aneesh")).thenReturn(movieList);
	final List<Movie> movies1 = movieServiceImpl.getMyMovies("aneesh");
	assertEquals(movieList, movies1);
	verify(movieRepo, times(1)).findByUserId("aneesh");
	

}






}
