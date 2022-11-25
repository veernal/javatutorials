import { NgModule } from "@angular/core";
import { BrowserModule } from "@angular/platform-browser";
import { AppComponent } from "./app.component";
import { CapitalizePipe } from './pipes/capitalize.pipe';

@NgModule({
    declarations: [AppComponent, CapitalizePipe], // components, directives, pipes
    imports: [BrowserModule], // other modules
    providers: [],
    bootstrap:[AppComponent] // starting point
  })
export class AppModule{}