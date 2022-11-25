import { Component, OnInit } from '@angular/core';
import { Movie } from './../../moive';
import { MoiveService } from './../../services/movie.service';

@Component({
  selector: 'app-compa',
  templateUrl: './compa.component.html',
  styleUrls: ['./compa.component.scss']
})
export class CompaComponent {

  constructor(private _movieService:MoiveService){}

  findMovies(){
    this._movieService.findAllMoviesFromServer().subscribe({
      next: (res:any)=>{
        console.log("success")
        console.log(res);
      },
      error: (e:any)=>{
        console.log("error++++")
        console.log(e)
      }
    })
  }
}
