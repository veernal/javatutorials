import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";

@Injectable({
    providedIn: "root"
})
export class UserService{

    // api gateway url -> 
    private host:string = "http://localhost:8080/movies";

    constructor(private http:HttpClient){}

    // get request
    getAllMovies(){
        return this.http.get(this.host)//.pipe(this.errorHandler);
    }
    // post request
    saveNewMovie(movie:any){
        return this.http.post(this.host, movie);
    }


    errorHandler=(e:any)=>{
        throw e;
    }


}
