import { NgModule } from "@angular/core";
import { BrowserModule } from "@angular/platform-browser";
import { AppComponent } from "./app.component";
import { ButtonComponent } from './components/button/button.component';
import { HeaderComponent } from './components/header/header.component';

@NgModule({
    declarations: [AppComponent, ButtonComponent, HeaderComponent], // components, directives, pipes
    imports: [BrowserModule], // other modules
    providers: [],
    bootstrap:[AppComponent] // starting point
  })
export class AppModule{}