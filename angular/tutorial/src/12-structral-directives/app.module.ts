import { HttpClientModule } from "@angular/common/http";
import { NgModule } from "@angular/core";
import { BrowserModule } from "@angular/platform-browser";
import { AppComponent } from "./app.component";
import { CompaComponent } from './components/compa/compa.component';

@NgModule({
    declarations: [AppComponent, CompaComponent], // components, directives, pipes
    imports: [BrowserModule, HttpClientModule], // other modules
    providers: [],
    bootstrap:[AppComponent] // starting point
  })
export class AppModule{}