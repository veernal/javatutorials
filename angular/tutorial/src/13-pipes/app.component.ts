import { Component } from "@angular/core";

@Component({
    selector: "app-root",
    templateUrl: "app.component.html",
    styleUrls: ["app.component.scss"],
    providers: []
  })
export class AppComponent{
  today:Date = new Date(1668661435823);
  today2:number = Date.now();

  movies:any[] = [{title: "Spiderman is not ant"}];

  subject:string = "aNgUlAR";
}