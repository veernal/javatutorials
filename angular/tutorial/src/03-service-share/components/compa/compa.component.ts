import { Component, OnInit } from '@angular/core';
import { UtilService } from './../../services/util.service';

@Component({
  selector: 'app-compa',
  templateUrl: './compa.component.html',
  styleUrls: ['./compa.component.scss']
})
export class CompaComponent implements OnInit {

  constructor(private service:UtilService) { }

  ngOnInit(): void {
  }

  change(a:number, b:number){
    this.service.num = a+b; // setter is used
  }

}
