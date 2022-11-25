import { NgModule } from "@angular/core";
import { BrowserModule } from "@angular/platform-browser";
import { AppComponent } from "./app.component";
import { DemoComponent } from './demo/demo.component';

@NgModule({
    declarations: [AppComponent, DemoComponent], // components, directives, pipes
    imports: [BrowserModule], // other modules
    providers: [],
    bootstrap:[AppComponent] // starting point
  })
export class AppModule{}