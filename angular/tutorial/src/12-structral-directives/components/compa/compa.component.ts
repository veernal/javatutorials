import { Component, OnInit } from '@angular/core';
import { Movie } from './../../moive';
import { MoiveService } from 'src/04-ajax/services/movie.service';

@Component({
  selector: 'app-compa',
  templateUrl: './compa.component.html',
  styleUrls: ['./compa.component.scss']
})
export class CompaComponent {

  // movies:Array<Movie> = [];
  movies:Movie[] = [];

  constructor(private _movieService:MoiveService){}

  findMovies(){
    this._movieService.findAllMoviesFromServer().subscribe({
      next: (res:any)=>{
        console.log("success")
        this.movies = res;
        console.log(this.movies);
      },
      error: (e:any)=>{
        console.log("error")
        console.log(e)
      }
    })
  }

  emptyMoviesList(){
    this.movies = []
    console.log(this.movies);
  }
}
