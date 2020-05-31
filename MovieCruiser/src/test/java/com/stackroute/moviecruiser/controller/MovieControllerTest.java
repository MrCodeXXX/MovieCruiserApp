package com.stackroute.moviecruiser.controller;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;   // ...or...
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.moviecruiser.domain.Movie;
import com.stackroute.moviecruiser.service.MovieServiceImpl;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@RunWith(SpringRunner.class)
@WebMvcTest(MovieController.class)
public class MovieControllerTest {

	@Autowired
	private transient MockMvc mvc;
	
	@MockBean
	private transient MovieServiceImpl service;
	
//	MovieController movieController;
	
	private transient Movie movie;
	
	static List<Movie> movies;
	String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyMSIsImlhdCI6MTU1MzQxMjM3Mn0.N-MYEuUtwgwit7nec-K3jPnB67vfRhy_K0jDePlwEdM";

@InjectMocks
MovieController movieController;
	@Before
	public void setUp() 
	{
		MockitoAnnotations.initMocks(this);
		mvc = MockMvcBuilders.standaloneSetup(movieController).build();
		movies = new ArrayList<>();
		movie = new Movie(1,"123","superman","good movie","wwww.abc.com","2015-03-23",45.5f,112,"user1");
		movies.add(movie);
		movie = new Movie(2,"987","superman","good movie","wwww.abc.com","2015-03-23",45.5f,112,"user1");
		movies.add(movie);
		
	}
	
	
	@Test
	public void testSaveNewMovie() throws Exception
	{
		
	
		System.out.println(token);
		when(service.saveMovie(movie)).thenReturn(true);
		mvc.perform(post("/api/v1/movieservice/movie/")
			.header("authorization", "Bearer "+token)
				.contentType(MediaType.APPLICATION_JSON)
				.content(jsonToString(movie)))
				.andExpect(status().isCreated());
		verify(service, times(1)).saveMovie(Mockito.any(Movie.class));
		
	}
		

	
	@Test
	public void testDeleteMyMovieById() throws Exception
	{

		
		
		when(service.deleteMovieById(1)).thenReturn(true);
		mvc.perform(delete("/api/v1/movieservice/movie/{id}",1))
				.andExpect(status().isOk());
		verify(service, times(1)).deleteMovieById(1);
		verifyNoMoreInteractions(service);
		
	}

	
	@Test
	public void testGetMyMovies() throws Exception
	{
		
		
		
		System.out.println(token);
		when(service.getMyMovies("user1")).thenReturn(movies);
		mvc.perform(get("/api/v1/movieservice/movies/")
	.header("authorization", "Bearer " + token)
		.contentType(MediaType.APPLICATION_JSON)
		.content(jsonToString(movie)))
		.andExpect(status().isOk()).andDo(print());
		verify(service, times(1)).getMyMovies("user1");
		verifyNoMoreInteractions(service);
		
	}
	
	
	
	private static String jsonToString(final Object obj) throws JsonProcessingException
	{
		String result;
		try 
		{
			final ObjectMapper mapper = new ObjectMapper();
			final String jsonContent = mapper.writeValueAsString(obj);
			result = jsonContent;
			
		}catch(JsonProcessingException e) 
		{
			result = "Json Processing Error";
		}
		return result;
	}
	

}
