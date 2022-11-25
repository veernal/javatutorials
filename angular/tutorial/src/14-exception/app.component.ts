import { Component, OnInit } from "@angular/core";
import { UserService } from "./service/user.service";

@Component({
    selector: "app-root",
    // template: "<h1>Hello users</h1>"
    templateUrl: "app.component.html",
    styleUrls: ["app.component.css"]
})
export class AppComponent implements OnInit {
    movies:any[] = [];

    constructor(private userService:UserService){
    }
    
    ngOnInit(){
        // load -> 5min
        this.fetchMovies();
    }

    fetchMovies(){
        this.userService.getAllMovies()
        .subscribe({
            next: (res:any)=>{
                console.log(res);
                this.movies = res;
            },
            // error: ()=>{}
        })
    }


    saveMovie(){
        let movie = {title: "demo", "director": "demo", rating: 2.1};

        this.userService.saveNewMovie(movie)
        .subscribe({
            next: (res:any)=>{
                console.log(res); 
                this.fetchMovies();
            }
        })
    }

}