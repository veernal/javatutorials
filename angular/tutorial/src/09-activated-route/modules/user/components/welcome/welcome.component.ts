import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-welcome',
  templateUrl: './welcome.component.html',
  styleUrls: ['./welcome.component.scss']
})
export class WelcomeComponent implements OnInit {

  uname:string = "";

  constructor(private _activateRoute:ActivatedRoute) { 
    // @PathVariable -> spring
    this._activateRoute.params.subscribe((pObj:any)=>{
      console.log(pObj);
      this.uname = pObj.name;
    })
  }

  ngOnInit(): void {
  }

}
