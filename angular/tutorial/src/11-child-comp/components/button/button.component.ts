import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-button',
  templateUrl: './button.component.html',
  styleUrls: ['./button.component.scss']
})
export class ButtonComponent implements OnInit {

  buttonStyle:any = {
      color: "darkgreen",
      border: "2px solid darkgreen"
    }
    @Input()
    bBackgroundColor:string = "darkseagreen";

  constructor() { }

  ngOnInit(): void {
  }

}
