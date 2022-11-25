import { Injectable } from "@angular/core";
import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Movie } from "../moive";

@Injectable({providedIn: 'root'})
export class MoiveService {

    constructor(private http:HttpClient){}

    // ctsb37/ws/p17-springboot
    // private host:string = "http://35.171.6.212:8080/api/movies";
    // rest api gateway
    // private host:string = "https://0hdvejpkvl.execute-api.us-east-1.amazonaws.com/dev/cinemas";
    // http api gateway
    private host:string = "https://cnu1tcxkxb.execute-api.us-east-1.amazonaws.com/shows";

    // get all
    findAllMoviesFromServer(){
        console.log("server: "+this.host)
        return this.http.get(this.host,{headers:{}});
    }

    // create
    saveMovieIntoDb(movie:Movie){
        return this.http.post(this.host, movie);
    }
}