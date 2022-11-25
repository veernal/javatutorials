import { Component, OnInit } from '@angular/core';
import { UtilService } from './../../services/util.service';

@Component({
  selector: 'app-compa',
  templateUrl: './compa.component.html',
  styleUrls: ['./compa.component.scss']
})
export class CompaComponent implements OnInit {

  result:number = 0;

  constructor(private service:UtilService) { }

  ngOnInit(): void {
  }

  sum(a:number, b:number){
    this.result = this.service.add(a, b);
  }

}
