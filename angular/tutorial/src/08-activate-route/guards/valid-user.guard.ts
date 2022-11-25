import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ValidUserGuard implements CanActivate {

  constructor(private _router:Router){}

  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): boolean {

      console.log("in guard");

    // return sessionStorage.getItem("isUserValid")==="true"||false;

    if(sessionStorage.getItem("isUserValid")==="true"){
      return true;
    } else {
      // go to login
      this._router.navigateByUrl("/user/login")
      return false;
    }

  }
  
}
