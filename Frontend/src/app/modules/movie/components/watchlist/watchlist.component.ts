import { Component, OnInit } from '@angular/core';
import { MovieService } from '../../movie.service';
import { Movie } from '../../movie';

@Component({
  selector: 'movie-watchlist',
  templateUrl: './watchlist.component.html',
  styleUrls: ['./watchlist.component.css']
})
export class WatchlistComponent implements OnInit {
  movies : Array <Movie>;
  useWatchListApi= true;
  constructor(private movieService : MovieService) {
    this.movies=[];
   }

  ngOnInit() {
    this.movieService.getMyWatchList().subscribe((movies)=>{
      console.log(movies);
      this.movies.push(...movies);
    });
  }

}
