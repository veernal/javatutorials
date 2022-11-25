import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class ValidUserGuard implements CanActivate {


  constructor(private _router:Router){}
  
  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): boolean {
      console.log("in guard");
      
      var author = localStorage.getItem('loggedInAuthor')
  
      if(author?.length === 0 || author?.length === null){
        alert('You are not allowed to view this page');
        return false;
      } else {
        // go to login
        this._router.navigateByUrl("/author/login")
        return true;
      }
  }
}
