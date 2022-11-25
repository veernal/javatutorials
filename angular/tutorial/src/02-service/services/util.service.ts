import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class UtilService {

  constructor() { }

  add(n1:number, n2:number):number{
    return n1+n2;
  }
  
}
