import { NgModule } from "@angular/core";
import { BrowserModule } from "@angular/platform-browser";
import { AppComponent } from "./app.component";
import { LoggerModule, NgxLoggerLevel } from "ngx-logger";
import { environment } from "src/environments/environment";
import { HttpClientModule } from "@angular/common/http";

@NgModule({
  declarations: [AppComponent], // comp, direct, pipe
  imports: [
    BrowserModule,
    HttpClientModule,
    LoggerModule.forRoot({
      serverLoggingUrl: '/api/logs',
      serverLogLevel: NgxLoggerLevel.ERROR,
      // level: NgxLoggerLevel.ERROR,
      level: environment.level
    })
  ], // other modules (app)
  bootstrap: [AppComponent]

})
export class AppModule{}

