import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MovieByIdComponent } from './components/movie-by-id/movie-by-id.component';
import { CreateMovieComponent } from './components/create-movie/create-movie.component';
import { RouterModule, Routes } from '@angular/router';
import { ListComponent } from './components/list/list.component';
import { MoviesComponent } from './movies/movies.component';

const routes:Routes = [
  {
    path: "v1", 
    component: MoviesComponent,
    children: [
      {    path: "list", component: ListComponent},
      {    path: "create", component: CreateMovieComponent},
      { path: "**", redirectTo: "list" }
    ]
  }
  
];

@NgModule({
  declarations: [
    MovieByIdComponent,
    CreateMovieComponent,
    ListComponent,
    MoviesComponent
  ],
  imports: [
    CommonModule,
    RouterModule.forChild(routes)
  ]
})
export class MovieModule { }
