import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class UtilService {

  private reusableObj = {
    result: 0,
    prop: true// any property
  }

  constructor() { }

  public set num(n:number){
    this.reusableObj.result = n;
  }

  public get num(){
    return this.reusableObj.result;
  }
  
}
