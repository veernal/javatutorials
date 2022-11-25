import { NgModule } from "@angular/core";
import { BrowserModule } from "@angular/platform-browser";
import { AppComponent } from "./app.component";
import { HomeComponent } from './components/home/home.component';
import { HeaderComponent } from './components/header/header.component';
import { AboutComponent } from './components/about/about.component';
import { RouterModule, Routes } from "@angular/router";

const routes:Routes = [
  {path: "home", component: HomeComponent},
  {path: "about", component: AboutComponent},
  {path: "**", redirectTo: "home" }
];

@NgModule({
    declarations: [AppComponent, HomeComponent, HeaderComponent, AboutComponent], // components, directives, pipes
    imports: [BrowserModule, RouterModule.forRoot(routes)], // other modules
    providers: [],
    bootstrap:[AppComponent] // starting point
  })
export class AppModule{}