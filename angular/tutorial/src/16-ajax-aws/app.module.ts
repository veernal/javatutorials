import { HttpClientModule } from "@angular/common/http";
import { NgModule } from "@angular/core";
import { BrowserModule } from "@angular/platform-browser";
import { AppComponent } from "./app.component";
import { CompaComponent } from './components/compa/compa.component';
import { CompbComponent } from './components/compb/compb.component';

@NgModule({
    declarations: [AppComponent, CompaComponent, CompbComponent], // components, directives, pipes
    imports: [BrowserModule, HttpClientModule], // other modules
    providers: [],
    bootstrap:[AppComponent] // starting point
  })
export class AppModule{}