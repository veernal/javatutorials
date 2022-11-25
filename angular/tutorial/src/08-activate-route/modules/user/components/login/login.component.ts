import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  constructor(private _router:Router) { }

  ngOnInit(): void {
  }

  getLogin(username:string){
    console.log(username+" is trying to get login");
    if(username!=null && username.length>0){
      console.log(username+" is valid user");
      sessionStorage.setItem("isUserValid", "true");
      this._router.navigateByUrl("/movies/v1/create")
    }
  }
}
