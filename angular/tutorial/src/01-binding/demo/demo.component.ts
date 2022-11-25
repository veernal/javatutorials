import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-demo',
  templateUrl: './demo.component.html',
  styleUrls: ['./demo.component.scss']
})
export class DemoComponent implements OnInit {

  name:string = "demo";

  constructor() { }

  ngOnInit(): void {
  }

  updateName(n:string){
    this.name = n;
  }

}
