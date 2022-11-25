import { HttpClientModule } from "@angular/common/http";
import { ErrorHandler, NgModule } from "@angular/core";
import { BrowserModule } from "@angular/platform-browser";
import { AppComponent } from "./app.component";
import { MyErrorHandler } from "./handler/error.handler";

@NgModule({
    declarations: [AppComponent],// component, pipe, directive
    imports: [BrowserModule, HttpClientModule],
    providers: [{provide: ErrorHandler, useClass: MyErrorHandler}],
    bootstrap: [AppComponent]
})
export class AppModule { }