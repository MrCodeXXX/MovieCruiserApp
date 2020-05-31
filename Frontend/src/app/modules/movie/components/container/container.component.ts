import { Component, OnInit, Input } from '@angular/core';
import { Movie } from '../../movie';
import { ActivatedRoute } from '@angular/router';
import { MovieService } from '../../movie.service';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'movie-container',
  templateUrl: './container.component.html',
  styleUrls: ['./container.component.css']
})
export class ContainerComponent implements OnInit {
  @Input()
  movies : Array<Movie>;

  @Input()
  useWatchListApi : boolean;

  constructor( private route : ActivatedRoute,private movieService : MovieService, private snackBar : MatSnackBar) {
    
   }

  ngOnInit() {
   
    
  }
  addMovieToWatchList(movie){
    let message=`${movie.title} add to watchlist`;
    

     this.movieService.addMovieToWatchList(movie).subscribe((movie)=>{
      console.log("in container"+JSON.stringify(movie)); 
     
      this.snackBar.open(message,"", {
        duration : 1000
      });
    }); 
  }

  deleteMovieFromWatchList(movie){
    let message=`${movie.title} removed from watchlist`;
    for(var i=0; i<=this.movies.length; i++){
      if(this.movies[i].title===movie.title){
        this.movies.splice(i,1);
      }
    }
     this.movieService.deleteFromMyWatchList(movie).subscribe((movie)=>{
      this.snackBar.open(message,"", {
        duration : 1000
      });
    }); 

  }

}
