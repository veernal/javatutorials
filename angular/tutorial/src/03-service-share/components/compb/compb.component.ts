import { Component, OnInit } from '@angular/core';
import { UtilService } from './../../services/util.service';

@Component({
  selector: 'app-compb',
  templateUrl: './compb.component.html',
  styleUrls: ['./compb.component.scss'],
  // providers: [UtilService]
})
export class CompbComponent implements OnInit {
  constructor(public service:UtilService) { }

  ngOnInit(): void {
  }
  
}
