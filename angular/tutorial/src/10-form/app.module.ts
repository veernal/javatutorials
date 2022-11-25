import { NgModule } from "@angular/core";
import { ReactiveFormsModule } from "@angular/forms";
import { BrowserModule } from "@angular/platform-browser";
import { AppComponent } from "./app.component";

@NgModule({
    declarations: [AppComponent], // components, directives, pipes
    imports: [BrowserModule, ReactiveFormsModule], // other modules
    providers: [],
    bootstrap:[AppComponent] // starting point
  })
export class AppModule{}