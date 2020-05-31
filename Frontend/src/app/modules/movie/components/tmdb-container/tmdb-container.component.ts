import { Component, OnInit, Input } from '@angular/core';
import { Movie } from '../../movie';
import { ActivatedRoute } from '@angular/router';
import { MovieService } from '../../movie.service';


@Component({
  selector: 'movie-tmdb-container',
  templateUrl: './tmdb-container.component.html',
  styleUrls: ['./tmdb-container.component.css']
})
export class TmdbContainerComponent implements OnInit {
  movies : Array<Movie>;
  movieType : string;
  constructor(private route : ActivatedRoute,private movieService : MovieService) { 
    this.movies= [];
    this.route.data.subscribe((data)=>{
      this.movieType=data.movieType;
    });
  }

  ngOnInit() {
   
    this.movieService.getMovies(this.movieType).subscribe((movies)=>{
      
      this.movies.push(...movies);
    }
    );
  }
}
