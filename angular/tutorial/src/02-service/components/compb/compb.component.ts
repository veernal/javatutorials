import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-compb',
  templateUrl: './compb.component.html',
  styleUrls: ['./compb.component.scss']
})
export class CompbComponent implements OnInit {
  res:number = 0;
  constructor() { }

  ngOnInit(): void {
  }

  addition(x:number, y:number){}
}
