package com.stackroute.moviecruiser.repository;



import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;


import com.stackroute.moviecruiser.repository.MovieRepository;
import com.stackroute.moviecruiser.MoviecruiserApplication;
import com.stackroute.moviecruiser.domain.Movie;

import static org.assertj.core.api.Assertions.assertThat;





@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class MovieRepoTest {

	
	@Autowired
	private transient MovieRepository repo;

	
	public void setRepo(MovieRepository repo) 
	{
		this.repo = repo;
	}
	
	Movie movie;
	
	@Before
	public void setUp()
	{
		movie = new Movie();
		//movie.setId(1);
		movie.setTitle("ABc");
		movie.setPoster_path("www.abc.com/abc.png");
		movie.setRelease_date("07mar2019");
		movie.setComments("abc");
		movie.setUserId("125");
		movie.setMovieId("abc");
		movie.setVote_average(45.5);
		movie.setVote_count(112);

	}


	@After
	public void tearDown()
	{
		repo.deleteAllInBatch();
	}

	@Test
	public void testSaveMovie() throws Exception
	{
		repo.save(movie);
		boolean fetch=repo.existsById(movie.getId());
		assertThat(fetch).isEqualTo(true);

		
		
	}
	
	@Test
	public void testUpdateMovie() throws Exception
	{
		repo.save(movie);
		Movie getMovie = repo.save(movie);
		assertEquals(getMovie.getTitle(),"ABc");
		 getMovie.setComments("edf");
		 Movie getMovie1=repo.save(getMovie);
		Movie movie2 = repo.save(getMovie1);
		assertEquals(movie2.getComments(),"edf");

	
	}	
	
	@Test
	public void testDeleteMovie() throws Exception
	{
		 Movie movie2 = repo.save(movie);
			assertEquals("ABc",movie2.getTitle());
			repo.delete(movie2);
			assertEquals(Optional.empty(),repo.findById(1));

		
	}
	
	@Test
	public void testGetMovie() throws Exception
	{
		final Movie movie1 = repo.save(movie);
		assertEquals(movie1.getTitle(),"ABc");

	
	}
	
	@Test
	public void getAllMovies() throws Exception
	{
		repo.save(new Movie(1,"123","superman","good movie","wwww.abc.com","2015-03-23",45,112,"125"));
		repo.save(new Movie(2,"987","superman","good movie","wwww.abc.com","2015-03-23",45,112,"125"));
		repo.save(new Movie(3,"9876","superman","good movie","wwww.abc.com","2015-03-23",45,112,"125"));
		repo.save(new Movie(4,"983","superman","good movie","wwww.abc.com","2015-03-23",45,112,"125"));
		final List<Movie> movies = repo.findByUserId("125");
		System.out.println("My movies: " + movies.get(0).getTitle());
		assertEquals(movies.size(),4);
	}
	
	
	
}
