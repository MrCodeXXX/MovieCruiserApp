import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Movie } from './movie';
import { map } from 'rxjs/operators';
import { Observable } from 'rxjs';
import {retry} from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class MovieService {
  movies : Array<Movie>;

  tmdbEndPoint : string;
  imagePrefix : string;
  apiKey : string;
  watchListEndPoint : string;
  springEndPoint : string;
  search : string;
  constructor(private http : HttpClient) { 
    
    
    this.apiKey="api_key=42720489b77342a51cd88ff3063672e0";
    this.tmdbEndPoint="https://api.themoviedb.org/3/movie";
    this.imagePrefix="https://image.tmdb.org/t/p/w500";
    this.watchListEndPoint="http://localhost:3000/watchlist";
    this.springEndPoint="http://localhost:8082/api/v1/movieservice/movie";
    this.search="http://api.themoviedb.org/3/search/movie?";

  }

  getMovies(type : string, page: number =1):Observable<Array<Movie>>{
    const endPoint=`${this.tmdbEndPoint}/${type}?${this.apiKey}&page=${page}`;
    return this.http.get(endPoint).pipe(
      retry(3),
      map(this.pickMovieresults),
      map(this.transformPosterPath.bind(this))
    );
  
  }

  

      
      

  transformPosterPath(movies) : Array<Movie>{
  
  
    return movies.map(movie=>{
      movie.poster_path=`${this.imagePrefix}${movie.poster_path}`;
      return movie; 
    });


  }

  pickMovieresults(response){
    return response['results'];
  }

  addMovieToWatchList(movie){
    console.log(movie);
    return this.http.post(this.springEndPoint, movie)
  }

  searchMovie(searchKey: string,page: number = 1): Observable<Array<Movie>> {
    if (searchKey.length > 0) {
      const searchEndpoint = `${this.search}${this.apiKey}&page=${page}&include_adult=false&query=${searchKey}`;
      return this.http.get(searchEndpoint).pipe(
        
        map(this.pickMovieresults),
        map(this.transformPosterPath.bind(this))
      );
    }
  }

 
 
  deleteFromMyWatchList(movie : Movie){
      const url =`${this.springEndPoint}/${movie.id}`;
      return this.http.delete(url,{responseType : 'text'});
  }  

 /*  getWatchListedMovies() : Observable<Array<Movie>>{
    return this.http.get<Array<Movie>>(this.watchListEndPoint);
  } */
  hello : any;
  saveWatchListMovies(movie){
    console.log(JSON.stringify (movie));

   this.hello=this.http.post(this.springEndPoint, movie);
    console.log("yes"+JSON.stringify (this.hello));
    return this.hello;
  }
  getMyWatchList(): Observable <Array<Movie>>{
    return this.http.get<Array<Movie>>(this.springEndPoint+'s');
  }

  updateMovie(movie){
    const url=`${this.springEndPoint}/${movie.id}`;
    return this.http.put(url, movie);
  }
}
