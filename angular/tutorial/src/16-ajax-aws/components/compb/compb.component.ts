import { Component, OnInit } from '@angular/core';
import { Movie } from './../../moive';
import { MoiveService } from './../../services/movie.service';

@Component({
  selector: 'app-compb',
  templateUrl: './compb.component.html',
  styleUrls: ['./compb.component.scss']
})
export class CompbComponent implements OnInit {
  constructor(private _movieService:MoiveService){}

  save(){
    let newMovie:Movie = {
      id:0,
      title: "Tea break",
      director: "arun",
      release: "2000-10-25",
      rating: 4.7
    }
    this._movieService.saveMovieIntoDb(newMovie).subscribe({
      next: (res:any)=>{
        console.log("success")
        console.log(res);
      },
      error: (e:any)=>{
        console.log("error")
        console.log(e)
      }
    })
  }
  ngOnInit(): void {
  }

}
