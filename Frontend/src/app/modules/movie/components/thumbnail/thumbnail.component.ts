import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { Movie } from '../../movie';
import { MovieService } from '../../movie.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { MoviedialogComponent } from '../moviedialog/moviedialog.component';
import { MatDialog } from '@angular/material/dialog';
@Component({
  selector: 'movie-thumbnail',
  templateUrl: './thumbnail.component.html',
  styleUrls: ['./thumbnail.component.css']
})
export class ThumbnailComponent implements OnInit {

@Input()
 movie : Movie;

@Input()
useWatchListApi: boolean;

@Output()
addMovie =new EventEmitter();

@Output()
deleteMovie =new EventEmitter();




  constructor( private snackBar : MatSnackBar, private dialog : MatDialog) { 

    
  }

  ngOnInit() {
   
  }

  addToWatchList(){
    this.addMovie.emit(this.movie)
   
    /* this.movieService.addMovieToWatchList(this.movie).subscribe((movie)=>{
      this.snackBar.open("Added to WatchList","", {
        duration : 1000
      });
    }); */
  }
  deleteFromWatchList(){
      this.deleteMovie.emit(this.movie);
  }

 updateFromWatchList(actionType){
   let dialogRef = this.dialog.open(MoviedialogComponent,{
    width : "400px",
    data : {
      obj : this.movie, actionType : actionType
    }
   });

   dialogRef.afterClosed().subscribe(result=>{
     console.log("The dialog was closed");
   });
 }

}
