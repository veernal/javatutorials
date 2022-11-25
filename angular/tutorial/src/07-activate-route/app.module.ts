import { NgModule } from "@angular/core";
import { BrowserModule } from "@angular/platform-browser";
import { AppComponent } from "./app.component";
import { HeaderComponent } from './components/header/header.component';
import { HomeComponent } from './components/home/home.component';
import { AboutComponent } from './components/about/about.component';
import { RouterModule, Routes } from "@angular/router";
import { ValidUserGuard } from "./guards/valid-user.guard";

const routes:Routes = [
  {path: "home", component: HomeComponent},
  {path: "about", component: AboutComponent},
  {
    path: "user", 
    loadChildren: ()=>import("./modules/user/user.module").then(M=>M.UserModule)
  },
  {
    path: "movies", 
    loadChildren: ()=>import("./modules/movie/movie.module").then(M=>M.MovieModule), 
    canActivate: [ValidUserGuard]
  },
  {path: "**", redirectTo: "home"}
]

@NgModule({
    declarations: [AppComponent, HeaderComponent, HomeComponent, AboutComponent], // components, directives, pipes
    imports: [BrowserModule, RouterModule.forRoot(routes)], // other modules
    bootstrap:[AppComponent] // starting point
  })
export class AppModule{}