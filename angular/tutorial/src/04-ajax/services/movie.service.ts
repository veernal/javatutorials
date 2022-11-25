import { Injectable } from "@angular/core";
import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Movie } from "../moive";

@Injectable({providedIn: 'root'})
export class MoiveService {

    constructor(private http:HttpClient){}

    // ctsb37/ws/p07-springboot-rest-h2
    private host:string = "http://localhost:8080/api/movies";

    // get all
    findAllMoviesFromServer(){
        return this.http.get(this.host,{headers:{}});
    }

    // create
    saveMovieIntoDb(movie:Movie){
        return this.http.post(this.host, movie);
    }
}